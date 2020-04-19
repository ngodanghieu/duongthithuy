package duong.thuy.parking.untils;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    public static final int EXPIRATION = 60 * 30;


    public enum StatusCode {
        OK(200000, "OK"),
        ERROR(400000, "Lỗi hệ thống"),
        DUPLICATE(400002, "Đã tồn tại"),
        INVALID(400003, "Tên tài khoản hoặc mật khẩu không đúng"),
        NOT_FOUND(400001, "Không có data");

        private int value;
        private String message;

        StatusCode(int value, String message) {
            this.value = value;
            this.message = message;
        }

        public int getValue() {
            return value;
        }

        public String getMessage() {
            return message;
        }
    }

    public enum Role {
        OWNER("OWNER"),
        USER("USER");

        private String role;

        Role(String message) {
            this.role = message;
        }

        public String getRole() {
            return role;
        }
    }

    public enum Status {
        ACTIVE(1),
        IN_ACTIVE(0);

        private int status;

        Status(int status) {
            this.status = status;
        }

        public String getStatus() {
            return String.valueOf(status);
        }

    }
}
