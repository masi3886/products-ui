package lt.bit.products.ui.controller;

import java.util.List;
import java.util.UUID;
import lt.bit.products.ui.model.CartItem;
import lt.bit.products.ui.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
class CustomerSiteController {

  private final static Logger LOG = LoggerFactory.getLogger(CustomerSiteController.class);
  private final CartService cartService;

  CustomerSiteController(CartService cartService) {
    this.cartService = cartService;
  }

  @PostMapping("/cart/add")
  @ResponseBody
  ModelAndView addToCart(@RequestParam UUID productId, @RequestParam String productName) {
    cartService.addToCart(productId, productName);
    List<CartItem> cartItems = cartService.getCartItems();
    ModelAndView mv = new ModelAndView("cartItems");
    mv.addObject("cartItems", cartItems);
    return mv;
  }
}
