/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Boris
 */
public class AgentManagerImplTest {
    //test1111111;
    private AgentManagerImpl manager;
    private DataSource ds;
    
    @Rule
    // attribute annotated with @Rule annotation must be public :-(
    public ExpectedException expectedException = ExpectedException.none();
    
    private static DataSource prepareDataSource() throws SQLException {
        EmbeddedDataSource ds = new EmbeddedDataSource();
        // we will use in memory database
        ds.setDatabaseName("memory:agentmgr-test");
        // database is created automatically if it does not exist yet
        ds.setCreateDatabase("create");
        return ds;
    }
    
    @Before
    public void setUp() throws SQLException {
        //ds = prepareDataSource();
        //DBUtils.executeSqlScript(ds,AgentManager.class.getResource("createTables.sql"));
        manager = new AgentManagerImpl();
        //manager.setDataSource(ds);
    }
    
    @After
    public void tearDown() throws SQLException {
        // Drop tables after each test
        //DBUtils.executeSqlScript(ds,GraveManager.class.getResource("dropTables.sql"));
    }
    
    @Test
    public void createAgent() {
        String str = "1984-01-14 10:39";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Agent agent = new Agent();
        agent.setName("BondJamesBond");
        agent.setWorkingSince(LocalDateTime.parse(str, formatter));
        agent.setCompromised(Boolean.FALSE);
        
        manager.createAgent(agent);

        Long agentId = agent.getId();
        assertNotNull(agentId);
        Agent result = manager.findAgentById(agentId);
        assertEquals(agent, result);
        assertNotSame(agent, result);
        assertDeepEquals(agent, result);
    }
    
    @Test
    public void updateAgent() {
        String str1 = "1984-01-14 10:39";
        String str2 = "1984-01-14 10:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        Agent agentForUpdate = new Agent();
        Agent anotherAgent = new Agent();
        
        agentForUpdate.setName("BondJamesBond");
        agentForUpdate.setWorkingSince(LocalDateTime.parse(str1, formatter));
        agentForUpdate.setCompromised(Boolean.FALSE);
        
        anotherAgent.setName("Johny English");
        anotherAgent.setWorkingSince(LocalDateTime.parse(str2, formatter));
        anotherAgent.setCompromised(Boolean.TRUE);

        manager.createAgent(agentForUpdate);
        manager.createAgent(anotherAgent);
        
        manager.updateAgent(agentForUpdate);
        
        assertThat(manager.findAgentById(agentForUpdate.getId()))
                .isEqualToComparingFieldByField(agentForUpdate);
        
        assertThat(manager.findAgentById(anotherAgent.getId()))
                .isEqualToComparingFieldByField(anotherAgent);
        
        /*Long agentId = agent.getId();
        assertNotNull(agentId);
        Agent result = manager.findAgentById(agentId);
        assertEquals(agent, result);
        assertNotSame(agent, result);
        assertDeepEquals(agent, result);*/
    }
    
    @Test
    public void findAllAgents() {
        assertTrue(manager.findAllAgents().isEmpty());

        String str1 = "1984-01-14 10:39";
        String str2 = "1984-01-14 10:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        
        Agent a1 = new Agent();
        Agent a2 = new Agent();
        
        a1.setName("BondJamesBond");
        a1.setWorkingSince(LocalDateTime.parse(str1, formatter));
        a1.setCompromised(Boolean.FALSE);
        
        a2.setName("Johny English");
        a2.setWorkingSince(LocalDateTime.parse(str2, formatter));
        a2.setCompromised(Boolean.TRUE);

        manager.createAgent(a1);
        manager.createAgent(a2);

        List<Agent> expected = Arrays.asList(a1, a2);
        List<Agent> actual = manager.findAllAgents();

        Collections.sort(actual, AGENT_ID_COMPARATOR);
        Collections.sort(expected, AGENT_ID_COMPARATOR);

        assertEquals(expected, actual);
        assertDeepEquals(expected, actual);
    }
    
