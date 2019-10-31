package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * @author Nathaly Salazar
 * @author Cristian Romero
 */
@Entity
@Table(name="hijo")
public class Hijo implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int idh;

private int documento;

private String nombre;

 @OneToOne
 @JoinColumn(name="id_padre")
private Persona padre;

    public Hijo() {
    }

    public Hijo(int documento, String nombre, Persona padre) {
        this.documento = documento;
        this.nombre = nombre;
        this.padre = padre;
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

    public Persona getPadre() {
        return padre;
    }

    public void setPadre(Persona padre) {
        this.padre = padre;
    }
 
    
}
