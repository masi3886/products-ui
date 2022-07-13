package lt.bit.products.ui.service;

import java.security.AccessControlException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lt.bit.products.ui.model.User;
import lt.bit.products.ui.service.domain.UserEntity;
import lt.bit.products.ui.service.domain.UserRepository;
import lt.bit.products.ui.service.domain.UserRole;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

@Service
@Transactional
@SessionAttributes({"authenticated", "admin", "userId", "userName"})
public class UserService {

  private final UserRepository repository;
  private final ModelMapper mapper;

  private boolean authenticated;
  private boolean admin;
  private Integer userId;
  private String userName;

  public UserService(UserRepository repository, ModelMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public void login(String username, String password) {
    Optional<UserEntity> user = repository.findByUsernameAndPassword(username, password);
    user.ifPresent(u -> {
      setAuthenticated(true);
      setAdmin(u.getRole() == UserRole.ADMIN);
      setUserId(u.getId());
      setUserName(u.getUsername());
      repository.updateLastLoginTime(LocalDateTime.now());
    });
  }

  public void logout() {
    setAuthenticated(false);
    setAdmin(false);
    setUserId(null);
    setUserName(null);
  }

  public boolean isAuthenticated() {
    return authenticated;
  }

  private void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }

  public boolean isAdmin() {
    return admin;
  }

  private void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public Integer getUserId() {
    return userId;
  }

  private void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  private void setUserName(String userName) {
    this.userName = userName;
  }

  public List<User> getUsers() {
    List<UserEntity> users = repository.findAll();
    // @formatter:off
    return mapper.map(users, new TypeToken<List<User>>() {}.getType());
    // @formatter:on
  }

  public User getUser(Integer id) {
    Optional<UserEntity> user = repository.findById(id);
    if (user.stream().anyMatch(u -> u.getRole() == UserRole.ADMIN)) {
      throw new AccessControlException("permission.error.ADMIN_USER_EDITING");
    }
    return user.map(u -> mapper.map(u, User.class)).orElseThrow();
  }

  public void saveUser(User user) {
    repository.save(mapper.map(user, UserEntity.class));
  }

  public void deleteUser(Integer id) {
    Optional<UserEntity> user = repository.findById(id);
    if (user.stream().anyMatch(u -> u.getRole() == UserRole.ADMIN)) {
      throw new AccessControlException("permission.error.ADMIN_USER_DELETION");
    }
    repository.deleteById(id);
  }
}
