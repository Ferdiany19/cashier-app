package dao;

import java.util.ArrayList;
import java.util.List;

import models.Order;
import services.BaseDao;
import services.ID;

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
    public Order findById(ID id) {
        return this.orders.get(id - 1);
    }

    @Override
    public Integer getSize() {
        return this.orders.size();
    }

    @Override
    public void update(ID id, Order data) {
        return this.orders.set(id - 1, data);
    }

    @Override
    public void delete(ID id) {
        this.orders.remove(id - 1);
    }

    @Override
    public void deleteAll() {
        this.orders.clear();
    }

}
