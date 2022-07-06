package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

import org.springframework.stereotype.Service;

@Service
public class ValidateService {

    public static boolean validateString(String string){
        return string != null && string.trim().length() > 0;
    }

}
