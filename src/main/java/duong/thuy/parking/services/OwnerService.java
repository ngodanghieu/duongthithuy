package duong.thuy.parking.services;

import duong.thuy.parking.request.CreateOwnerRequest;
import duong.thuy.parking.response.ResponseData;
import org.springframework.http.ResponseEntity;

public interface OwnerService {
    public ResponseEntity<ResponseData> createOwner(CreateOwnerRequest request, int userId);
}
