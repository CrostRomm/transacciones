/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entity.Persona;
import Entity.Persona2;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hp-14
 */
@Local
public interface PersonaFacadeLocal {

    void create(Persona persona);

    void edit(Persona persona);

    void remove(Persona persona);

    Persona find(Object id);

    List<Persona> findAll();

    List<Persona> findRange(int[] range);

    int count();
    
    public void padreRequiredNoTransaccional(Persona p1, Persona p2);
    
    public void requiresNewNoTransaccional(Persona p1, Persona p2);
    
    public void requiresNewModificaNoTransaccional(Persona p2);
    
    public String mandatoryNoTransaccional(Persona p1, Persona p2);
    
    public void supportNoTransaccional(Persona p1);
    
    public String supportNoTransaccionalBD(Persona p1);
    
    public void noSupportNoTransaccional(Persona p1);
    
    public String noSupportNoTransaccionalBD(Persona p1);
    
    public void neverNoTransaccional(Persona p1);
    
    public String neverNoTransaccionalBD(Persona p1);
    
    public void requiredTransaccional(Persona p1);
}
