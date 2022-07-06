package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services;

public class ValidateString {

    public static boolean validateString(String string){
        return string != null && string.trim().length() > 0;
    }

}
