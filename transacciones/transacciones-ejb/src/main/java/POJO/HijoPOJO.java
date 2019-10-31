package POJO;
/**
 * @author Nathaly Salazar
 * @author Cristian Romero
 */
public class HijoPOJO {
    private int idh;
    private int documento;
    private String nombre;
    private int idPadre;

    public HijoPOJO() {
    }

    public HijoPOJO(int documento, String nombre, int idPadre) {
        this.documento = documento;
        this.nombre = nombre;
        this.idPadre = idPadre;
    }

    public int getIdh() {
        return idh;
    }

    public void setIdh(int idh) {
        this.idh = idh;
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

    public int getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }
    
    
}
