package lt.bit.products.ui.integration;

import java.util.List;
import java.util.stream.Collectors;
import lt.bit.products.ui.service.domain.ProductEntity;
import lt.bit.products.ui.service.domain.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class ProductsImporter {

  private static final Logger LOG = LoggerFactory.getLogger(ProductsImporter.class);
  private final ProductStoreClient client;
  private final ProductRepository repository;

  ProductsImporter(ProductStoreClient client,
      ProductRepository repository) {
    this.client = client;
    this.repository = repository;
  }

  @Scheduled(fixedRate = 30000L)
  void importProducts() {
    if (noNewProducts()) {
      return;
    }
    LOG.info("Importing products...");
    List<ProductStoreResponse> newProducts = client.getProducts();
    List<ProductEntity> productsToSave = newProducts.stream()
        .map(storeProduct -> {
          ProductEntity entity = new ProductEntity();
          entity.setName(storeProduct.getName());
          entity.setDescription(storeProduct.getDescription());
          entity.setExternalId(String.valueOf(storeProduct.getId()));
          return entity;
        })
        .collect(Collectors.toList());
    repository.saveAll(productsToSave);
    LOG.info("Imported {} products.", productsToSave.size());
  }

  boolean noNewProducts() {
    Long productsInStore = client.getProductsCount();
    long ourProducts = repository.count();
    return productsInStore <= ourProducts;
  }
}
