package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces;

import java.util.Optional;

public interface ModelUserRepository<T> extends ModelRepository<T> {

    void updateRole(T t);

    T getOneByName(String userName);

}
