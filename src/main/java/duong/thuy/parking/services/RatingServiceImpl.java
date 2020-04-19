package duong.thuy.parking.services;

import duong.thuy.parking.entities.Parking;
import duong.thuy.parking.entities.Ratings;
import duong.thuy.parking.entities.Transactions;
import duong.thuy.parking.repository.RatingRepository;
import duong.thuy.parking.repository.TransactionRepository;
import duong.thuy.parking.request.ChangeTransactionStatusRequest;
import duong.thuy.parking.request.RatingRequest;
import duong.thuy.parking.response.ResponseData;
import duong.thuy.parking.untils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class RatingServiceImpl extends BaseService implements RatingService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public ResponseEntity<ResponseData> updateStatusRequestParking(RatingRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            List<Transactions> transactionsList = transactionRepository.findAllById(request.getTransactionId());

            if (!CollectionUtils.isEmpty(transactionsList)) {
                ratingRepository.save(createNewRating(request,transactionsList.get(0)));
                return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                        Constant.StatusCode.OK.getMessage()));
            } else {
                return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.NOT_FOUND.getValue(),
                        Constant.StatusCode.NOT_FOUND.getMessage()));
            }
        } catch (Exception ex) {
            return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.ERROR.getValue(),
                    Constant.StatusCode.ERROR.getMessage()));
        }
    }

    private Ratings createNewRating(RatingRequest request,Transactions transactions){
        Ratings ratings = new Ratings();
        ratings.setParkingId(transactions.getParkingId());
        ratings.setStars(request.getStats());
        ratings.setCredentialId(transactions.getCredentialId());
        return ratings;

    }
}
