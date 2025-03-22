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
    private String predicted_disease;
    private String disease_description;
    private List<String> precautions;

    public String getPredicted_disease() {
        return predicted_disease;
    }

    public void setPredicted_disease(String predicted_disease) {
        this.predicted_disease = predicted_disease;
    }

    public String getDisease_description() {
        return disease_description;
    }

    public void setDisease_description(String disease_description) {
        this.disease_description = disease_description;
    }

    public List<String> getPrecautions() {
        return precautions;
    }

    public void setPrecautions(List<String> precautions) {
        this.precautions = precautions;
    }
}
