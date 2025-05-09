package demo.demo;

import demo.demo.analyzer.DealController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.demo.analyzer.DealDto;
import demo.demo.analyzer.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DealController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DealService dealService;

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

    @Test
    void amountIsMissing_ReturnsBadRequest() throws Exception {
        dealDto.setAmount(null);

        ResultActions response = mockMvc.perform(post("/deal/add-deal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dealDto)));

        response.andExpect(status().isBadRequest());
    }
    @Test
    void amountIsNegative_ReturnsBadRequest() throws Exception {
        dealDto.setAmount(-12.03);

        ResultActions response = mockMvc.perform(post("/deal/add-deal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dealDto)));

        response.andExpect(status().isBadRequest());
    }
    @Test
    void fromCurrencyIsMissing_ReturnsBadRequest() throws Exception {
        dealDto.setFromCurrency(null);

        ResultActions response = mockMvc.perform(post("/deal/add-deal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dealDto)));

        response.andExpect(status().isBadRequest());
    }
    @Test
    void toCurrencyIsMissing_ReturnsBadRequest() throws Exception {
        dealDto.setToCurrency(null);

        ResultActions response = mockMvc.perform(post("/deal/add-deal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dealDto)));

        response.andExpect(status().isBadRequest());
    }
    @Test
    void toCurrencyIsLongerThan3Characters_ReturnsBadRequest() throws Exception {
        dealDto.setToCurrency("JORDANINADINAR");

        ResultActions response = mockMvc.perform(post("/deal/add-deal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dealDto)));

        response.andExpect(status().isBadRequest());
    }
    @Test
    void fromCurrencyIsLongerThan3Characters_ReturnsBadRequest() throws Exception {
        dealDto.setFromCurrency("JORDANINADINAR");

        ResultActions response = mockMvc.perform(post("/deal/add-deal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dealDto)));

        response.andExpect(status().isBadRequest());
    }
    @Test
    void DealController_CreateDeal() throws Exception{
        ResultActions response = mockMvc.perform(post("/deal/add-deal")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dealDto)));

        response.andExpect(status().isCreated());
    }
}
