package duong.thuy.parking.services;

import duong.thuy.parking.entities.Credentials;
import duong.thuy.parking.entities.Owners;
import duong.thuy.parking.entities.Parking;
import duong.thuy.parking.entities.Ratings;
import duong.thuy.parking.repository.CredentialsRepository;
import duong.thuy.parking.repository.ParkingRepository;
import duong.thuy.parking.repository.RatingRepository;
import duong.thuy.parking.request.ParkingSpotRequest;
import duong.thuy.parking.response.AmountOwnerResponse;
import duong.thuy.parking.response.ParkingResponse;
import duong.thuy.parking.response.ResponseData;
import duong.thuy.parking.untils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ParkingServiceImpl extends BaseService implements ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    @Transactional
    public ResponseEntity<ResponseData> createParking(ParkingSpotRequest request, Long userId) {
        ResponseData responseData = new ResponseData();
        try {
            List<Parking> parkingList = parkingRepository.findAllByLongitudeAndLatitude(request.getLongitude(), request.getLatitude());

            if (CollectionUtils.isEmpty(parkingList)) {
                parkingRepository.save(createNewParking(request, userId));
            } else {
                return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.DUPLICATE.getValue(),
                        Constant.StatusCode.DUPLICATE.getMessage()));
            }
            return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                    Constant.StatusCode.OK.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.ERROR.getValue(),
                    Constant.StatusCode.ERROR.getMessage()));
        }
    }

    @Override
    public ResponseEntity<ResponseData> getAllParkingByOwner(Long userId) {
        ResponseData responseData = new ResponseData();
        try {
            List<Parking> parkingList = parkingRepository.findAllByOwnerId(userId);

            if (!CollectionUtils.isEmpty(parkingList)) {
                responseData.setData(parkingList);
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

    @Override
    public ResponseEntity<ResponseData> getStartAndPoint(int parkingId, Long userId) {

        ResponseData responseData = new ResponseData();
        try {
            List<Ratings> ratingsList = ratingRepository.findAllByParkingId(parkingId);
            List<Credentials> credentialsList = credentialsRepository.findAllById(userId);

            if (!CollectionUtils.isEmpty(ratingsList) && !CollectionUtils.isEmpty(credentialsList)) {
                Float start = 0f;
                for (Ratings rating : ratingsList) {
                    start += rating.getStars();
                }
                responseData.setData(new AmountOwnerResponse((long) credentialsList.get(0).getPoints(), start / ratingsList.size()));
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

    @Override
    public ResponseEntity<ResponseData> getAllParkingApproved() {
        ResponseData responseData = new ResponseData();
        try {
            List<Parking> parkingList = parkingRepository.findAllByStatus(Constant.Status.ACTIVE.getStatus());

            if (!CollectionUtils.isEmpty(parkingList)) {
                responseData.setData(parkingList);
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

    @Override
    public ResponseEntity<ResponseData> getDetailParking(int parkingId) {
        ResponseData responseData = new ResponseData();
        try {
            List<Parking> parkingList = parkingRepository.findById(parkingId);

            if (!CollectionUtils.isEmpty(parkingList)) {
                responseData.setData(dataResponse(parkingList.get(0)));
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

    private ParkingResponse dataResponse(Parking parking){
        ParkingResponse result = new ParkingResponse();
        result.setAddress(parking.getAddress());
        result.setBlockAmount(Long.valueOf(parking.getBlockAmount()));
        result.setCapacity(parking.getCapacity());
        result.setCertificateOfland(parking.getCertificateOfLand());
        result.setCreated_at(parking.getCreatedAt().toString());
        result.setId(parking.getId());
        result.setKindOf(parking.getKindOf());
        result.setLatitude(Double.valueOf(parking.getLatitude()));
        result.setLongitude(Double.valueOf(parking.getLongitude()));
        result.setModified_at(parking.getModifiedAt().toString());
        result.setOwnerId((long) parking.getOwnerId());
        result.setParkingImages(parking.getParkingImage());
        result.setParkingName(parking.getParkingName());
        result.setStatus(parking.getStatus());
        result.setProperties(parking.getProperties());
        result.setPayment(parking.getPayment());
        return result;
    }

    private Parking createNewParking(ParkingSpotRequest request, Long userId) {
        Parking data = new Parking();
        data.setAddress(request.getAddress());
        data.setLatitude(request.getLatitude());
        data.setLongitude(request.getLongitude());
        data.setCreatedAt(new Date());
        data.setModifiedAt(new Date());
        data.setStatus(Constant.Status.IN_ACTIVE.getStatus());
        data.setParkingImage(request.getGaraImage());
        data.setCapacity(request.getCapacity());
        data.setCertificateOfLand(request.getCertifitateLandImage());
        data.setBlockAmount(request.getPrice());
        data.setKindOf(request.getGaraType());
        data.setOwnerId(userId);
        data.setParkingName(request.getParkingName());
        data.setCertificateOfLand(request.getCertifitateLandImage());
        data.setProperties(request.getProperties());
        data.setPayment(request.getPayMethod());
        return data;
    }
}
