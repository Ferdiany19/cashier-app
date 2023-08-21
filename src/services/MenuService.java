package services;

import java.util.List;

import models.Menu;

public interface MenuService {
    void createMenu(Menu menu);

    List<Menu> getAllMenus();

    Menu getMenuById(Integer id);

    Integer getMenuNumber();

    void updateMenu(Integer id, Menu menu);

    void deleteMenu(Integer id);

    void deleteAllMenus();

}
