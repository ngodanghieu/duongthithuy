package duong.thuy.parking.repository;

import duong.thuy.parking.entities.Owners;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owners,Long> {
}
