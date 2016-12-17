/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainBean.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jameszeigler
 */
@Entity
@Table(name="VISITORS")
public class Visitor implements Serializable {

     private static final long serialVersionUID = 1L;
     @Id
     @Column(name="ID")
     private String       clientIP;
     @Column(name="VISITS")
     private int            visits;
     @Column(name="LAST_VISIT")
     private Date        lastVisit;
     
     
     public String getClientIP(){
          return clientIP;
     }
     
     public void setClientIP(String IP){
          if(IP != null)
               this.clientIP = IP;
     }
     
     public int getVisits(){
          return visits;
     }
     
     public void setVisits(int visits){
          this.visits = visits;
     }
     
     public void addVisit(){
          visits++;
     }
     
     public void recordRecentVisit(Date visitDate){
          this.lastVisit = visitDate;
     }
     
     public Date getLastVisit(){
          return lastVisit;
     }

     @Override
     public int hashCode() {
          int hash = 0;
          hash += (clientIP != null ? clientIP.hashCode() : 0);
          return hash;
     }

     @Override
     public boolean equals(Object object) {
          // TODO: Warning - this method won't work in the case the id fields are not set
          if (!(object instanceof Visitor)) {
               return false;
          }
          Visitor other = (Visitor) object;
          if ((this.clientIP == null && other.clientIP != null) || (this.clientIP != null && !this.clientIP.equals(other.clientIP))) {
               return false;
          }
          return true;
     }

     @Override
     public String toString() {
          return "mainBean.Entities.Visitor[ id=" + clientIP + " ]";
     }
     
}
