package ecosistema;

import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Simulacion extends JFrame implements ActionListener{
    Tablero tablero = new Tablero();
    private JLabel timeLabel;
    private JLabel dias;
    private byte seconds;
    public static byte totalSeconds=0;
    private short minutes;
    private DecimalFormat timeFormatter;
    public static Timer timer;
    private int cantDias=0;

    public Simulacion(){
        //definir caracterisitcas de la ventana 
        
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(tablero);
        add(tablero.start);
        add(tablero.pause);
        add(tablero.reanudar);
        add(tablero.culminar);
        timeLabel=new JLabel();
        add(timeLabel);
        dias=new JLabel();
        add(dias);
        seconds=00;
        minutes=00;
        timeLabel.setFont(new Font("Consolas",Font.PLAIN,34));
        timeLabel.setBounds(615, 270, 100, 30);
        dias.setFont(new Font("Consolas",Font.PLAIN,20));
        dias.setBounds(620, 230, 100, 30);
        setBackground(Color.white);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tablero.start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	timer.stop();
                seconds=00;
                minutes=00;
                timeLabel.setText(timeFormatter.format(minutes)+":"+timeFormatter.format(seconds));
                timer.start();
                tablero.start.setEnabled(false);
            }
    	});
        
        tablero.pause.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	timer.stop();
                tablero.pause.setEnabled(false);
                tablero.reanudar.setEnabled(true);
            }
    	});
        
        tablero.reanudar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	timer.start();
                tablero.pause.setEnabled(true);
            }
    	});
        
        tablero.culminar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	timer.stop();
                tablero.pause.setEnabled(false);
                tablero.reanudar.setEnabled(false);
                tablero.start.setEnabled(false);
            }
    	});
        
        timeFormatter=new DecimalFormat("00");
        timer=new Timer(1000,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
              
                    if(seconds>=0 && seconds<60){
                        seconds++;
                        totalSeconds++;
                    }else if(seconds>=60){
                        seconds=0;
                        totalSeconds++;
                        minutes++;
                    }
                timeLabel.setText(timeFormatter.format(minutes)+":"+timeFormatter.format(seconds));
                tablero.repaint();
                if(totalSeconds%24==0 && totalSeconds>0){
                   cantDias++;
                   dias.setText("Días: "+cantDias);
                }   
        
            }
        });
        timeLabel.setText(timeFormatter.format(minutes)+":"+timeFormatter.format(seconds));
        
        dias.setText("Días: "+cantDias);
        setVisible(true);
    }
 
    @Override
	public void actionPerformed(ActionEvent e){
	}

    //Main    
    public static void main(String[] args) {
        Simulacion simulacion = new Simulacion();
    }  
}