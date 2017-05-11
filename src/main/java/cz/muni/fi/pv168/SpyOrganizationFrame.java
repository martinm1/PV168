/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168;

import com.toedter.calendar.JCalendar;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.LoggerFactory;


/**
 *
 * @author martin
 */
public class SpyOrganizationFrame extends javax.swing.JFrame {
    
    Locale defaultLocale = new Locale("cs_CZ");//Locale.getDefault();
    private static ResourceBundle bundle = ResourceBundle.getBundle("localization", new Locale("cs_CZ")/*Locale.getDefault()*/);
    private final AgentManagerImpl agentManager = new AgentManagerImpl();
    private final MissionManagerImpl missionManager = new MissionManagerImpl();
    private final SpyOrganizationManagerImpl spyOrganizationManager = new SpyOrganizationManagerImpl();
    private final static org.slf4j.Logger log = LoggerFactory.getLogger(SpyOrganizationFrame.class);
    
    /*
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            agent.setWorkingSince(LocalDateTime.parse(LocalDateTime.ofInstant(jDateChooser1.getDate().toInstant(), ZoneId.systemDefault()).format(formatter), formatter));
        
    */
    
    /**
     * Creates new form AgentFrame
     */
    public SpyOrganizationFrame() {
        
        DataSource dataSource = Main.createMemoryDatabase();
        agentManager.setDataSource( dataSource );
        missionManager.setDataSource( dataSource );
        spyOrganizationManager.setDataSource( dataSource );
        
        
        //spyOrganizationManager.assignMission(agentManager.findAgentById(34l), missionManager.findMissionById(1l));
        
        initComponents();
        initMissionComponents();
        initAgentComponents();
        log.info("All swing components initialized");
        localizeSwingComponents();
        
        
    }
    
    private void localizeSwingComponents(){
        
        jButton1.setText(bundle.getString("Add Agent"));
        jButton3.setText(bundle.getString("Delete Agent"));
        jButton2.setText(bundle.getString("Add Mission"));
        jButton4.setText(bundle.getString("Delete Mission"));
        jButton5.setText(bundle.getString("Assign"));
        jButton6.setText(bundle.getString("Unassign"));
        jButton7.setText(bundle.getString("Update date"));
    
        label2.setText(bundle.getString("Name"));
        label3.setText(bundle.getString("Workingsince"));
        label4.setText(bundle.getString("Compromised"));
        label6.setText(bundle.getString("Assignment"));
        label7.setText(bundle.getString("Danger level"));
        
        jCheckBox1.setText(bundle.getString("Yup"));
        
        jTabbedPane1.setTitleAt(0, bundle.getString("Agents"));
        jTabbedPane1.setTitleAt(1, bundle.getString("Missions"));
        jTabbedPane1.setTitleAt(2, bundle.getString("Assignments"));
    
    }
    
    private void initMissionComponents(){
        List<Mission> allMissions = missionManager.findAllMissions();
        MissionTableModel model = (MissionTableModel) jTable2.getModel();
        MissionTableModel model2 = (MissionTableModel) jTable4.getModel();
        
        
        for (Mission mission : allMissions){
            model.addMission(mission);
            model2.addMission(mission);
           // jTable2.setRowSelectionInterval(0, 0);
        }
    }
    
    private void initAgentComponents(){
        
        List<Agent> allAgents = agentManager.findAllAgents();
        AgentTableModel model = (AgentTableModel) jTable1.getModel();
        AgentTableModel model2 = (AgentTableModel) jTable3.getModel();
        AssignmentTableModel model3 = (AssignmentTableModel) jTable5.getModel();
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        jDateChooser1.setDate(cal.getTime());
        jDateChooser2.setDate(cal.getTime());
        
        for (Agent agent : allAgents){
            
            model.addAgent(agent);
            model2.addAgent(agent);
            if (!(spyOrganizationManager.findMissionWithAgent(agent) == null)) model3.addAgent(agent);
           // jTable1.setRowSelectionInterval(0, 0);
        }
    }
     
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField6 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        AgentPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        jCheckBox1 = new javax.swing.JCheckBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();
        MissionPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        label6 = new java.awt.Label();
        jTextField5 = new javax.swing.JTextField();
        label7 = new java.awt.Label();
        jTextField3 = new javax.swing.JTextField();
        AssignmentPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jTextField6.setText("jTextField4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new AgentTableModel());
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Add agent");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete agent");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField2.setPreferredSize(new java.awt.Dimension(100, 22));

