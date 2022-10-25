import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Color;
import  java.text.*;

import net.proteanit.sql.DbUtils;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import com.toedter.calendar.JDateChooser;

class ApplyLeaveTeacher extends JFrame implements ActionListener
{
    //2 combobox, 1 date chooser , 2 buttons
    JButton sumbitBtn, cancelBtn;
    JDateChooser dateChooser;
    JComboBox rollNoComboBox, durationComboBox;
    String []empIDList;
    ResultSet rs;
    Connection c;
    Statement st;
    JScrollPane scrollPane;
    JLabel mainLabel,empIdLabel,dateLabel,durationLabel;
    JDateChooser DOB;
    ApplyLeaveTeacher()
    {

        this.setTitle("Apply Teacher Leave");
        this.setFont(new Font("serif",Font.BOLD,50));
        this.setLayout(null);

        mainLabel=new JLabel("Apply Teacher Leave:");
        mainLabel.setBounds(120, 50, 250, 30);
        mainLabel.setFont(new Font("serif", Font.BOLD, 25));
        this.add(mainLabel);

        empIdLabel=new JLabel("EmpID:");
        empIdLabel.setBounds(80, 100, 250, 30);
        empIdLabel.setFont(new Font("serif", Font.BOLD, 20));
        this.add(empIdLabel);

        empIDList=getRollNoFromDB();
        rollNoComboBox=new JComboBox(empIDList);
        rollNoComboBox.setBounds(80,130,250,30);
        rollNoComboBox.addActionListener(this);
        this.add(rollNoComboBox);

        dateLabel=new JLabel("Date:");
        dateLabel.setBounds(80, 160, 250, 30);
        dateLabel.setFont(new Font("serif", Font.BOLD, 20));
        this.add(dateLabel);

        DOB=new JDateChooser();
        DOB.setBounds(80,190,250,30);
        this.add(DOB);

        durationLabel=new JLabel("Duration:");
        durationLabel.setBounds(80, 220, 250, 30);
        durationLabel.setFont(new Font("serif", Font.BOLD, 20));
        this.add(durationLabel);

        String[] durationList={"Full Day", "Half Day"};
        durationComboBox=new JComboBox(durationList);
        durationComboBox.setBounds(80,250,250,30);
        this.add(durationComboBox);

        sumbitBtn=new JButton("Submit");
        sumbitBtn.setBackground(Color.WHITE);
        sumbitBtn.setForeground(Color.BLACK);
        sumbitBtn.setBounds(100,350,120,30);
        sumbitBtn.setFont(new Font("serif", Font.PLAIN, 25));
        sumbitBtn.addActionListener(this);
        this.add(sumbitBtn);

        cancelBtn=new JButton("Back");
        cancelBtn.setBackground(Color.WHITE);
        cancelBtn.setForeground(Color.BLACK);
        cancelBtn.setBounds(280,350,120,30);
        cancelBtn.setFont(new Font("serif", Font.PLAIN, 25));
        cancelBtn.addActionListener(this);
        this.add(cancelBtn);
        
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource()==cancelBtn)
        {
            this.setVisible(false);
        }
        else if(a.getSource()==sumbitBtn)
        {
            insertTeacherLeaveIntoDB();
        }
    }
    String[] getRollNoFromDB()
    {
        String[] a={};
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
        return a;
    }
    public void insertTeacherLeaveIntoDB() 
    {
        String empID, date, duration;
        empID=(String)rollNoComboBox.getSelectedItem();
        date=DateFormat.getDateInstance().format(DOB.getDate());
        duration=(String)durationComboBox.getSelectedItem();
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            //name varchar(30), fatherName varchar(30),rollNo varchar (10),dob varchar(40),address varchar(100),phone varchar(30),email varchar(20),classX varchar(30),classXII varchar(30),aadhar varchar(30),course varchar(30),branch varchar(30));
            Statement st=c.createStatement();
            String selectAllQuery="insert into teacherleave values('"+empID+"','"+date+"','"+duration+"');";
            st.execute(selectAllQuery); 
            JOptionPane.showMessageDialog(null, "inserted Successfully");
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
        new ApplyLeaveTeacher();
    }
}