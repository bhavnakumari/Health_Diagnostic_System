from flask import Flask, request, jsonify
import joblib
import numpy as np
import pandas as pd
# Load the extra data once at startup
desc_df = pd.read_csv("/Users/bhavnakumari/GitHub/Health_Diagnostic_System/Symptom-Analysis-Model/datasets/symptom_Description.csv")
prec_df = pd.read_csv("/Users/bhavnakumari/GitHub/Health_Diagnostic_System/Symptom-Analysis-Model/datasets/symptom_precaution.csv")

# Load the saved model
model = joblib.load("symptom_disease_model.pkl")

# Standardize text
desc_df['Disease'] = desc_df['Disease'].str.strip().str.lower()
prec_df['Disease'] = prec_df['Disease'].str.strip().str.lower()

app = Flask(__name__)
@app.route("/predict", methods=["POST"])
def predict():
    try:
        data = request.json
        symptoms = np.array(data["symptoms"])

        required_features = 17
        if len(symptoms) < required_features:
            symptoms = np.pad(symptoms, (0, required_features - len(symptoms)), mode='constant')

        symptoms = symptoms.reshape(1, -1)
        prediction = model.predict(symptoms)[0]
        predicted_disease = prediction.lower()

        # Get description
        description_row = desc_df[desc_df['Disease'] == predicted_disease]
        description = description_row['Description'].values[0] if not description_row.empty else "No description available."

        # Get precautions
        precaution_row = prec_df[prec_df['Disease'] == predicted_disease]
        precautions = precaution_row.iloc[0, 1:].dropna().tolist() if not precaution_row.empty else ["No precautions available."]
        
        probabilities = model.predict_proba(symptoms)
        top_predictions = sorted(zip(model.classes_, probabilities[0]), key=lambda x: x[1], reverse=True)
        top_3 = top_predictions[:3]

        return jsonify({
            "predicted_disease": top_3[0][0],
            "confidence_score": f"{round(top_3[0][1] * 100, 2)}%",
            "alternative_predictions": [
                {
                    "disease": pred[0],
                    "confidence": f"{round(pred[1] * 100, 2)}%"
                } for pred in top_3[1:]
            ]
        })

    except Exception as e:
        print("Error:", str(e))
        return jsonify({"error": str(e)}), 500


if __name__ == "__main__":
    app.run(debug=True)