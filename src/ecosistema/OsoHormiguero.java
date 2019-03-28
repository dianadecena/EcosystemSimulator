
package ecosistema;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class OsoHormiguero extends SerVivo implements Interface {
    private String sexo;
    private double pos_x;
    private double pos_y;
    private static final int TAMX = 11;
    private static final int TAMY = 11;
    private int direccionO = 1;
    private boolean moverse = true;

    public OsoHormiguero(String nombre, String sexo, int pos_x, int pos_y) {
        super(nombre);
        this.sexo = sexo; 
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    public String getSexo() {
        return sexo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
    
    public Rectangle2D getShape() {
        return new Rectangle2D.Double(pos_x, pos_y, TAMX, TAMY);
    }
    
    //Interacciones
    @Override
    public void nacer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void alimentarse(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void morir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reproducirse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Movimiento
    public int getDireccionO() {
        return direccionO;
    }

    public void setDireccionO(int direccionO) {
        this.direccionO = direccionO;
    }

    public boolean isMoverse() {
        return moverse;
    }

    public void setMoverse(boolean moverse) {
        this.moverse = moverse;
    }
    
    //Animacion
    public void moverOso(){
        switch(direccionO){
            case 1:{ //Subir
                pos_y = pos_y - 11;
                break;
            }
            case 2:{ //Bajar
                pos_y = pos_y + 11;
                break;
            }
            case 3:{ //Derecha
                pos_x = pos_x + 11;
                break;
                
            }
            case 4:{ //Izq
                pos_x = pos_x - 11;
                break;
            }
        }
    }
}
