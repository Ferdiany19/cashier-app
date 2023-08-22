package dao;

import java.util.ArrayList;
import java.util.List;

import models.Order;
import services.BaseDao;

public class OrderDao implements BaseDao<Order, Integer> {

    List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order data) {
        this.orders.add(data);
    }

    @Override
    public List<Order> findAll() {
        return this.orders;
    }

    @Override
    public Order findById(Integer id) {
        return this.orders.get(id - 1);
    }

    @Override
    public Integer getSize() {
        return this.orders.size();
    }

    @Override
    public void update(Integer id, Order data) {
        this.orders.set(id - 1, data);
    }

    @Override
    public void delete(Integer id) {
        this.orders.remove(id - 1);
    }

    @Override
    public void deleteAll() {
        this.orders.clear();
    }

}
