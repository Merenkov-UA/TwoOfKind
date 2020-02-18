package twoofkind;


import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;




public class TwoOfKind extends JFrame{
    private final int N = 4;
    private  static Timer timer, gTimer;
    TimeTick tick;
    private  static JLabel shownLabel_1, shownLabel_2,timeLabel;
    private static char[] symbols;
    private Random rnd;
    private static int score=0, i=0;
   
    
    public static void main(String[] args) {
        (new TwoOfKind()).setVisible( true );
        
    }
    
    int[] pairs;
    
    public TwoOfKind(){
        super("Two of a Kind");
        this.setBounds(500, 300, 500, 550);        
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	
	symbols = new char[N*N];
           for(int i = 0; i < N*N; i+=2)
           {
               symbols[i]= (char) (0x0533+i);
               symbols[i+1]= (char) (0x0533+i);
           }
           rnd = new Random();
           int n1, n2;
           char c;
           for(int i = 0; i< N*N; i += 2)
           {
                n1 = rnd.nextInt(N*N);
                do{n2 = rnd.nextInt(N*N);}while(n1==n2);
                
                c = symbols[n1];
                symbols[n1] = symbols[n2];
                symbols[n2]= c;
           }
           
              
        shownLabel_1 = shownLabel_2 = null;
        timer = new Timer(1000, new TimerTick());
        tick = new TimeTick();
        gTimer = new Timer(1000, tick);
        timeLabel = new JLabel("Time: " + i);
       
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        JPanel statPanel = new JPanel();
        statPanel.setLayout(new GridLayout(1,6,5,0));
        statPanel.setMinimumSize( new Dimension( 500, 50 ) ) ;
       
        statPanel.add(timeLabel);
        final JLabel txt2 = new JLabel( "Score: " ) ;
        txt2.setOpaque(true);
        txt2.setBackground(Color.yellow);
        statPanel.add( txt2 ) ;
        
      
        
        JPanel gameField = new JPanel();        
        gameField.setLayout( new GridLayout(N, N, 4, 4) ) ;
                
        Font font;
        font = new Font("STIXGeneral",Font.PLAIN, 30);
        for(int i = 0; i < N*N; ++i)
       
        {
            
          
            final JLabel tmp = new JLabel("" + symbols[i]);
           
            tmp.setOpaque(true);
            tmp.setBackground(java.awt.Color.DARK_GRAY);
            tmp.setForeground(java.awt.Color.DARK_GRAY);
            tmp.setFont(font);
            tmp.setHorizontalAlignment(JLabel.CENTER);
           
            tmp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent me){
                    gTimer.start();
                   if(shownLabel_1 == null){
                        tmp.setForeground(Color.RED);
                        shownLabel_1 = tmp;
                        
                   }
                   else if(shownLabel_2 == null && shownLabel_1 != tmp){
                       tmp.setForeground(Color.RED);
                        shownLabel_2 = tmp;
                        timer.start();
                        
                   }
                   
                   if(shownLabel_1!= null &&shownLabel_2!= null)
                     if(shownLabel_1.getText().equals(shownLabel_2.getText()))
                 {
                    shownLabel_1.setForeground(Color.GREEN);
                    shownLabel_2.setForeground(Color.GREEN);
                    score +=10;
                    timer.stop();
                    shownLabel_1 = shownLabel_2 = null;
                    txt2.setText("Score: " + score);
                
                 }
                     if(score == 80)
                     {
                         JOptionPane.showMessageDialog(null, "You win!");
                        
                     }                    
                }                
            });            
            gameField.add(tmp);            
        }
        gameField.setPreferredSize( new Dimension( 500, 500 ) ) ;
        mainPanel.add( statPanel ) ;
        mainPanel.add( gameField ) ;
        
        this.setContentPane(mainPanel);
      
        
    }
    
  
    private static class TimerTick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
                shownLabel_1.setForeground(Color.DARK_GRAY);
                shownLabel_2.setForeground(Color.DARK_GRAY);
                timer.stop();
                shownLabel_1 = shownLabel_2 = null;
            
        }
            
    }
    private static class TimeTick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            i++;
            timeLabel.setText("Time: "+i);
        }
            
    }
    
    
    
    
}
