import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import com.mysql.cj.xdevapi.Result;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;

import net.proteanit.sql.DbUtils;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
class AddTeacher extends JFrame implements ActionListener
{
    JLabel title,nameLabel,employeeIDField,employeeIDLabel, addressLabel,emailLabel,classXIILabel,educationLabel,fatherNameLabel, dOBLabel, phoneLabel,classXLabel,aadharLabel,branchLabel;
    JTextField nameField,addressField,emailField, classXIIField, courseField, fatherField,doBField, phoneField,classXField,aadharField,branchField;
    JButton submitBtn, cancleBtn; 
    JComboBox educationComboBox, departmentComboBox;
    String []coursesList={"B.Tech","B.Com","B.Sc"};
    String[] departmentsList={"Computer Science","Mechanical","Civil","IT","Electrical"};
    ResultSet rs;
    Connection c;
    Statement st;
    JDateChooser DOB;
    int empIDVal=5563452;
    AddTeacher()
    {
        this.setLayout(null);
        title=new JLabel("New Teacher Details");
        title.setBounds(350, 40, 450, 50);
        title.setFont(new Font("serif", Font.BOLD, 35));
        this.add(title);

        nameLabel=new JLabel("Name");
        nameLabel.setBounds(50, 140, 250, 20);
        nameLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(nameLabel);

        nameField=new JTextField();
        nameField.setBounds(250,140,200,30);
        this.add(nameField);

        employeeIDLabel=new JLabel("Employee ID");
        employeeIDLabel.setBounds(50, 200, 280, 20);
        employeeIDLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(employeeIDLabel);

        employeeIDField=new JLabel(""+getEmpID());
        employeeIDField.setBounds(250,200,200,30);
        employeeIDField.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(employeeIDField);

        addressLabel=new JLabel("Address");
        addressLabel.setBounds(50, 260, 250, 20);
        addressLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(addressLabel);

        addressField=new JTextField();
        addressField.setBounds(250,260,200,30);
        this.add(addressField);

        emailLabel=new JLabel("Email");
        emailLabel.setBounds(50, 320, 250, 20);
        emailLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(emailLabel);

        emailField=new JTextField();
        emailField.setBounds(250,320,200,30);
        this.add(emailField);

        classXIILabel=new JLabel("Class XII marks(%)");
        classXIILabel.setBounds(50, 380, 250, 20);
        classXIILabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(classXIILabel);

        classXIIField=new JTextField();
        classXIIField.setBounds(250,380,200,30);
        this.add(classXIIField);

        educationLabel=new JLabel("Education");
        educationLabel.setBounds(50, 440, 250, 20);
        educationLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(educationLabel);

        educationComboBox=new JComboBox(coursesList);
        educationComboBox.setBounds(250,440,200,30);
        this.add(educationComboBox);

        fatherNameLabel=new JLabel("Father's Name");
        fatherNameLabel.setBounds(550, 140, 250, 20);
        fatherNameLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(fatherNameLabel);

        fatherField=new JTextField();
        fatherField.setBounds(720,140,200,30);
        this.add(fatherField);

        dOBLabel=new JLabel("DOB");
        dOBLabel.setBounds(550, 200, 250, 20);
        dOBLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(dOBLabel);

        // doBField=new JTextField();
        // doBField.setBounds(720,200,200,30);
        // this.add(doBField);
        DOB=new JDateChooser();
        DOB.setBounds(720,200,200,30);
        this.add(DOB);
        
        phoneLabel=new JLabel("Phone");
        phoneLabel.setBounds(550, 260, 250, 20);
        phoneLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(phoneLabel);

        phoneField=new JTextField();
        phoneField.setBounds(720,260,200,30);
        this.add(phoneField);

        classXLabel=new JLabel("Class X marks(%)");
        classXLabel.setBounds(550, 320, 250, 20);
        classXLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(classXLabel);

        classXField=new JTextField();
        classXField.setBounds(720,320,200,30);
        this.add(classXField);

        aadharLabel=new JLabel("Aadhar");
        aadharLabel.setBounds(550, 380, 250, 20);
        aadharLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(aadharLabel);

        aadharField=new JTextField();
        aadharField.setBounds(720,380,200,30);
        this.add(aadharField);

        branchLabel=new JLabel("Branch");
        branchLabel.setBounds(550, 440, 250, 20);
        branchLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(branchLabel);

        departmentComboBox=new JComboBox(departmentsList);
        departmentComboBox.setBounds(720,440,200,30);
        this.add(departmentComboBox);

        submitBtn=new JButton("Submit");
        submitBtn.setBackground(Color.BLACK);
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setBounds(250,550,150,40);
        submitBtn.setFont(new Font("serif", Font.PLAIN, 25));
        submitBtn.addActionListener(this);
        this.add(submitBtn);

        cancleBtn=new JButton("Cancel");
        cancleBtn.setBackground(Color.BLACK);
        cancleBtn.setForeground(Color.WHITE);
        cancleBtn.setBounds(580,550,150,40);
        cancleBtn.setFont(new Font("serif", Font.PLAIN, 25));
        cancleBtn.addActionListener(this);
        this.add(cancleBtn);

        this.setSize(1000,750);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) 
    {
        new AddTeacher();
    }
    public int getEmpID() 
    {
        //ie, even if it is not first record or any other record we have to get last record from database
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            st=c.createStatement();
            String selectQuery="Select *from teacher ORDER BY empID DESC;";
            rs=st.executeQuery(selectQuery);
            if(!rs.next())//moving cursor to first line
                return empIDVal;
            empIDVal=1+Integer.valueOf(rs.getString(3));
            // JOptionPane.showMessageDialog(null, "Details inserted Successfully :)");
            c.close();
            return empIDVal;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString());
            return empIDVal;
        }
    }
    public void insertIntoDB()
    {
        String name,address,email,classXII, fatherName, dob,phone,classX,aadhar;
        String department,education,empID; 

        name=nameField.getText();
        System.out.println(name+",");
        address=addressField.getText();
        System.out.println(address+",");
        email=emailField.getText();
        classXII=classXIIField.getText();
        fatherName=fatherField.getText();
        System.out.println(fatherName);
        dob=(String)((JTextField) DOB.getDateEditor().getUiComponent()).getText();
        phone=phoneField.getText();
        classX=classXField.getText();
        aadhar=aadharField.getText();
        empID=employeeIDField.getText();
        // System.out.println(empID);
        department=(String)departmentComboBox.getSelectedItem();
        education=(String)educationComboBox.getSelectedItem();
        // name+"','"+fatherName+"','"+empID+"','"+dob+"','"+address+"','"+phone+"','"+email+"','"+classX+"','"+classXII+"','"+aadhar+"','"+education+"','"+department+"');";
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            // Statement st=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            st=c.createStatement();
            String insertQuery="insert into teacher values('"+name+"','"+fatherName+"','"+empID+"','"+dob+"','"+address+"','"+phone+"','"+email+"','"+classX+"','"+classXII+"','"+aadhar+"','"+education+"','"+department+"');";
            st.execute(insertQuery);  
            JOptionPane.showMessageDialog(null, "Details inserted Successfully :)");
            c.close();
            this.setVisible(false);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return;
    }
    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource()==submitBtn)
        {
            //insert values into DB
            insertIntoDB();
        }
        else
        {
            // cancleBtn
            this.setVisible(false);
        }
    }
}