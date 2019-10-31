package POJO;
/**
 * @author Nathaly Salazar
 * @author Cristian Romero
 */
public class PadrePOJO {
    private int idp;
    private int documento;
    private String nombre;

    public PadrePOJO() {
    }

    public PadrePOJO(int documento, String nombre) {
        this.documento = documento;
        this.nombre = nombre;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
