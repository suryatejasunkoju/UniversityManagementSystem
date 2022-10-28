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
    JTextField textField1,textField2,textField3,textField4,textField5,textField6,textField7,textField8,textField9,textField10,textField11,textField12;
    JLabel titleLabel,selectSemLabel,selectRollNoLabel, subjectLabel, marksLabel;
    JComboBox rollNoComboBox,semComboBox;
    String rollNoList[],semList[];
    ResultSet rs;
    Connection c;
    Statement st;
    EnterMarks()
    {
        this.setLayout(null);

        titleLabel=new JLabel("Enter Marks of the Student:-");
        titleLabel.setBounds(250,0,500,35);
        titleLabel.setFont(new Font("serif", Font.BOLD, 35));
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setForeground(Color.BLACK);
        this.add(titleLabel);

        //adding loginImage
        ImageIcon i=new ImageIcon("icons/exam.jpg");
        Image n=i.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon loginImage=new ImageIcon(n);
        JLabel imageLabel=new JLabel(loginImage);
        imageLabel.setBounds(500, 100, 400, 300);
        this.add(imageLabel);

        selectRollNoLabel=new JLabel("Select Roll No:");
        selectRollNoLabel.setBounds(50,60,180,30);
        selectRollNoLabel.setFont(new Font("serif",Font.BOLD,20));
        selectRollNoLabel.setForeground(Color.BLACK);
        selectRollNoLabel.setBackground(Color.WHITE);
        this.add(selectRollNoLabel);

        rollNoList=getRollNoFromDB();
        rollNoComboBox=new JComboBox(rollNoList);
        rollNoComboBox.setBounds(50,100,180,30);
        rollNoComboBox.addActionListener(this);
        this.add(rollNoComboBox);

        selectSemLabel=new JLabel("Select Semester:");
        selectSemLabel.setBounds(250,60,180,30);
        selectSemLabel.setFont(new Font("serif",Font.BOLD,20));
        selectSemLabel.setForeground(Color.BLACK);
        selectSemLabel.setBackground(Color.WHITE);
        this.add(selectSemLabel);

        semList=getSemFromDB();
        semComboBox=new JComboBox(semList);
        semComboBox.setBounds(250,100,180,30);
        semComboBox.addActionListener(this);
        this.add(semComboBox);

        subjectLabel=new JLabel("Subject Name:");
        subjectLabel.setBounds(50,150,180,30);
        subjectLabel.setFont(new Font("serif",Font.PLAIN,20));
        subjectLabel.setForeground(Color.BLACK);
        subjectLabel.setBackground(Color.WHITE);
        this.add(subjectLabel);

        marksLabel=new JLabel("Marks:");
        marksLabel.setBounds(300,150,180,30);
        marksLabel.setFont(new Font("serif",Font.PLAIN,20));
        marksLabel.setForeground(Color.BLACK);
        marksLabel.setBackground(Color.WHITE);
        this.add(marksLabel);

        textField1=new JTextField("");
        textField1.setFont(new Font("serif",Font.PLAIN,15));
        textField1.setBounds(50,200,180,30);
        textField1.addActionListener(this);
        this.add(textField1);

        textField2=new JTextField("");
        textField2.setFont(new Font("serif",Font.PLAIN,15));
        textField2.setBounds(50,250,180,30);
        textField2.addActionListener(this);
        this.add(textField2);

        textField3=new JTextField("");
        textField3.setFont(new Font("serif",Font.PLAIN,15));
        textField3.setBounds(50,300,180,30);
        textField3.addActionListener(this);
        this.add(textField3);

        textField4=new JTextField("");
        textField4.setFont(new Font("serif",Font.PLAIN,15));
        textField4.setBounds(50,350,180,30);
        textField4.addActionListener(this);
        this.add(textField4);

        textField5=new JTextField("");
        textField5.setFont(new Font("serif",Font.PLAIN,15));
        textField5.setBounds(50,400,180,30);
        textField5.addActionListener(this);
        this.add(textField5);

        textField6=new JTextField("");
        textField6.setFont(new Font("serif",Font.PLAIN,15));
        textField6.setBounds(50,450,180,30);
        textField6.addActionListener(this);
        this.add(textField6);

        textField7=new JTextField("");
        textField7.setFont(new Font("serif",Font.PLAIN,15));
        textField7.setBounds(300,200,100,30);
        textField7.addActionListener(this);
        this.add(textField7);

        textField8=new JTextField("");
        textField8.setFont(new Font("serif",Font.PLAIN,15));
        textField8.setBounds(300,250,100,30);
        textField8.addActionListener(this);
        this.add(textField8);

        textField9=new JTextField("");
        textField9.setFont(new Font("serif",Font.PLAIN,15));
        textField9.setBounds(300,300,100,30);
        textField9.addActionListener(this);
        this.add(textField9);

        textField10=new JTextField("");
        textField10.setFont(new Font("serif",Font.PLAIN,15));
        textField10.setBounds(300,350,100,30);
        textField10.addActionListener(this);
        this.add(textField10);

        textField11=new JTextField("");
        textField11.setFont(new Font("serif",Font.PLAIN,15));
        textField11.setBounds(300,400,100,30);
        textField11.addActionListener(this);
        this.add(textField11);

        textField12=new JTextField("");
        textField12.setFont(new Font("serif",Font.PLAIN,15));
        textField12.setBounds(300,450,100,30);
        textField12.addActionListener(this);
        this.add(textField12);

        //login button
        loginBtn=new JButton("Submit");
        loginBtn.setBounds(200,550,120,30);
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("serif", Font.BOLD, 20));
        loginBtn.addActionListener(this);
        this.add(loginBtn);

        //cancel button
        cancelBtn=new JButton("Cancel");
        cancelBtn.setBounds(600, 550, 120, 30);
        cancelBtn.setBackground(Color.BLACK);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("serif", Font.BOLD, 20));
        cancelBtn.addActionListener(this);
        this.add(cancelBtn);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void insertIntoDB() 
    {
        String rollNo,semester,marks1,marks2,marks3,marks4,marks5,marks6,subject1,subject2,subject3,subject4,subject5,subject6;
       
        rollNo=(String)rollNoComboBox.getSelectedItem();
        semester=(String)semComboBox.getSelectedItem();

        marks1=textField7.getText();
        marks2=textField8.getText();
        marks3=textField9.getText();
        marks4=textField10.getText();
        marks5=textField11.getText();
        marks6=textField12.getText();

        subject1=textField1.getText();
        subject2=textField2.getText();
        subject3=textField3.getText();
        subject4=textField4.getText();
        subject5=textField5.getText();
        subject6=textField6.getText();
        try 
        {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            st=c.createStatement();
            
            String insertMarks="insert into marks values('"+rollNo+"','"+semester+"','"+marks1+"','"+marks2+"','"+marks3+"','"+marks4+"','"+marks5+"','"+marks6+"');";
            System.out.println(insertMarks);
            st.execute(insertMarks);
            
            String insertSubjects="insert into subject values('"+rollNo+"','"+semester+"','"+subject1+"','"+subject2+"','"+subject3+"','"+subject4+"','"+subject5+"','"+subject6+"');";
            System.out.println(insertSubjects);
            st.execute(insertSubjects);
            JOptionPane.showMessageDialog(null, "Updated Successfully");
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
        //adding functionality for loginBtn
        if(a.getSource()==loginBtn)
        {
            //submit button
            insertIntoDB();
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
        String[] a={"1st semester","2nd semester","3rd semester","4th semester","5th semester","6th semester","7th semester","8th semester"};
        return a;
    }
    public static void main(String[] args) 
    {
        new EnterMarks();
    }
}