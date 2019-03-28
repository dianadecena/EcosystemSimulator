
package ecosistema;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Tablero extends JPanel{
    public SerVivo[][] ecosistema;
    private String texto, texto1, texto2, texto3;
    private int direccionAux;
    private boolean moverseAux;
    private int contador=0;
    JButton start; 
    JButton pause;
    JButton reanudar;
    JButton culminar;
    
    public Tablero(){
        //crear matriz de seres vivos
        ecosistema = new SerVivo[50][50];
        texto = "Oso Hormiguero (hembra)";
        texto1 = "Oso Hormiguero (macho)";
        texto2 = "Hormiga";
        texto3 = "Planta";
        //boton de comenzar 
        start = new JButton("Comenzar");
        Font fuente = new Font("Consolas", Font.PLAIN, 15);
        start.setFont(fuente);
        Color color = new Color(247,251,77);
        start.setBackground(color);
        Color color1 = new Color(49,49,49);
        start.setForeground(color1);
        start.setBounds(615, 320, 100, 30);
        
        //boton de pausar
        pause = new JButton("Pausar");
        pause.setFont(fuente);
        Color color2 = new Color(252,165,69);
        pause.setBackground(color2);
        pause.setForeground(color1);
        pause.setBounds(615, 370, 100, 30);
   
        //boton de reanudar 
        reanudar = new JButton("Reanudar");
        reanudar.setFont(fuente);
        Color color3 = new Color(205,160,245);
        reanudar.setBackground(color3);
        reanudar.setForeground(color1);
        reanudar.setBounds(615, 420, 100, 30);
        
        //boton de culminar 
        culminar = new JButton("Culminar");
        culminar.setFont(fuente);
        Color color4 = new Color(151,203,255);
        culminar.setBackground(color4);
        culminar.setForeground(color1);
        culminar.setBounds(615, 500, 100, 30);
        
        crearHormigas();
        crearOsos();
        crearPlantas();
        crearTierra();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        dibujar(g2);
    }
    
    public void dibujar(Graphics2D g){
        dibujarCasillas(g);
        info(g);
        
        //dibujar tierra
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                if(ecosistema[i][j] instanceof Tierra){
                    Color color = new Color(222,193,177);
                    g.setColor(color);
                    g.fill(((Tierra)(ecosistema[i][j])).getShape());
                }
            }
        }
        
        // dibujar hormigas
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                if(ecosistema[i][j] instanceof Hormiga){
                    Color color = new Color(49,49,49);
                    g.setColor(color);
                    g.fill(((Hormiga)(ecosistema[i][j])).getShape());
                }
            }
        }
        
        // dibujar osos hormigueros
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                if(ecosistema[i][j] instanceof OsoHormiguero){
                    if(((OsoHormiguero)(ecosistema[i][j])).getSexo().equals("hembra")){
                        Color color = new Color(200,0,0);
                        g.setColor(color);
                    }else{
                        Color color = new Color(40,54,227);
                        g.setColor(color);
                    }
                    g.fill(((OsoHormiguero)(ecosistema[i][j])).getShape());
                }
            }
        }
        
        // dibujar plantas 
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                if(ecosistema[i][j] instanceof Planta){
                    Color color = new Color(20,197,113);
                    g.setColor(color);
                    g.fill(((Planta)(ecosistema[i][j])).getShape());
                }
            }
        }
        if(contador > 1){
            update();
        }
        contador++;
    }
    
    //La hormiga apunta hacia arriba (1)
    public void moverHArriba(int i, int j, int num){ 
        if(j-1 < 0){
            switch(num){
                case 0:{
                    moverHDerecha(i, j, num); //3
                    break;
                }case 1: {
                    moverHIzquierda(i, j, num); //4
                    break;
                }
            }
        }else if(ecosistema[i][j-1] instanceof Tierra){
            ecosistema[i][j-1] = ecosistema[i][j];
            ((Hormiga)(ecosistema[i][j])).moverHormiga();
            ((Hormiga)(ecosistema[i][j])).setDireccionH(4);
            ecosistema[i][j] = new Tierra("tierra", i*11, j*11+11);
        }
        else if(ecosistema[i][j-1] instanceof Planta){
            
            
            //comer (mover y sobreescribir)
            ecosistema[i][j-1] = ecosistema[i][j];
            ((Hormiga)(ecosistema[i][j])).moverHormiga();
            ((Hormiga)(ecosistema[i][j])).setDireccionH(3);
            ecosistema[i][j] = new Tierra("tierra", i*11, j*11+11);
        }
        else if(ecosistema[i][j-1] instanceof Hormiga){
            //reproducir y nacer
        }
        else if(ecosistema[i][j-1] instanceof OsoHormiguero){
            //morir
        }
    }
    
    //La hormiga apunta hacia abajo (2)
    public void moverHAbajo(int i, int j, int num){ 
        if(j+1 > 49){
            switch(num){
                case 0:{
                    moverHDerecha(i, j, num);// 3
                    break;
                }case 1: {
                    moverHIzquierda(i, j, num);// 4
                    break;
                }
            }
        }else if(ecosistema[i][j+1] instanceof Tierra){
            ecosistema[i][j+1] = ecosistema[i][j];
            ((Hormiga)(ecosistema[i][j])).moverHormiga();
            ((Hormiga)(ecosistema[i][j])).setDireccionH(3);
            ecosistema[i][j] = new Tierra("tierra", i*11, j*11+11);
        }else if(ecosistema[i][j+1] instanceof Planta){
            //alimentarse
            ecosistema[i][j+1] = ecosistema[i][j];
            ((Hormiga)(ecosistema[i][j])).moverHormiga();
            ((Hormiga)(ecosistema[i][j])).setDireccionH(4);
            ecosistema[i][j] = new Tierra("tierra", i*11, j*11+11);
        }else if(ecosistema[i][j+1] instanceof Hormiga){
            //reproducir
        }else if(ecosistema[i][j+1] instanceof OsoHormiguero){
            //morir
        }
    }
    
    //La hormiga apunta hacia la derecha (3)
    public void moverHDerecha(int i, int j, int num){ 
        if(i+1 > 49){
                switch(num){
                    case 0:{
                        moverHArriba(i, j, num);// 1
                        break;
                    }case 1: {
                        moverHAbajo(i, j, num);// 2
                        break;
                    }
                }
            }else if(ecosistema[i+1][j] instanceof Tierra){
            ecosistema[i+1][j] = ecosistema[i][j];
            ((Hormiga)(ecosistema[i][j])).moverHormiga();
            ((Hormiga)(ecosistema[i][j])).setDireccionH(1);
            ecosistema[i][j] = new Tierra("tierra", i*11, j*11+11);
        }else if(ecosistema[i+1][j] instanceof Planta){
            //comer
            ecosistema[i+1][j] = ecosistema[i][j];
            ((Hormiga)(ecosistema[i][j])).moverHormiga();
            ((Hormiga)(ecosistema[i][j])).setDireccionH(2);
            ecosistema[i][j] = new Tierra("tierra", i*11, j*11+11);
        }else if(ecosistema[i+1][j] instanceof Hormiga){
            //reproducir
        }else if(ecosistema[i+1][j] instanceof OsoHormiguero){
            //morir
        }
    }
    
    // La hormiga apunta hacia la izquierda (4)
    public void moverHIzquierda(int i, int j, int num){ 
        if(i-1 < 0){
            switch(num){
                case 0:{
                    moverHArriba(i, j, num);// 1
                    break;
                }case 1: {
                    moverHAbajo(i, j, num);// 2
                    break;
                }
            }
        }else if(ecosistema[i-1][j] instanceof Tierra){
            ecosistema[i-1][j] = ecosistema[i][j];
            ((Hormiga)(ecosistema[i][j])).moverHormiga();
            ((Hormiga)(ecosistema[i][j])).setDireccionH(2);
            ecosistema[i][j] = new Tierra("tierra", i*11, j*11+11);
        }else if(ecosistema[i-1][j] instanceof Planta){
            //comer
            ecosistema[i-1][j] = ecosistema[i][j];
            ((Hormiga)(ecosistema[i][j])).moverHormiga();
            ((Hormiga)(ecosistema[i][j])).setDireccionH(1);
            ecosistema[i][j] = new Tierra("tierra", i*11, j*11+11);
        }else if(ecosistema[i-1][j] instanceof Hormiga){
            //reproducir
        }else if(ecosistema[i-1][j] instanceof OsoHormiguero){
            //morir
        }
    }
    
    //El oso apunta hacia arriba (1)
    public void moverOArriba(int i, int j, int num){
        if(j-1 < 0){
            switch(num){
                case 0:{
                    moverODerecha(i, j, num);
                    break;
                }case 1: {
                    moverOIzquierda(i, j, num);
                    break;
                }
            }
        }else if(ecosistema[i][j-1] instanceof Tierra || ecosistema[i][j-1] instanceof Planta){
            ecosistema[i][j-1] = ecosistema[i][j];
            ((OsoHormiguero)(ecosistema[i][j])).moverOso();
            ((OsoHormiguero)(ecosistema[i][j])).setDireccionO(4);
            ecosistema[i][j] = new Tierra("tierra", i*11, j*11+11);
        }else if(ecosistema[i][j-1] instanceof Hormiga){
            //comer
        }
        else if(ecosistema[i][j] instanceof OsoHormiguero){
            //reproducir o pelear
        }
    }
    //El oso apunta hacia abajo (2)
    public void moverOAbajo(int i, int j, int num){
        if(j+1 > 49){
            switch(num){
                case 0:{
                    moverODerecha(i, j, num);
                    break;
                }case 1: {
                    moverOIzquierda(i, j, num);
                    break;
                }
            }
        }else if(ecosistema[i][j+1] instanceof Tierra || ecosistema[i][j+1] instanceof Planta){
            ecosistema[i][j+1] = ecosistema[i][j];
            ((OsoHormiguero)(ecosistema[i][j])).moverOso();
            ((OsoHormiguero)(ecosistema[i][j])).setDireccionO(3);
            ecosistema[i][j] = new Tierra("tierra", i*11, j*11+11);
        }else if(ecosistema[i][j+1] instanceof Hormiga){
            //comer
        }
        else if(ecosistema[i][j] instanceof OsoHormiguero){
            //reproducir o pelear
        }
    }
    //El oso apunta hacia la derecha (3)
    public void moverODerecha(int i, int j, int num){
        if(i+1 > 49){
            switch(num){
                case 0:{
                    moverOArriba(i, j, num);
                    break;
                }case 1: {
                    moverOAbajo(i, j, num);
                    break;
                }
            }
        }else if(ecosistema[i+1][j] instanceof Tierra || ecosistema[i+1][j] instanceof Planta){
            ecosistema[i+1][j] = ecosistema[i][j];
            ((OsoHormiguero)(ecosistema[i][j])).moverOso();
            ((OsoHormiguero)(ecosistema[i][j])).setDireccionO(1);
            ecosistema[i][j] = new Tierra("tierra", i*11, j*11+11);
        }else if(ecosistema[i+1][j] instanceof Hormiga){
            //comer
        }
        else if(ecosistema[i][j] instanceof OsoHormiguero){
            //reproducir o pelear
        }
    }
    //El oso apunta hacia la izquierda (4)
    public void moverOIzquierda(int i, int j, int num){
        if(i-1 < 0){
            switch(num){
                case 0:{
                    moverOArriba(i, j, num);
                    break;
                }case 1: {
                    moverOAbajo(i, j, num);
                    break;
                }
            }
        }else if(ecosistema[i-1][j] instanceof Tierra || ecosistema[i-1][j] instanceof Planta){
            ecosistema[i-1][j] = ecosistema[i][j];
            ((OsoHormiguero)(ecosistema[i][j])).moverOso();
            ((OsoHormiguero)(ecosistema[i][j])).setDireccionO(2);
            ecosistema[i][j] = new Tierra("tierra", i*11, j*11+11);
        }else if(ecosistema[i-1][j] instanceof Hormiga){
            //comer
        }
        else if(ecosistema[i][j] instanceof OsoHormiguero){
            //reproducir o pelear
        }
    }
    
    // actualizar posiciones de las hormigas y osos hormigueros
    private void update() {
        int num;
        num = (int)(Math.random());
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                if(ecosistema[i][j] instanceof Hormiga){
                    moverseAux = ((Hormiga)(ecosistema[i][j])).isMoverse();
                    if(moverseAux == true){
                        direccionAux = ((Hormiga)(ecosistema[i][j])).getDireccionH();
                        switch(direccionAux){
                            case 1:{ //La hormiga apunta arriba
                                moverHArriba(i, j, num);
                                break;
                            }case 2:{ //La hormiga apunta abajo
                                moverHAbajo(i, j, num);
                                break;
                            }case 3:{ //La hormiga apunta derecha
                                moverHDerecha(i, j, num);
                                break;
                            }case 4:{ //La hormiga apunta izquierda
                                moverHIzquierda(i, j, num);
                                break;
                            }
                        }//((Hormiga)(ecosistema[i][j])).setMoverse(false);
                    }
                }
                else if(ecosistema[i][j] instanceof OsoHormiguero){
                    moverseAux = ((OsoHormiguero)(ecosistema[i][j])).isMoverse();
                    if(moverseAux == true){
                        direccionAux = ((OsoHormiguero)(ecosistema[i][j])).getDireccionO();
                        switch(direccionAux){
                            case 1:{ //El oso apunta arriba
                                moverOArriba(i, j, num);
                                break;
                            }case 2:{ //El oso apunta abajo
                                moverOAbajo(i, j, num);
                                break;
                            }case 3:{ //El oso apunta derecha
                                moverODerecha(i, j, num);
                                break;
                            }case 4:{ //El oso apunta izq
                                moverOIzquierda(i, j, num);
                                break;
                            }
                        }//((OsoHormiguero)(ecosistema[i][j])).setMoverse(false);
                    }
                }if(i==49 && j==49){
                    setTrue();
                }
            }
        } 
        
        /*if(totalSeconds%48==0 && totalSeconds>0){
         for(int i=0; i<50; i++){
           for(int j=0; j<50; j++){
               if(ecosistema[i][j] instanceof Planta){
                   if(i<49){
                       if(ecosistema[i+1][j] instanceof Tierra){
                       ecosistema[i+1][j] = new Planta("planta", (i*11)+1, j*11+11);
                   }
                   }
                   if(j<49){
                       if(ecosistema[i][j+1] instanceof Tierra){
                       ecosistema[i][j+1] = new Planta("planta", i*11, (j*11+11)+1);
                   }
                   }
                   if(j>0){
                       if(ecosistema[i][j-1] instanceof Tierra){
                       ecosistema[i][j-1] = new Planta("planta", i*11, (j*11+11)-1);
                   }
                   }
                   if(i>0){
                       if(ecosistema[i-1][j] instanceof Tierra){
                       ecosistema[i-1][j] = new Planta("planta", (i*11)-1, j*11+11);
                   }
                   }
               }
           }
        }
     }*/
    }
    
    //Permitir movimiento
    public void setTrue(){
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                if(ecosistema[i][j] instanceof Hormiga){
                    ((Hormiga)(ecosistema[i][j])).setMoverse(true);
                }else if(ecosistema[i][j] instanceof OsoHormiguero){
                    ((OsoHormiguero)(ecosistema[i][j])).setMoverse(true);
                }
            }
        }
    }
    
    //dibujar cuadriculas del tablero
    public void dibujarCasillas(Graphics g){
     for(int i=0; i<50; i++){
        for(int j=0; j<50; j++){
          int pos_x = i*11;
          int pos_y = j*11+11;
          Color color = new Color(199,199,199);
          g.setColor(color);
          g.drawRect(pos_x, pos_y, 11, 11);
        }
      }
    }
    
    //informacion sobre las figuras del tablero en el lado derecho
    public void info(Graphics2D g){
        Color color = new Color(125,240,206);
        g.setColor(color);
        RoundRectangle2D rec = new RoundRectangle2D.Double(570, 30, 200, 200, 10, 10);
        g.fill(rec);
        Font fuente = new Font("Consolas", Font.PLAIN, 13);
        g.setFont(fuente);
        g.setColor(Color.black);
        g.drawString(texto, 600, 55);
        Color color1 = new Color(200,0,0);
        g.setColor(color1);
        g.fillRect(585, 45, 13, 13);
        Color color2 = new Color(40,54,227);
        g.setColor(color2);
        g.fillRect(585, 75, 13, 13);
        g.setFont(fuente);
        g.setColor(Color.black);
        g.drawString(texto1, 600, 85);
        Color color3 = new Color(49,49,49);
        g.setColor(color3);
        Ellipse2D elli = new Ellipse2D.Double(585,105,13,13);
        g.fill(elli);
        g.setFont(fuente);
        g.setColor(Color.black);
        g.drawString(texto2, 600, 115);
        Color color4 = new Color(22,190,106);
        g.setColor(color4);
        g.fillRoundRect(585, 135, 13, 13, 2, 0);
        g.setFont(fuente);
        g.setColor(Color.black);
        g.drawString(texto3, 600, 145);
    }
    
    //dibujar osos hormigueros 
    public void crearOsos(){
        //hembras
        int ososHembras = 0; 
        while(ososHembras < 2){
                int x = numAleatorio();
                int y = numAleatorio();
                    if(ecosistema[x][y]==null){
                       ecosistema[x][y] = new OsoHormiguero("oso1","hembra", x*11, y*11+11);
                       ososHembras++;
                    }
        }
        
        //machos
        int ososMachos = 0; 
        while(ososMachos < 2){
                int x = numAleatorio();
                int y = numAleatorio();
                for(int i=x; i<50; i++){
                for(int j=y; j<50; j++){
                    if(ecosistema[x][y]==null){
                       ecosistema[x][y] = new OsoHormiguero("oso1","macho", i*11, j*11+11);
                       ososMachos++;
                    }
                }
            }
        }
    }
    
    //crear hormigas 
    public void crearHormigas(){
        int hormigas = 0; 
        while(hormigas < 3){
                int x = numAleatorio();
                int y = numAleatorio();
                for(int i=x; i<50; i++){
                for(int j=y; j<50; j++){
                    if(ecosistema[x][y]==null){
                       ecosistema[x][y] = new Hormiga("hormiga", i*11, j*11+11);
                       hormigas++;
                    }
                }
            }
        }
    }
     
    //dibujar plantas en el 40% de las cuadriculas 
    public void crearPlantas(){
        int plantas = 0; 
        while(plantas < 1000){
            int x = numAleatorio();
            int y = numAleatorio();
            for(int i=x; i<50; i++){
                for(int j=y; j<50; j++){
                    if(ecosistema[x][y]==null){
                       ecosistema[x][y] = new Planta("planta", i*11, j*11+11);
                       plantas++;
                    } 
                }
            }      
        }
    }
    
    public void crearTierra(){
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                if(ecosistema[i][j]==null){
                    ecosistema[i][j] = new Tierra("tierra", i*11, j*11+11);
                } 
            }
        }      
    }
     
    //calcular numero aleatorio entre 0 y 50 para las posiciones 
    public int numAleatorio(){
        int num=(int)(Math.random()*50);
        return num;
    }  
}