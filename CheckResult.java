import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;

import net.proteanit.sql.DbUtils;

class CheckResult extends JFrame implements ActionListener
{
    
    ResultSet rs;
    Connection c;
    Statement st;
    JTable table;
    JLabel nameLabel;
    JScrollPane scrollPane;
    JButton loginBtn, cancelBtn;
    CheckResult()
    {
        this.setLayout(null);
        nameLabel=new JLabel("Check Result:-");
        nameLabel.setBounds(350,0,400,35);
        nameLabel.setFont(new Font("serif", Font.BOLD, 35));
        nameLabel.setBackground(Color.WHITE);
        nameLabel.setForeground(Color.BLACK);
        this.add(nameLabel);

        //login button
        loginBtn=new JButton("Search");
        loginBtn.setBounds(80,70,100,30);
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("serif", Font.BOLD, 18));
        loginBtn.addActionListener(this);
        this.add(loginBtn);

        //cancel button
        cancelBtn=new JButton("Back");
        cancelBtn.setBounds(200,70,100,30);
        cancelBtn.setBackground(Color.BLACK);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("serif", Font.BOLD, 18));
        cancelBtn.addActionListener(this);
        this.add(cancelBtn);

        // buildAndDisplayFeeTable();

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
            table.setBounds(100,50,1050,200);
            scrollPane=new JScrollPane(table);
            scrollPane.setBounds(0, 60, 1000, 150);
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
    public static void main(String[] args) 
    {
        new CheckResult();    
    }
}

