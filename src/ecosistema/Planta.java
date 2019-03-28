
package ecosistema;

import java.awt.geom.RoundRectangle2D;

public class Planta extends SerVivo implements Interface{ 
    private double pos_x;
    private double pos_y;
    private static final int TAMX = 11;
    private static final int TAMY = 11;
    private boolean morir = true;

    public Planta(String nombre ,double pos_x, double pos_y) {
        super(nombre);
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
    
    public RoundRectangle2D getShape() {
        return new RoundRectangle2D.Double(pos_x, pos_y, TAMX, TAMY, 2, 2);
    }
    
    //Interacciones
    @Override
    public void nacer(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void morir() {
        morir = false;
    }

    public boolean isMorir() {
        return morir;
    }

    @Override
    public void reproducirse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void alimentarse(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
