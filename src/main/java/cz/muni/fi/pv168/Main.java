/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;
import org.apache.derby.jdbc.ClientDriver;

/**
 *
 * @author Boris
 */
public class Main {
    final static Logger log = LoggerFactory.getLogger(Main.class);
    
    public static DataSource createMemoryDatabase() {
        
        Properties conf = new Properties();
        InputStream inputStream = Main.class.getResourceAsStream("/DBconfig.properties");
        try {
            conf.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }  
        
        
        
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(ClientDriver.class.getName());
        
        bds.setUrl(conf.getProperty("dbUrl")/*jdbc:derby://localhost:1527/MissionDB"*/);
        //bds.setUsername("");
        //bds.setPassword("");
        
        //new ResourceDatabasePopulator(
          //      new ClassPathResource("schema-javadb.sql"),
            //    new ClassPathResource("test-data.sql"))
              //  .execute(bds);
        
        
        return bds;
    
    }
    
    public static void main(String[] args) {

        log.info("zaciname");

        DataSource dataSource = createMemoryDatabase();
        MissionManager missionManager = new MissionManagerImpl();
        missionManager.setDataSource(dataSource);

        List<Mission> allMissions = missionManager.findAllMissions();
        System.out.println("allMissions = " + allMissions);
        
        AgentManager agentManager = new AgentManagerImpl();
        missionManager.setDataSource(dataSource);

        List<Agent> allAgents = agentManager.findAllAgents();
        System.out.println("allAgents = " + allAgents);

    }
    
}
