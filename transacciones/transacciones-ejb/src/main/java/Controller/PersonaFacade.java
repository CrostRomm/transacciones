/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Persona;
import Default.AbstractFacade;
import Entity.Persona2;
import Interfaces.Persona2FacadeLocal;
import Interfaces.PersonaFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hp-14
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PersonaFacade extends AbstractFacade<Persona> implements PersonaFacadeLocal {

    @EJB
    Persona2FacadeLocal padre2Ejb;
    
    @PersistenceContext(unitName = "persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void padreRequiredNoTransaccional(Persona p1, Persona p2){
        em.persist(p1);
        em.persist(p2);
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void requiresNewNoTransaccional(Persona p1, Persona p2){
        em.persist(p1); 
        requiresNewModificaNoTransaccional(p2); //Metodo independiente, si hay una excepcion aquí después de invocarlo hace rollback "requiresNewNoTransaccional"
        //Una excepción ocurre aquí! La orden de compra tiene un rollback, 
        // pero la actualización de la cuenta no!
        
        
        //El atributo de transacción REQUIRES_NEW sólo debe usarse 
        //si la acción sobre la base de datos necesita guardarse sin 
        //importar el resultado de la transacción subyacente.

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void requiresNewModificaNoTransaccional(Persona p2) {
        em.merge(p2);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public String mandatoryNoTransaccional(Persona p1, Persona p2) {
        try {
            em.persist(p1);
            em.persist(p2);
        } catch (Exception e) {
            return e.getClass().getName();
        }
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void supportNoTransaccional(Persona p1){
        p1 = new Persona(05153,"support ejemplo");
        int i= 1;
        i += 3+4; 
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public String supportNoTransaccionalBD(Persona p1) {
        try {
            em.persist(p1);
            em.persist(new Persona(99,"Aurelio Cheveroni"));
        } catch (Exception e) {
            return e.getClass().getName();
        }
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void noSupportNoTransaccional(Persona p1) {
        p1 = new Persona(05153,"support ejemplo");
        int i= 1;
        i += 3+4; 
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public String noSupportNoTransaccionalBD(Persona p1) {
        try {
            em.persist(p1);
            em.persist(new Persona(99,"Aurelio Cheveroni"));
        } catch (Exception e) {
            return e.getClass().getName();
        }
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void neverNoTransaccional(Persona p1) {
        p1 = new Persona(05153,"support ejemplo");
        int i= 1;
        i += 3+4; 
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public String neverNoTransaccionalBD(Persona p1) {
        try {
            em.persist(p1);
            em.persist(new Persona(99,"Aurelio Cheveroni"));
        } catch (Exception e) {
            return e.getClass().getName();
        }
        return null;
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void requiredTransaccional(Persona p1){
        Persona2 p2 = new Persona2(999,"Juanito Estrella");
        Persona2 p3 = new Persona2(777,"Miguelito Coco");
        try{    
            em.persist(p1);
            padre2Ejb.required2Transaccional(p2, p3);
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Required Error en EJB2", "Se logro registrar el usuario: "+p1.getNombre()+", pero los otros usuarios: "+p2.getNombre()+" y "+p3.getNombre()+" no pudieron registrarse!!"));
        }
        
    }
}
