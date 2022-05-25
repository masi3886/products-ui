package lt.bit.products.ui.service;

import java.util.List;
import java.util.UUID;
import lt.bit.products.ui.model.Product;
import lt.bit.products.ui.service.domain.ProductEntity;
import lt.bit.products.ui.service.domain.ProductRepository;
import lt.bit.products.ui.service.error.ErrorCode;
import lt.bit.products.ui.service.error.ValidationException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

  private final ProductRepository repository;
  private final ModelMapper mapper;

  public ProductService(ProductRepository repository) {
    this.repository = repository;
    mapper = new ModelMapper();
  }

  public List<Product> getProducts() {
    List<ProductEntity> products = repository.findAll();
    // @formatter:off
    return mapper.map(products, new TypeToken<List<Product>>() {}.getType());
    // @formatter:on
  }

  public void saveProduct(Product product) throws ValidationException {
    UUID id = product.getId();
    if (id != null && !repository.existsById(id)) {
      throw new ValidationException(ErrorCode.PRODUCT_NOT_FOUND, id);
    }
    repository.save(mapper.map(product, ProductEntity.class));
  }

  public void deleteProduct(UUID id) {
    repository.deleteById(id);
  }

  public Product getProduct(UUID id) {
    return findProduct(id);
  }

  private Product findProduct(UUID id) {
    return repository.findById(id).map(p -> mapper.map(p, Product.class)).orElseThrow();
  }
}
