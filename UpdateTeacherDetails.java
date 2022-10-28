import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.text.DateFormat;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
class UpdateTeacherDetails extends JFrame implements ActionListener
{
    JLabel title,nameLabel,empIDLabel, addressLabel,emailLabel,classXIILabel,departmentLabel,fatherNameLabel, dOBLabel, phoneLabel,classXLabel,aadharLabel,educationLabel;
    JTextField nameField,addressField,emailField, classXIIField, courseField, fatherField, phoneField,classXField,aadharField;
    JButton submitBtn, cancleBtn; 
    JComboBox departmentComboBox,educationComboBox,employeeIDField;
    String[] educationList, departmentList,empIDList;
    JDateChooser DOB;

    ResultSet rs;
    Connection c;
    Statement st;
    UpdateTeacherDetails()
    {
        this.setLayout(null);
        title=new JLabel("Update Teacher Details");
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

        empIDLabel=new JLabel("Emp ID");
        empIDLabel.setBounds(50, 200, 250, 20);
        empIDLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(empIDLabel);

        employeeIDField=new JComboBox(getEmpIDFromDB());
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

        departmentLabel=new JLabel("Department");
        departmentLabel.setBounds(50, 440, 250, 20);
        departmentLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(departmentLabel);

        departmentList=getDepartmentList();
        departmentComboBox=new JComboBox(departmentList);
        departmentComboBox.setBounds(250,440,200,30);
        this.add(departmentComboBox);

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

        educationLabel=new JLabel("Branch");
        educationLabel.setBounds(550, 440, 250, 20);
        educationLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(educationLabel);

        educationList=getEducationList();
        educationComboBox=new JComboBox(educationList);
        educationComboBox.setBounds(720,440,200,30);
        this.add(educationComboBox);

        submitBtn=new JButton("Update");
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
    public String[] getEducationList() 
    {
        String[] a={"B.Tech","B.Com","B.Sc"};
        return a;
    }
    public String[] getDepartmentList() 
    {
        String[] a={"Computer Science","Mechanical","Civil","IT","Electrical"};
        return a;
    }
    public static void main(String[] args) 
    {
        new UpdateTeacherDetails();
    } 
    public void updateIntoDB() 
    {
        String name,address,email,classXII, fatherName, dob,phone,classX,aadhar;
        String education,department,empID;
        empID=(String)employeeIDField.getSelectedItem();
        name=nameField.getText();
        address=addressField.getText();
        email=emailField.getText();
        classXII=classXIIField.getText();
        fatherName=fatherField.getText();
        dob=DateFormat.getDateInstance().format(DOB.getDate());
        phone=phoneField.getText();
        classX=classXField.getText();
        aadhar=aadharField.getText();
        department=(String)departmentComboBox.getSelectedItem();
        education=(String)educationComboBox.getSelectedItem();
        try 
        {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            //name+"','"+fatherName+"','"+empID+"','"+dob+"','"+address+"','"+phone+"','"+email+"','"+classX+"','"+classXII+"','"+aadhar+"','"+course+"','"+education+"','"+department
            st=c.createStatement();
            String insertQuery="UPDATE teacher SET name ='"+name+"',fatherName='"+fatherName+"',dob='"+dob+"',address='"+address+"',phone='"+phone+"',email='"+email+"',classX='"+classX+"',classXII='"+classXII+"',aadhar='"+aadhar+"',education='"+education+"',department='"+department+"' WHERE empID ='"+empID+"';";
            st.execute(insertQuery);     
            JOptionPane.showMessageDialog(null, "inserted Successfully");
            this.setVisible(false);
            c.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    public String[] getEmpIDFromDB() 
    {
        String a[];
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            //name varchar(30), fatherName varchar(30),rollNo varchar (10),dob varchar(40),address varchar(100),phone varchar(30),email varchar(20),classX varchar(30),classXII varchar(30),aadhar varchar(30),course varchar(30),branch varchar(30));
            st=c.createStatement();
            String selectAllQuery="select empID from teacher;";
            rs=st.executeQuery(selectAllQuery); 
            int size=0;
            ArrayList<String> s=new ArrayList<>();
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
    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource()==submitBtn)
        {
            updateIntoDB();
        }
        else if(a.getSource()==cancleBtn)
        {
            // cancleBtn
            this.setVisible(false);
        }
    }
}