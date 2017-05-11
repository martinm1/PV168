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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Boris
 */
public class AgentManagerImpl implements AgentManager {
    
    private static final Logger logger = Logger.getLogger(
            AgentManagerImpl.class.getName());
    
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    private void checkDataSource() {
        if (dataSource == null) {
            throw new IllegalStateException("DataSource is not set");
        }
    }
    
    @Override
    public void createAgent(Agent agent) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        checkDataSource();
        validate(agent);
        if (agent.getId() != null) {
            throw new IllegalEntityException("agent id is already set");
        }        
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = dataSource.getConnection();
            // Temporary turn autocommit mode off. It is turned back on in 
            // method DBUtils.closeQuietly(...) 
            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "INSERT INTO Agent (name,workingSince,compromised) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, agent.getName());
            st.setString(2, agent.getWorkingSince().format(formatter));
            st.setBoolean(3, agent.getCompromised());

            int count = st.executeUpdate();
            DBUtils.checkUpdatesCount(count, agent, true);

            Long id = DBUtils.getId(st.getGeneratedKeys());
            agent.setId(id);
            conn.commit();
        } catch (SQLException ex) {
            String msg = "Error when inserting agent into db";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtils.doRollbackQuietly(conn);
            DBUtils.closeQuietly(conn, st);
        }
    }

    @Override
    public void updateAgent(Agent agent) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        checkDataSource();
        validate(agent);
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
                    "UPDATE Agent SET name = ?, workingSince = ?, compromised = ? WHERE id = ?");
            st.setString(1, agent.getName());
            st.setString(2, agent.getWorkingSince().format(formatter));
            st.setBoolean(3, agent.getCompromised());
            st.setLong(4, agent.getId());

            int count = st.executeUpdate();
            DBUtils.checkUpdatesCount(count, agent, false);
            conn.commit();
        } catch (SQLException ex) {
            String msg = "Error when updating agent in the db";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtils.doRollbackQuietly(conn);
            DBUtils.closeQuietly(conn, st);
        }
    }

    @Override
    public void deleteAgent(Agent agent) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        checkDataSource();
        validate(agent);
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
                    "DELETE FROM Agent WHERE id = ?");
            st.setLong(1, agent.getId());

            int count = st.executeUpdate();
            DBUtils.checkUpdatesCount(count, agent, false);
            conn.commit();
        } catch (SQLException ex) {
            String msg = "Error when deleting agent from the db";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtils.doRollbackQuietly(conn);
            DBUtils.closeQuietly(conn, st);
        }
    }

    @Override
    public Agent findAgentById(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        checkDataSource();
        
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = dataSource.getConnection();
            st = conn.prepareStatement(
                    "SELECT id, name,workingSince,compromised FROM Agent WHERE id = ?");
            st.setLong(1, id);
            return executeQueryForSingleAgent(st);
        } catch (SQLException ex) {
            String msg = "Error when getting agent with id = " + id + " from DB";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtils.closeQuietly(conn, st);
        }
    }

    @Override
    public List<Agent> findAllAgents() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        checkDataSource();
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = dataSource.getConnection();
            st = conn.prepareStatement(
                    "SELECT id, name,workingSince,compromised FROM Agent");
            return executeQueryForMultipleAgents(st);
        } catch (SQLException ex) {
            String msg = "Error when getting all agents from DB";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtils.closeQuietly(conn, st);
        }  
    }
         
    
    static Agent executeQueryForSingleAgent(PreparedStatement st) throws SQLException, ServiceFailureException {
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            Agent result = rowToAgent(rs);                
            if (rs.next()) {
                throw new ServiceFailureException(
                        "Internal integrity error: more agents with the same id found!");
            }
            return result;
        } else {
            return null;
        }
    }
    
    static List<Agent> executeQueryForMultipleAgents(PreparedStatement st) throws SQLException {
        ResultSet rs = st.executeQuery();
        List<Agent> result = new ArrayList<Agent>();
        while (rs.next()) {
            result.add(rowToAgent(rs));
        }
        return result;
    }
    
    private static Agent rowToAgent(ResultSet rs) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        Agent result = new Agent();
        result.setId(rs.getLong("id"));
        result.setName(rs.getString("name"));
        result.setWorkingSince(LocalDateTime.parse(rs.getString("workingSince"), formatter));
        result.setCompromised(rs.getBoolean("compromised"));
        return result;
    }
    
    private static void validate(Agent agent) {
        if (agent == null) {
            throw new IllegalArgumentException("agent is null");
        }
        if (agent.getName() == null) {
            throw new IllegalEntityException("name is null");
        }
        if (agent.getWorkingSince() == null) {
            throw new IllegalEntityException("workingSince is null");
        }   
        if (agent.getCompromised() == null) {
            throw new IllegalEntityException("compromised is null");
        } 
    }
}
