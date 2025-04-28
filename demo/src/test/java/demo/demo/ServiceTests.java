package demo.demo;

import demo.demo.analyzer.Deal;
import demo.demo.analyzer.DealDto;
import demo.demo.analyzer.DealRepository;
import demo.demo.analyzer.DealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ServiceTests {

    private DealRepository dealRepository;
    private DealService dealService;
    private DealDto dealDto;

    @BeforeEach
    void setUp() {
        dealRepository = mock(DealRepository.class);
        dealService = new DealService(dealRepository);
        dealDto = new DealDto();
        dealDto.setId(1L);
        dealDto.setFromCurrency("USD");
        dealDto.setToCurrency("EUR");
        dealDto.setAmount(10.105);
    }

    @Test
    void addNewDeal_Success() {
        when(dealRepository.existsById(dealDto.getId())).thenReturn(false);
        when(dealRepository.save(any(Deal.class))).thenReturn(new Deal());

        dealService.addNewDeal(dealDto);

        verify(dealRepository, times(1)).save(any(Deal.class));
    }

    @Test
    void addNewDeal_ThrowsException_WhenDealAlreadyExists() {
        when(dealRepository.existsById(dealDto.getId())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            dealService.addNewDeal(dealDto);
        });

        assertEquals("Deal Already Exists, Deal Id: 1", exception.getMessage());
        verify(dealRepository, never()).save(any(Deal.class));
    }

    @Test
    void addNewDeal_ThrowsException_WhenCurrencyIsInvalid() {
        dealDto.setFromCurrency("III");

        when(dealRepository.existsById(dealDto.getId())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            dealService.addNewDeal(dealDto);
        });

        assertEquals("Invalid ISO Currency", exception.getMessage());
        verify(dealRepository, never()).save(any(Deal.class));
    }
}
