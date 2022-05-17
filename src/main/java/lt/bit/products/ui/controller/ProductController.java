package lt.bit.products.ui.controller;

import java.util.List;
import java.util.UUID;
import lt.bit.products.ui.model.Product;
import lt.bit.products.ui.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
class ProductController {

  private ProductService service;

  ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping("/products")
  String showProducts(Model model) {
    List<Product> products = service.getProducts();
    model.addAttribute("productItems", products);
    return "productList";
  }

  @GetMapping("/products/{id}")
  String editProduct(@PathVariable UUID id, Model model) {
    model.addAttribute("productItem", service.getProduct(id));
    return "productForm";
  }

}
