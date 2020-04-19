package duong.thuy.parking.services;

import duong.thuy.parking.request.ChangeTransactionStatusRequest;
import duong.thuy.parking.request.CreateOwnerRequest;
import duong.thuy.parking.request.CreateTransactionRequest;
import duong.thuy.parking.response.ResponseData;
import org.springframework.http.ResponseEntity;

import javax.websocket.server.PathParam;

public interface TransactionService {

    ResponseEntity<ResponseData> createTransaction(CreateTransactionRequest request, Long userId);

    ResponseEntity<ResponseData> getListParkingRequestForHost(int parkingId, String status);

    ResponseEntity<ResponseData> getListParkingRequestForUser(String status);

    ResponseEntity<ResponseData> updateStatusRequestParking(ChangeTransactionStatusRequest request);

    ;
}
