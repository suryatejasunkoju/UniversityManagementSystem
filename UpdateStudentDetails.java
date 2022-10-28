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
class UpdateStudentDetails extends JFrame implements ActionListener
{
    JLabel title,nameLabel,rollNoLabel,searchLabel, addressLabel,emailLabel,classXIILabel,courseLabel,fatherNameLabel, dOBLabel, phoneLabel,classXLabel,aadharLabel,branchLabel;
    JTextField nameField,rollNoField,addressField,emailField, classXIIField, courseField, fatherField,doBField, phoneField,classXField,aadharField,branchField;
    JButton submitBtn, cancleBtn; 
    JComboBox courseComboBox, branchComboBox,rollNoComboBox;
    String[] coursesList, branchesList, rollNoList;
    JDateChooser DOB;
    ResultSet rs;
    Connection c;
    Statement st;
    UpdateStudentDetails()
    {
        this.setLayout(null);
        title=new JLabel("Update Student Details");
        title.setBounds(350, 40, 450, 50);
        title.setFont(new Font("serif", Font.BOLD, 35));
        this.add(title);

        this.setLayout(null);
        searchLabel=new JLabel("Select Rollno:");
        searchLabel.setBounds(50, 150, 250, 30);
        searchLabel.setFont(new Font("serif", Font.BOLD, 20));
        this.add(searchLabel);

        rollNoList=getRollNoList();
        rollNoComboBox=new JComboBox(rollNoList);
        rollNoComboBox.setBounds(250,150,200,30);
        rollNoComboBox.setFont(new Font("serif", Font.PLAIN, 20));
        rollNoComboBox.addActionListener(this);
        this.add(rollNoComboBox);

        nameLabel=new JLabel("Name");
        nameLabel.setBounds(50, 200, 250, 20);
        nameLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(nameLabel);

        nameField=new JTextField();
        nameField.setBounds(250,200,200,30);
        this.add(nameField);

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

        courseLabel=new JLabel("Course");
        courseLabel.setBounds(50, 440, 250, 20);
        courseLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(courseLabel);

        coursesList=getCourseList();
        courseComboBox=new JComboBox(coursesList);
        courseComboBox.setBounds(250,440,200,30);
        this.add(courseComboBox);

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

        branchLabel=new JLabel("Branch");
        branchLabel.setBounds(550, 440, 250, 20);
        branchLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(branchLabel);

        branchesList=getBranchList();
        branchComboBox=new JComboBox(branchesList);
        branchComboBox.setBounds(720,440,200,30);
        this.add(branchComboBox);

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
    public String[] getCourseList() 
    {
        String[] a={"B.Tech","B.Com","B.Sc"};
        return a;
    }
    public String[] getBranchList() 
    {
        String[] a={"Computer Science","Mechanical","Civil","IT","Electrical"};
        return a;
    }
    public static void main(String[] args) 
    {
        new UpdateStudentDetails();
    } 
    public void updateIntoDB() 
    {
        String name,address,email,classXII, fatherName, dob,phone,classX,aadhar;
        String course,branch,rollNo;
        rollNo=(String)rollNoComboBox.getSelectedItem();
        name=nameField.getText();
        address=addressField.getText();
        email=emailField.getText();
        classXII=classXIIField.getText();
        fatherName=fatherField.getText();
        dob=DateFormat.getDateInstance().format(DOB.getDate());
        phone=phoneField.getText();
        classX=classXField.getText();
        aadhar=aadharField.getText();
        branch=(String)branchComboBox.getSelectedItem();
        course=(String)courseComboBox.getSelectedItem();
        try 
        {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            //name+"','"+fatherName+"','"+empID+"','"+dob+"','"+address+"','"+phone+"','"+email+"','"+classX+"','"+classXII+"','"+aadhar+"','"+course+"','"+education+"','"+department
            st=c.createStatement();
            String insertQuery="UPDATE student SET name='"+name+"',fatherName='"+fatherName+"',dob='"+dob+"',address='"+address+"',phone='"+phone+"',email='"+email+"',classX='"+classX+"',classXII='"+classXII+"',aadhar='"+aadhar+"',course='"+course+"',branch='"+branch+"' WHERE rollNo ='"+rollNo+"';";
            st.execute(insertQuery);     
            JOptionPane.showMessageDialog(null, "Updated Successfully");
            // this.setVisible(false);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString());
        }
        c.close();
    }
    String[] getRollNoList()
    {
        String[] a={};
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
                System.out.print(a[i]+",");
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