package demo.demo.analyzer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
@Slf4j
public class DealService {
    private final DealRepository dealRepository;

    @Autowired
    public DealService(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    public void addNewDeal(DealDto dealDto) {
        try {
            if(dealDto.getId() != null && dealRepository.existsById(dealDto.getId())){
                throw new IllegalArgumentException("Deal Already Exists, Deal Id: "+ dealDto.getId());
            }
            currencyChecker(dealDto);
            Deal deal = dealRepository.save(Deal.createInstance(dealDto));
            log.info("Deal with id: {} , was saved successfully in database",deal.getId());
        }catch (Exception e){
            log.error("Error Adding New Deal {}",e.getMessage());
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
            log.error("Currency Used Is Not ISO Standard Currency");
            throw new IllegalArgumentException("Invalid ISO Currency");
        }
    }
}
