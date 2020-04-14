package duong.thuy.parking.services;

import duong.thuy.parking.request.LoginRequest;
import duong.thuy.parking.request.RegisterRequest;
import duong.thuy.parking.response.ResponseData;
import org.springframework.http.ResponseEntity;

public interface CredentialsService {

    ResponseEntity<ResponseData> register(RegisterRequest request);

    ResponseEntity<ResponseData> login(LoginRequest request);
}
