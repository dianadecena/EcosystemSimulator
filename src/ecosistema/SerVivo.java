
package ecosistema;

public abstract class SerVivo {
    protected String nombre;

    public SerVivo(String nombre) {
        this.nombre = nombre;
        
    }

    public String getNombre() {
        return nombre;
    }
    
    public abstract void nacer();
    
    public abstract void alimentarse();
}

