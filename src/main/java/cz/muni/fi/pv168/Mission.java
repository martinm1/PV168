/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168;

import java.util.Objects;

/**
 *
 * @author martin
 */
public class Mission{
    private Long id;
    private int danger;
    private String assignment;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDanger(int danger) {
        this.danger = danger;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public Long getId() {
        return id;
    }

    public int getDanger() {
        return danger;
    }

    public String getAssignment() {
        return assignment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Mission other = (Mission) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mission{" + "id=" + id + ", danger=" + danger + ", assignment=" + assignment + '}';
    }   
    
}
