package vn.iotstar.entity;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name="Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="userName",unique = true, nullable = false)
    private String userName;

    @Column(name="fullName",nullable = false)
    private String fullName;

    @Column(name="password",nullable = false)
    private String passWord;

    @Column(name="roleId",nullable = false)
    private int roleid;

    @Column(name="phone")
    private String phone;

    @Column(name="createdDate")
    private Date createdDate;

    @Column(name="token")
    private String token;
    
    @Column(name="expiry_token")
    private Timestamp expiry_token;

}
