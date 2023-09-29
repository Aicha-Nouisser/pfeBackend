package tn.esprit.spring.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class CountResult {
    private double count;
    private String result;

    public CountResult(double count, String result) {
        this.count = count;
        this.result = result;
    }

    // Getters and setters
}
