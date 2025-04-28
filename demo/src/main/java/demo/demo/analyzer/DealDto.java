package demo.demo.analyzer;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class DealDto {
    Long id;
    @NotBlank(message = "From Currency Can't Be Empty")
    @Size(min = 3, max = 3, message = "ISO currency must be 3 characters")
    String fromCurrency;
    @NotBlank(message = "To Currency Can't Be Empty")
    @Size(min = 3, max = 3, message = "ISO currency must be 3 characters")
    String toCurrency;
    @DecimalMin(value = "0.0",message = "Amount must be greater than or equal to 0.0")
    @NotNull
    Double amount;
    Timestamp dealTimestamp;

}
