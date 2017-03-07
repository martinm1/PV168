/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168;

import java.time.LocalDateTime;

/**
 *
 * @author martin
 */
public class Agent implements SpyOrganizationManager, AgentManager{
  Long id;
  String name; 
  LocalDateTime workingSince;
  
  Boolean compromised;
  
}
