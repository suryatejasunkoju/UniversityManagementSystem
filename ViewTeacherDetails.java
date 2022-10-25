import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;

import net.proteanit.sql.DbUtils;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
class ViewTeacherDetails extends JFrame implements ActionListener
{
    JComboBox rollNoComboBox;
    JTable table;
    ResultSet rs;
    JButton searchBtn, backBtn;
    JLabel searchLabel;
    String[] empIDList;
    JScrollPane scrollPane;
    ViewTeacherDetails()
    {
        this.setLayout(null);
        searchLabel=new JLabel("Select EmpID to Search:");
        searchLabel.setBounds(80, 50, 250, 30);
        searchLabel.setFont(new Font("serif", Font.BOLD, 20));
        this.add(searchLabel);

        empIDList=getEmpIDFromDB();
        rollNoComboBox=new JComboBox(empIDList);
        rollNoComboBox.setBounds(320,50,150,30);
        rollNoComboBox.addActionListener(this);
        this.add(rollNoComboBox);

        searchBtn=new JButton("Search");
        searchBtn.setBackground(Color.WHITE);
        searchBtn.setForeground(Color.BLACK);
        searchBtn.setBounds(500,50,150,30);
        searchBtn.setFont(new Font("serif", Font.PLAIN, 25));
        searchBtn.addActionListener(this);
        this.add(searchBtn);

        backBtn=new JButton("Back");
        backBtn.setBackground(Color.WHITE);
        backBtn.setForeground(Color.BLACK);
        backBtn.setBounds(700,50,120,30);
        backBtn.setFont(new Font("serif", Font.PLAIN, 25));
        backBtn.addActionListener(this);
        this.add(backBtn);


        this.setSize(1000,750);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    String[] getEmpIDFromDB()
    {
        String[] a;
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            //name varchar(30), fatherName varchar(30),rollNo varchar (10),dob varchar(40),address varchar(100),phone varchar(30),email varchar(20),classX varchar(30),classXII varchar(30),aadhar varchar(30),course varchar(30),branch varchar(30));
            Statement st=c.createStatement();
            String selectAllQuery="select empID from teacher;";
            rs=st.executeQuery(selectAllQuery); 
            ArrayList<String> s=new ArrayList<>();
            int size=0;
            while (rs.next()) 
            {
                size++;
                s.add(rs.getString(1));    
            } 
            a=new String[size];
            for(int i =0;i<size;i++)
            {
                a[i] = s.get(i);
            }
            c.close();
            return a;
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString());
        }
        a=null;
        return a;
    }
    public void TeacherDetailsTable() 
    {
        String empID=(String)rollNoComboBox.getSelectedItem();
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            //name varchar(30), fatherName varchar(30),rollNo varchar (10),dob varchar(40),address varchar(100),phone varchar(30),email varchar(20),classX varchar(30),classXII varchar(30),aadhar varchar(30),course varchar(30),branch varchar(30));
            Statement st=c.createStatement();
            String selectAllQuery="select *from teacher where empID='"+empID+"';";
            rs=st.executeQuery(selectAllQuery);  

            // while (rs.next()) 
            // {
            //     System.out.println(rs.getString(2));  
            // }
            
            table =new JTable();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.setBounds(10,160,950,400);
            table.setFont(new Font("serif", Font.PLAIN, 15));
            scrollPane=new JScrollPane(table);
            scrollPane.setBounds(10, 160, 950, 300);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
        if(a.getSource()==searchBtn)
        {
            TeacherDetailsTable();
        }
        else if(a.getSource()==backBtn)
        {
            this.setVisible(false);
        }
    }
    public static void main(String[] args) 
    {
        new ViewTeacherDetails();
    }
}