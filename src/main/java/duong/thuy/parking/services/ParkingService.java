package duong.thuy.parking.services;

import duong.thuy.parking.request.CreateOwnerRequest;
import duong.thuy.parking.request.ParkingSpotRequest;
import duong.thuy.parking.response.ResponseData;
import org.springframework.http.ResponseEntity;

public interface ParkingService {
    public ResponseEntity<ResponseData> createParking(ParkingSpotRequest request, int userId);

    public ResponseEntity<ResponseData> getAllParkingByOwner(int userId);

    public ResponseEntity<ResponseData> getStartAndPoint(int parkingId,int userId);

    public ResponseEntity<ResponseData> getAllParkingApproved();
}
