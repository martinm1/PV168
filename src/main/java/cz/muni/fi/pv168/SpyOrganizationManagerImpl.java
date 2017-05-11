/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Boris
 */
public class SpyOrganizationManagerImpl implements SpyOrganizationManager {
    
    private static final Logger logger = Logger.getLogger(
            MissionManagerImpl.class.getName());
    
    private DataSource dataSource;
    
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    private void checkDataSource() {
        if (dataSource == null) {
            throw new IllegalStateException("DataSource is not set");
        }
    }
    
    @Override
    public Mission findMissionWithAgent(Agent agent) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        checkDataSource();        
        if (agent == null) {
            throw new IllegalArgumentException("agent is null");
        }        
        if (agent.getId() == null) {
            throw new IllegalEntityException("agent id is null");
        }        
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = dataSource.getConnection();
            st = conn.prepareStatement(
                    "SELECT Mission.id, danger, assignment " +
                    "FROM Mission JOIN Agent ON Mission.id = Agent.missionId " +
                    "WHERE Agent.id = ?");
            st.setLong(1, agent.getId());
            return MissionManagerImpl.executeQueryForSingleMission(st);
        } catch (SQLException ex) {
            String msg = "Error when trying to find mission with agent " + agent;
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtils.closeQuietly(conn, st);
        }  
    }

    @Override
    public List<Agent> findAgentsOnMission(Mission mission) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        checkDataSource();        
        if (mission == null) {
            throw new IllegalArgumentException("mission is null");
        }        
        if (mission.getId() == null) {
            throw new IllegalEntityException("mission id is null");
        }        
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = dataSource.getConnection();
            st = conn.prepareStatement(
                    "SELECT Agent.id, name, workingSince, compromised " +
                    "FROM Agent JOIN Mission ON Mission.id = Agent.missionId " +
                    "WHERE Mission.id = ?");
            st.setLong(1, mission.getId());
            return AgentManagerImpl.executeQueryForMultipleAgents(st);
        } catch (SQLException ex) {
            String msg = "Error when trying to find agents on mission " + mission;
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtils.closeQuietly(conn, st);
        }
    }

    @Override
    public void assignMission(Agent agent, Mission mission) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        checkDataSource();
        if (mission == null) {
            throw new IllegalArgumentException("mission is null");
        }        
        if (mission.getId() == null) {
            throw new IllegalEntityException("mission id is null");
        }        
        if (agent == null) {
            throw new IllegalArgumentException("agent is null");
        }        
        if (agent.getId() == null) {
            throw new IllegalEntityException("agent id is null");
        }        
        Connection conn = null;
        PreparedStatement updateSt = null;
        try {
            conn = dataSource.getConnection();
            // Temporary turn autocommit mode off. It is turned back on in 
            // method DBUtils.closeQuietly(...) 
            conn.setAutoCommit(false);
            checkIfMissionExists(conn, mission);
            updateSt = conn.prepareStatement(
                    "UPDATE Agent SET missionId = ? WHERE id = ? AND missionId IS NULL");
            updateSt.setLong(1, mission.getId());
            updateSt.setLong(2, agent.getId());
            int count = updateSt.executeUpdate();
            if (count == 0) {
                throw new IllegalEntityException("Agent " + agent + " not found or it is already assigned another mission");
            }
            DBUtils.checkUpdatesCount(count, agent, false);            
            conn.commit();
        } catch (SQLException ex) {
            String msg = "Error when assigning agent the mission";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtils.doRollbackQuietly(conn);
            DBUtils.closeQuietly(conn, updateSt);
        }
    }

    @Override
    public void unassignMission(Agent agent, Mission mission) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        checkDataSource();
        if (mission == null) {
            throw new IllegalArgumentException("mission is null");
        }        
        if (mission.getId() == null) {
            throw new IllegalEntityException("mission id is null");
        }        
        if (agent == null) {
            throw new IllegalArgumentException("agent is null");
        }        
        if (agent.getId() == null) {
            throw new IllegalEntityException("agent id is null");        
        }                             
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = dataSource.getConnection();
            // Temporary turn autocommit mode off. It is turned back on in 
            // method DBUtils.closeQuietly(...) 
            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "UPDATE Agent SET missionId = NULL WHERE id = ? AND missionId = ?");
            st.setLong(1, agent.getId());
            st.setLong(2, mission.getId());
            int count = st.executeUpdate();
            DBUtils.checkUpdatesCount(count, agent, false);            
            conn.commit();
        } catch (SQLException ex) {
            String msg = "Error when assigning agent the mission";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtils.doRollbackQuietly(conn);
            DBUtils.closeQuietly(conn, st);
        }
    }
    
    private static void checkIfMissionExists(Connection conn, Mission mission) throws IllegalEntityException, SQLException {
        PreparedStatement checkSt = null;
        try {
            checkSt = conn.prepareStatement(
                    "SELECT Id FROM Mission WHERE Mission.id = ?");
            checkSt.setLong(1, mission.getId());
            ResultSet rs = checkSt.executeQuery();
            if (!rs.next()) { 
                throw new IllegalEntityException("Mission " + mission + " does not exist in db");
            }
        } finally {
            DBUtils.closeQuietly(null, checkSt);
        }
    }
    
}
