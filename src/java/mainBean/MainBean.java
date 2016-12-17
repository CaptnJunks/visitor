/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainBean;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import mainBean.Entities.Visitor;

/**
 *
 * @author jameszeigler
 */
@Named(value = "mainBean")
@RequestScoped
public class MainBean {
     
     @Inject
     private HttpServletRequest request;
     @Inject
     UserTransaction transaction;
     @PersistenceContext(unitName="ResetPU")
     private EntityManager entityManager;
     private Visitor visitor;
     private Date today = new Date();

     /**
      * Creates a new instance of MainBean
      */
     public MainBean() {
          
     }
     
     public String getVisitorIP(){
          String IP = request.getRemoteAddr();
          return IP;
     }
     
     public void recordVisit(){
          visitor.addVisit();
          visitor.recordRecentVisit(today);
     }
     
     public boolean checkVisitor(){
          String IP = getVisitorIP();
          try {
               transaction.begin();
          } catch (NotSupportedException | SystemException ex) {
               Logger.getLogger(MainBean.class.getName()).log(Level.SEVERE, null, ex);
          }
               entityManager.joinTransaction();
               visitor = entityManager.find(Visitor.class, IP);
               if(visitor != null){
                    recordVisit();
               }
               else{
                    visitor = new Visitor();
                    visitor.setClientIP(IP);
                    recordVisit();
                }
          entityManager.persist(visitor);
          try {
               transaction.commit();
          } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException ex) {
               Logger.getLogger(MainBean.class.getName()).log(Level.SEVERE, null, ex);
          }
          return true;
     }
     
     public int getTotalVisitors(){
          Query query = entityManager.createNativeQuery("SELECT * FROM visitors");
          int visitors = query.getResultList().size();
          return visitors;
     }
     
     public int getTotalVisits(){
          Query query = entityManager.createNativeQuery("SELECT * FROM visitors", Visitor.class);
          List<Visitor> visitors = query.getResultList();
          int visits = 0;
          for(int i = 0; i < visitors.size(); i++){
               visits = visits +  visitors.get(i).getVisits();
          }
          return visits;
     }
     
     public int getCurentVisitorsVisits(){
          return visitor.getVisits();
     }
     
}
