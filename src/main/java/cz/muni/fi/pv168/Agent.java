/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author martin
 */
public class Agent {
  private Long id;
  private String name; 
  private LocalDateTime workingSince; 
  private Boolean compromised;
  
  public void setId(Long id){
      this.id = id;
  }
  
  public void setName(String name){
      this.name = name;
  }
  
  public void setWorkingSince(LocalDateTime workingSince){
      this.workingSince = workingSince;
  }
  
  public void setCompromised(Boolean compromised){
      this.compromised = compromised;
  }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getWorkingSince() {
        return workingSince;
    }

    public Boolean getCompromised() {
        return compromised;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Agent other = (Agent) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Agent{" + "id=" + id + ", name=" + name + ", workingSince=" + workingSince + ", compromised=" + compromised + '}';
    }
  
  
}
