import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class Login extends JFrame implements ActionListener
{
    JLabel userNameLabel, userPasswordLabel;
    JTextField textField1;
    JPasswordField passwordField;
    JButton loginBtn, cancelBtn;
    Login()
    {
        
        this.getContentPane().setBackground(Color.white);
        this.setLayout(null);

        //username text
        userNameLabel=new JLabel("UserName:");
        userNameLabel.setBounds(60, 20, 100, 20);
        userNameLabel.setFont(new Font("serif", Font.BOLD, 20));
        this.add(userNameLabel);

        //username text field- to take input data
        textField1=new JTextField();
        textField1.setBounds(180, 20, 150, 30);
        textField1.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(textField1);

        //password text
        userPasswordLabel=new JLabel("Password:");
        userPasswordLabel.setBounds(60,60,100,20);
        userPasswordLabel.setFont(new Font("serif", Font.BOLD, 20));
        this.add(userPasswordLabel);

        //username text field- to take input data
        passwordField=new JPasswordField();
        passwordField.setBounds(180, 60, 150, 30);
        passwordField.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(passwordField);

        //login button
        loginBtn=new JButton("Login");
        loginBtn.setBounds(40,140,120,30);
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("serif", Font.BOLD, 18));
        loginBtn.addActionListener(this);
        this.add(loginBtn);

        //cancel button
        cancelBtn=new JButton("Cancel");
        cancelBtn.setBounds(220, 140, 120, 30);
        cancelBtn.setBackground(Color.BLACK);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("serif", Font.BOLD, 18));
        cancelBtn.addActionListener(this);
        this.add(cancelBtn);

        //adding loginImage
        ImageIcon i=new ImageIcon("icons/second.jpg");
        Image n=i.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon loginImage=new ImageIcon(n);
        JLabel imageLabel=new JLabel(loginImage);
        imageLabel.setBounds(400, 30, 100, 100);
        this.add(imageLabel);

        
        this.setSize(550, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // new Project();
    }
    public boolean isVerifiedAccount()
    {
        try 
        {
            String userName=textField1.getText (),password=passwordField.getText ();
            // System.out.println(userName+","+password);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            // Statement st=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Statement st=c.createStatement();
            String selectQuery="select *from adminLogin where username='"+userName+"'AND  password='"+password+"';";
            ResultSet rs=st.executeQuery(selectQuery);   
            if(rs.next())
            {
                this.setVisible(false);
                c.close();  
                return true;
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "You are not Verified!!!. Please Contact Admin");
                c.close();
                return false;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString());
            return false; 
        }  
    }
    public void actionPerformed(ActionEvent a)
    {
        //adding functionality for loginBtn
        if(a.getSource()==loginBtn)
        {
            // System.out.println("source="+a.getSource().getClass()+", Button=loginBtn");
            if(isVerifiedAccount())
            {
                new Project();
            }
            else 
            {
                this.setVisible(false);
                System.exit(ABORT);
            }
        }
        //adding functionality for cacelBtn
        else if(a.getSource()==cancelBtn)
        {
            System.out.println("source="+a.getSource().getClass()+", Button=submitBtn");
            System.exit(ABORT);  
        }
    }
    public static void main(String[] args) 
    {
        new Login();
    }
}