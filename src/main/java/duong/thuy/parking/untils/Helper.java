package duong.thuy.parking.untils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Helper {
    public static String HasPw(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(4));
    }

    public static Boolean CheckPw(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
