package demo.demo.analyzer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
@Slf4j
public class DealService {
    @Autowired
    private DealRepository dealRepository;


    public Deal addNewDeal(DealDto dealDto) {
        try {
            currencyChecker(dealDto);
            return dealRepository.save(Deal.createInstance(dealDto));
        }catch (Exception e){
            throw e;
        }
    }

    private void currencyChecker(DealDto dealDto){
        log.info("Checking Currencies For Deal!");
        try {
            log.info("From Currency {}",dealDto.getFromCurrency());
            Currency.getInstance(dealDto.getFromCurrency());
            log.info("To Currency {}",dealDto.getToCurrency());
            Currency.getInstance(dealDto.getToCurrency());
        }catch (IllegalArgumentException  e){
            throw new IllegalArgumentException("Invalid ISO Currency");
        }
    }
}
