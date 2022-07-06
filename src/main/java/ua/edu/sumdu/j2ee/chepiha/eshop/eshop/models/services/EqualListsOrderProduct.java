package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.OrderProduct;

import java.util.List;
import java.util.Objects;

public class EqualListsOrderProduct {

    public static boolean compare(List<OrderProduct> listFirst, List<OrderProduct> listSecond) {
        if (listFirst == null || listSecond == null) {
            return false;
        }

        if (listFirst.size() != listSecond.size()) {
            return false;
        }

        boolean result =true;
        for (int i=0; i<=listFirst.size(); i++) {
            if ( Objects.equals(listFirst.get(i), listSecond.get(i)) ) {
                continue;
            }
            result = false;
            break;
        }

        return result;
    }

}
