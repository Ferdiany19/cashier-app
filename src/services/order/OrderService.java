package services.order;

import java.util.List;

import models.Order;

public interface OrderService {
    void createOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(Integer id);

    Integer getOrderNumber();

    void updateOrder(Integer id, Order order);

    void deleteOrder(Integer id);

    void deleteAllOrders();
}
