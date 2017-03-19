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
    
    /**
     * Stores new agent into the database. Id for the new agent is automatically
     * generated and stored into attribute id.
     * 
     * @param agent agent to be created.
     * @throws IllegalArgumentException when agent is null.
     * @throws IllegalEntityException when agent has already assigned id or does not have assigned some attribute values.
     * @throws ServiceFailureException when database operation fails.
     */
    void createAgent(Agent agent) throws ServiceFailureException, IllegalEntityException;
    
    /**
     * Updates agent stored in the database.
     * 
     * @param agent updated agent to be stored into database.
     * @throws IllegalArgumentException when agent is null.
     * @throws IllegalEntityException when agent has null id or id not stored in the database or does not have assigned some attribute values.
     * @throws ServiceFailureException when database operation fails.
     */
    void updateAgent(Agent agent) throws ServiceFailureException, IllegalEntityException;
    
    /**
     * Deletes agent from the database. 
     * 
     * @param agent agent to be deleted from database.
     * @throws IllegalArgumentException when agent is null.
     * @throws IllegalEntityException when agent has null id or id not stored in the database or does not have assigned some attribute values.
     * @throws ServiceFailureException when database operation fails.
     */
    void deleteAgent(Agent agent) throws ServiceFailureException, IllegalEntityException;
    
    /**
     * Returns agent with given id.
     * 
     * @param id primary key of requested agent.
     * @return agent with given id or null if requested agent is not stored in the database.
     * @throws IllegalArgumentException when given id is null.
     * @throws ServiceFailureException when database operation fails.
     */
    Agent findAgentById(Long id) throws ServiceFailureException;
    
    /**
     * Returns list of all agents in the database.
     * 
     * @return list of all agents in the database.
     * @throws ServiceFailureException when database operation fails.
     */
    List<Agent> findAllAgents() throws ServiceFailureException;
}
