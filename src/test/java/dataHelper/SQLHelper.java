package dataHelper;

import com.google.gson.internal.bind.JsonTreeReader;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.management.Query;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private static QueryRunner queryRunner;
    private  static Connection connection;

    @SneakyThrows
    public static void setup(){
        queryRunner= new QueryRunner();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app","pass");
    }

    @SneakyThrows
    public static void resetStatus(String login){
        setup();
        String data = "UPDATE users SET status = 'active' WHERE login = ?;";
        queryRunner.update(connection,data,login);
    }
    @SneakyThrows
    public static String getValidVerifyCode(String login){
        setup();
        String data = "SELECT code FROM auth_codes JOIN users ON user_id = users.id WHERE login = ? ORDER BY created DESC LIMIT 1;";
        return queryRunner.query(connection,data,new ScalarHandler<>(),login);
    }

    @SneakyThrows
    public static void resetVerifyCode(){
        setup();
        String data = "DELETE FROM auth_codes;";
        queryRunner.update(connection,data);
    }

    @SneakyThrows
    public static String userStatus(String login){
        setup();
        String data = "SELECT status FROM users WHERE login = ?;";
        return queryRunner.query(connection,data,new ScalarHandler<>(),login);
    }

}
