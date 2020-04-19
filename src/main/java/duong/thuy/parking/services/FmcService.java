package duong.thuy.parking.services;

import duong.thuy.parking.request.FmcRequest;
import duong.thuy.parking.response.ResponseData;
import org.springframework.http.ResponseEntity;

public interface FmcService {
    ResponseEntity<ResponseData> requestUpdateFCMToken(FmcRequest request, Long userId);
}
