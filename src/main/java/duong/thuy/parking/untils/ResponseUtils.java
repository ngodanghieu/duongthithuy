package duong.thuy.parking.untils;

import duong.thuy.parking.response.ResponseData;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ResponseUtils {

    public ResponseData responseSuccess(ResponseData myResponse, int code, String message){
        if (myResponse.getMeta() == null){
            ResponseData.Meta meta = new ResponseData.Meta();
            meta.setCode(Constant.StatusCode.OK.getValue());
            meta.setMessage("OK");
            myResponse.setMeta(meta);
        }
        if (code > 0)
            myResponse.getMeta().setCode(code);

        if (!StringUtils.isEmpty(message)){
            myResponse.getMeta().setMessage(message);
        }

        return myResponse;
    }
}
