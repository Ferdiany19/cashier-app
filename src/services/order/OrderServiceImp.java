package services.order;

import java.util.List;

import dao.OrderDao;
import models.Order;

public class OrderServiceImp implements OrderService {
    OrderDao orderDao;

    public OrderServiceImp(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderServiceImp() {
    }

    @Override
    public void createOrder(Order order) {
        this.orderDao.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderDao.findAll();
    }

    @Override
    public Order getOrderById(Integer id) {
        return this.orderDao.findById(id);
    }

    @Override
    public Integer getOrderNumber() {
        return this.orderDao.getSize();
    }

    @Override
    public void updateOrder(Integer id, Order order) {
        this.orderDao.update(id, order);
    }

    @Override
    public void deleteOrder(Integer id) {
        this.orderDao.delete(id);
    }

    @Override
    public void deleteAllOrders() {
        this.orderDao.deleteAll();
    }

}
