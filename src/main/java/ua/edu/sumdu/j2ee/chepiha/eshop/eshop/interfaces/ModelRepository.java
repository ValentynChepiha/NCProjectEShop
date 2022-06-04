package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces;

import java.util.List;

public interface ModelRepository<T> extends ModelSelectRepository<T> {

    long create(T model);

    void update(T model);
    void delete(long id);
}
