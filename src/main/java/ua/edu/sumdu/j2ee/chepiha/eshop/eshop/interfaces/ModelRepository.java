package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces;

import java.util.List;

public interface ModelRepository<T> {

    void create(T model);
    void update(T model);
    void delete(long id);

    List<T> getAll();
    T getOne(long id);
}
