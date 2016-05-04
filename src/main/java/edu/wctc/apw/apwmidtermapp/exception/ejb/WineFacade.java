/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.exception.ejb;

import edu.wctc.apw.apwmidtermapp.model.Wine;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andre_000
 */
@Stateless
public class WineFacade extends AbstractFacade<Wine> {

    @PersistenceContext(unitName = "edu.wctc.apw_apwMidTermApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WineFacade() {
        super(Wine.class);
    }
    
}
