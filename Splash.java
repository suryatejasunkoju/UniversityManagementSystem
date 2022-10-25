import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Image;
import java.lang.ClassLoader;

class Splash extends JFrame implements Runnable
{
    Splash()
    {
        // ImageIcon icon1=new ImageIcon(getClass().ClassLoader.getSystemResource("first.jpg"));
        // // add(icon);//we cant directly add imageIcon to window
        // Image scaledImage1= icon1.getImage().getScaledInstance(950, 650, Image.SCALE_DEFAULT);
        // ImageIcon icon11=new ImageIcon(scaledImage1);
        // JLabel imageLabel=new JLabel(icon11);
        // imageLabel.setBounds(0,0, 950, 650);

        ImageIcon i=new ImageIcon(getClass().getResource("icons/first.png"));
        Image image=i.getImage().getScaledInstance(1250, 800, Image.SCALE_SMOOTH);//baseWidth=1250,baseHeight=800
        ImageIcon icon=new ImageIcon(image);
        JLabel imageLabel=new JLabel(icon);
        this.add(imageLabel);
        this.setVisible(true);

        int baseWidth=700, baseHeight=400;
        for (int j = 0; j <10; j++) 
        {
            this.setSize(baseWidth,baseHeight);
            this.setLocationRelativeTo(null);
            try 
            {
                Thread.sleep(200);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            baseWidth+=40;
            baseHeight+=40;
        }
        // System.out.println("baseWidth="+baseWidth+",baseHeight="+baseHeight);//baseWidth=1250,baseHeight=800
        try 
        {
            Thread.sleep(3000);    
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        //vanish this page and
        //pop up login page after 1sec
        this.setVisible(false);
        new Login();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void run()
    {

    }
    public static void main(String[] args) 
    {
        new Splash();
    }
}