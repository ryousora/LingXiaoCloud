package service.dto;

import java.util.Date;

public class UserDTO {

    private Integer userId;

    private String username;

    private Byte gender;

    private String email;

    private String iconimg_url;

    private Date lastLogin;


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
        this.username = username == null ? null : username.trim();
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
        this.email = email == null ? null : email.trim();
    }

    public String getIconimg_url() {
        return iconimg_url;
    }

    public void setIconimg_url(String iconimg_url) {
        this.iconimg_url = iconimg_url == null ? null : iconimg_url.trim();
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", gender=" + gender.intValue() +
                ", email='" + email + '\'' +
                ", iconimg_url='" + iconimg_url + '\'' +
                ", lastLogin=" + lastLogin +
                '}';
    }
}