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
    Mission findMissionWithAgent(Agent agent);
    
    List<Agent> findAgentsOnMission(Mission mission);
    
    void assignMission(Agent agent, Mission mission);
    
    void unassignMission(Agent agent, Mission mission);
}
