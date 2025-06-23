package com.mallmanagement.admin;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mall_admin")
public class MallAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mallAdminId;

    private String username;
    private String password;
    private String email;
    private Integer loginAttempts;
    private Boolean isActive;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * Default no-argument constructor required by JPA.
     */
    public MallAdmin() {
    }

    /**
     * Constructor for creating a new MallAdmin with all fields.
     */
    public MallAdmin(Integer mallAdminId, String username, String password, String email, Integer loginAttempts, Boolean isActive, LocalDateTime lastLoginTime) {
        this.mallAdminId = mallAdminId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.loginAttempts = loginAttempts;
        this.isActive = isActive;
        this.lastLoginTime = lastLoginTime;
    }

    // --- Getters and Setters for all fields ---

    public Integer getMallAdminId() {
        return mallAdminId;
    }

    public void setMallAdminId(Integer mallAdminId) {
        this.mallAdminId = mallAdminId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(Integer loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "MallAdmin{" +
                "mallAdminId=" + mallAdminId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", loginAttempts=" + loginAttempts +
                ", isActive=" + isActive +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
