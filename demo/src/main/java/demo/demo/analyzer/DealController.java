package demo.demo.analyzer;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deal")
@Slf4j
public class DealController {
    private final DealService dealService;

    @Autowired
    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping("/add-deal")
    public ResponseEntity<?> addNewDeal(@Valid @RequestBody DealDto dealDto){
        log.info("Received Deal {}",dealDto.toString());
        dealService.addNewDeal(dealDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Deal Successfully Added!");
    }
}
