import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import java.util.ArrayList;

import net.proteanit.sql.DbUtils;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
class StudentLeaveDetails extends JFrame implements ActionListener
{
    String[] options={"Full Day", "Half Day"};
    String[] rollNoList;
    JButton cancleBtn,submitBtn;
    JComboBox rollNoComboBox,optionsCombo;
    JLabel title,rollNoLabel,timeLabel,dateLabel;
    JDateChooser DOB;
    ResultSet rs;
    Connection c;
    Statement st;
    JTable table;
    JScrollPane scrollPane;
    StudentLeaveDetails()
    {
        this.setLayout(null);
        title=new JLabel("Student Leave Details");
        title.setBounds(80, 40, 450, 50);
        title.setFont(new Font("serif", Font.BOLD, 35));
        this.add(title);

        rollNoLabel=new JLabel("Roll No:");
        rollNoLabel.setBounds(50, 120, 200, 20);
        rollNoLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(rollNoLabel);

        rollNoList=getRollNoFromDB();
        rollNoComboBox=new JComboBox(rollNoList);
        rollNoComboBox.setBounds(150,120,200,30);
        rollNoComboBox.addActionListener(this);
        this.add(rollNoComboBox);

        submitBtn=new JButton("Search");
        submitBtn.setBackground(Color.BLACK);
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setBounds(50,200,150,40);
        submitBtn.setFont(new Font("serif", Font.PLAIN, 20));
        submitBtn.addActionListener(this);
        this.add(submitBtn);

        cancleBtn=new JButton("Back");
        cancleBtn.setBackground(Color.BLACK);
        cancleBtn.setForeground(Color.WHITE);
        cancleBtn.setBounds(350,200,150,40);
        cancleBtn.setFont(new Font("serif", Font.PLAIN, 20));
        cancleBtn.addActionListener(this);
        this.add(cancleBtn);

        this.setSize(1000,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    String[] getRollNoFromDB()
    {
        String[] a={};
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            // Statement st=c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            st=c.createStatement();
            String selectQuery="select DISTINCT rollNo from studentleave;";
            rs=st.executeQuery(selectQuery);  

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
            c.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return a;
    }
    public void StudentLeaveDetailsTable() 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            st=c.createStatement();
            String selectQuery="select *from studentleave where rollno='"+(String)rollNoComboBox.getSelectedItem()+"';";
            rs=st.executeQuery(selectQuery);  

            table =new JTable();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.setBounds(10,260,950,400);
            table.setFont(new Font("serif", Font.PLAIN, 15));
            scrollPane=new JScrollPane(table);
            scrollPane.setBounds(20, 250, 950, 200);
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
        if(a.getSource()==cancleBtn)
        {
            this.setVisible(false);
        }
        else if(a.getSource()==submitBtn)
        {
            StudentLeaveDetailsTable();
        }
    }
    public static void main(String[] args) 
    {
        new StudentLeaveDetails();
    }
}