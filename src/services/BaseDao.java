package services;

import java.util.List;

public interface BaseDao<E, ID> {
    void save(E data);

    List<E> findAll();

    E findById(ID id);

    Integer getSize();

    void update(ID id, E data);

    void delete(ID id);

    void deleteAll();
}
