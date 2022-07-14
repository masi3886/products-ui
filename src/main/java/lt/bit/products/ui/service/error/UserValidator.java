package lt.bit.products.ui.service.error;

import lt.bit.products.ui.model.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserValidator {

  public void validate(User user) throws ValidationException {
    validatePasswords(user.getPassword(), user.getConfirmedPassword(), user.getId() == null);
  }

  private void validatePasswords(String newPassword, String confirmedPassword, boolean newUser)
      throws ValidationException {

    if (StringUtils.hasLength(newPassword) && !newPassword.equals(confirmedPassword)) {
      throw new ValidationException(ErrorCode.PASSWORDS_DO_NOT_MATCH);
    } else if (newUser) {
      throw new ValidationException(ErrorCode.PASSWORD_REQUIRED);
    }
  }

}
