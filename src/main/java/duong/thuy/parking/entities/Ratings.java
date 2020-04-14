package duong.thuy.parking.entities;
// Generated Apr 12, 2020 6:47:23 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ratings generated by hbm2java
 */
@Entity
@Table(name = "ratings", catalog = "baido")
public class Ratings implements java.io.Serializable {

	private Long id;
	private int stars;
	private int credentialId;
	private int parkingId;

	public Ratings() {
	}

	public Ratings(int stars, int credentialId, int parkingId) {
		this.stars = stars;
		this.credentialId = credentialId;
		this.parkingId = parkingId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "stars", nullable = false)
	public int getStars() {
		return this.stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	@Column(name = "credential_id", nullable = false)
	public int getCredentialId() {
		return this.credentialId;
	}

	public void setCredentialId(int credentialId) {
		this.credentialId = credentialId;
	}

	@Column(name = "parking_id", nullable = false)
	public int getParkingId() {
		return this.parkingId;
	}

	public void setParkingId(int parkingId) {
		this.parkingId = parkingId;
	}

}