package duong.thuy.parking.controller;

import duong.thuy.parking.request.RatingRequest;
import duong.thuy.parking.response.ResponseData;
import duong.thuy.parking.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("rating/parking")
    public ResponseEntity<ResponseData> updateStatusRequestParking(@Valid @RequestBody RatingRequest request,
                                                                   BindingResult bindingResult) {
        return ratingService.updateStatusRequestParking(request);
    }
}
