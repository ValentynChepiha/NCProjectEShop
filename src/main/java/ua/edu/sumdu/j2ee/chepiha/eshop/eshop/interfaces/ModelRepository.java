package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces;

public interface ModelRepository<T> extends ModelSelectRepository<T> {

    long create(T model);

    void update(T model);
    void delete(long id);
}
