package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces;

public interface ModelUserRepository<T> extends ModelRepository<T> {

    void updateRole(T t);

}
