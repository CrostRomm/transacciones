/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entity.Hijo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hp-14
 */
@Local
public interface HijoFacadeLocal {

    void create(Hijo hijo);

    void edit(Hijo hijo);

    void remove(Hijo hijo);

    Hijo find(Object id);

    List<Hijo> findAll();

    List<Hijo> findRange(int[] range);

    int count();
    
}
