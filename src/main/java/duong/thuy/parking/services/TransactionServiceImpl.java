package duong.thuy.parking.services;

import duong.thuy.parking.entities.Credentials;
import duong.thuy.parking.entities.Owners;
import duong.thuy.parking.entities.Parking;
import duong.thuy.parking.entities.Transactions;
import duong.thuy.parking.repository.CredentialsRepository;
import duong.thuy.parking.repository.OwnerRepository;
import duong.thuy.parking.repository.ParkingRepository;
import duong.thuy.parking.repository.TransactionRepository;
import duong.thuy.parking.request.ChangeTransactionStatusRequest;
import duong.thuy.parking.request.CreateTransactionRequest;
import duong.thuy.parking.response.ResponseData;
import duong.thuy.parking.response.TicketResponse;
import duong.thuy.parking.untils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl extends BaseService implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ParkingRepository parkingRepository;

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

    @Override
    public ResponseEntity<ResponseData> getListParkingRequestForHost(int parkingId, String status) {
        ResponseData responseData = new ResponseData();
        try {
            List<Transactions> transactionsList = transactionRepository.findAllByParkingIdAndStatus(parkingId, status);
            if (!CollectionUtils.isEmpty(transactionsList)) {
                responseData.setData(transactionsList.stream().map(this::mapCollection).collect(Collectors.toList()));
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
    public ResponseEntity<ResponseData> getListParkingRequestForUser(String status) {
        ResponseData responseData = new ResponseData();
        try {
            List<Transactions> transactionsList = transactionRepository.findAllByStatus(status);
            if (!CollectionUtils.isEmpty(transactionsList)) {
                responseData.setData(transactionsList.stream().map(this::mapCollection).collect(Collectors.toList()));
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
    public ResponseEntity<ResponseData> updateStatusRequestParking(ChangeTransactionStatusRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            List<Transactions> transactionsList = transactionRepository.findAllById(request.getTransactionId());
            if (!CollectionUtils.isEmpty(transactionsList) && transactionsList.size() == 1) {
                transactionsList.get(0).setStatus(request.getStatus().toString());
                transactionRepository.save(transactionsList.get(0));
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

    private TicketResponse mapCollection(Transactions transactions) {
        TicketResponse ticketResponse = new TicketResponse();
        try {
            List<Parking> parkingList = parkingRepository.findById(transactions.getParkingId());
            if (!CollectionUtils.isEmpty(parkingList)) {
                ticketResponse.setAddress(parkingList.get(0).getAddress());
            }

            List<Owners> ownersList = ownerRepository.findByUserId(transactions.getCredentialId());
            if (!CollectionUtils.isEmpty(ownersList)) {
                ticketResponse.setHostPhoneNumber(ownersList.get(0).getPhoneNumber());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ticketResponse.setUserPhoneNumber(transactions.getReasonMesage());

        ticketResponse.setAmount(transactions.getAmount());
        ticketResponse.setCreated_at(transactions.getCreatedAt().toString());
        ticketResponse.setEndTime(transactions.getEndTime().toString());

        ticketResponse.setLicence(transactions.getLiencePlate());
        ticketResponse.setStartTime(transactions.getStartTime().toString());
        ticketResponse.setStatus(transactions.getStatus());
        ticketResponse.setParkingId(transactions.getParkingId());

        ticketResponse.setTransactionId(transactions.getId());
        return ticketResponse;
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
        data.setStatus(Constant.Status.IN_ACTIVE.getStatus());
        return data;
    }
}
