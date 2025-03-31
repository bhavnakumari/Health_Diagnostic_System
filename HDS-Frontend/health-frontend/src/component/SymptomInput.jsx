import React, { useState } from "react";
import axios from "axios";
import './SymptomInput.css';
import severityDict from "../utils/severityDict";


const symptomNames = Object.keys(severityDict);

function SymptomInput() {
  const [symptoms, setSymptoms] = useState(["", "", "", "", ""]);
  const [result, setResult] = useState(null);

  const handleSymptomChange = (index, value) => {
    const updated = [...symptoms];
    updated[index] = value.toLowerCase().replace(/\s+/g, "_"); // normalize
    setSymptoms(updated);
  };

  const handleSubmit = async () => {
    // ✅ Step 1: Convert symptom names to severity scores
    const severityScores = symptoms
      .filter((sym) => sym !== "")              // ignore empty/None values
      .map((sym) => severityDict[sym] || 0);    // map names to weights
  
    // ✅ Step 2: Pad to 17 features
    const MAX_FEATURES = 17;
    const paddedScores = [
      ...severityScores,
      ...Array(MAX_FEATURES - severityScores.length).fill(0)
    ];
  
    // ✅ Step 3: Send to backend (only for diagnosis)
    try {
      const token = localStorage.getItem("token");
      const response = await axios.post("http://localhost:8080/diagnosis-api/diagnose", {
        symptoms: paddedScores
      }, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
  
      setResult(response.data);
  
    } catch (error) {
      console.error(error);
      alert("Prediction failed.");
    }
  };

  return (
    <div className="container">
      <h1 className="title">Disease Prediction From Symptoms</h1>
      <h6>Note: Atleast write/select 3 symptoms from the dropdown for a good prediction</h6>
      <div className="form-box">
        {symptoms.map((symptom, i) => (
          <div key={i} className="form-group">
            <label>Symptom {i + 1}</label>
            <select
              className="dropdown"
              value={symptom}
              onChange={(e) => handleSymptomChange(i, e.target.value)}
            >
              <option value="">None</option>
              {symptomNames.map((name, j) => (
                <option key={j} value={name}>
                  {name.replace(/_/g, " ")}
                </option>
              ))}
            </select>
          </div>
        ))}

        <button className="btn-diagnose" onClick={handleSubmit}>Predict</button>
      </div>

      {result && (
        <div className="result-box">
          <h3>🩺 Predicted Disease: <span>{result.predicted_disease}</span></h3>
          <p><strong>Confidence:</strong> {result.confidence_score}</p>
          <h4>🔁 Alternative Predictions:</h4>
          <ul>
            {result.alternative_predictions.map((alt, i) => (
              <li key={i}>
                {alt.disease} – <em>{alt.confidence}</em>
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}

export default SymptomInput;