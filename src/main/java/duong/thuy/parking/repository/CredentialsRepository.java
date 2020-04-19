package duong.thuy.parking.repository;

import duong.thuy.parking.entities.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CredentialsRepository extends JpaRepository<Credentials,Long> {

    List<Credentials> findAllByUserNameAndEmail(String userName , String email);

    List<Credentials> findAllByUserName(String userName);

    List<Credentials> findAllById(Long userId);

}
