/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.LoggerFactory;

/**
 *
 * @author martin
 */
public class SpyOrganizationFrame extends javax.swing.JFrame {
    Locale defaultLocale = Locale.getDefault();
    //ResourceBundle text = ResourceBundle.getBundle("Text", defaultLocale);
    private final AgentManagerImpl agentManager = new AgentManagerImpl();
    private final MissionManagerImpl missionManager = new MissionManagerImpl();
    private final SpyOrganizationManagerImpl spyOrganizationManager = new SpyOrganizationManagerImpl();
    private final static org.slf4j.Logger log = LoggerFactory.getLogger(SpyOrganizationFrame.class);
    
    /**
     * Creates new form AgentFrame
     */
    public SpyOrganizationFrame() {
        DataSource dataSource = Main.createMemoryDatabase();
        agentManager.setDataSource( dataSource );
        missionManager.setDataSource( dataSource );
        spyOrganizationManager.setDataSource( dataSource );
        initComponents();
        initMissionComponents();
        initAgentComponents();
        //log.info("All swing components initialized");
        
    }
    
    
    
    private void initMissionComponents(){
        List<Mission> allMissions = missionManager.findAllMissions();
        MissionTableModel model = (MissionTableModel) jTable2.getModel();
        
        for (Mission mission : allMissions){
            model.addMission(mission);
        }
    }
    
    private void initAgentComponents(){
        List<Agent> allAgents = agentManager.findAllAgents();
        AgentTableModel model = (AgentTableModel) jTable1.getModel();
        
        for (Agent agent : allAgents){
            model.addAgent(agent);
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jTextField6 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        AgentPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        jCheckBox1 = new javax.swing.JCheckBox();
        jSlider2 = new javax.swing.JSlider();
        label9 = new java.awt.Label();
        MissionPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        label6 = new java.awt.Label();
        jTextField5 = new javax.swing.JTextField();
        label7 = new java.awt.Label();
        jTextField3 = new javax.swing.JTextField();
        jTabbedPane2 = new javax.swing.JTabbedPane();

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", " " }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", " " }));

        label2.setText("Name");

        label3.setText("Working since");

        label4.setText("Compromised");

        jCheckBox1.setText("Yup");

        jSlider2.setMaximum(1999);
        jSlider2.setMinimum(1900);
        jSlider2.setValue(1950);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jSlider2, org.jdesktop.beansbinding.ELProperty.create("${value}"), label9, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout AgentPanelLayout = new javax.swing.GroupLayout(AgentPanel);
        AgentPanel.setLayout(AgentPanelLayout);
        AgentPanelLayout.setHorizontalGroup(
            AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AgentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addGroup(AgentPanelLayout.createSequentialGroup()
                        .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(AgentPanelLayout.createSequentialGroup()
                        .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1)
                        .addGap(206, 206, 206))
                    .addGroup(AgentPanelLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(AgentPanelLayout.createSequentialGroup()
                        .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)))
                .addContainerGap())
        );
        AgentPanelLayout.setVerticalGroup(
            AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AgentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(54, 54, 54)
                .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(label3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AgentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(AgentPanelLayout.createSequentialGroup()
                        .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
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

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPane1.addTab("Assignments", jTabbedPane2);

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

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MissionTableModel model = (MissionTableModel) jTable2.getModel();
        Mission tm2 = new Mission();
        tm2.setAssignment(jTextField5.getText());
        tm2.setDanger(Integer.parseInt(jTextField3.getText()));
        
        missionManager.createMission(tm2);
        
        model.addMission(tm2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AgentTableModel model = (AgentTableModel) jTable1.getModel();
        Agent ta1 = new Agent();
        ta1.setName(jTextField2.getText());
        ta1.setCompromised(jCheckBox1.isSelected());
        
        String year = label9.getText();
        String month = String.valueOf(jComboBox2.getSelectedItem());
        String day = String.valueOf(jComboBox1.getSelectedItem());
        String date = year+"-"+month+"-"+day+" 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.print(date);
        ta1.setWorkingSince(LocalDateTime.parse(date, formatter));
        

        agentManager.createAgent(ta1);
        model.addAgent(ta1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        MissionTableModel model = (MissionTableModel) jTable2.getModel();
        Long Id = (Long) jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 0);
        model.delMission(missionManager.findMissionById(Id));
        missionManager.deleteMission(missionManager.findMissionById(Id));
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        AgentTableModel model = (AgentTableModel) jTable1.getModel();
        Long Id = (Long) jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0);
        model.delAgent(agentManager.findAgentById(Id));
        agentManager.deleteAgent(agentManager.findAgentById(Id));
    }//GEN-LAST:event_jButton3ActionPerformed
    
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
                    return "Id";
                case 1:
                    return "Name";
                case 2:
                    return "Working since";
                case 3:
                    return "Compromised";
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
                default:
                    throw new IllegalArgumentException("columnIndex");
            }
            Long Id = (Long) jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0);
            fireTableCellUpdated(rowIndex,columnIndex);
            AgentTableModel model = (AgentTableModel) jTable1.getModel();
            Agent upAgent = agentManager.findAgentById(Id);
            upAgent.setName((String) model.getValueAt(jTable1.getSelectedRow(), 1));
            upAgent.setWorkingSince((LocalDateTime) model.getValueAt(jTable1.getSelectedRow(), 2));
            upAgent.setCompromised((Boolean) model.getValueAt(jTable1.getSelectedRow(), 3));
            
            agentManager.updateAgent(agent);          
        }
        
        public boolean isCellEditable(int rowIndex, int columnIndex){
            switch (columnIndex){
                case 0:
                case 2:
                case 3:
                    return false;
                case 1:
                    
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
                    return "Danger";
                case 2:
                    return "Assignment";
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
                    mission.setDanger((Integer) aValue);
                    break;
                case 2:
                    mission.setAssignment((String) aValue);
                    break;
                default:
                    throw new IllegalArgumentException("columnIndex");
            }
            Long Id = (Long) jTable2.getModel().getValueAt(jTable2.getSelectedRow(), 0);
            fireTableCellUpdated(rowIndex,columnIndex);
            MissionTableModel model = (MissionTableModel) jTable2.getModel();
            Mission upMission = missionManager.findMissionById(Id);
            upMission.setDanger((int) model.getValueAt(jTable2.getSelectedRow(), 1));
            upMission.setAssignment((String) model.getValueAt(jTable2.getSelectedRow(), 2));
            
            
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
    private javax.swing.JPanel MissionPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label9;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
