package lt.bit.products.ui.service;

import java.util.List;
import java.util.stream.Collectors;
import lt.bit.products.ui.model.Order;
import lt.bit.products.ui.service.domain.OrderEntity;
import lt.bit.products.ui.service.domain.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

  private final OrderRepository repository;
  private final ModelMapper mapper;

  OrderService(OrderRepository repository, ModelMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public void createOrder(OrderEntity order) {
    repository.save(order);
  }

  public List<Order> getOrders() {
    return repository.findAll().stream()
        .map(o -> mapper.map(o, Order.class))
        .collect(Collectors.toList());
  }
}