        label2.setText("Name");

        label3.setText("Working since");

        label4.setText("Compromised");

        jCheckBox1.setText("Yup");

        jButton7.setText("Update date");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AgentPanelLayout = new javax.swing.GroupLayout(AgentPanel);
        AgentPanel.setLayout(AgentPanelLayout);
        AgentPanelLayout.setHorizontalGroup(
            AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AgentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addGroup(AgentPanelLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AgentPanelLayout.createSequentialGroup()
                        .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1)
                        .addGap(160, 160, 160))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AgentPanelLayout.createSequentialGroup()
                        .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(AgentPanelLayout.createSequentialGroup()
                                .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(40, 40, 40)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AgentPanelLayout.createSequentialGroup()
                                .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                .addGap(246, 246, 246)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(152, 152, 152)))
                .addContainerGap())
        );
        AgentPanelLayout.setVerticalGroup(
            AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AgentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton3))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addGap(54, 54, 54)
                .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AgentPanelLayout.createSequentialGroup()
                        .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Agents", AgentPanel);

        jTable2.setModel(new MissionTableModel());
        jScrollPane2.setViewportView(jTable2);

        jButton2.setText("Add mission");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Delete mission");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        label6.setText("Assignment");

        jTextField5.setPreferredSize(new java.awt.Dimension(100, 22));

        label7.setText("Danger Level");

        jTextField3.setPreferredSize(new java.awt.Dimension(100, 22));

        javax.swing.GroupLayout MissionPanelLayout = new javax.swing.GroupLayout(MissionPanel);
        MissionPanel.setLayout(MissionPanelLayout);
        MissionPanelLayout.setHorizontalGroup(
            MissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MissionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addGroup(MissionPanelLayout.createSequentialGroup()
                        .addGroup(MissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MissionPanelLayout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(MissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        MissionPanelLayout.setVerticalGroup(
            MissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MissionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Missions", MissionPanel);

        jTable3.setModel(new AgentTableModel());
        jScrollPane3.setViewportView(jTable3);

        jTable4.setModel(new MissionTableModel());
        jScrollPane4.setViewportView(jTable4);

        jTable5.setModel(new AssignmentTableModel());
        jScrollPane5.setViewportView(jTable5);

        jButton5.setText("Assign");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Unassign");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AssignmentPanelLayout = new javax.swing.GroupLayout(AssignmentPanel);
        AssignmentPanel.setLayout(AssignmentPanelLayout);
        AssignmentPanelLayout.setHorizontalGroup(
            AssignmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssignmentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AssignmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AssignmentPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(AssignmentPanelLayout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(187, 187, 187))
        );
        AssignmentPanelLayout.setVerticalGroup(
            AssignmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssignmentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AssignmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(AssignmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Assignments", AssignmentPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MissionTableModel model = (MissionTableModel) jTable2.getModel();
        MissionTableModel model2 = (MissionTableModel) jTable4.getModel();
        Mission tm2 = new Mission();
        tm2.setAssignment(jTextField5.getText());
        
        try{
            tm2.setDanger(Integer.parseInt(jTextField3.getText()));
            missionManager.createMission(tm2);
        
            model.addMission(tm2);
            model2.addMission(tm2);
          //  jTable2.setRowSelectionInterval(0, 0);
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, bundle.getString("Danger level must be a number"));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AgentTableModel model = (AgentTableModel) jTable1.getModel();
        AgentTableModel model2 = (AgentTableModel) jTable3.getModel();
        Agent ta1 = new Agent();
        ta1.setName(jTextField2.getText());
        ta1.setCompromised(jCheckBox1.isSelected());
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(jDateChooser1.getDate());
        //String year = String.valueOf(cal.get(Calendar.YEAR));//label9.getText();
        //String month  = String.valueOf(cal.get(Calendar.MONTH));//String.valueOf(jComboBox2.getSelectedItem());
        //String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// = String.valueOf(jComboBox1.getSelectedItem());
        //String date = year+"-"+month+"-"+day+" 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        //System.out.println(date);
        //ta1.setWorkingSince(LocalDateTime.parse(date, formatter));
        
        
        //System.out.println(
        //        LocalDateTime.ofInstant(jDateChooser1.getDate().toInstant(), ZoneId.systemDefault())
        //);
        
        ta1.setWorkingSince(LocalDateTime.parse(LocalDateTime.ofInstant(jDateChooser1.getDate().toInstant(), ZoneId.systemDefault()).format(formatter), formatter));
        
        
        agentManager.createAgent(ta1);
        
       
        model.addAgent(ta1);
        model2.addAgent(ta1);
       // jTable1.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        MissionTableModel model = (MissionTableModel) jTable2.getModel();
        MissionTableModel model2 = (MissionTableModel) jTable4.getModel();
        
        if(jTable2.getSelectedRow()>=0 ){
            Long Id = (Long) jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 0);


            if(spyOrganizationManager.findAgentsOnMission(missionManager.findMissionById(Id)).isEmpty())
            {
                model.delMission(missionManager.findMissionById(Id));
                model2.delMission(missionManager.findMissionById(Id));
                missionManager.deleteMission(missionManager.findMissionById(Id));
                //if(model.getRowCount()!=0){
                //    jTable4.setRowSelectionInterval(0, 0);
                //    jTable2.setRowSelectionInterval(0, 0);
                //}
            }
            else
                JOptionPane.showMessageDialog(this, bundle.getString("Agent assigned to this mission"));
        }
        else
            JOptionPane.showMessageDialog(this, bundle.getString("No mission selected in DB"));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        AgentTableModel model = (AgentTableModel) jTable1.getModel();
        AgentTableModel model2 = (AgentTableModel) jTable3.getModel();
        
        if(jTable1.getSelectedRow()>=0 ){
            Long Id = (Long) jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0);
            if(spyOrganizationManager.findMissionWithAgent(agentManager.findAgentById(Id)) == null)
            {
                model.delAgent(agentManager.findAgentById(Id));
                model2.delAgent(agentManager.findAgentById(Id));
                agentManager.deleteAgent(agentManager.findAgentById(Id));
               // if(model.getRowCount()!=0){
               //     jTable1.setRowSelectionInterval(0, 0);
               //     jTable3.setRowSelectionInterval(0, 0);
               // }
            }
            else
                JOptionPane.showMessageDialog(this, bundle.getString("agent is on mission"));
        }
        else
            JOptionPane.showMessageDialog(this, bundle.getString("no agent selected in DB"));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        AssignmentTableModel assModel = (AssignmentTableModel) jTable5.getModel();
        if(jTable3.getSelectedRow() >= 0 && jTable4.getSelectedRow() >= 0){
            Long aId = (Long) jTable3.getModel().getValueAt(jTable3.getSelectedRow(), 0);
            Long mId = (Long) jTable4.getModel().getValueAt(jTable4.getSelectedRow(), 0);

            if(spyOrganizationManager.findMissionWithAgent(agentManager.findAgentById(aId)) == null)
            {
                spyOrganizationManager.assignMission(agentManager.findAgentById(aId), missionManager.findMissionById(mId));
                assModel.addAgent(agentManager.findAgentById(aId));

            }
            else
                JOptionPane.showMessageDialog(this, bundle.getString("agent is alredy on mission"));
        }
        else
            JOptionPane.showMessageDialog(this, bundle.getString("agent or mission not selected"));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        AssignmentTableModel assModel = (AssignmentTableModel) jTable5.getModel();
        
        if(jTable5.getSelectedRow() >=0){
            Long aId = (Long) jTable5.getModel().getValueAt(jTable5.getSelectedRow(), 0);
            Long mId = (Long) jTable5.getModel().getValueAt(jTable5.getSelectedRow(), 1);

            assModel.delAgent(agentManager.findAgentById(aId));


            spyOrganizationManager.unassignMission(agentManager.findAgentById(aId), missionManager.findMissionById(mId));
        }
        else
            JOptionPane.showMessageDialog(this, bundle.getString("No assigned mission selected in DB"));
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        AgentTableModel assModel = (AgentTableModel) jTable1.getModel();
        AgentTableModel assMode3 = (AgentTableModel) jTable3.getModel();
        
        if(jTable1.getSelectedRow() >=0){
            Long aId = (Long) jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Agent agent = agentManager.findAgentById(aId);
            agent.setWorkingSince(LocalDateTime.parse(LocalDateTime.ofInstant(jDateChooser2.getDate().toInstant(), ZoneId.systemDefault()).format(formatter), formatter));
            agentManager.updateAgent(agent);
            assModel.delAgent(agent);
            assModel.addAgent(agent);
            assMode3.delAgent(agent);
            assMode3.addAgent(agent);
            
        }
        else
            JOptionPane.showMessageDialog(this, bundle.getString("no agent selected in DB"));
        
    }//GEN-LAST:event_jButton7ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SpyOrganizationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SpyOrganizationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SpyOrganizationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SpyOrganizationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SpyOrganizationFrame().setVisible(true);
            }
        });
    }
    
    private class AssignmentTableModel extends AbstractTableModel {

        private List<Agent> agents = new ArrayList<Agent>();

        @Override
        public int getRowCount() {
            return agents.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }
        

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return bundle.getString("Agent Id");
                case 1:
                    return bundle.getString("Mission Id");
                default:
                    throw new IllegalArgumentException("columnIndex");
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Agent agent = agents.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return agent.getId();
                case 1:
                    return spyOrganizationManager.findMissionWithAgent(agent).getId();
                default:
                    throw new IllegalArgumentException("columnIndex");
            }
        }
        
        public  Class<?> getColumnClass(int columnIndex){
            switch (columnIndex){
                case 0:
                    return Long.class;
                case 1: 
                    return Long.class;
                default:
                    throw new IllegalArgumentException("columnIndex");                    
            }
        
        }
        
        
        public void addAgent(Agent agent) {
            agents.add(agent);
            int lastRow = agents.size() - 1;
            fireTableRowsInserted(lastRow, lastRow);
        }
        
        public void delAgent(Agent agent) {
            
            agents.remove(agent);
            fireTableRowsDeleted(jTable5.getSelectedRow(),jTable5.getSelectedRow());
        }
        
    }
    
    
    
    private class AgentTableModel extends AbstractTableModel {

        private List<Agent> agents = new ArrayList<Agent>();

        @Override
        public int getRowCount() {
            return agents.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }
        

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return bundle.getString("Id");//"Id";
                case 1:
                    return bundle.getString("Name");//"Name";
                case 2:
                    return bundle.getString("Workingsince");//"Working since";
                case 3:
                    return bundle.getString("Compromised");//"Compromised";
                default:
                    throw new IllegalArgumentException("columnIndex");
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Agent agent = agents.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return agent.getId();
                case 1:
                    return agent.getName();
                case 2:
                    return agent.getWorkingSince();
                case 3:
                    return agent.getCompromised();
                default:
                    throw new IllegalArgumentException("columnIndex");
            }
        }
        
       
       
        
        public  Class<?> getColumnClass(int columnIndex){
            switch (columnIndex){
                case 0:
                    return Long.class;
                case 1: 
                    return String.class;
                case 2:
                    return LocalDateTime.class;
                case 3:
                    return Boolean.class;
                default:
                    throw new IllegalArgumentException("columnIndex");                    
            }
        
        }
        
        
        public void setValueAt(Object aValue, int rowIndex, int columnIndex){
            Agent agent = agents.get(rowIndex);
            switch(columnIndex) {
                case 0:
                    agent.setId((Long) aValue);
                    break;
                case 1:
                    agent.setName((String) aValue);
                    break;
                case 2:
                    agent.setWorkingSince((LocalDateTime) aValue);
                    break;
                case 3:
                    agent.setCompromised((Boolean) aValue);
                    break;
                default:
                    throw new IllegalArgumentException("columnIndex");
            }
            
            
            Long Id = agent.getId();//(Long) jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0);
            fireTableCellUpdated(rowIndex,columnIndex);
            AgentTableModel model = (AgentTableModel) jTable1.getModel();
            Agent upAgent = agentManager.findAgentById(Id);
            upAgent.setName((String) model.getValueAt(/*jTable1.getSelectedRow()*/rowIndex, 1));
            upAgent.setWorkingSince((LocalDateTime) model.getValueAt(/*jTable1.getSelectedRow()*/rowIndex, 2));
            upAgent.setCompromised((Boolean) model.getValueAt( /*jTable1.getSelectedRow()*/rowIndex, 3));
            
            agentManager.updateAgent(agent);          
        }
        
        public boolean isCellEditable(int rowIndex, int columnIndex){
            switch (columnIndex){
                case 0:
                case 2:
                    return false; 
                case 1:
                case 3:
                    return true;
                default: 
                    throw new IllegalArgumentException("columnIndex");
            
            }
        
        }
        
        
        
        public void addAgent(Agent agent) {
            agents.add(agent);
            int lastRow = agents.size() - 1;
            fireTableRowsInserted(lastRow, lastRow);
        }
        
        public void delAgent(Agent agent) {
            agents.remove(agent);
            fireTableRowsDeleted(jTable2.getSelectedRow(),jTable2.getSelectedRow());
        }
    }
    
    private class MissionTableModel extends AbstractTableModel {

        private List<Mission> missions = new ArrayList<Mission>();

        
        @Override
        public int getRowCount() {
            return missions.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "Id";
                case 1:
                    return bundle.getString("Danger");
                case 2:
                    return bundle.getString("Assignment");
                default:
                    throw new IllegalArgumentException("columnIndex");
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Mission mission = missions.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return mission.getId();
                case 1:
                    return mission.getDanger();
                case 2:
                    return mission.getAssignment();
                default:
                    throw new IllegalArgumentException("columnIndex");
            }
        }
        
        public  Class<?> getColumnClass(int columnIndex){
            switch (columnIndex){
                case 0:
                    return Long.class;
                case 1: 
                    return Integer.class;
                case 2:
                    return String.class;
                default:
                    throw new IllegalArgumentException("columnIndex");                    
            }
        
        }
        
        
        public void setValueAt(Object aValue, int rowIndex, int columnIndex){
            Mission mission = missions.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    mission.setId((Long) aValue);
                    break;
                case 1:
                    try{
                        Integer integer = ((Integer) aValue);
                        mission.setDanger(integer);
                        break;
                    }
                    catch (NumberFormatException e){
                        mission.setDanger(mission.getDanger());
                        break;
                    }
                    
                case 2:
                    mission.setAssignment((String) aValue);
                    break;
                default:
                    throw new IllegalArgumentException("columnIndex");
            }
            Long Id = mission.getId();
            fireTableCellUpdated(rowIndex,columnIndex);
            MissionTableModel model = (MissionTableModel) jTable2.getModel();
            Mission upMission = missionManager.findMissionById(Id);

            upMission.setDanger(((Integer) model.getValueAt(/*jTable2.getSelectedRow()*/rowIndex, 1)).intValue());
            upMission.setAssignment((String) model.getValueAt(/*jTable2.getSelectedRow()*/rowIndex, 2));


            missionManager.updateMission(mission);
            
        }
        
        public boolean isCellEditable(int rowIndex, int columnIndex){
            switch (columnIndex){
                case 0:
                    return false;
                case 1:
                case 2:
                    return true;
                default: 
                    throw new IllegalArgumentException("columnIndex");
            
            }
        
        }
        
        public void addMission(Mission mission) {
            missions.add(mission);
            int lastRow = missions.size() - 1;
            fireTableRowsInserted(lastRow, lastRow);
        }
        
        public void delMission(Mission mission) {
            missions.remove(mission);
            fireTableRowsDeleted(jTable2.getSelectedRow(),jTable2.getSelectedRow());
        }
        
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AgentPanel;
    private javax.swing.JPanel AssignmentPanel;
    private javax.swing.JPanel MissionPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label6;
    private java.awt.Label label7;
    // End of variables declaration//GEN-END:variables
}
