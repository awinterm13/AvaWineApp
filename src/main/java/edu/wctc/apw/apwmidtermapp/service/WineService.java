/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.service;

import edu.wctc.apw.apwmidtermapp.repository.WineRespository;
import edu.wctc.apw.apwmidtermapp.entity.Wine;
import edu.wctc.apw.apwmidtermapp.exception.ParameterMissingException;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class acts as the manager class over the WineRepository
 * @author andre_000
 */
@Repository
@Transactional(readOnly = true)
public class WineService {
    private transient final Logger LOG = LoggerFactory.getLogger(WineService.class);

    @Inject
    private WineRespository wineRepo;

    
/**
 * Default constructor needed for injection
 */
    public WineService() {
    }
/**
 *  A method that Calls the WineRepositories findAll Method.
 * @return List of wine objects
 */
    public List<Wine> findAll() {
        return wineRepo.findAll();
    }
    
//    /**
//     * This custom method is necessary because we are using Hibernate which
//     * does not load lazy relationships (in this case Books).
//     * @param id
//     * @return 
//     */
//    public Wine findByIdAndFetchBooksEagerly(String id) {
//        Integer wineId = new Integer(id);
//        
//        // You could do this to eagerly load the books, but it's slow
////        return authorRepo.findByIdAndFetchBooksEagerly(authorId);
//
//        // Instead do this, it's faster
//        Wine wine = wineRepo.findOne(wineId);
//        author.getBookSet().size();
//        return author;
//    }

    /**
 *  A method that Calls the WineRepositories findOne Method.
     * @param id Integer 
 * @return A wine object
 */
    public Wine findById(String id) {
        if(id.isEmpty()){
             throw new ParameterMissingException();
        }
        return wineRepo.findOne(new Integer(id));
    }

    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param wine 
     */
    @Transactional
    public void remove(Wine wine) {
         if(wine == null){
             throw new ParameterMissingException();
        }
        LOG.debug("Deleting author: " + wine.getWineName());
        wineRepo.delete(wine);
    }

    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param wine 
     * @return  wine
     */
    @Transactional
    public Wine edit(Wine wine) {
        if(wine == null){
             throw new ParameterMissingException();
        }
        return wineRepo.saveAndFlush(wine);
    }
}

