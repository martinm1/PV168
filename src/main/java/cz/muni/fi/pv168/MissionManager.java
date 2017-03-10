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
public interface MissionManager {
    void createMission(Mission mission);
    
    void updateMission(Mission mission);
    
    void deleteMission(Mission mission);
    
    Mission findMissionById(Long id);
    
    List<Mission> findAllMissions(); 
}
