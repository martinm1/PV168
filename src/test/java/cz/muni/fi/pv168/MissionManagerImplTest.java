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
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.After;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Boris
 */
public class MissionManagerImplTest {
    private MissionManagerImpl manager;
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
        DBUtils.executeSqlScript(ds,MissionManager.class.getResource("createTables.sql"));
        manager = new MissionManagerImpl();
        manager.setDataSource(ds);
    }
    
    @After
    public void tearDown() throws SQLException {
        DBUtils.executeSqlScript(ds,MissionManager.class.getResource("dropTables.sql"));
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
    
    @Test
    public void createMissionWithExistingId(){
        Mission m = newMission(8,"TestMission");
        m.setId(1L);
        
        expectedException.expect(IllegalEntityException.class);
        manager.createMission(m);
    }
    
    @Test
    public void createMissionWithNegativeDangerLvl() {
    Mission m = newMission(-5,"TestMission");
    
    expectedException.expect(IllegalEntityException.class);
    manager.createMission(m);
    }
    
    @Test
    public void createMissionWithNullAssignment() {
        Mission m = newMission(8,null);
        
        expectedException.expect(IllegalEntityException.class);
        manager.createMission(m);   
    }
    
    @Test
    public void updateMissionAssignment() {
        Mission missionForUpdate = newMission(8,"TestMission1");
        Mission anotherMission = newMission(2,"TestMission2");
        manager.createMission(anotherMission);
        manager.createMission(missionForUpdate);
        
        missionForUpdate.setAssignment("UpdatedMission");
        manager.updateMission(missionForUpdate);
        
        assertThat(manager.findMissionById(missionForUpdate.getId()))
                .isEqualToComparingFieldByField(missionForUpdate);
        
        assertThat(manager.findMissionById(anotherMission.getId()))
                .isEqualToComparingFieldByField(anotherMission);
    }
    
    @Test
    public void updateMissionDangerLvl() {
        Mission missionForUpdate = newMission(8,"TestMission1");
        Mission anotherMission = newMission(2,"TestMission2");
        manager.createMission(anotherMission);
        manager.createMission(missionForUpdate);
        
        missionForUpdate.setDanger(1);
        manager.updateMission(missionForUpdate);
        
        assertThat(manager.findMissionById(missionForUpdate.getId()))
                .isEqualToComparingFieldByField(missionForUpdate);
        
        assertThat(manager.findMissionById(anotherMission.getId()))
                .isEqualToComparingFieldByField(anotherMission);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void updateNullMission(){
        manager.updateMission(null);
    }
    
    @Test
    public void updateMissionWithNegativeDangerLvl() {
        Mission m = newMission(8,"TestMission");
        manager.createMission(m);
        m.setDanger(-5);
        expectedException.expect(IllegalEntityException.class);
        manager.updateMission(m);
    }
    
    @Test
    public void updateMissionWithNullAssignment() {
        Mission m = newMission(8,"TestMission");
        manager.createMission(m);
        m.setAssignment(null);
        expectedException.expect(IllegalEntityException.class);
        manager.updateMission(m);
    }
    
    @Test
    public void deleteMission() {
        Mission m1 = newMission(8,"TestMission1");
        Mission m2 = newMission(2,"TestMission2");
        manager.createMission(m2);
        manager.createMission(m1);
        
        assertThat(manager.findMissionById(m1.getId())).isNotNull();
        assertThat(manager.findMissionById(m2.getId())).isNotNull();
        
        manager.deleteMission(m1);
        
        assertThat(manager.findMissionById(m1.getId())).isNull();
        assertThat(manager.findMissionById(m2.getId())).isNotNull();
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deleteNullMission() {
        manager.deleteMission(null);
    }
    
    @Test
    public void deleteMissionWithNullId(){
        Mission m = newMission(8,"TestMission");
        m.setId(null);
        expectedException.expect(IllegalEntityException.class);
        manager.deleteMission(m);
    
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
    
   

    @Test
    public void updateMissionWithSqlExceptionThrown() throws SQLException {
        
        Mission mission = newMission(8,"Test");
        manager.createMission(mission);
        SQLException sqlException = new SQLException();
        DataSource failingDataSource = mock(DataSource.class);
        when(failingDataSource.getConnection()).thenThrow(sqlException);
        manager.setDataSource(failingDataSource);
        assertThatThrownBy(() -> manager.updateMission(mission))
                .isInstanceOf(ServiceFailureException.class)
                .hasCause(sqlException);
    }

    @Test
    public void findMissionWithSqlExceptionThrown() throws SQLException {
        
        Mission mission = newMission(8,"Test");
        manager.createMission(mission);
        SQLException sqlException = new SQLException();
        DataSource failingDataSource = mock(DataSource.class);
        when(failingDataSource.getConnection()).thenThrow(sqlException);
        manager.setDataSource(failingDataSource);
        assertThatThrownBy(() -> manager.findMissionById(mission.getId()))
                .isInstanceOf(ServiceFailureException.class)
                .hasCause(sqlException);
    }

    @Test
    public void deleteMissionWithSqlExceptionThrown() throws SQLException {
        
        Mission mission = newMission(8,"Test");
        manager.createMission(mission);
        SQLException sqlException = new SQLException();
        DataSource failingDataSource = mock(DataSource.class);
        when(failingDataSource.getConnection()).thenThrow(sqlException);
        manager.setDataSource(failingDataSource);
        assertThatThrownBy(() -> manager.deleteMission(mission))
                .isInstanceOf(ServiceFailureException.class)
                .hasCause(sqlException);
    }

    @Test
    public void findAllMissionsWithSqlExceptionThrown() throws SQLException {
        SQLException sqlException = new SQLException();
        DataSource failingDataSource = mock(DataSource.class);
        when(failingDataSource.getConnection()).thenThrow(sqlException);
        manager.setDataSource(failingDataSource);
        assertThatThrownBy(() -> manager.findAllMissions())
                .isInstanceOf(ServiceFailureException.class)
                .hasCause(sqlException);
    }



}

