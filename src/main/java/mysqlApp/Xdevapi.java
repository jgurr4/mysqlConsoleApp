package mysqlApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.xdevapi.*;

public class Xdevapi {

    private static final Logger LOGGER = LoggerFactory.getLogger(Xdevapi.class);

    public static String documentHandler(String name, String password) throws SQLException, IOException {

        Properties config = new Properties();
        config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        String xurl = config.getProperty("xurl");
        String xuser = config.getProperty("xuser");
        String xpass = config.getProperty("xpass");

        Session sess = null;
        Client cli = null;
        SqlResult rs = null;

        try {
            ClientFactory cf = new ClientFactory();
            cli = cf.getClient("" + xurl + xuser + xpass, "{\"pooling\":{\"enabled\":true, \"maxSize\":8, \"maxIdleTime\":30000, \"queueTimeout\":10000} }");
            sess = cli.getSession();
            try {
                Schema db = sess.getSchema("documentstore");
                Collection myColl = db.createCollection("contacts", true);
            /* single connection method with no connection pooling:
            Session mySession = new SessionFactory().getSession("" + xurl + xuser + xpass);
             */

                // Retrieve user if exists. If not create user.
                rs = sess.sql("Select * from contacts where name = " + name).execute();
                Row row1 = rs.fetchOne();
                System.out.println("" + row1);
                // Also try:
                // DbDoc doc = myColl.getOne(name);

                if (row1 == null) {
                    AddStatement myAdd = myColl.add("name = :param1 AND password = :param2");
                    AddResult addRes = myAdd.bind("param1", name).bind("param2", password).execute();
                    System.out.println("Add Document result is:" + addRes);

                    // I don't know how to use variables with this method:
                    // myColl.add("{\"name\":\"Lukas\", \"age\":32}").execute();

                /* However this method works good:
                DbDoc myDoc = myColl.newDoc();
                myDoc.add("name", new JsonString().setValue(name));
                myDoc.add("password", new JsonString().setValue(password));
                myColl.add(myDoc);
                 */
                }

                // Delete user from collection:
                Result myRemove = myColl.remove(name).execute();
                System.out.println("Remove Document result is: " + myRemove);

                return myRemove.toString();

            } catch (XDevAPIError err) { // special exception class for server errors
                System.err.println("The following error occurred: " + err.getMessage());
            } finally {
                cli.close();
                sess.close();
            }
        } catch (Exception err){
            System.err.println("The database session could not be opened: " + err.getMessage());
        }
        return rs.toString();
    }
}
