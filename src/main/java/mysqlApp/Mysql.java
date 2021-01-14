package mysqlApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;


public class Mysql {


    private static final Logger LOGGER = LoggerFactory.getLogger(Mysql.class);

    public static String handleInput(String biome) throws SQLException, IOException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<String> abilities = new ArrayList<String>();
        Properties config = new Properties();
        config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

        String url = config.getProperty("url");
        String username = config.getProperty("username");
        String password = config.getProperty("password");


        try {
            conn = DriverManager.getConnection("" + url + username + password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM monsterList where biome = " + biome);

            //Non-select statements use stmt.execute():
//            if (stmt.execute("SELECT foo FROM bar")) {
//                rs = stmt.getResultSet();
//            }
            // Now do something with the ResultSet ....
            rs.next();
            String mName = rs.getString("monster_name");
            String size = rs.getString(3);
            String exp = rs.getString("exp");
            int hp = rs.getInt("hp");
            int def = rs.getInt("defense");
            String weakTo = rs.getString("weakness");
            String immunity = rs.getString("strong against");
            String levelRange = rs.getString("level range");
            String desc = rs.getString("description");
            abilities.addAll(Arrays.asList(rs.getString("physical a"), rs.getString("physical b"), rs.getString("magical a"), rs.getString("magical b"), rs.getString("special")));
            String result = mName + ", " + size + ", " + exp + ", " + hp + ", " + def + ", " + weakTo + ", " + immunity
                    + ", " + levelRange + ", " + desc + ", " + abilities;
            System.out.println(result);
            return result;

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
        return rs.toString();
    }
}