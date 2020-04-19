package duong.thuy.parking.repository;

import duong.thuy.parking.entities.Owners;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owners,Long> {

    List<Owners> findAllByPhoneNumber(String phone);

}
