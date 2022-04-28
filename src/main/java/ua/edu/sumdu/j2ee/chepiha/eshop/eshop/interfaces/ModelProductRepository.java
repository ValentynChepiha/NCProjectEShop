package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces;

import java.util.List;

public interface ModelProductRepository<T> extends ModelRepository<T> {

    List<T> getAllWithoutOneId(long oneId);
    void updateCount(long id, int count);

}
