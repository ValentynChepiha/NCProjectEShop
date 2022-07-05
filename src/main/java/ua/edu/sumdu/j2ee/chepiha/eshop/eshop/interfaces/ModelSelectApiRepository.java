package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces;

import java.util.List;

public interface ModelSelectApiRepository<T> extends  ModelSelectRepository<T>{
    List<T> getQueryList(List<Long> listId);
}
