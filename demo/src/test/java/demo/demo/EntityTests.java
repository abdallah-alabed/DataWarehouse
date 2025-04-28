package demo.demo;

import demo.demo.analyzer.Deal;
import demo.demo.analyzer.DealDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class EntityTests {

    private DealDto dealDto;
    @BeforeEach
    public void init(){
        dealDto = new DealDto();
        dealDto.setAmount(1.111);
        dealDto.setFromCurrency("USD");
        dealDto.setToCurrency("JOD");
        dealDto.setDealTimestamp(Timestamp.valueOf("2025-01-01 00:00:00"));
    }

    @Test
    void createEntityFromDto_FullData(){
        Deal deal = Deal.createInstance(dealDto);

        assertThat(deal.getId()).isEqualTo(dealDto.getId());
        assertThat(deal.getFromCurrency()).isEqualTo(dealDto.getFromCurrency());
        assertThat(deal.getToCurrency()).isEqualTo(dealDto.getToCurrency());
        assertThat(deal.getAmount()).isEqualTo(dealDto.getAmount());
        assertThat(deal.getDealTimestamp()).isEqualTo(dealDto.getDealTimestamp());
    }

    @Test
    void createEntityFromDto_MissingTimestamp(){
        dealDto.setDealTimestamp(null);
        Deal deal = Deal.createInstance(dealDto);

        assertThat(deal.getDealTimestamp()).isEqualTo(Timestamp.valueOf(LocalDateTime.now()));
    }


}
