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
        manager = new AgentManagerImpl();
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
