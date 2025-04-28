package demo.demo.analyzer;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deal")
public class DealController {
    @Autowired
    private DealService dealService;

    @PostMapping("/add-deal")
    public ResponseEntity<?> addNewDeal(@Valid @RequestBody DealDto dealDto){
        Deal deal = null;
        try {
            deal = dealService.addNewDeal(dealDto);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(deal);
    }
}
