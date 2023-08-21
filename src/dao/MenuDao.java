package dao;

import java.util.ArrayList;
import java.util.List;

import models.Menu;
import services.BaseDao;
import services.ID;

public class MenuDao implements BaseDao<Menu, Integer> {
    List<Menu> menus = new ArrayList<>();

    @Override
    public void save(Menu data) {
        menus.add(data);
    }

    @Override
    public List<Menu> findAll() {
        return this.menus;
    }

    @Override
    public Menu findById(ID id) {
        return this.menus.get(id - 1);
    }

    @Override
    public Integer getSize() {
        return this.menus.size();
    }

    @Override
    public void update(ID id, Menu data) {
        this.menus.set(id - 1, data);
    }

    @Override
    public void delete(ID id) {
        this.menus.remove(id - 1);
    }

    @Override
    public void deleteAll() {
        this.menus.clear();
    };

    // public void findByMenu() {
    // menus.getKategori();
    // }
}
