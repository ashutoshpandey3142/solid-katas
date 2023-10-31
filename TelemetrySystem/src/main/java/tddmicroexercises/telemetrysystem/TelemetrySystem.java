package tddmicroexercises.telemetrysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelemetrySystem {
    public static void main(String[] args) {
        SpringApplication.run(TelemetryDiagnosticControls.class, args);
    }
}
