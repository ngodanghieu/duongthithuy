package duong.thuy.parking.services;

import duong.thuy.parking.entities.Transactions;
import duong.thuy.parking.repository.TransactionRepository;
import duong.thuy.parking.request.CreateTransactionRequest;
import duong.thuy.parking.response.ResponseData;
import duong.thuy.parking.untils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionServiceImpl extends BaseService implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<ResponseData> createTransaction(CreateTransactionRequest request, int userId) {
        ResponseData responseData = new ResponseData();
        try {
            transactionRepository.save(createNewTransaction(request, userId));
            return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                    Constant.StatusCode.OK.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.ERROR.getValue(),
                    Constant.StatusCode.ERROR.getMessage()));
        }
    }

    private Transactions createNewTransaction(CreateTransactionRequest request, int userId) {
        Transactions data = new Transactions();
        data.setCreatedAt(new Date());
        data.setCredentialId(userId);
        data.setEndTime(new Date(request.getEndTime()));
        data.setStartTime(new Date(request.getStartTime()));
        data.setLiencePlate(request.getLicence());
        data.setSesion(new Date());
        data.setReasonMesage(request.getPhoneNumber());
        data.setParkingId(request.getParkingId());
        data.setAmount(request.getRequestedPayment());
        return data;
    }
}
