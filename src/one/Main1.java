package one;
//Type 3 driver is internally being used by weblogic server
/**
 * All naming operations are relative to a context. The initial context implements the Context interface and provides the starting point for resolution of names.
 */

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

public class Main1 {
    public static void main(String []args)
    {
        Context ctx = null;
        Hashtable ht = new Hashtable();
        ht.put(Context.INITIAL_CONTEXT_FACTORY,
                "weblogic.jndi.WLInitialContextFactory");
        ht.put(Context.PROVIDER_URL,
                "t3://localhost:7001"); //t3 is a propriety protocol
        try {
            ctx = new InitialContext(ht);
            //firstDataSource is alias or JNDI name
            javax.sql.DataSource ds
                    = (javax.sql.DataSource) ctx.lookup ("firstDataSource");//JNDI lookup
            java.sql.Connection conn = ds.getConnection();

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
        catch (NamingException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {ctx.close();}
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
