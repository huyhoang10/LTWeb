package vn.iostar.entity;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, nullable = false)
    private String email;
	
	@Column(unique = true, nullable = false)
    private String userName;
	
	@Column(nullable = false)
    private String fullName;
	
	@Column(nullable = false)
    private String passWord;
	
	@Column(nullable = false)
    private int roleid;
	
	
    private String phone;
    
    private Date createdDate;
    
    private String token;
    
    private Timestamp expiry_token;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getExpiry_token() {
		return expiry_token;
	}

	public void setExpiry_token(Timestamp expiry_token) {
		this.expiry_token = expiry_token;
	}

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
