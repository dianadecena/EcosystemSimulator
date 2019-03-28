
package ecosistema;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Hormiga extends SerVivo implements Interface {
    private double pos_x;
    private double pos_y;
    private static final int TAMX = 11;
    private static final int TAMY = 11;
    private int dx = 1;
    private int direccionH = 1;
    private boolean moverse=true;

    public Hormiga(String nombre, int pos_x, int pos_y) {
        super(nombre);
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
 
    //Interacciones
    @Override
    public void nacer(){
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
    
    public Ellipse2D getShape() {
        return new Ellipse2D.Double(pos_x, pos_y, TAMX, TAMY);
    }
    
    //Movimiento
    public int getDireccionH() {
        return direccionH;
    }

    public void setDireccionH(int direccionH) {
        this.direccionH = direccionH;
    }

    public boolean isMoverse() {
        return moverse;
    }

    public void setMoverse(boolean moverse) {
        this.moverse = moverse;
    }
    
    //Animacion
    public void moverHormiga(){
        switch(direccionH){
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
