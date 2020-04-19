package duong.thuy.parking.services;

import duong.thuy.parking.request.ParkingSpotRequest;
import duong.thuy.parking.response.ResponseData;
import org.springframework.http.ResponseEntity;

public interface ParkingService {
    public ResponseEntity<ResponseData> createParking(ParkingSpotRequest request, Long userId);

    public ResponseEntity<ResponseData> getAllParkingByOwner(Long userId);

    public ResponseEntity<ResponseData> getStartAndPoint(int parkingId, Long userId);

    public ResponseEntity<ResponseData> getAllParkingApproved();

    public ResponseEntity<ResponseData> getDetailParking(int parkingId);
}
