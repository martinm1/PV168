/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168;

import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author martin
 */
public interface MissionManager {
    
    
    void setDataSource(DataSource dataSource);
    /**
     * Stores new mission into the database. Id for the new mission is automatically
     * generated and stored into attribute id.
     * 
     * @param mission mission to be created.
     * @throws IllegalArgumentException when mission is null.
     * @throws IllegalEntityException when mission has already assigned id or does not have assigned some attribute values.
     * @throws ServiceFailureException when database operation fails.
     */
    void createMission(Mission mission) throws ServiceFailureException, IllegalEntityException;
    
    /**
     * Updates mission stored in the database.
     * 
     * @param mission updated mission to be stored into database.
     * @throws IllegalArgumentException when mission is null.
     * @throws IllegalEntityException when mission has null id or id not stored in the database or does not have assigned some attribute values.
     * @throws ServiceFailureException when database operation fails.
     */
    void updateMission(Mission mission) throws ServiceFailureException, IllegalEntityException;
    
    /**
     * Deletes mission from the database. 
     * 
     * @param mission mission to be deleted from database.
     * @throws IllegalArgumentException when mission is null.
     * @throws IllegalEntityException when mission has null id or id not stored in the database or does not have assigned some attribute values.
     * @throws ServiceFailureException when database operation fails.
     */
    void deleteMission(Mission mission) throws ServiceFailureException, IllegalEntityException;
    
    /**
     * Returns mission with given id.
     * 
     * @param id primary key of requested mission.
     * @return mission with given id or null if requested mission is not stored in the database.
     * @throws IllegalArgumentException when given id is null.
     * @throws ServiceFailureException when database operation fails.
     */
    Mission findMissionById(Long id) throws ServiceFailureException;
    
    /**
     * Returns list of all missions in the database.
     * 
     * @return list of all missions in the database.
     * @throws ServiceFailureException when database operation fails.
     */
    List<Mission> findAllMissions() throws ServiceFailureException; 
}
