package com.musicalist.intermediator.intermediator.Extra;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificarCorreo {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }   
}
