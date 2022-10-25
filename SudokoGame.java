import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
class SudokoGame extends JFrame implements ActionListener
{
    final static int BOARD_LENGTH=9;
    JTextField[][] inputGridTextField;
    JButton showAnsBtn, createNewBoardBtn, exitBtn;
    int height=30, width=30;
    int x=50, y=50,score=Integer.MAX_VALUE;
    SudokoGame()
    {
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        inputGridTextField=new JTextField[BOARD_LENGTH][BOARD_LENGTH];
        int[][] board=createRandomBoard();
        int i=0, j=0;
        String text="";
        for (JTextField[] jTextFieldArr : inputGridTextField) 
        {
            for (JTextField jTextField : jTextFieldArr) 
            {
                
                if(board[i][j]!=0)
                {
                    text=board[i][j]+"";
                }
                else
                {
                    text="";
                }
                jTextField=new JTextField(text);   
                // jTextField.setName("0");
                jTextField.setFont(new Font("serif", Font.BOLD, 25)); 
                System.out.println(jTextField.getName());
                jTextField.setBounds(x,y,30,30);
                this.add(jTextField);
                x+=32;   
                j++; 
            }
            j=0;
            i++;
            x=50;
            y+=32;
        }
        
        // draw();

        showAnsBtn=new JButton("Show Solution");
        showAnsBtn.setBounds(100, 500, 50, 30);
        showAnsBtn.addActionListener(this);
        this.add(showAnsBtn);

        createNewBoardBtn=new JButton("Create New Board");
        createNewBoardBtn.setBounds(200, 500, 50, 30);
        createNewBoardBtn.addActionListener(this);
        this.add(createNewBoardBtn);

        exitBtn=new JButton("Exit");
        exitBtn.setBounds(300, 500, 50, 30);
        exitBtn.addActionListener(this);
        this.add(exitBtn);

        this.setBackground(Color.BLACK);
        this.setSize(1000,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource()==showAnsBtn)
        {
            int[][] grid=getGridValues(inputGridTextField);
            int[][] solvedAns=solveSudoko(grid);
            undraw(inputGridTextField);
            draw(solvedAns);
        }
        else if(a.getSource()==createNewBoardBtn)
        {
            undraw(inputGridTextField);
            int b[][]=createRandomBoard();
            draw(b);
        }
        else if(a.getSource()==exitBtn)
        {
            System.exit(ABORT);
        }
    }
    public void draw(int[][] matrix) 
    {
        x=50;
        y=50;
        inputGridTextField=new JTextField[BOARD_LENGTH][BOARD_LENGTH];
        for (int i = 0; i < matrix.length; i++) 
        {
            JTextField[] jTextFieldArr=new JTextField[9];
            for (int j = 0; j < matrix.length; j++) 
            {
                JTextField jTextField=new JTextField("");   
                // jTextField.setName("0");
                jTextField.setFont(new Font("serif", Font.BOLD, 25)); 
                System.out.println(jTextField.getName());
                jTextField.setBounds(x,y,30,30);
                this.add(jTextField);
                x+=32;    
            }    
            x=50;
            y+=32;
        }
        // for (JTextField[] jTextFieldArr : inputGridTextField) 
        // {
        //     for (JTextField jTextField : jTextFieldArr) 
        //     {
        //         jTextField=new JTextField("");   
        //         // jTextField.setName("0");
        //         jTextField.setFont(new Font("serif", Font.BOLD, 25)); 
        //         System.out.println(jTextField.getName());
        //         jTextField.setBounds(x,y,30,30);
        //         this.add(jTextField);
        //         x+=32;    
        //     }
        //     x=50;
        //     y+=32;
        // }
        // this.add(inputGridTextField);
    }
    public void undraw(JTextField[][] grid) 
    {
        this.remove(inputGridTextField);
        // for (JTextField[] jTextFields : grid) 
        // {
        //     for (JTextField curr : jTextFields) 
        //     {
        //         this.remove(curr);        
        //     }
        // }
    }
    public int[][] getGridValues(JTextField[][] grid)
    {
        JTextField curr=null;
        int[][] val=new int[9][9];
        for (int i = 0; i < val.length; i++) 
        {
            for (int j = 0; j < val.length; j++) 
            {
                curr=grid[i][j];
                try 
                {
                    val[i][j]=Integer.valueOf(curr.getText());   
                } 
                catch (Exception e) 
                {
                    System.out.println("Enter 1 to 9 digits only");
                }
            }    
        }
        return val;
    }
    public int[][] createRandomBoard() 
    {
        Board b=new Board();
        int[][] board=b.getFinalBoard(); 
        return board;    
    }
    public int[][] solveSudoko(int[][] grid) 
    {
        Solve s=new Solve(grid);
        return s.getSolvedBoard();
    }
    public static void main(String[] args) 
    {
        new SudokoGame();
    }
}