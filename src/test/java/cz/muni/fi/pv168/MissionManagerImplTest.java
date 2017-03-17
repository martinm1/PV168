/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 *
 * @author Boris
 */
public class MissionManagerImplTest {
    private MissionManagerImpl manager;
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Before
    public void setUp() throws SQLException {
        manager = new MissionManagerImpl();
    }
    
    @Test
    public void createMission(){
        Mission mission = newMission(5,"Watch Putin fight bears");
        manager.createMission(mission);
        
        Long missionId = mission.getId();
        assertNotNull(missionId);
        Mission result = manager.findMissionById(missionId);
        assertEquals(mission, result);
        assertNotSame(mission, result);
        assertDeepEquals(mission, result);
        }
    
    @Test
    public void getAllMissions() {
        assertTrue(manager.findAllMissions().isEmpty());
        
        Mission m1 = newMission(8,"TestMission 1");
        Mission m2 = newMission(2,"TestMission 2");
        
        manager.createMission(m1);
        manager.createMission(m2);
        
        List<Mission> expected = Arrays.asList(m1, m2);
        List<Mission> actual = manager.findAllMissions();
        
        Collections.sort(actual, MISSION_ID_COMPARATOR);
        Collections.sort(expected, MISSION_ID_COMPARATOR);
        
        assertEquals(expected, actual);
        assertDeepEquals(expected, actual);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createNullMission(){
        manager.createMission(null);
    }
    
    
    
    
    public static Mission newMission(int danger, String assignment){
        Mission mission = new Mission();
        mission.setDanger(danger);
        mission.setAssignment(assignment);
        return mission;
    }
    
    private void assertDeepEquals(Mission expected, Mission actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDanger(), actual.getDanger());
        assertEquals(expected.getAssignment(), actual.getAssignment());
    }
    
    private void assertDeepEquals(List<Mission> expectedList, List<Mission> actualList) {
        for (int i = 0; i < expectedList.size(); i++) {
            Mission expected = expectedList.get(i);
            Mission actual = actualList.get(i);
            assertDeepEquals(expected, actual);
        }
    }
    
    private static final Comparator<Mission> MISSION_ID_COMPARATOR =
            (m1, m2) -> m1.getId().compareTo(m2.getId());
}

