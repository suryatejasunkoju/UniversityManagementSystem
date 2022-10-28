//mySql password:root
import java.sql.Connection;//Connection is an interface
import java.sql.DriverManager;
class ConnectingDB
{
    Connection c;//reference of Interface but not an object
    ConnectingDB()
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/");
        } 
        catch (Exception e) 
        {
            System.out.println("While Connecting Database we got some problem");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) 
    {
        new Connection();
    }
}