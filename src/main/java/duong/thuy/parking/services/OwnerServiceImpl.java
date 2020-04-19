package duong.thuy.parking.services;

import duong.thuy.parking.entities.Credentials;
import duong.thuy.parking.entities.Owners;
import duong.thuy.parking.repository.OwnerRepository;
import duong.thuy.parking.request.CreateOwnerRequest;
import duong.thuy.parking.response.ResponseData;
import duong.thuy.parking.untils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.security.acl.Owner;
import java.util.List;

@Service
public class OwnerServiceImpl extends BaseService implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    @Transactional
    public ResponseEntity<ResponseData> createOwner(CreateOwnerRequest request, Long userId) {
        ResponseData responseData = new ResponseData();
        try {
            List<Owners> ownersList = ownerRepository.findAllByPhoneNumber(request.getPhoneNumber());

            if (CollectionUtils.isEmpty(ownersList)) {
                ownerRepository.save(createOwnerEntity(request,userId));
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

    private Owners createOwnerEntity(CreateOwnerRequest request, Long userId) {
        Owners data = new Owners();
        data.setFullName(request.getFullName());
        data.setCmndImage(request.getCmndImage());
        data.setPhoneNumber(request.getPhoneNumber());
        data.setUserId(userId);
        data.setStatus(Constant.Status.IN_ACTIVE.getStatus());
        return data;
    }

}