    @Test
    public void deleteAgent() {

        String str1 = "1984-01-14 10:39";
        String str2 = "1984-01-14 10:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        
        Agent a1 = new Agent();
        Agent a2 = new Agent();
        
        a1.setName("BondJamesBond");
        a1.setWorkingSince(LocalDateTime.parse(str1, formatter));
        a1.setCompromised(Boolean.FALSE);
        
        a2.setName("Johny English");
        a2.setWorkingSince(LocalDateTime.parse(str2, formatter));
        a2.setCompromised(Boolean.TRUE);

        manager.createAgent(a1);
        manager.createAgent(a2);

        assertNotNull(manager.findAgentById(a1.getId()));
        assertNotNull(manager.findAgentById(a2.getId()));

        manager.deleteAgent(a1);

        assertNull(manager.findAgentById(a1.getId()));
        assertNotNull(manager.findAgentById(a2.getId()));

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createNullAgent() {
        manager.createAgent(null);
    }
    
    @Test
    public void createAgentWithExistingId() {
        String str1 = "1984-01-14 10:39";
        String str2 = "1984-01-14 10:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        
        Agent a1 = new Agent();
        Agent a2 = new Agent();
        
        a1.setName("BondJamesBond");
        a1.setWorkingSince(LocalDateTime.parse(str1, formatter));
        a1.setCompromised(Boolean.FALSE);
        manager.createAgent(a1);
        
        a2.setName("Johny English");
        a2.setWorkingSince(LocalDateTime.parse(str2, formatter));
        a2.setCompromised(Boolean.TRUE);
        a2.setId(a1.getId());
        
        expectedException.expect(IllegalEntityException.class);
        manager.createAgent(a2);
    }
    
    @Test
    public void createAgentWithNullName() {
        String str = "1984-01-14 10:39";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Agent agent = new Agent();
        agent.setName(null);
        agent.setWorkingSince(LocalDateTime.parse(str, formatter));
        agent.setCompromised(Boolean.FALSE);
        
        expectedException.expect(IllegalEntityException.class);
        manager.createAgent(agent);
    }
    
    @Test
    public void createAgentWithNullWorkingSince() {
        Agent agent = new Agent();
        agent.setName("BondJamesBond");
        agent.setWorkingSince(null);
        agent.setCompromised(Boolean.FALSE);
        
        expectedException.expect(IllegalEntityException.class);
        manager.createAgent(agent);
    }
    
    @Test
    public void createAgentWithNullCompromised() {
        String str = "1984-01-14 10:39";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        Agent agent = new Agent();
        agent.setName("BondJamesBond");
        agent.setWorkingSince(LocalDateTime.parse(str, formatter));
        agent.setCompromised(null);
        
        expectedException.expect(IllegalEntityException.class);
        manager.createAgent(agent);
    }
    
    @Test
    public void updateAgentWithNullName() {
        String str1 = "1984-01-14 10:39";
        String str2 = "1984-01-14 10:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        Agent agentForUpdate = new Agent();
        Agent anotherAgent = new Agent();
        
        agentForUpdate.setName("BondJamesBond");
        agentForUpdate.setWorkingSince(LocalDateTime.parse(str1, formatter));
        agentForUpdate.setCompromised(Boolean.FALSE);
        
        anotherAgent.setName("Johny English");
        anotherAgent.setWorkingSince(LocalDateTime.parse(str2, formatter));
        anotherAgent.setCompromised(Boolean.TRUE);

        manager.createAgent(agentForUpdate);
        manager.createAgent(anotherAgent);
        
        agentForUpdate.setName(null);
        expectedException.expect(IllegalEntityException.class);
        manager.updateAgent(agentForUpdate);
        
    }
    
    @Test
    public void updateAgentWithNullWorkingSince() {
        String str1 = "1984-01-14 10:39";
        String str2 = "1984-01-14 10:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        Agent agentForUpdate = new Agent();
        Agent anotherAgent = new Agent();
        
        agentForUpdate.setName("BondJamesBond");
        agentForUpdate.setWorkingSince(LocalDateTime.parse(str1, formatter));
        agentForUpdate.setCompromised(Boolean.FALSE);
        
        anotherAgent.setName("Johny English");
        anotherAgent.setWorkingSince(LocalDateTime.parse(str2, formatter));
        anotherAgent.setCompromised(Boolean.TRUE);

        manager.createAgent(agentForUpdate);
        manager.createAgent(anotherAgent);
        
        agentForUpdate.setWorkingSince(null);
        expectedException.expect(IllegalEntityException.class);
        manager.updateAgent(agentForUpdate);
        
    }
    
    @Test
    public void updateAgentWithNullCompromised() {
        String str1 = "1984-01-14 10:39";
        String str2 = "1984-01-14 10:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        Agent agentForUpdate = new Agent();
        Agent anotherAgent = new Agent();
        
        agentForUpdate.setName("BondJamesBond");
        agentForUpdate.setWorkingSince(LocalDateTime.parse(str1, formatter));
        agentForUpdate.setCompromised(Boolean.FALSE);
        
        anotherAgent.setName("Johny English");
        anotherAgent.setWorkingSince(LocalDateTime.parse(str2, formatter));
        anotherAgent.setCompromised(Boolean.TRUE);

        manager.createAgent(agentForUpdate);
        manager.createAgent(anotherAgent);
        
        agentForUpdate.setCompromised(null);
        expectedException.expect(IllegalEntityException.class);
        manager.updateAgent(agentForUpdate);
        
    }
    
    @Test
    public void deleteAgentWithNonExistingId() {
        String str = "1984-01-14 10:39";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Agent agent = new Agent();
        agent.setName("BondJamesBond");
        agent.setWorkingSince(LocalDateTime.parse(str, formatter));
        agent.setCompromised(Boolean.FALSE);
        
        manager.createAgent(agent);
        
        Long agentId = 12345678910L;
        
        agent.setId(agentId);
        
        expectedException.expect(IllegalEntityException.class);
        manager.deleteAgent(agent);
    }
    
    @Test
    public void deleteAgentWithNullName() {
        String str = "1984-01-14 10:39";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Agent agent = new Agent();
        agent.setName("BondJamesBond");
        agent.setWorkingSince(LocalDateTime.parse(str, formatter));
        agent.setCompromised(Boolean.FALSE);
        
        manager.createAgent(agent);
        
        agent.setName(null);
        
        expectedException.expect(IllegalEntityException.class);
        manager.deleteAgent(agent);
    }
    
    @Test
    public void deleteAgentWithNullWorkingSince() {
        String str = "1984-01-14 10:39";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Agent agent = new Agent();
        agent.setName("BondJamesBond");
        agent.setWorkingSince(LocalDateTime.parse(str, formatter));
        agent.setCompromised(Boolean.FALSE);
        
        manager.createAgent(agent);
        
        agent.setWorkingSince(null);
        
        expectedException.expect(IllegalEntityException.class);
        manager.deleteAgent(agent);
    }
    
    @Test
    public void deleteAgentWithNullCompromised() {
        String str = "1984-01-14 10:39";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Agent agent = new Agent();
        agent.setName("BondJamesBond");
        agent.setWorkingSince(LocalDateTime.parse(str, formatter));
        agent.setCompromised(Boolean.FALSE);
        
        manager.createAgent(agent);
        
        agent.setCompromised(null);
        
        expectedException.expect(IllegalEntityException.class);
        manager.deleteAgent(agent);
    }
    
    
    
    private void assertDeepEquals(Agent expected, Agent actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getWorkingSince(), actual.getWorkingSince());
        assertEquals(expected.getCompromised(), actual.getCompromised());
    }
    
    private void assertDeepEquals(List<Agent> expectedList, List<Agent> actualList) {
        for (int i = 0; i < expectedList.size(); i++) {
            Agent expected = expectedList.get(i);
            Agent actual = actualList.get(i);
            assertDeepEquals(expected, actual);
        }
    }
    
    private static final Comparator<Agent> AGENT_ID_COMPARATOR =
            (a1, a2) -> a1.getId().compareTo(a2.getId());
}
