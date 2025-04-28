package demo.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.demo.analyzer.DealDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class DataWarehouseApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private DealDto dealDto;

    @BeforeEach
    public void init(){
        dealDto = new DealDto();
        dealDto.setAmount(5.55);
        dealDto.setFromCurrency("USD");
        dealDto.setToCurrency("JOD");
    }

    //This test doesnt mock the service so we get the actual "Internal Server Error" from there!
    @Test
    public void fromCurrencyIsNotISOCurrency_ReturnsInternalServerError() throws Exception {
        dealDto.setFromCurrency("PPP");

        ResultActions response = mockMvc.perform(post("/deal/add-deal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dealDto)));

        response.andExpect(status().isInternalServerError());
    }
}
