package lt.bit.products.ui.controller;

import javax.servlet.http.HttpServletRequest;
import lt.bit.products.ui.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class UserController {

  private final UserService userService;

  UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/auth/login")
  String loginForm() {
    if (userService.isAuthenticated()) {
      return "redirect:/products";
    }
    return "login";
  }

  @PostMapping("/auth/login")
  String login(HttpServletRequest request, Model model) {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if (username.equals("admin") && password.equals("123")) {
      userService.setAuthenticated(true);
      return "redirect:/products";
    }
    model.addAttribute("errorMsg", "Invalid username or password");
    return "login";
  }

  @GetMapping("/auth/logout")
  String logout() {
    userService.setAuthenticated(false);
    return "login";
  }
}
