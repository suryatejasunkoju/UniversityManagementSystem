import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
class About extends JFrame
{
    JLabel collegeTitle,collegeAddressLabel,pinCodeLabel, contactMobileLabel,emailLabel,imageLabel;
    JLabel emailValLabel,collegeAddressValLabel,l, pinCodeValLabel, contactMobileValLabel;
    About()
    {
        this.setLayout(null);
        collegeTitle=new JLabel("RLS College Of Engineering & Technology");
        collegeTitle.setBounds(150, 40, 650, 50);
        collegeTitle.setFont(new Font("serif", Font.BOLD, 35));
        this.add(collegeTitle);

        
        ImageIcon i=new ImageIcon(getClass().getResource("icons/first.png"));
        Image image=i.getImage().getScaledInstance(400, 250, Image.SCALE_SMOOTH);//baseWidth=1250,baseHeight=800
        ImageIcon icon=new ImageIcon(image);
        JLabel imageLabel=new JLabel(icon);
        imageLabel.setBounds(500,130,400,250);
        this.add(imageLabel);

        collegeAddressLabel=new JLabel("Address");
        collegeAddressLabel.setBounds(50, 150, 100, 20);
        collegeAddressLabel.setFont(new Font("serif", Font.BOLD, 20));
        this.add(collegeAddressLabel);

        collegeAddressValLabel=new JLabel(":  Human street, GreenEarth City.");
        collegeAddressValLabel.setBounds(150, 150, 350, 25);
        collegeAddressValLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(collegeAddressValLabel);
        
        pinCodeLabel=new JLabel("Pin Code");
        pinCodeLabel.setBounds(50, 200, 100, 20);
        pinCodeLabel.setFont(new Font("serif", Font.BOLD, 20));
        this.add(pinCodeLabel);

        pinCodeValLabel=new JLabel(":  549 108");
        pinCodeValLabel.setBounds(150, 200, 250, 20);
        pinCodeValLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(pinCodeValLabel);

        emailLabel=new JLabel("Email");
        emailLabel.setBounds(50, 250, 100, 20);
        emailLabel.setFont(new Font("serif", Font.BOLD, 20));
        this.add(emailLabel);

        emailValLabel=new JLabel(":  Contact@rls.com");
        emailValLabel.setBounds(150, 250, 350, 25);
        emailValLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(emailValLabel);

        contactMobileLabel=new JLabel("Phone");
        contactMobileLabel.setBounds(50, 300, 100, 20);
        contactMobileLabel.setFont(new Font("serif", Font.BOLD, 20));
        this.add(contactMobileLabel);

        contactMobileValLabel=new JLabel(":  9676240832");
        contactMobileValLabel.setBounds(150, 300, 250, 20);
        contactMobileValLabel.setFont(new Font("serif", Font.PLAIN, 20));
        this.add(contactMobileValLabel);

        l=new JLabel("© 2022 RLS Ed-Tech. All rights reserved");
        l.setBounds(50, 450, 450, 15);
        l.setFont(new Font("serif", Font.PLAIN, 15));
        this.add(l);
        
        this.setSize(1000,550);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) 
    {
        new About();
    } 
}