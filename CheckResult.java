import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
import javax.swing.JComboBox;
import net.proteanit.sql.DbUtils;

import java.util.ArrayList;

class CheckResult extends JFrame implements ActionListener
{
    
    ResultSet rs;
    Connection c;
    Statement st;
    JLabel rollNoLabel,nameLabel,selectSemLabel,selectRollNoLabel;
    JComboBox rollNoComboBox,semComboBox;
    JButton loginBtn, cancelBtn;
    JPanel panel;
    JLabel marksLabel,marksLabel1,marksLabel2,marksLabel3,marksLabel4,marksLabel5,marksLabel6;
    JLabel subLabel,subLabel1,subLabel2,subLabel3,subLabel4,subLabel5,subLabel6;
    String sub1="",sub2="",sub3="",sub4="",sub5="",sub6="",marks1="",marks2="",marks3="",marks4="",marks5="",marks6="";
    CheckResult()
    {
        this.setLayout(null);
        nameLabel=new JLabel("Check Result:-");
        nameLabel.setBounds(350,0,400,35);
        nameLabel.setFont(new Font("serif", Font.BOLD, 35));
        nameLabel.setBackground(Color.WHITE);
        nameLabel.setForeground(Color.BLACK);
        this.add(nameLabel);

        selectRollNoLabel=new JLabel("Select Roll No:");
        selectRollNoLabel.setBounds(50,60,180,30);
        selectRollNoLabel.setFont(new Font("serif",Font.BOLD,20));
        selectRollNoLabel.setForeground(Color.BLACK);
        selectRollNoLabel.setBackground(Color.WHITE);
        this.add(selectRollNoLabel);

        rollNoComboBox=new JComboBox(getRollNoFromDB());
        rollNoComboBox.setBounds(50,100,180,30);
        rollNoComboBox.addActionListener(this);
        this.add(rollNoComboBox);

        selectSemLabel=new JLabel("Select Semester:");
        selectSemLabel.setBounds(250,60,180,30);
        selectSemLabel.setFont(new Font("serif",Font.BOLD,20));
        selectSemLabel.setForeground(Color.BLACK);
        selectSemLabel.setBackground(Color.WHITE);
        this.add(selectSemLabel);

        semComboBox=new JComboBox(getSemFromDB());
        semComboBox.setBounds(250,100,180,30);
        semComboBox.addActionListener(this);
        this.add(semComboBox);

        //login button
        loginBtn=new JButton("Search");
        loginBtn.setBounds(100,200,100,30);
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("serif", Font.BOLD, 18));
        loginBtn.addActionListener(this);
        this.add(loginBtn);

