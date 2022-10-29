import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
class PayFee  extends JFrame implements ActionListener
{
    JButton payBtn, cancelBtn,updateFeeBtn;
    JComboBox rollNoComboBox,semComboBox,courseComboBox;
    JLabel mainLabel,rollNoLabel,semLabel,courseLabel,nameLabel,fatherNameLabel,payableLabel,nameValLabel,fatherNameValLabel,payableValLabel,payingAmountLabel,payingLabel;
    JTextField payingAmountTextField;
    ResultSet rs;
    Connection c;
    Statement st;
    int paidFees=0,payableAmount=0,payingAmount=0;
    String selectedBranch,selectedSem,selectedCourse,currRollNo,name,fatherName;
    PayFee()
    {
        this.setTitle("Pay Student Fee");
        this.setFont(new Font("serif",Font.BOLD,50));
        this.setLayout(null);
        
        rollNoLabel=new JLabel("Select Rollno");
        rollNoLabel.setBounds(30, 30, 250, 30);
        rollNoLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(rollNoLabel);

        rollNoComboBox=new JComboBox(getRollNoFromDB());
        rollNoComboBox.setBounds(230,30,150,30);
        rollNoComboBox.addActionListener(this);
        this.add(rollNoComboBox);

        courseLabel=new JLabel("Select course");
        courseLabel.setBounds(30, 70, 250, 30);
        courseLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(courseLabel);

        courseComboBox=new JComboBox(courseList());
        courseComboBox.setBounds(230,70,150,30);
        courseComboBox.addActionListener(this);
        this.add(courseComboBox);

        semLabel=new JLabel("Select Semester");
        semLabel.setBounds(30, 110, 250, 30);
        semLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(semLabel);

        semComboBox=new JComboBox(semList());
        semComboBox.setBounds(230,110,150,30);
        semComboBox.addActionListener(this);
        this.add(semComboBox);

        nameLabel=new JLabel("Name");
        nameLabel.setBounds(30,150,150,30);
        nameLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(nameLabel);

        nameValLabel=new JLabel("Not Available");
        nameValLabel.setBounds(230,150,150,30);
        nameValLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(nameValLabel);

        fatherNameLabel=new JLabel("Father Name");
        fatherNameLabel.setBounds(30,190,150,30);
        fatherNameLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(fatherNameLabel);

        fatherNameValLabel=new JLabel("Not Available");
        fatherNameValLabel.setBounds(230,190,150,30);
        fatherNameValLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(fatherNameValLabel);

        payableLabel=new JLabel("Payable Amount");
        payableLabel.setBounds(30,230,150,30);
        payableLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(payableLabel);

        payableValLabel=new JLabel("Not Available");
        payableValLabel.setBounds(230,230,150,30);
        payableValLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(payableValLabel);

        payingLabel=new JLabel("Paying Amount");
        payingLabel.setBounds(30,270,150,30);
        payingLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(payingLabel);

        payingAmountLabel=new JLabel("Rs.");
        payingAmountLabel.setBounds(230,270,30,30);
        payingAmountLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(payingAmountLabel);

        payingAmountTextField=new JTextField();
        payingAmountTextField.setBounds(260,270,80,30);
        payingAmountTextField.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(payingAmountTextField);

        updateFeeBtn=new JButton("Update Fee");
        updateFeeBtn.setBackground(Color.WHITE);
        updateFeeBtn.setForeground(Color.BLACK);
        updateFeeBtn.setBounds(30,500,160,30);
        updateFeeBtn.setFont(new Font("serif", Font.PLAIN, 20));
        updateFeeBtn.addActionListener(this);
        this.add(updateFeeBtn);

        payBtn=new JButton("Pay");
        payBtn.setBackground(Color.WHITE);
        payBtn.setForeground(Color.BLACK);
        payBtn.setBounds(200,500,80,30);
        payBtn.setFont(new Font("serif", Font.PLAIN, 20));
        payBtn.addActionListener(this);
        this.add(payBtn);

        cancelBtn=new JButton("Cancel");
        cancelBtn.setBackground(Color.WHITE);
        cancelBtn.setForeground(Color.BLACK);
        cancelBtn.setBounds(300,500,150,30);
        cancelBtn.setFont(new Font("serif", Font.PLAIN, 20));
        cancelBtn.addActionListener(this);
        this.add(cancelBtn);
        
        this.setSize(1000,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent a)
    {
        currRollNo=(String)rollNoComboBox.getSelectedItem();
        selectedCourse=(String)courseComboBox.getSelectedItem();
        selectedSem=(String)semComboBox.getSelectedItem();
        if(a.getSource()==cancelBtn)
        {
            this.setVisible(false);
        }
        else if(a.getSource()==payBtn)
        {
            payFeeIntoDB();
        }
        else if(a.getSource()==updateFeeBtn)
        {
            updateFeeAction();
        }
    }
    public void settingConnectionThings() 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            st=c.createStatement();
        }
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public String getSelectedBranch(ResultSet rs, Statement st, String rollNo)
    {
        String selectQuery="select branch from student where rollNo='"+rollNo+"'";
        try 
        {
            rs=st.executeQuery(selectQuery);
            rs.next();   
            selectQuery=rs.getString(1);//using selectQuery as temp
            st.close();
            return selectQuery;
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Error in fetching Branch:/n"+e.toString());
            return "";
        }
    }
    public String[] courseList()
    {
        settingConnectionThings();
        String selectQuery="select course from fee;";
        String ans[]=null;
        try 
        {
            int size=0;
            rs=st.executeQuery(selectQuery);
            ArrayList<String> arr=new ArrayList<>(8);
            while(rs.next()) 
            {
                size++;
                arr.add(rs.getString(1));
            }   
            ans=new String[size];
            for(int i=0; i<size; i++)
            {
                ans[i]=arr.get(i);
                System.out.print(ans[i]+",");
            }
            return ans;
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Error in fetching Course:/n"+e.toString()); 
        }
        return ans;
    }
    public void updateFeeAction()
    {
        //when update button is pressed 
        //we need to get studentName,fatherName with rollNo from student table
        //we need to get feesToBePaid from fee table
        //we need to get feesPaid from collegefee table if the row with rollNo exists.
        settingConnectionThings();
        // System.out.println("rollNo="+currRollNo);
        String selectQuery="select name, fatherName from student where rollNo='"+currRollNo+"';";
        try 
        {
            rs=st.executeQuery(selectQuery); 
            rs.next(); 
            name=rs.getString(1);
            nameValLabel.setText(name+"");
            fatherName=rs.getString(2);
            fatherNameValLabel.setText(fatherName+""); 
            // System.out.println("name="+name+"father="+fatherName);
            c.close();
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Cannot fetch StudentName and FatherName from DB");
        }
        try 
        {
            settingConnectionThings();
            System.out.println("rollNo="+currRollNo+",sem="+selectedSem);
            String selectAllQuery="select *from collegefee where rollno='"+currRollNo+"' AND "+"semester='"+selectedSem+"';";
            rs=st.executeQuery(selectAllQuery); 
            
            if(rs.next())
            {
                //if current rollno is present in  college fee, ie, he paid earlier. Then we have to retrieve how much balance he has to pay now;
                selectAllQuery="select total from collegefee where rollno='"+currRollNo+"';";
                rs=st.executeQuery(selectAllQuery);
                rs.next();
                
                payableAmount=Integer.valueOf(rs.getString(1));
                //updating this amount in label
                payableValLabel.setText("Rs."+payableAmount);
            }
            else
            {
                //if current rollno is not present in  college fee, ie,this is the first time he is paying fee
                System.out.println("sem="+selectedSem+",course="+selectedCourse);
                selectAllQuery="select "+selectedSem+" from fee where course='"+selectedCourse+"';";
                // System.out.println("In else 1");
                rs=st.executeQuery(selectAllQuery);
                rs.next();
                // System.out.println("In else 2");
                System.out.println(rs.getString(1));
                payableAmount=Integer.valueOf(rs.getString(1));//payableAmount=fee thats in fee table for that sem
                payableValLabel.setText("Rs."+payableAmount);
            }
            c.close();
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Cannot update Payable Amount");
            return;
        }
        //undrawing frame and redrawing frame.
        this.setVisible(false);
        this.setVisible(true);
    }
    public void payFeeIntoDB() 
    {
        try 
        {
            payingAmount=Integer.valueOf(payingAmountTextField.getText());   
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Only numbers are accepted in textfield");
            return;
        }
        try 
        {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            // c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            // st=c.createStatement();
            settingConnectionThings();

            if(payingAmount>payableAmount)
            {
                JOptionPane.showMessageDialog(null, "You cant pay more than applicable");
                return;
            }
            selectedBranch=getSelectedBranch(rs,st,currRollNo);
            settingConnectionThings();
            payableAmount=payableAmount-payingAmount;
            String insertQuery="insert into collegefee values('"+currRollNo+"','"+selectedCourse+"','"+selectedBranch+"','"+selectedSem+"','"+payableAmount+"') ";
            st.execute(insertQuery);
            c.close();
            JOptionPane.showMessageDialog(null, "Fee paid Successfully :)");
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Cannot Pay Fee \n"+e.getMessage());
        }
        return;    
    }
    public String[] semList() 
    {
        String[] list={"semester1","semester2","semester3","semester4","semester5","semester6","semester7","semester8"};
        return list;    
    }
    String[] getRollNoFromDB()
    {
        String[] a={};
        try 
        {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            // Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            // //name varchar(30), fatherName varchar(30),rollNo varchar (10),dob varchar(40),address varchar(100),phone varchar(30),email varchar(20),classX varchar(30),classXII varchar(30),aadhar varchar(30),course varchar(30),branch varchar(30));
            // Statement st=c.createStatement();
            settingConnectionThings();
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
            }
            // c.close();
            for(String x:a)
            {
                System.out.print(x+",");
            }
            return a;
        }
        catch (Exception e) 
        {
            // e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return a;
    }
    public static void main(String[] args) 
    {
        new PayFee();    
    }
}