package one;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * RMI driver doing the lookup operations
 */

public class Main2 {
    public static void main(String []args) throws ClassNotFoundException {


        try {
            java.sql.Driver myDriver = (java.sql.Driver)
                    Class.forName("weblogic.jdbc.rmi.Driver").newInstance();//  using type3 driver explicitly
            String url =	"jdbc:weblogic:rmi";
            java.util.Properties props = new java.util.Properties();
            props.put("weblogic.server.url", "t3://localhost:7001");
            props.put("weblogic.jdbc.datasource", "firstDataSource");
            java.sql.Connection conn = myDriver.connect(url, props);

            Statement stmt = conn.createStatement();
            stmt.execute("select * from actor");
            ResultSet rs = stmt.getResultSet();

            while(rs.next())
            {
                System.out.println(rs.getString(1));
            }
            stmt.close();
            conn.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    }
