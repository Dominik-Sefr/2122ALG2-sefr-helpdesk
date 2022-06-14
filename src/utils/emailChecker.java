package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * třída používá regex pro kontrolu emailu
 */
public class emailChecker {
    /**
     * konstruktor
     */
    public emailChecker(){}

    /**
     * kontroluje zda je email validní
     * @param email
     * @return boolean
     */
    public Boolean isValid(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
