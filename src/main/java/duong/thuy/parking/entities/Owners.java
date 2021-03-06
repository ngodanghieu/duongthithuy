package duong.thuy.parking.entities;
// Generated Apr 12, 2020 6:47:23 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Owners generated by hbm2java
 */
@Entity
@Table(name = "owners", catalog = "baido")
public class Owners implements java.io.Serializable {

	private Long id;
	private String fullName;
	private String cmndImage;
	private String phoneNumber;
	private Long userId;
	private String status;

	public Owners() {
	}

	public Owners(String fullName, String cmndImage, String phoneNumber, Long userId, String status) {
		this.fullName = fullName;
		this.cmndImage = cmndImage;
		this.phoneNumber = phoneNumber;
		this.userId = userId;
		this.status = status;
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

	@Column(name = "full_name", nullable = false, length = 65535)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "cmnd_image", nullable = false, length = 65535)
	public String getCmndImage() {
		return this.cmndImage;
	}

	public void setCmndImage(String cmndImage) {
		this.cmndImage = cmndImage;
	}

	@Column(name = "phone_number", nullable = false, length = 65535)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "status", nullable = false, length = 65535)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
