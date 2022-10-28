import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
class TestDB
{
    // Statement s;
    TestDB() 
    {
        try 
        {
            // s = c.createStatement();
            // String query="select * from login;";
            // ResultSet rs=s.executeQuery(query);
            // Connection c=ConnectionProvider.getCon();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            // Statement st=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Statement st=c.createStatement();
            String insertQuery="insert into login values('"+"surya"+"','"+"suridu');";
            String selectAllQuery="select *from login;";
            // ResultSet rs=st.executeQuery();
            // ResultSet rs=st.executeQuery(query);  
            st.execute(insertQuery);
            ResultSet rs=st.executeQuery(selectAllQuery);  
            while(rs.next())
            {
                System.out.println(rs.getString(1)+"  "+rs.getString(2));
                // c.close();  
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    public static void main(String[] args) 
    {
        new TestDB();
    }
}