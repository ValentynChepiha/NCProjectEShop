package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListEquals<T> {

    public boolean compare(List<T> listFirst, List<T> listSecond) {
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
