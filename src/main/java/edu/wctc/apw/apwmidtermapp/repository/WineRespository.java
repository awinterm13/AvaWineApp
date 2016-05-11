/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.repository;

import edu.wctc.apw.apwmidtermapp.entity.Wine;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * A Spring Generated class that plugs into Hibernate or EclipseLink and provides basic crud.
 * @author andre_000
 */
public interface WineRespository extends JpaRepository<Wine, Integer>, Serializable {
    

}
