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
public class SymptomRequest {
    private List<Integer> symptoms;

    public List<Integer> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Integer> symptoms) {
        this.symptoms = symptoms;
    }
}
