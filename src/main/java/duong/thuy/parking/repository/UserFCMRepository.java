package duong.thuy.parking.repository;

import duong.thuy.parking.entities.UserDevices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFCMRepository  extends JpaRepository<UserDevices,Long> {
}
