/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author martin
 */
public class SpyOrganizationManagerImplTest {
    
    private SpyOrganizationManagerImpl manager;
    private AgentManagerImpl agentManager;
    private MissionManagerImpl missionManager;
    private Mission m1, m2, m3, missionWithNullId, missionNotInDB;
    private Agent a1, a2, a3, a4, a5, agentWithNullId, agentNotInDB;
    private DataSource ds;
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    private static DataSource prepareDataSource() throws SQLException {
        EmbeddedDataSource ds = new EmbeddedDataSource();
        ds.setDatabaseName("memory:missionmgr-test");
        ds.setCreateDatabase("create");
        return ds;
    }
    
    @Before
    public void setUp() throws SQLException {
        ds = prepareDataSource();
        DBUtils.executeSqlScript(ds, MissionManager.class.getResource("createTables.sql"));
        manager = new SpyOrganizationManagerImpl();
        manager.setDataSource(ds);
        agentManager = new AgentManagerImpl();
        agentManager.setDataSource(ds);
        missionManager = new MissionManagerImpl(); 
        missionManager.setDataSource(ds);
        prepareTestData();
        
    }
    
    @After
    public void tearDown() throws SQLException {
        DBUtils.executeSqlScript(ds, MissionManager.class.getResource("dropTables.sql"));
    }
    
    private void prepareTestData(){
        m1 = newMission(8,"TestM1");
        m2 = newMission(20,"TestM2");
        m3 = newMission(2,"TestM3");
        
        a1 = newAgent("TestA1","1984-01-14 10:39",Boolean.TRUE);
        a2 = newAgent("TestA2","1995-01-14 10:39",Boolean.FALSE);
        a3 = newAgent("TestA3","1984-08-10 10:09",Boolean.TRUE);
        a4 = newAgent("TestA4","1978-01-14 21:12",Boolean.FALSE);
        a5 = newAgent("TestA5","2012-03-04 10:39",Boolean.TRUE);
    
        missionManager.createMission(m1);
        missionManager.createMission(m2);
        missionManager.createMission(m3);
        
        agentManager.createAgent(a1);
        agentManager.createAgent(a2);
        agentManager.createAgent(a3);
        agentManager.createAgent(a4);
        agentManager.createAgent(a5);
        
        missionWithNullId = newMission(0,"TestNullM");
        missionWithNullId.setId(null);
        missionNotInDB = newMission(100,"TestOutM");
        missionNotInDB.setId(m2.getId() + 100);
        
        agentWithNullId = newAgent("TestNullA","1984-08-10 10:09",Boolean.FALSE);
        agentNotInDB = newAgent("TestOutA","1984-08-10 10:09",Boolean.TRUE);
        
    }
    
