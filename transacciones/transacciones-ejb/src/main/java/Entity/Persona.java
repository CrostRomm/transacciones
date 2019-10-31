package Entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * @author Nathaly Salazar
 * @author Cristian Romero
 */
@Entity
@Table(name="persona")
public class Persona implements Serializable{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idp;
    
    private int documento;
    
    private String nombre;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "padre")
    private Hijo hijo;

    public Persona() {
    }

    public Persona(int idp, int documento, String nombre) {
        this.idp = idp;
        this.documento = documento;
        this.nombre = nombre;
    }
    
    
    
    public Persona(int documento, String nombre) {
        this.documento = documento;
        this.nombre = nombre;
    }

    public int getId() {
        return idp;
    }

    public void setId(int idp) {
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
