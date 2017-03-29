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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.xml.bind.ValidationException;

/**
 *
 * @author Boris
 */
public class MissionManagerImpl implements MissionManager {

    private DataSource dataSource;
    
    private static final Logger logger = Logger.getLogger(
            MissionManagerImpl.class.getName());
    
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }
    
    private void checkDataSource() {
        if (dataSource == null) {
            throw new IllegalStateException("DataSource is not set");
        }
    }
    
    @Override
    public void createMission(Mission mission) {
        checkDataSource();
        validate(mission);
        if (mission.getId() != null){
            throw new IllegalEntityException("Mission id is already set");
        }
        
        Connection conn = null;
        PreparedStatement st = null;
        
        try {
            conn = dataSource.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO Mission (danger,assignment)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setInt(1, mission.getDanger());
            st.setString(2, mission.getAssignment());
            
            int count = st.executeUpdate();
            DBUtils.checkUpdatesCount(count, mission, true);
            
            Long id = DBUtils.getId(st.getGeneratedKeys());
            mission.setId(id);
            conn.commit();
        } catch (SQLException ex){
            String msg = "Error when inserting mission into db";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally{
            DBUtils.doRollbackQuietly(conn);
            DBUtils.closeQuietly(conn, st);
        }
    }

    @Override
    public void updateMission(Mission mission) {
        checkDataSource();
        validate(mission);
        if (mission.getId() == null){
            throw new IllegalEntityException("Mission id is null");
        }
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            st = conn.prepareStatement("UPDATE Mission SET danger = ?, assignment = ?");
            st.setInt(1, mission.getDanger());
            st.setString(2, mission.getAssignment());
            
            int count = st.executeUpdate();
            DBUtils.checkUpdatesCount(count, mission, false);
            conn.commit();
        } catch (SQLException ex){
            String msg = "Error when updating mission in the db";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtils.doRollbackQuietly(conn);
            DBUtils.closeQuietly(conn, st);
        }

    }

    @Override
    public void deleteMission(Mission mission) {
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
            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "DELETE FROM mission WHERE id = ?");
            st.setLong(1, mission.getId());

            int count = st.executeUpdate();
            DBUtils.checkUpdatesCount(count, mission, false);
            conn.commit();
        } catch (SQLException ex) {
            String msg = "Error when deleting mission from the db";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtils.doRollbackQuietly(conn);
            DBUtils.closeQuietly(conn, st);
        }
    }

    @Override
    public Mission findMissionById(Long id) {
        checkDataSource();        
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = dataSource.getConnection();
            st = conn.prepareStatement(
                    "SELECT id, danger, assignment FROM Mission WHERE id = ?");
            st.setLong(1, id);
            return executeQueryForSingleMission(st);
        } catch (SQLException ex) {
            String msg = "Error when getting mission with id = " + id + " from DB";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtils.closeQuietly(conn, st);
        }
    }
    

    @Override
    public List<Mission> findAllMissions() {
        checkDataSource();
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = dataSource.getConnection();
            st = conn.prepareStatement(
                    "SELECT id, danger, assignment FROM Mission");
            return executeQueryForMultipleMissions(st);
        } catch (SQLException ex) {
            String msg = "Error when getting all missions from DB";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtils.closeQuietly(conn, st);
        }
    }
    
    
    static Mission executeQueryForSingleMission(PreparedStatement st) throws SQLException, ServiceFailureException {
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            Mission result = rowToMission(rs);                
            if (rs.next()) {
                throw new ServiceFailureException(
                        "Internal integrity error: more missions with the same id found!");
            }
            return result;
        } else {
            return null;
        }
    }
    
    static List<Mission> executeQueryForMultipleMissions(PreparedStatement st) throws SQLException{
        ResultSet rs = st.executeQuery();
        List<Mission> result = new ArrayList<Mission>();
        while (rs.next()) {
            result.add(rowToMission(rs));
        }
        return result;    
    } 
    
    private static Mission rowToMission(ResultSet rs) throws SQLException{
        Mission result = new Mission();
        result.setId(rs.getLong("id"));
        result.setDanger(rs.getInt("danger"));
        result.setAssignment(rs.getString("assignment"));
        return result;
    }
    
    private static void validate(Mission mission){
        if (mission == null) {
            throw new IllegalArgumentException("Mission is null");
        }
        if (mission.getDanger()<0){
            throw new IllegalArgumentException("Danger level is negative");
        }
        
    }
    
}
