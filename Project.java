import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

class Project extends JFrame implements ActionListener
{
    ImageIcon i, mainPageImage;
    JMenuBar menuBar;
    String space="              ";
    JMenu about,leaveApply,newInformation,viewDetails,leaveDetails,exam,updateInfo,feeDetails,exit;
    JMenuItem viewTeacherLeaveDetails,viewStudentLeaveDetails,aboutItem, newFacultyInfo, newStudentInfo,viewStudentDetails,viewTeacherDetails,updateFacultyInfo,updateStudentInfo,enterMarks,examinationDetails,feeForm,feeStructure,exitItem,applyTeacherLeave,applyStudentLeave;
    Project()
    {
        for(int i=0; i<10; i++)
            space+="             ";
        this.setTitle(space+"University Management System");
        this.setFont(new Font("serif",Font.BOLD,50));
        this.setLayout(null);
        //adding mainPageImage
        i=new ImageIcon("icons/image.png");//actual image -> third.jpg
        Image n=i.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        mainPageImage=new ImageIcon(n);
        JLabel imageLabel=new JLabel(mainPageImage);
        imageLabel.setBounds(0,0,1500,750);
        this.add(imageLabel);

        //setting menu bar
        menuBar=new JMenuBar();
        menuBar.setBounds(0, 0, 1500, 20);
            //creating menus for menuBar
            newInformation = new JMenu("NewInformation");
            newInformation.setForeground(Color.BLACK);
            newInformation.setBackground(Color.lightGray);
            newInformation.setFont(new Font("serif", Font.BOLD, 25));
            //adding menuBar to JFrame
            menuBar.add(newInformation);
                //creating menuItems for menuBar
                newFacultyInfo=new JMenuItem("Add New Faculty Information");
                newFacultyInfo.setFont(new Font("serif", Font.PLAIN, 18));
                newFacultyInfo.addActionListener(this);
                newStudentInfo=new JMenuItem("Add New Student Information");
                newStudentInfo.setFont(new Font("serif", Font.PLAIN, 18));
                newStudentInfo.addActionListener(this);
                newInformation.add(newFacultyInfo);
                newInformation.add(newStudentInfo);

            //creating menus for menuBar
            viewDetails = new JMenu("ViewDetails");
            viewDetails.setFont(new Font("serif", Font.BOLD, 25));
            viewDetails.setForeground(Color.BLACK);
            viewDetails.setBackground(Color.lightGray);
            //adding menuBar to JFrame
            menuBar.add(viewDetails);
                //creating menuItems for menuBar
                viewStudentDetails=new JMenuItem("View Student Details");
                viewStudentDetails.setFont(new Font("serif", Font.PLAIN, 18));
                viewStudentDetails.addActionListener(this);
                viewTeacherDetails=new JMenuItem("View Faculty Details");
                viewTeacherDetails.setFont(new Font("serif", Font.PLAIN, 18));
                viewTeacherDetails.addActionListener(this);
                viewDetails.add(viewStudentDetails);
                viewDetails.add(viewTeacherDetails);

            leaveApply = new JMenu("ApplyLeave");
            leaveApply.setFont(new Font("serif", Font.BOLD, 25));
            leaveApply.setForeground(Color.BLACK);
            leaveApply.setBackground(Color.lightGray);
            //adding menuBar to JFrame
            // menuBar.add(leaveApply);
                //creating menuItems for menuBar
                applyStudentLeave=new JMenuItem("Apply Student Leave");
                applyStudentLeave.setFont(new Font("serif", Font.PLAIN, 18));
                applyStudentLeave.addActionListener(this);
                applyTeacherLeave=new JMenuItem("Apply Teacher Leave");
                applyTeacherLeave.setFont(new Font("serif", Font.PLAIN, 18));
                applyTeacherLeave.addActionListener(this);
                leaveApply.add(applyStudentLeave);
                leaveApply.add(applyTeacherLeave);
            menuBar.add(leaveApply);

            leaveDetails = new JMenu("LeaveDetails");
            leaveDetails.setFont(new Font("serif", Font.BOLD, 25));
            leaveDetails.setForeground(Color.BLACK);
            leaveDetails.setBackground(Color.lightGray);
            //adding menuBar to JFrame
            menuBar.add(leaveDetails);
                //creating menuItems for menuBar
                viewStudentLeaveDetails=new JMenuItem("Student Leave Details");
                viewStudentLeaveDetails.setFont(new Font("serif", Font.PLAIN, 18));
                viewStudentLeaveDetails.addActionListener(this);
                viewTeacherLeaveDetails=new JMenuItem("Faculty Leave Details");
                viewTeacherLeaveDetails.setFont(new Font("serif", Font.PLAIN, 18));
                viewTeacherLeaveDetails.addActionListener(this);
                leaveDetails.add(viewStudentLeaveDetails);
                leaveDetails.add(viewTeacherLeaveDetails);

            exam= new JMenu("Examination");
            exam.setFont(new Font("serif", Font.BOLD, 25));
            exam.setForeground(Color.BLACK);
            exam.setBackground(Color.lightGray);
            //adding menuBar to JFrame
            menuBar.add(exam);
                //creating menuItems for menuBar
                examinationDetails=new JMenuItem("Student Examination Details");
                examinationDetails.setFont(new Font("serif", Font.PLAIN, 18));
                examinationDetails.addActionListener(this);
                enterMarks=new JMenuItem("Enter Marks");
                enterMarks.setFont(new Font("serif", Font.PLAIN, 18));
                enterMarks.addActionListener(this);
                exam.add(examinationDetails);
                exam.add(enterMarks);

            updateInfo = new JMenu("UpdateDetails");
            updateInfo.setFont(new Font("serif", Font.BOLD, 25));
            updateInfo.setForeground(Color.BLACK);
            updateInfo.setBackground(Color.lightGray);
            //adding menuBar to JFrame
            menuBar.add(updateInfo);
                //creating menuItems for menuBar
                updateFacultyInfo=new JMenuItem("Update Student Details");
                updateFacultyInfo.setFont(new Font("serif", Font.PLAIN, 18));
                updateFacultyInfo.addActionListener(this);
                updateStudentInfo=new JMenuItem("Update Faculty Details");
                updateStudentInfo.setFont(new Font("serif", Font.PLAIN, 18));
                updateStudentInfo.addActionListener(this);
                updateInfo.add(updateFacultyInfo);
                updateInfo.add(updateStudentInfo);
            
            feeDetails = new JMenu("FeeDetails");
            feeDetails.setFont(new Font("serif", Font.BOLD, 25));
            feeDetails.setForeground(Color.BLACK);
            feeDetails.setBackground(Color.lightGray);
            menuBar.add(feeDetails);
                //creating menuItems for menuBar
                feeStructure=new JMenuItem("Fee Structure");
                feeStructure.setFont(new Font("serif", Font.PLAIN, 18));
                feeStructure.addActionListener(this);
                feeForm=new JMenuItem("Student Fee Form");
                feeForm.setFont(new Font("serif", Font.PLAIN, 18));
                feeForm.addActionListener(this);
                feeDetails.add(feeStructure);
                feeDetails.add(feeForm);

            about=new JMenu("About");
            about.setFont(new Font("serif", Font.BOLD, 25));
            about.setForeground(Color.BLACK);
            about.setBackground(Color.lightGray);
                // creating menuItems for menuBar
                aboutItem=new JMenuItem("About Us");
                aboutItem.setFont(new Font("serif", Font.PLAIN, 18));
                aboutItem.addActionListener(this);
                about.add(aboutItem);
                menuBar.add(about);
            
            exit=new JMenu("Exit");
            exit.setFont(new Font("serif", Font.BOLD, 25));
                exitItem=new JMenuItem("Exit");
                exitItem.setFont(new Font("serif", Font.PLAIN, 18));
                exitItem.addActionListener(this);
                exit.add(exitItem);
            menuBar.add(exit);
        //Frame settings
        this.setJMenuBar(menuBar);// this.add(menuBar); wont work
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500,800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        // System.out.println(this.getSize());//give height and width insanly big values, then print size() to get size of laptop
    }
    public void quit()
    {
        JFrame exitFrame=new JFrame();
        exitFrame.setLayout(null);
        exitFrame.setBounds(600, 400, 400, 200);
        exitFrame.setLocationRelativeTo(null);
        // exitFrame.setSize(100,20);
        exitFrame.setBackground(Color.WHITE);
        exitFrame.setForeground(Color.BLACK);

        JLabel label=new JLabel("Do you want to Quit ???");
        label.setFont(new Font("serif", Font.BOLD, 20));
        label.setBounds(100,20,300,30);
        exitFrame.add(label);
        JButton yesBtn=new JButton("YES");
        yesBtn.setBounds(100, 100, 80, 30);
        yesBtn.addActionListener
        (
            new ActionListener()
            {  
                public void actionPerformed(ActionEvent e)
                {  
                    System.exit(ABORT);  
                }  
            }
        ); 
        exitFrame.add(yesBtn);
        JButton noBtn=new JButton("NO");
        noBtn.setBounds(200, 100, 80, 30);
        noBtn.addActionListener
        (
            new ActionListener()
            {  
                public void actionPerformed(ActionEvent e)
                {  
                    exitFrame.setVisible(false);
                }  
            }
        );
        exitFrame.add(noBtn);
        exitFrame.setVisible(true);
    }
    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource()==exitItem)
        {
            quit();
        }
        else if(a.getSource()==aboutItem)
        {
            new About();
        }
        else if(a.getSource()==applyTeacherLeave)
        {
            new ApplyLeaveTeacher();
        }
        else if(a.getSource()==applyStudentLeave)
        {
            new ApplyLeaveStudent();
        }
        else if(a.getSource()==viewTeacherLeaveDetails)
        {
            new TeacherLeaveDetails();
        }
        else if(a.getSource()==viewStudentLeaveDetails)
        {
            new StudentLeaveDetails();
        }
        else if(a.getSource()==newFacultyInfo)
        {
            System.out.println("newFacultyInfo");
            new AddTeacher();
        }
        else if(a.getSource()==newStudentInfo)
        {
            System.out.println("newStudentInfo");
            new AddStudent();
        }
        else if(a.getSource()==viewStudentDetails)
        {
            System.out.println("viewStudentDetails");
            new ViewStudentDetails();
        }
        else if(a.getSource()==viewTeacherDetails)
        {
            System.out.println("viewTeacherDetails");
            new ViewTeacherDetails();
        }
        else if(a.getSource()==updateFacultyInfo)
        {
            System.out.println("updateFacultyInfo");
            new UpdateTeacherDetails();
        }
        else if(a.getSource()==updateStudentInfo)
        {
            System.out.println("updateStudentInfo");
            new UpdateStudentDetails();
        }
        else if(a.getSource()==enterMarks)
        {
            System.out.println("enterMarks");
            new EnterMarks();
        }
        else if(a.getSource()==examinationDetails)
        {
            System.out.println("examinationDetails");
            // new ExamDetails();
        }
        else if(a.getSource()==feeForm)
        {
            System.out.println("feeForm");
            // new FeeForm();
        }
        else if(a.getSource()==feeStructure)
        {
            System.out.println("FeeStructure");
            new FeeStructure();
        }
    }
    public static void main(String[] args) 
    {
        new Project();    
    }
}