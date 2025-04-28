package demo.demo.analyzer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "deal")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Slf4j
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_id")
    private Long id;

    @Column(name = "from_currency",nullable = false)
    @Size(min = 3, max = 3, message = "ISO currency must be 3 characters")
    private String fromCurrency;// ordering currency

    @Column(name = "to_currency",nullable = false)
    @Size(min = 3, max = 3, message = "ISO currency must be 3 characters")
    private String toCurrency;

    @Column(name = "deal_amount",nullable = false)
    private Double amount; // amount in ordering currency

    private Timestamp dealTimestamp;

    public static Deal createInstance(DealDto dto){
        log.info("Creating new deal instance from dto!");
        Deal deal = new Deal();
        deal.setId(dto.getId());
        deal.setAmount(dto.getAmount());
        deal.setFromCurrency(dto.getFromCurrency());
        deal.setToCurrency(dto.getToCurrency());
        deal.setDealTimestamp(dto.getDealTimestamp() == null ? Timestamp.valueOf(LocalDateTime.now()):dto.getDealTimestamp());
        return deal;
    }
}
