package duong.thuy.parking.services;

import antlr.collections.impl.LList;
import duong.thuy.parking.entities.Credentials;
import duong.thuy.parking.repository.CredentialsRepository;
import duong.thuy.parking.request.LoginRequest;
import duong.thuy.parking.request.RegisterRequest;
import duong.thuy.parking.response.LoginResponse;
import duong.thuy.parking.response.ResponseData;
import duong.thuy.parking.response.UserProfileResponse;
import duong.thuy.parking.untils.Constant;
import duong.thuy.parking.untils.Helper;
import duong.thuy.parking.untils.JwtUltis;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CredentialsServiceImpl extends BaseService implements CredentialsService {

    @Autowired
    private CredentialsRepository credentialsRepository;


    @Override
    public ResponseEntity<ResponseData> register(RegisterRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            List<Credentials> credentialsList =
                    credentialsRepository.findAllByUserNameAndEmail(request.getUserName(), request.getEmail());

            if (CollectionUtils.isEmpty(credentialsList)) {
                credentialsRepository.save(createCredentials(request));
            } else {
                return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.DUPLICATE.getValue(),
                        Constant.StatusCode.DUPLICATE.getMessage()));
            }
            return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                    Constant.StatusCode.OK.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.ERROR.getValue(),
                    Constant.StatusCode.ERROR.getMessage()));
        }
    }

    @Override
    public ResponseEntity<ResponseData> login(LoginRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            List<Credentials> credentialsList = credentialsRepository.findAllByUserName(request.getUserName());
            if (!CollectionUtils.isEmpty(credentialsList) && credentialsList.size() == 1) {
                if (checkPass(credentialsList.get(0).getPassword(), request.getPassword())) {
                    credentialsList.get(0).setToken(generateToken(credentialsList.get(0)));
                    credentialsRepository.save(credentialsList.get(0));
                    responseData.setData(mapEntityToModel(credentialsList.get(0)));
                    return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.OK.getValue(),
                            Constant.StatusCode.OK.getMessage()));
                }
            }
            return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.INVALID.getValue(),
                    Constant.StatusCode.INVALID.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.ok(responseUtils.responseSuccess(responseData, Constant.StatusCode.ERROR.getValue(),
                    Constant.StatusCode.ERROR.getMessage()));
        }
    }

    @Override
    public ResponseEntity<ResponseData> getUserProfile(int userId) {

        ResponseData responseData = new ResponseData();
        try {
            List<Credentials> credentialsList = credentialsRepository.findAllById(userId);
            if (!CollectionUtils.isEmpty(credentialsList)) {
                responseData.setData(dataResponse(credentialsList.get(0)));
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

    private UserProfileResponse dataResponse(Credentials credentials) {
        UserProfileResponse result = new UserProfileResponse();
        result.setEmail(credentials.getEmail());
        result.setPoints(String.valueOf(credentials.getPoints()));
        result.setUsername(credentials.getUserName());
        return result;
    }

    private Credentials createCredentials(RegisterRequest request) {
        Credentials credentials = new Credentials();
        credentials.setUserName(request.getUserName());
        credentials.setEmail(request.getEmail());
        credentials.setPoints(0);
        credentials.setRole(Constant.Role.USER.getRole());
        credentials.setPassword(Helper.HasPw(request.getPassword()));
        credentials.setCreatedAt(new Date());
        credentials.setModifiedAt(new Date());
        return credentials;
    }

    private boolean checkPass(String passOld, String inputPass) {
        return Helper.CheckPw(inputPass, passOld);
    }

    String generateToken(Credentials credentials) {
        return JwtUltis.generateToken(credentials, Collections.singletonList(credentials.getRole()));
    }

    private LoginResponse mapEntityToModel(Credentials credentials) {
        return new LoginResponse(credentials.getToken(), credentials.getRole());
    }
}
