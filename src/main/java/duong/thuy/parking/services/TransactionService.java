package duong.thuy.parking.services;

import duong.thuy.parking.request.CreateOwnerRequest;
import duong.thuy.parking.request.CreateTransactionRequest;
import duong.thuy.parking.response.ResponseData;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    ResponseEntity<ResponseData> createTransaction(CreateTransactionRequest request, int userId);
}
