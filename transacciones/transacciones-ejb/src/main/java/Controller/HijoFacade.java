package Controller;

import Entity.Hijo;
import Default.AbstractFacade;
import Interfaces.HijoFacadeLocal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hp-14
 */
@Stateless
public class HijoFacade extends AbstractFacade<Hijo> implements HijoFacadeLocal {

    @PersistenceContext(unitName = "persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HijoFacade() {
        super(Hijo.class);
    }
    
}
