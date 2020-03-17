package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender", nullable = false)
    private Byte gender;

    @Column(name = "email")
    private String email;

    @Column(name = "iconimg_url")
    private String iconimg_url;

    @Column(name = "created_time", nullable = false)
    private Date createdTime;

    @Column(name = "last_login", nullable = false)
    private Date lastLogin;

    public User() {
        gender=0;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        gender=0;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIconimg_url() {
        return iconimg_url;
    }

    public void setIconimg_url(String iconimg_url) {
        this.iconimg_url = iconimg_url;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", iconimg='" + iconimg_url + '\'' +
                ", createdTime=" + createdTime +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
