package services;

import java.util.List;

import dao.MenuDao;
import models.Menu;

public class MenuServiceImp implements MenuService {

    MenuDao menuDao;

    public MenuServiceImp(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public MenuServiceImp() {
    }

    @Override
    public void createMenu(Menu menu) {
        menuDao.save(menu);
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuDao.findAll();
    }

    @Override
    public Menu getMenuById(Integer id) {
        return menuDao.findById(id);
    }

    @Override
    public Integer getMenuNumber() {
        return menuDao.getSize();
    }

    @Override
    public void updateMenu(Integer id, Menu menu) {
        menuDao.update(id, menu);
    }

    @Override
    public void deleteMenu(Integer id) {
        menuDao.delete(id);
    }

    @Override
    public void deleteAllMenus() {
        menuDao.deleteAll();
    }

}
