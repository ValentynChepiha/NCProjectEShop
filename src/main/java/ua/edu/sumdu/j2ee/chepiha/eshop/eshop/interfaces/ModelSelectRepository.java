package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces;

import java.util.List;

public interface ModelSelectRepository<T> {
    List<T> getAll();
    T getOne(long id);
}
