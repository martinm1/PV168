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
public interface SpyOrganizationManager {
    
    /**
     * Find the mission that is assigned to given agent or null if he has no assigned mission.
     * 
     * @param agent agent whose mission we seek
     * @return mission that is assigned to given agent
     * @throws IllegalArgumentException when agent is null
     * @throws IllegalEntityException when agent has null id or id not stored in the database or does not have assigned some attribute values
     * @throws ServiceFailureException when database operation fails
     */
    Mission findMissionWithAgent(Agent agent) throws ServiceFailureException, IllegalEntityException;
    
    /**
     * Find all agents that are assigned to given mission.
     * 
     * @param mission mission whose agents we seek
     * @return collection of agents assigned to given mission
     * @throws IllegalArgumentException when mission is null
     * @throws IllegalEntityException when mission has null id or id not stored in the database or does not have assigned some attribute values
     * @throws ServiceFailureException when database operation fails
     */
    List<Agent> findAgentsOnMission(Mission mission) throws ServiceFailureException, IllegalEntityException;
    
    /**
     * Assigs given agent to given mission.
     * 
     * @param agent agent to be assigned to the mission
     * @param mission to be assigned to the agent
     * @throws IllegalArgumentException when agent or mission is null
     * @throws IllegalEntityException when agent or mission has null id or id not stored in the database or does not have assigned some attribute values
     * @throws ServiceFailureException when database operation fails.
     */
    void assignMission(Agent agent, Mission mission) throws ServiceFailureException, IllegalEntityException;
    
    /**
     * Unassigs given agent from given mission.
     * 
     * @param agent agent to be unassigned from the mission
     * @param mission to be unassigned from the agent
     * @throws IllegalArgumentException when agent or mission is null
     * @throws IllegalEntityException when agent or mission has null id or id not stored in the database or does not have assigned some attribute values
     * @throws ServiceFailureException when database operation fails.
     */
    void unassignMission(Agent agent, Mission mission) throws ServiceFailureException, IllegalEntityException;
}
