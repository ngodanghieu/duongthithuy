package duong.thuy.parking.services;

import duong.thuy.parking.entities.UserDevices;
import duong.thuy.parking.repository.UserFCMRepository;
import duong.thuy.parking.request.FmcRequest;
import duong.thuy.parking.response.ResponseData;
import duong.thuy.parking.untils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FmcServiceImpl extends BaseService implements FmcService {

    @Autowired
    private UserFCMRepository userFCMRepository;

    @Override
    @Transactional
    public ResponseEntity<ResponseData> requestUpdateFCMToken(FmcRequest request, Long userId) {
        ResponseData responseData = new ResponseData();
        try {
            userFCMRepository.save(createNewFMC(request, userId));
            return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                    Constant.StatusCode.OK.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.ERROR.getValue(),
                    Constant.StatusCode.ERROR.getMessage()));
        }
    }

    private UserDevices createNewFMC(FmcRequest request, Long userId) {
        UserDevices userDevices = new UserDevices();
        userDevices.setCredentialId(userId);
        userDevices.setDeviceToken(request.getDeviceToken());
        return userDevices;
    }
}
