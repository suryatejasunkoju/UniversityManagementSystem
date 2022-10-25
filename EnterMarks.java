import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

class EnterMarks extends JFrame implements ActionListener
{
    JButton loginBtn,cancelBtn;
    JTextField textField1;
    JLabel nameLabel;
    JComboBox rollNoComboBox,semComboBox;
    String rollNoList[],semList[];

    ResultSet rs;
    Connection c;
    Statement st;
    EnterMarks()
    {
        this.setLayout(null);
        nameLabel=new JLabel("Enter Marks of the Student:-");
        nameLabel.setBounds(250,0,500,35);
        nameLabel.setFont(new Font("serif", Font.BOLD, 35));
        nameLabel.setBackground(Color.WHITE);
        nameLabel.setForeground(Color.BLACK);
        this.add(nameLabel);

        //adding loginImage
        ImageIcon i=new ImageIcon("icons/exam.jpg");
        Image n=i.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        ImageIcon loginImage=new ImageIcon(n);
        JLabel imageLabel=new JLabel(loginImage);
        imageLabel.setBounds(600, 100, 300, 200);
        this.add(imageLabel);

        rollNoList=getRollNoFromDB();
        rollNoComboBox=new JComboBox(rollNoList);
        rollNoComboBox.setBounds(50,80,180,30);
        rollNoComboBox.addActionListener(this);
        this.add(rollNoComboBox);

        semList=getSemFromDB();
        semComboBox=new JComboBox(semList);
        semComboBox.setBounds(250,80,180,30);
        semComboBox.addActionListener(this);
        this.add(semComboBox);

        //login button
        loginBtn=new JButton("Search");
        loginBtn.setBounds(150,350,120,30);
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("serif", Font.BOLD, 18));
        loginBtn.addActionListener(this);
        this.add(loginBtn);

        //cancel button
        cancelBtn=new JButton("Cancel");
        cancelBtn.setBounds(350, 350, 120, 30);
        cancelBtn.setBackground(Color.BLACK);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("serif", Font.BOLD, 18));
        cancelBtn.addActionListener(this);
        this.add(cancelBtn);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent a)
    {
        //adding functionality for loginBtn
        if(a.getSource()==loginBtn)
        {
            //submit
            
        }
        else if(a.getSource()==cancelBtn) 
        {
            this.setVisible(false);
        }
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
    public String[] getSemFromDB() 
    {
        String[] a={};
        
        return a;
    }
    public static void main(String[] args) 
    {
        new EnterMarks();
    }
}