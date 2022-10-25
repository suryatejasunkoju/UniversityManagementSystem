import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JScrollPane;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;

import net.proteanit.sql.DbUtils;

class FeeStructure extends JFrame implements ActionListener
{
    ResultSet rs;
    Connection c;
    Statement st;
    JTable table;
    JLabel nameLabel;
    JScrollPane scrollPane;
    JButton backBtn;
    FeeStructure()
    {
        this.setLayout(null);
        nameLabel=new JLabel("Fee Structure:-");
        nameLabel.setBounds(350,0,280,35);
        nameLabel.setFont(new Font("serif", Font.BOLD, 35));
        this.add(nameLabel);

        buildAndDisplayFeeTable();

        backBtn=new JButton("Back");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBounds(650,50,120,30);
        backBtn.setFont(new Font("serif", Font.PLAIN, 25));
        backBtn.addActionListener(this);
        this.add(backBtn);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1050,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void buildAndDisplayFeeTable() 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            st=c.createStatement();
            String selectQuery="select *from fee;";
            rs=st.executeQuery(selectQuery);  
            
            // while (rs.next()) 
            // {
            //     System.out.println("["+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+"]");    
            // }
            //values are displaying

            table =new JTable();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.setBounds(100,110,1050,200);
            scrollPane=new JScrollPane(table);
            scrollPane.setBounds(0, 110, 1000, 150);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            this.add(scrollPane);
            c.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource()==backBtn)
        {
            this.setVisible(false);
        }
    }
    public static void main(String[] args) 
    {
        new FeeStructure();
    }
}
