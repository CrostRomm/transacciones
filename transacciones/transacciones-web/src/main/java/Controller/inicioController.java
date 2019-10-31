package Controller;

import Entity.Hijo;
import Entity.Persona;
import Interfaces.HijoFacadeLocal;
import Interfaces.PersonaFacadeLocal;
import POJO.HijoPOJO;
import POJO.PadrePOJO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
/**
 * @author Nathaly Salazar
 * @author Cristian Romero
 */
@ManagedBean(name="bean")
@ViewScoped
public class inicioController implements Serializable{
    
    @EJB
    private PersonaFacadeLocal padreEjb;
    
    @EJB
    private HijoFacadeLocal hijoEjb;
    
    private List<Persona> listaPadre;
    
    private List<Hijo> listaHijo; 
    
    private PadrePOJO pojoP;
    
    private HijoPOJO pojoH;
    
    @PostConstruct
    public void init(){
        listaPadre = new ArrayList();
        listaHijo = new ArrayList();
        pojoH = new HijoPOJO();
        pojoP = new PadrePOJO();
    }
    /**
     * Constructor
     */
    public inicioController() {
    }

    public List<Persona> getListaPadre() {
        return listaPadre;
    }

    public void setListaPadre(List<Persona> listaPadre) {
        this.listaPadre = listaPadre;
    }

    public List<Hijo> getListaHijo() {
        return listaHijo;
    }

    public void setListaHijo(List<Hijo> listaHijo) {
        this.listaHijo = listaHijo;
    }

    public PadrePOJO getPojoP() {
        return pojoP;
    }

    public void setPojoP(PadrePOJO pojoP) {
        this.pojoP = pojoP;
    }

    public HijoPOJO getPojoH() {
        return pojoH;
    }

    public void setPojoH(HijoPOJO pojoH) {
        this.pojoH = pojoH;
    }

    public PersonaFacadeLocal getPadreEjb() {
        return padreEjb;
    }

    public void setPadreEjb(PersonaFacadeLocal padreEjb) {
        this.padreEjb = padreEjb;
    }

    public HijoFacadeLocal getHijoEjb() {
        return hijoEjb;
    }

    public void setHijoEjb(HijoFacadeLocal hijoEjb) {
        this.hijoEjb = hijoEjb;
    }
    /**
     * Listar Padre
     * @return lista de padres
     */
    public List<Persona> pintarPadre(){
        listaPadre = padreEjb.findAll();
        return listaPadre; 
    }
    /**
     * Listar hijo
     * @return lista de hijos
     */
    public List<Hijo> pintarHijo(){
        listaHijo = hijoEjb.findAll();
        return listaHijo; 
    }
    
    public void requiredNoTransaccional(){
        try {
            Persona p1 = new Persona(pojoP.getDocumento(), pojoP.getNombre());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registro: "," "+pojoP.getNombre()));
            Persona p2 = new Persona(pojoP.getDocumento(), pojoP.getNombre());
            padreEjb.padreRequiredNoTransaccional(p1,p2);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Required Error", "este era un ejemplo donde se intenta insertar el usuario #1: "+pojoP.getNombre()+" y luego el usuario #2: "+pojoP.getNombre()));
        }
    }
    
    public void requiredNewNoTransaccional(){
        Persona p2 = padreEjb.find(2);
        try{
            Persona p1 = new Persona(pojoP.getDocumento(), pojoP.getNombre());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registro: "," "+pojoP.getNombre()));
            p2 = new Persona(33,"Prueba required");
            padreEjb.requiresNewNoTransaccional(p1,p2); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se edito "," La persona: "+pojoP.getNombre()+", ahora se llama: "+p2.getNombre()));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Requires_new Error", "Se inserto: "+pojoP.getNombre()+", pero se intentó editar un usuario con datos que ya exisitian: nombre - "+p2.getNombre()+", documento -"+p2.getDocumento()+". Lo cuál no se pudo completar"));
        }
    }
    public void mandatoryNoTransaccional(){
        try{
            Persona p1 = new Persona(pojoP.getDocumento(), pojoP.getNombre());
            Persona p2 = new Persona(pojoP.getDocumento(), pojoP.getNombre());
            String error = padreEjb.mandatoryNoTransaccional(p1,p2);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Mandatory Error"," El error que se obtuvo fue: "+error));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Mandatory Error"," El error que se obtuvo fue: "+e.getClass().getName()));
        }   
    }
    public void supportNoTransaccional(){
        try {
            Persona p1 = new Persona(pojoP.getDocumento(), pojoP.getNombre());
            padreEjb.supportNoTransaccional(p1);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Support logico"," Todo salio bien"));
        }catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Support logico"," Hubo un error revise el codigo"));
        }
    }
    public void supportNoTransaccionalBD(){
        try{
            Persona p1 = new Persona(pojoP.getDocumento(), pojoP.getNombre());
            String error = padreEjb.supportNoTransaccionalBD(p1);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Support BD "," El error que se obtuvo fue: "+error));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Support BD "," El error que se obtuvo fue: "+e.getClass().getName()));
        }   
    }
    public void noSupportNoTransaccional(){
        try {
            Persona p1 = new Persona(pojoP.getDocumento(), pojoP.getNombre());
            padreEjb.noSupportNoTransaccional(p1);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No Supported logico"," Todo salio bien"));
        }catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "No Supported logico"," Hubo un error revise el codigo"));
        }
    }
    public void noSupportNoTransaccionalBD(){
        try{
            Persona p1 = new Persona(pojoP.getDocumento(), pojoP.getNombre());
            String error = padreEjb.noSupportNoTransaccionalBD(p1);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "No Supported BD "," El error que se obtuvo fue: "+error));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "No Supported BD "," El error que se obtuvo fue: "+e.getClass().getName()));
        }   
    }
    
    public void neverNoTransaccional(){
        try {
            Persona p1 = new Persona(pojoP.getDocumento(), pojoP.getNombre());
            padreEjb.noSupportNoTransaccional(p1);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Never Lógico"," Todo salio bien"));
        }catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Never Lógico"," Hubo un error revise el codigo"));
        }
    }
    
    public void neverNoTransaccionalBD(){
        try{
            Persona p1 = new Persona(pojoP.getDocumento(), pojoP.getNombre());
            String error = padreEjb.noSupportNoTransaccionalBD(p1);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Never BD"," El error que se obtuvo fue: "+error));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Never BD"," El error que se obtuvo fue: "+e.getClass().getName()));
        }   
    }
    public void requiredTransaccional(){
        try {
            Persona p1 = new Persona(pojoP.getDocumento(), pojoP.getNombre());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Required Transaciconal - Se registro: "," "+pojoP.getNombre()));
            padreEjb.requiredTransaccional(p1);
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Required Error", "Se hizo rollback a causa de un error"));
        }
    }
    public void requiresNewTransaccional(){
        try {
            Persona p1 = new Persona(pojoP.getDocumento(), pojoP.getNombre());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Required Transaciconal - Se registro: "," "+pojoP.getNombre()));
            padreEjb.requiredTransaccional(p1);
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Required Error", "Se hizo rollback a causa de un error"));
        }
    }
}
