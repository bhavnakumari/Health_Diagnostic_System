package com.health_diagnostic_backend.Health.Diagnostic.System.Controller;


import com.health_diagnostic_backend.Health.Diagnostic.System.Entity.PredictedResponse;
import com.health_diagnostic_backend.Health.Diagnostic.System.Entity.SymptomRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/diagnosis-api")
public class DiagnosisController {

    @Value("${flask.api.url}")
    private String flaskApiUrl;

    @PostMapping("/diagnose")
    public ResponseEntity<PredictedResponse> diagnose(@RequestBody SymptomRequest request) {
        System.out.println("Received symptoms: " + request.getSymptoms());
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<SymptomRequest> entity = new HttpEntity<>(request, headers);
            System.out.print(entity.getBody().getSymptoms());
            ResponseEntity<PredictedResponse> response = restTemplate.postForEntity(
                    flaskApiUrl + "/predict",
                    entity,
                    PredictedResponse.class
            );
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

}
