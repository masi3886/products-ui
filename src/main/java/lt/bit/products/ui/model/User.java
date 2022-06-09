package lt.bit.products.ui.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lt.bit.products.ui.service.domain.UserRole;
import lt.bit.products.ui.service.domain.UserStatus;

public class User {

  private Integer id;
  private String username;
  private String password;
  private UserRole role;
  private UserStatus status;
  private LocalDate createdAt;
  private LocalDateTime loggedInAt;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public UserStatus getStatus() {
    return status;
  }

  public void setStatus(UserStatus status) {
    this.status = status;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getLoggedInAt() {
    return loggedInAt;
  }

  public void setLoggedInAt(LocalDateTime loggedInAt) {
    this.loggedInAt = loggedInAt;
  }
}
