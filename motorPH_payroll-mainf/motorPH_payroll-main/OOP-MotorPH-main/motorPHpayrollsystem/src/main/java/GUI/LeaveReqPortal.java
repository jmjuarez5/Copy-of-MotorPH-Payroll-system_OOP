/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import OOP.SessionTimeoutManager;
import OOP.SystemIT;
import CTRL_SRVS.LeaveService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ira
 */
public class LeaveReqPortal extends javax.swing.JFrame {
    public LeaveReqPortal() {
        initComponents();
        loadLeaveRequests();
        setEmployeeNumber();
        
                        //Call timeout
        SessionTimeoutManager.start(this, () -> {
        // This runs on Swing EDT — safe to show dialogs and dispose
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Session timed out due to inactivity.",
                "Session Expired",
                javax.swing.JOptionPane.WARNING_MESSAGE
            );
            new GUI.LogIn().setVisible(true); // Assuming Login is in GUI package
            this.dispose();
        });
    }
    
    public LeaveReqPortal(OOP.Employee employee) {
        initComponents();
        loadLeaveRequests();
        setEmployeeNumber(employee);
        
                        //Call timeout
        SessionTimeoutManager.start(this, () -> {
        // This runs on Swing EDT — safe to show dialogs and dispose
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Session timed out due to inactivity.",
                "Session Expired",
                javax.swing.JOptionPane.WARNING_MESSAGE
            );
            new GUI.LogIn().setVisible(true); // Assuming Login is in GUI package
            this.dispose();
        });
    }
        @Override
    public void dispose() {
        SessionTimeoutManager.stop(); // Explicit cleanup
        super.dispose();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        welcomeBack = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        line = new javax.swing.JPanel();
        empNum1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        refresh = new javax.swing.JButton();
        requestLeave = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        EndMonth = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        StartMonth = new javax.swing.JComboBox<>();
        LeaveTypeBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        endDay = new javax.swing.JTextField();
        startDay = new javax.swing.JTextField();
        endYear = new javax.swing.JTextField();
        startYear = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaveTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        welcomeBack1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        welcomeBack.setBackground(new java.awt.Color(255, 255, 255));
        welcomeBack.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        welcomeBack.setForeground(new java.awt.Color(255, 255, 255));
        welcomeBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeBack.setText("Leave Request");
        jPanel1.add(welcomeBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 209, -1));

        jLabel1.setFont(new java.awt.Font("Calisto MT", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Employee Number:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 121, -1, -1));

        line.setBackground(new java.awt.Color(255, 255, 255));
        line.setPreferredSize(new java.awt.Dimension(248, 3));

        javax.swing.GroupLayout lineLayout = new javax.swing.GroupLayout(line);
        line.setLayout(lineLayout);
        lineLayout.setHorizontalGroup(
            lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        lineLayout.setVerticalGroup(
            lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(line, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 65, 360, 10));

        empNum1.setText("#");
        jPanel1.add(empNum1, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 121, -1, -1));

        jLabel3.setText("Leave Type:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(487, 172, -1, -1));

        jLabel4.setText("Start Date:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(459, 230, -1, -1));

        jLabel6.setText("Notes:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 341, -1, -1));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray));

        refresh.setText("Refresh Table");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        requestLeave.setText("Request Leave");
        requestLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestLeaveActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Calisto MT", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("End: ");

        EndMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        jLabel8.setFont(new java.awt.Font("Calisto MT", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Start:");

        StartMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        LeaveTypeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vacation Leave (VL)", "Sick Leave (SL)", "Paternity Leave (PL)", "Emergency Leave (EL)" }));

        jLabel7.setFont(new java.awt.Font("Calisto MT", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Day:");

        jLabel2.setFont(new java.awt.Font("Calisto MT", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Year:");

        jLabel10.setFont(new java.awt.Font("Calisto MT", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Day:");

        jLabel11.setFont(new java.awt.Font("Calisto MT", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Year:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(4, 4, 4)
                        .addComponent(startYear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(StartMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)
                        .addGap(9, 9, 9)
                        .addComponent(startDay, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4)
                        .addComponent(endYear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(EndMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addGap(9, 9, 9)
                        .addComponent(endDay, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(LeaveTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(refresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(requestLeave)
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(LeaveTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(startYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StartMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(startDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jLabel9)
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(endYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EndMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(endDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refresh)
                    .addComponent(requestLeave))
                .addGap(13, 13, 13))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 370, 300));

        leaveTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Leave Type", "Start Date", "End Date", "Status"
            }
        ));
        leaveTable.setEnabled(false);
        leaveTable.setFocusable(false);
        leaveTable.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.setViewportView(leaveTable);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        welcomeBack1.setBackground(new java.awt.Color(255, 255, 255));
        welcomeBack1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        welcomeBack1.setForeground(new java.awt.Color(255, 255, 255));
        welcomeBack1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeBack1.setText("Your request");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(welcomeBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(welcomeBack1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void requestLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestLeaveActionPerformed
        try {
            String leaveType = (String) LeaveTypeBox.getSelectedItem();
            String startMonthStr = (String) StartMonth.getSelectedItem();
            String startDayStr = startDay.getText().trim();
            String startYearStr = startYear.getText().trim();
            String endMonthStr = (String) EndMonth.getSelectedItem();
            String endDayStr = endDay.getText().trim();
            String endYearStr = endYear.getText().trim();

            // Get employee number from the label
            String employeeNumber = empNum1.getText().trim();

            // Validate required fields
            if (employeeNumber == null || employeeNumber.isEmpty() || "#".equals(employeeNumber)) {
                JOptionPane.showMessageDialog(this, "Employee number is not set. Please log in again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (startDayStr.isEmpty() || startYearStr.isEmpty() || endDayStr.isEmpty() || endYearStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all date fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Create proper date format: YYYY-MM-DD with zero-padding for days
            String startDate = startYearStr + "-" + getMonthNumber(startMonthStr) + "-" + padDay(startDayStr);
            String endDate = endYearStr + "-" + getMonthNumber(endMonthStr) + "-" + padDay(endDayStr);

            // Use LeaveService for business logic
            LeaveService leaveService = new LeaveService();
            leaveService.submitLeaveRequest(employeeNumber, leaveType, startDate, endDate);
            
            JOptionPane.showMessageDialog(this, "Leave Request Submitted Successfully!");
            
            // Refresh the table to show the newly submitted request
            loadLeaveRequests();
            
            // Clear the form
            startDay.setText("");
            startYear.setText("");
            endDay.setText("");
            endYear.setText("");
            
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error submitting leave request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_requestLeaveActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        refreshTable();
    }//GEN-LAST:event_refreshActionPerformed
    private void setEmployeeNumber() {
        // Get the current employee from the session
        OOP.Employee currentEmployee = getCurrentEmployee();
        if (currentEmployee != null) {
            empNum1.setText(currentEmployee.getEmployeeNumber());
        } else {
            empNum1.setText("#");
        }
    }
    
    private void setEmployeeNumber(OOP.Employee employee) {
        if (employee != null) {
            empNum1.setText(employee.getEmployeeNumber());
        } else {
            empNum1.setText("#");
        }
    }
    
    private OOP.Employee getCurrentEmployee() {
        // Try to get the current employee from the session
        // This assumes there's a way to access the current user session
        // For now, we'll return null and the label will show "#"
        // In a real implementation, this would get the employee from the login session
        return null;
    }
    
    private void loadLeaveRequests() {
        DefaultTableModel model = (DefaultTableModel) leaveTable.getModel();
        model.setRowCount(0);
        String csvFile = SystemIT.LEAVES_CSV;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length >= 5) {
                    String startDate = data[2].trim();
                    String endDate = data[3].trim();
                    model.addRow(new Object[]{data[0].trim(), data[1].trim(), startDate, endDate, data[4].trim()});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void refreshTable() {
        loadLeaveRequests();
    }
    
    /**
     * Convert month name to month number (01-12)
     * @param monthName The month name (e.g., "January", "February")
     * @return Month number as two-digit string
     */
    private String getMonthNumber(String monthName) {
        switch (monthName) {
            case "January": return "01";
            case "February": return "02";
            case "March": return "03";
            case "April": return "04";
            case "May": return "05";
            case "June": return "06";
            case "July": return "07";
            case "August": return "08";
            case "September": return "09";
            case "October": return "10";
            case "November": return "11";
            case "December": return "12";
            default: return "01";
        }
    }
    
    /**
     * Pad day value with leading zero if needed (e.g., "1" becomes "01")
     * @param dayStr The day as string
     * @return Day as two-digit string
     */
    private String padDay(String dayStr) {
        if (dayStr == null || dayStr.isEmpty()) {
            return "01"; // Default to 01 if empty
        }
        try {
            int day = Integer.parseInt(dayStr.trim());
            if (day < 1 || day > 31) {
                return "01"; // Default to 01 if invalid
            }
            return String.format("%02d", day);
        } catch (NumberFormatException e) {
            return "01"; // Default to 01 if not a number
        }
    }
    
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
            java.util.logging.Logger.getLogger(LeaveReqPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeaveReqPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeaveReqPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeaveReqPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> EndMonth;
    private javax.swing.JComboBox<String> LeaveTypeBox;
    private javax.swing.JComboBox<String> StartMonth;
    private javax.swing.JLabel empNum1;
    private javax.swing.JTextField endDay;
    private javax.swing.JTextField endYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable leaveTable;
    private javax.swing.JPanel line;
    private javax.swing.JButton refresh;
    private javax.swing.JButton requestLeave;
    private javax.swing.JTextField startDay;
    private javax.swing.JTextField startYear;
    private javax.swing.JLabel welcomeBack;
    private javax.swing.JLabel welcomeBack1;
    // End of variables declaration//GEN-END:variables
}   
