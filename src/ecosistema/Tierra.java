package ecosistema;

import java.awt.geom.Rectangle2D;

public class Tierra extends SerVivo implements Interface{
    private double pos_x;
    private double pos_y;
    private static final int TAMX = 11;
    private static final int TAMY = 11;

    public Tierra(String nombre, int pos_x, int pos_y) {
        super(nombre);
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public Rectangle2D getShape() {
        return new Rectangle2D.Double(pos_x, pos_y, TAMX, TAMY);
    }

    @Override
    public void nacer() {
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
    
    @Override
    public void alimentarse(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
