package lt.bit.products.ui.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lt.bit.products.ui.model.CartItem;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

@Service
@SessionAttributes("cartItems")
public class CartService {

  private Map<UUID, CartItem> cartItems = new HashMap<>();

  public void addToCart(UUID productId, String productName) {
    CartItem item;
    if (cartItems.containsKey(productId)) {
      item = cartItems.get(productId);
      item.setCount(item.getCount() + 1);
    } else {
      item = new CartItem(productId, productName, 1);
    }
    cartItems.put(productId, item);
  }
}
