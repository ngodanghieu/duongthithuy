package duong.thuy.parking.services;

import duong.thuy.parking.request.ChangeTransactionStatusRequest;
import duong.thuy.parking.request.RatingRequest;
import duong.thuy.parking.response.ResponseData;
import org.springframework.http.ResponseEntity;

public interface RatingService {
    ResponseEntity<ResponseData> updateStatusRequestParking(RatingRequest request);
}
