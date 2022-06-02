package lt.bit.products.ui.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lt.bit.products.ui.model.Supplier;
import lt.bit.products.ui.service.SupplierService;
import lt.bit.products.ui.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupplierController {

  private final SupplierService service;
  private final UserService userService;

  public SupplierController(SupplierService service, UserService userService) {
    this.service = service;
    this.userService = userService;
  }

  @GetMapping("/suppliers")
  String showProducts(Model model, HttpServletRequest request) {
    if (!userService.isAuthenticated()) {
      return "login";
    }

    List<Supplier> suppliers = service.getSuppliers();

    model.addAttribute("supplierItems", suppliers);
    return "supplierList";
  }
}
