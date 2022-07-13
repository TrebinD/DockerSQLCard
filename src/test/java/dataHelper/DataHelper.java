package dataHelper;

import com.github.javafaker.Faker;
import data.UserData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataHelper {
    private static Faker faker = new Faker();

    public static UserData validUser(){
        return new UserData("vasya","qwerty123");
    }

    public static String randomPass(){
        String ramndomPassword = faker.internet().password();
        return ramndomPassword;
    }

    public static String validVerifyCode(String login){
        String verifyCode = SQLHelper.getValidVerifyCode(login);
        return verifyCode;
    }

    public static String invalidVerifyCode(){
        int verifyCode = faker.number().numberBetween(100000,999999);
        return String.valueOf(verifyCode);
    }

    public static void assertStatus(String login){
        String expected = "blocked";
        String actual = SQLHelper.userStatus(login);
        assertEquals(expected,actual);

    }
}