    @Test
    public void findMissionWithAgent() {
        assertThat(manager.findMissionWithAgent(a1)).isNull();
        assertThat(manager.findMissionWithAgent(a2)).isNull();
        assertThat(manager.findMissionWithAgent(a3)).isNull();
        assertThat(manager.findMissionWithAgent(a4)).isNull();
        assertThat(manager.findMissionWithAgent(a5)).isNull();
    
        manager.assignMission(a1, m2);
        
        assertThat(manager.findMissionWithAgent(a1)).isEqualToComparingFieldByField(m2);
        
        assertThat(manager.findMissionWithAgent(a2)).isNull();
        assertThat(manager.findMissionWithAgent(a3)).isNull();
        assertThat(manager.findMissionWithAgent(a4)).isNull();
        assertThat(manager.findMissionWithAgent(a5)).isNull();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void findMissionWithNullAgent() {
        manager.findMissionWithAgent(null);
    }
    
    @Test(expected = IllegalEntityException.class)
    public void findMissionWithAgentHavingNullId() {
        manager.findMissionWithAgent(agentWithNullId);
    }
    
    @Test
    public void findAgentsOnMission(){
        assertThat(manager.findAgentsOnMission(m1)).isEmpty();
        assertThat(manager.findAgentsOnMission(m2)).isEmpty();
        assertThat(manager.findAgentsOnMission(m3)).isEmpty();
        
        manager.assignMission(a1,m1);
        manager.assignMission(a2,m1);
        manager.assignMission(a3,m1);
        manager.assignMission(a4,m2);
        manager.assignMission(a5,m2);
    
        assertThat(manager.findAgentsOnMission(m1)).usingFieldByFieldElementComparator().containsOnly(a1,a2,a3);
        assertThat(manager.findAgentsOnMission(m2)).usingFieldByFieldElementComparator().containsOnly(a4,a5);
        assertThat(manager.findAgentsOnMission(m3)).isEmpty();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void findAgentsOnNullMission(){
        manager.findAgentsOnMission(null);
    }
    
    @Test(expected = IllegalEntityException.class)
    public void findAgentsOnMissionHavingNullId() {
        manager.findAgentsOnMission(missionWithNullId);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void assignNullAgentOnMission() {
        manager.assignMission(null, m2);
    }

    @Test(expected = IllegalEntityException.class)
    public void assignAgentWithNullIdOnMission() {
        manager.assignMission(agentWithNullId, m2);
    }

    @Test(expected = IllegalEntityException.class)
    public void assignAgentNotInDBOnMission() {
        manager.assignMission(agentNotInDB, m2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void assignAgentOnNullMission() {
        manager.assignMission(a2, null);
    }

    @Test(expected = IllegalEntityException.class)
    public void assignAgentOnMissionWithNullId() {
        manager.assignMission(a2, missionWithNullId);
    }

    @Test(expected = IllegalEntityException.class)
    public void assignAgentOnMissionNotInDB() {
        manager.assignMission(a2, missionNotInDB);
    }
    
    @Test
    public void assignAgentOnMission(){
        assertThat(manager.findMissionWithAgent(a1)).isNull();
        assertThat(manager.findMissionWithAgent(a2)).isNull();
        assertThat(manager.findMissionWithAgent(a3)).isNull();
        assertThat(manager.findMissionWithAgent(a4)).isNull();
        assertThat(manager.findMissionWithAgent(a5)).isNull();
        
        manager.assignMission(a1,m1);
        manager.assignMission(a2,m2);
        manager.assignMission(a3,m2);
        
        assertThat(manager.findAgentsOnMission(m1)).usingFieldByFieldElementComparator().containsOnly(a1);
        assertThat(manager.findAgentsOnMission(m2)).usingFieldByFieldElementComparator().containsOnly(a2,a3);
        assertThat(manager.findAgentsOnMission(m3)).isEmpty();
        
        assertThat(manager.findMissionWithAgent(a1)).isEqualToComparingFieldByField(m1);
        assertThat(manager.findMissionWithAgent(a2)).isEqualToComparingFieldByField(m2);
        assertThat(manager.findMissionWithAgent(a3)).isEqualToComparingFieldByField(m2);
        assertThat(manager.findMissionWithAgent(a4)).isNull();
        assertThat(manager.findMissionWithAgent(a5)).isNull();
        
    }

    @Test
    public void unassignMission(){
    
        manager.assignMission(a1, m1);
        manager.assignMission(a2, m1);
        manager.assignMission(a3, m1);
        manager.assignMission(a4, m2);
    
        assertThat(manager.findMissionWithAgent(a1)).isEqualToComparingFieldByField(m1);
        assertThat(manager.findMissionWithAgent(a2)).isEqualToComparingFieldByField(m1);
        assertThat(manager.findMissionWithAgent(a3)).isEqualToComparingFieldByField(m1);
        assertThat(manager.findMissionWithAgent(a4)).isEqualToComparingFieldByField(m2);
        assertThat(manager.findMissionWithAgent(a5)).isNull();
        
        manager.unassignMission(a1,m1);
        
        assertThat(manager.findAgentsOnMission(m1)).usingFieldByFieldElementComparator().containsOnly(a2,a3);
        assertThat(manager.findAgentsOnMission(m2)).usingFieldByFieldElementComparator().containsOnly(a4);
        assertThat(manager.findAgentsOnMission(m3)).isEmpty();
        
        assertThat(manager.findMissionWithAgent(a1)).isNull();
        assertThat(manager.findMissionWithAgent(a2)).isEqualToComparingFieldByField(m1);
        assertThat(manager.findMissionWithAgent(a3)).isEqualToComparingFieldByField(m1);
        assertThat(manager.findMissionWithAgent(a4)).isEqualToComparingFieldByField(m2);
        assertThat(manager.findMissionWithAgent(a5)).isNull();
    }
    
    @Test(expected = IllegalEntityException.class)
    public void unassignAgentWithNullIdFromMission() {
        manager.unassignMission(agentWithNullId, m2);
    }

    @Test(expected = IllegalEntityException.class)
    public void unassignAgentNotInDBFromMission() {
        manager.unassignMission(agentNotInDB, m2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void unassignAgentFromNullMission() {
        manager.unassignMission(a2, null);
    }

    @Test(expected = IllegalEntityException.class)
    public void unassignAgentFromMissionWithNullId() {
        manager.unassignMission(a2, missionWithNullId);
    }

    @Test(expected = IllegalEntityException.class)
    public void unassignAgentFromMissionNotInDB() {
        manager.unassignMission(a2, missionNotInDB);
    }
    
    
    public static Mission newMission(int danger, String assignment){
        Mission mission = new Mission();
        mission.setDanger(danger);
        mission.setAssignment(assignment);
        return mission;
    }
    
    public static Agent newAgent(String name, String date, Boolean compromised){
        Agent agent = new  Agent();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        agent.setName(name);
        agent.setWorkingSince(LocalDateTime.parse(date, formatter));
        agent.setCompromised(compromised);
        return agent; 
    }
    
}
