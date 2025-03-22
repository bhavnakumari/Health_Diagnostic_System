package com.health_diagnostic_backend.Health.Diagnostic.System.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PredictedResponse {
    private String confidence_score;
    private String predicted_disease;
    private List<Prediction> alternative_predictions;

    public String getConfidence_score() {
        return confidence_score;
    }

    public void setConfidence_score(String confidence_score) {
        this.confidence_score = confidence_score;
    }

    public String getPredicted_disease() {
        return predicted_disease;
    }

    public void setPredicted_disease(String predicted_disease) {
        this.predicted_disease = predicted_disease;
    }

    public List<Prediction> getAlternative_predictions() {
        return alternative_predictions;
    }

    public void setAlternative_predictions(List<Prediction> alternative_predictions) {
        this.alternative_predictions = alternative_predictions;
    }
}