        //cancel button
        cancelBtn=new JButton("Back");
        cancelBtn.setBounds(300,200,100,30);
        cancelBtn.setBackground(Color.BLACK);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("serif", Font.BOLD, 18));
        cancelBtn.addActionListener(this);
        this.add(cancelBtn);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1050,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent a) 
    {
        if(a.getSource()==cancelBtn)
        {
            this.setVisible(false);
        }
        else if(a.getSource()==loginBtn)
        {
            displayResult();
        }
        return;     
    }
    // public void Result() 
    // {
    //     JFrame result=new JFrame();

    // }
    public void displayResult() 
    {
        try 
        {

            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            st=c.createStatement();
            String rollNo=(String)rollNoComboBox.getSelectedItem(),semester=(String)semComboBox.getSelectedItem();
            
            String selectQuery="select *from subject where rollNo='"+rollNo+"' AND semester='"+semester+"';";
            rs=st.executeQuery(selectQuery);  
            
            rs.next();
            // String sub1="",sub2="",sub3="",sub4="",sub5="",sub6="",marks1="",marks2="",marks3="",marks4="",marks5="",marks6="";
            sub1=rs.getString(3);
            sub2=rs.getString(4);
            sub3=rs.getString(5);
            sub4=rs.getString(6);
            sub5=rs.getString(7);
            sub6=rs.getString(8);

            System.out.println(sub1+","+sub2+","+sub3+","+sub4+","+sub5+","+sub6);
            selectQuery="select *from marks where rollNo='"+rollNo+"' AND semester='"+semester+"';";
            // System.out.println(selectQuery);
            rs=st.executeQuery(selectQuery);  

            rs.next();

            marks1=rs.getString(3);
            marks2=rs.getString(4);
            marks3=rs.getString(5);
            marks4=rs.getString(6);
            marks5=rs.getString(7);
            marks6=rs.getString(8);
            
            System.out.println(marks1+","+marks2+","+marks3+","+marks4+","+marks5+","+marks6);
            //values are displaying

            // JLabel marksLabel1,marksLabel2,marksLabel3,marksLabel4,marksLabel5,marksLabel6;
            // JLabel subLabel1,subLabel2,subLabel3,subLabel4,subLabel5,subLabel6;
            
            subLabel=new JLabel("Subject");
            subLabel.setBounds(500,50,180,30);
            subLabel.setFont(new Font("serif",Font.BOLD,20));
            subLabel.setForeground(Color.BLACK);
            subLabel.setBackground(Color.WHITE);
            this.add(subLabel); 

            marksLabel=new JLabel("Marks");
            marksLabel.setBounds(650,50,180,30);
            marksLabel.setFont(new Font("serif",Font.BOLD,20));
            marksLabel.setForeground(Color.BLACK);
            marksLabel.setBackground(Color.WHITE);
            this.add(marksLabel); 

            try 
            {
                this.remove(subLabel1);
                this.remove(subLabel2);
                this.remove(subLabel3);
                this.remove(subLabel4);
                this.remove(subLabel5);
                this.remove(subLabel6);
                this.remove(marksLabel1);
                this.remove(marksLabel2);
                this.remove(marksLabel3);
                this.remove(marksLabel4);
                this.remove(marksLabel5);
                this.remove(marksLabel6);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            subLabel1=new JLabel(""+sub1);
            subLabel1.setBounds(500,100,180,30);
            subLabel1.setFont(new Font("serif",Font.PLAIN,20));
            subLabel1.setForeground(Color.BLACK);
            subLabel1.setBackground(Color.WHITE);
            this.add(subLabel1); 
            // result.add(subLabel1);

            subLabel2=new JLabel(""+sub2);
            subLabel2.setBounds(500,130,180,30);
            subLabel2.setFont(new Font("serif",Font.PLAIN,20));
            subLabel2.setForeground(Color.BLACK);
            subLabel2.setBackground(Color.WHITE);
            this.add(subLabel2);
            // result.add(subLabel2);

            subLabel3=new JLabel(""+sub3);
            subLabel3.setBounds(500,160,180,30);
            subLabel3.setFont(new Font("serif",Font.PLAIN,20));
            subLabel3.setForeground(Color.BLACK);
            subLabel3.setBackground(Color.WHITE);
            this.add(subLabel3);
            // result.add(subLabel3);

            subLabel4=new JLabel(""+sub4);
            subLabel4.setBounds(500,190,180,30);
            subLabel4.setFont(new Font("serif",Font.PLAIN,20));
            subLabel4.setForeground(Color.BLACK);
            subLabel4.setBackground(Color.WHITE);
            this.add(subLabel4);
            // result.add(subLabel4);

            subLabel5=new JLabel(""+sub5);
            subLabel5.setBounds(500,220,180,30);
            subLabel5.setFont(new Font("serif",Font.PLAIN,20));
            subLabel5.setForeground(Color.BLACK);
            subLabel5.setBackground(Color.WHITE);
            this.add(subLabel5);
            // result.add(subLabel5);
            
            subLabel6=new JLabel(""+sub6);
            subLabel6.setBounds(500,250,180,30);
            subLabel6.setFont(new Font("serif",Font.PLAIN,20));
            subLabel6.setForeground(Color.BLACK);
            subLabel6.setBackground(Color.WHITE);
            this.add(subLabel6);
            // result.add(subLabel6);
            
            marksLabel1=new JLabel(":   "+marks1);
            marksLabel1.setBounds(650,100,180,30);
            marksLabel1.setFont(new Font("serif",Font.PLAIN,20));
            marksLabel1.setForeground(Color.BLACK);
            marksLabel1.setBackground(Color.WHITE);
            this.add(marksLabel1);
            // result.add(marksLabel1);

            marksLabel2=new JLabel(":   "+marks2);
            marksLabel2.setBounds(650,130,180,30);
            marksLabel2.setFont(new Font("serif",Font.PLAIN,20));
            marksLabel2.setForeground(Color.BLACK);
            marksLabel2.setBackground(Color.WHITE);
            this.add(marksLabel2);
            

            marksLabel3=new JLabel(":   "+marks3);
            marksLabel3.setBounds(650,160,180,30);
            marksLabel3.setFont(new Font("serif",Font.PLAIN,20));
            marksLabel3.setForeground(Color.BLACK);
            marksLabel3.setBackground(Color.WHITE);
            this.add(marksLabel3);
            

            marksLabel4=new JLabel(":   "+marks4);
            marksLabel4.setBounds(650,190,180,30);
            marksLabel4.setFont(new Font("serif",Font.PLAIN,20));
            marksLabel4.setForeground(Color.BLACK);
            marksLabel4.setBackground(Color.WHITE);
            this.add(marksLabel4);

            marksLabel5=new JLabel(":   "+marks5);
            marksLabel5.setBounds(650,220,180,30);
            marksLabel5.setFont(new Font("serif",Font.PLAIN,20));
            marksLabel5.setForeground(Color.BLACK);
            marksLabel5.setBackground(Color.WHITE);
            this.add(marksLabel5);

            marksLabel6=new JLabel(":   "+marks6);
            marksLabel6.setBounds(650,250,180,30);
            marksLabel6.setFont(new Font("serif",Font.PLAIN,20));
            marksLabel6.setForeground(Color.BLACK);
            marksLabel6.setBackground(Color.WHITE);
            this.add(marksLabel6);

            this.setVisible(false);
            this.setVisible(true);

            c.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    public String[] getSemFromDB() 
    {
        String[] a={"1st semester","2nd semester","3rd semester","4th semester","5th semester","6th semester","7th semester","8th semester"};
        return a;
    }
    String[] getRollNoFromDB()
    {
        String a[]={};
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            //name varchar(30), fatherName varchar(30),rollNo varchar (10),dob varchar(40),address varchar(100),phone varchar(30),email varchar(20),classX varchar(30),classXII varchar(30),aadhar varchar(30),course varchar(30),branch varchar(30));
            st=c.createStatement();
            String selectAllQuery="select rollNo from student;";
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
                System.out.print(a[i]+" ");
            }
            System.out.println();
            System.out.println(s);
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
    public static void main(String[] args) 
    {
        new CheckResult();    
    }
}

