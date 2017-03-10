/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168;

import java.util.List;

/**
 *
 * @author martin
 */
public interface AgentManager { //comment
    void createAgent(Agent agent);
    
    void updateAgent(Agent agent);
    
    void deleteAgent(Agent agent);
    
    Agent findAgentById(Long id);
    
    List<Agent> findAllAgents();
}
