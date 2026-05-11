/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import OOP.Admin;
import OOP.SystemIT;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import OOP.SessionTimeoutManager;
import OOP.InputValidator;
import OOP.ValidationRule;
import java.util.HashMap;
import java.util.Map;
import CTRL_SRVS.PasswordService;
import CTRL_SRVS.EmployeeService;
import DAO.EmployeeDAO;

/**
 *
 * @author Ira
 */
public class AdminHRPortal extends javax.swing.JFrame {

    // Temporary storage for original passwords to enable showing them
    private String originalPassword = "";
    private PasswordService passwordService;

    public AdminHRPortal() {
        initComponents();
        // Initialize PasswordService
        this.passwordService = new PasswordService();
        // Call timeout
        SessionTimeoutManager.start(this, () -> {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Session timed out due to inactivity.",
                "Session Expired",
                javax.swing.JOptionPane.WARNING_MESSAGE
            );
            new GUI.LogIn().setVisible(true);
            this.dispose();
        });
    }

    @Override
    public void dispose() {
        SessionTimeoutManager.stop();
        super.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        updateTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        search = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        backtoAdmin = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        GovDetailPane = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        sss = new javax.swing.JTextField();
        tin = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pagIBIG = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        philHealth = new javax.swing.JTextField();
        PayrollPane = new javax.swing.JPanel();
        basicSalary = new javax.swing.JTextField();
        riceSub = new javax.swing.JTextField();
        hourlyRate = new javax.swing.JTextField();
        clothingAllowance = new javax.swing.JTextField();
        monthlyRate = new javax.swing.JTextField();
        phoneAllowance = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        FinerDetailPane = new javax.swing.JPanel();
        address = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        firstName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lastName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        birthday = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        phoneNum = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        status = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        position = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        immediateSupervisor = new javax.swing.JTextField();
        empNum = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CredentialsPane = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        resetPassword = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(54, 117, 136));

        updateTable.setBackground(new java.awt.Color(242, 242, 242));
        updateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {
                "#", "Last Name", "First Name", "SSS #", "PhilHealth #", "TIN #", "Pag IBIG #",
                "Birthday", "Address", "Phone Number", "Status", "Position", "Immediate Supervisor",
                "Basic Salary", "Rice Subsidy", "Phone Allowance", "Clothing Allowance",
                "Gross Semi-Monthly Rate", "Hourly Rate", "Email", "Password"
            }
        ) {
            Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) { return types[columnIndex]; }
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit[columnIndex]; }
        });
        updateTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(updateTable);
        if (updateTable.getColumnModel().getColumnCount() > 0) {
            updateTable.getColumnModel().getColumn(0).setMinWidth(25);
            updateTable.getColumnModel().getColumn(0).setPreferredWidth(25);
            updateTable.getColumnModel().getColumn(0).setMaxWidth(25);
            updateTable.getColumnModel().getColumn(3).setMinWidth(0);
            updateTable.getColumnModel().getColumn(3).setPreferredWidth(0);
            updateTable.getColumnModel().getColumn(3).setMaxWidth(0);
            updateTable.getColumnModel().getColumn(4).setMinWidth(0);
            updateTable.getColumnModel().getColumn(4).setPreferredWidth(0);
            updateTable.getColumnModel().getColumn(4).setMaxWidth(0);
            updateTable.getColumnModel().getColumn(5).setMinWidth(0);
            updateTable.getColumnModel().getColumn(5).setPreferredWidth(0);
            updateTable.getColumnModel().getColumn(5).setMaxWidth(0);
            updateTable.getColumnModel().getColumn(6).setMinWidth(0);
            updateTable.getColumnModel().getColumn(6).setPreferredWidth(0);
            updateTable.getColumnModel().getColumn(6).setMaxWidth(0);
            updateTable.getColumnModel().getColumn(8).setMinWidth(0);
            updateTable.getColumnModel().getColumn(8).setPreferredWidth(0);
            updateTable.getColumnModel().getColumn(8).setMaxWidth(0);
            updateTable.getColumnModel().getColumn(13).setMinWidth(0);
            updateTable.getColumnModel().getColumn(13).setPreferredWidth(0);
            updateTable.getColumnModel().getColumn(13).setMaxWidth(0);
            updateTable.getColumnModel().getColumn(14).setMinWidth(0);
            updateTable.getColumnModel().getColumn(14).setPreferredWidth(0);
            updateTable.getColumnModel().getColumn(14).setMaxWidth(0);
            updateTable.getColumnModel().getColumn(15).setMinWidth(0);
            updateTable.getColumnModel().getColumn(15).setPreferredWidth(0);
            updateTable.getColumnModel().getColumn(15).setMaxWidth(0);
            updateTable.getColumnModel().getColumn(16).setMinWidth(0);
            updateTable.getColumnModel().getColumn(16).setPreferredWidth(0);
            updateTable.getColumnModel().getColumn(16).setMaxWidth(0);
            updateTable.getColumnModel().getColumn(17).setMinWidth(0);
            updateTable.getColumnModel().getColumn(17).setPreferredWidth(0);
            updateTable.getColumnModel().getColumn(17).setMaxWidth(0);
        }

        jSeparator1.setBackground(new java.awt.Color(0, 51, 102));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(54, 117, 136));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, java.awt.Color.gray, java.awt.Color.gray));

        deleteButton.setBackground(new java.awt.Color(175, 196, 222));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { deleteButtonActionPerformed(evt); }
        });

        createButton.setBackground(new java.awt.Color(175, 196, 222));
        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { createButtonActionPerformed(evt); }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 10));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Enter Employee Number/Last name/First name");

        backtoAdmin.setBackground(new java.awt.Color(176, 196, 222));
        backtoAdmin.setText("Back to Portal");
        backtoAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { backtoAdminActionPerformed(evt); }
        });

        searchButton.setBackground(new java.awt.Color(175, 196, 222));
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { searchButtonActionPerformed(evt); }
        });

        updateButton.setBackground(new java.awt.Color(176, 196, 222));
        updateButton.setFont(new java.awt.Font("Segoe UI", 1, 12));
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { updateButtonActionPerformed(evt); }
        });

        refresh.setBackground(new java.awt.Color(176, 196, 222));
        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { refreshActionPerformed(evt); }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(updateButton)
                        .addGap(12, 12, 12)
                        .addComponent(createButton)
                        .addGap(12, 12, 12)
                        .addComponent(deleteButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backtoAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchButton)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(createButton)
                    .addComponent(deleteButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backtoAdmin)
                    .addComponent(refresh))
                .addGap(15, 15, 15))
        );

        GovDetailPane.setBackground(new java.awt.Color(54, 117, 136));
        GovDetailPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, java.awt.Color.gray, java.awt.Color.gray));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("SSS Number:");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("TIN Number:");

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("PagIBIG Number:");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PhilHealth Number:");

        javax.swing.GroupLayout GovDetailPaneLayout = new javax.swing.GroupLayout(GovDetailPane);
        GovDetailPane.setLayout(GovDetailPaneLayout);
        GovDetailPaneLayout.setHorizontalGroup(
            GovDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GovDetailPaneLayout.createSequentialGroup()
                .addGroup(GovDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GovDetailPaneLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(GovDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12))
                        .addGap(8, 8, 8)
                        .addGroup(GovDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tin, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(sss)))
                    .addGroup(GovDetailPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(GovDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(GovDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(philHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pagIBIG, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        GovDetailPaneLayout.setVerticalGroup(
            GovDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GovDetailPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GovDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(sss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(GovDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(GovDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(philHealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(GovDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(pagIBIG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        PayrollPane.setBackground(new java.awt.Color(54, 117, 136));
        PayrollPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, java.awt.Color.gray, java.awt.Color.gray));

        riceSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { riceSubActionPerformed(evt); }
        });

        hourlyRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { hourlyRateActionPerformed(evt); }
        });

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Gross Semi-Monthly Rate:");

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Phone Allowance:");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Clothing Allowance:");

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Rice Subsidy:");

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Basic Salary:");

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Hourly Rate:");

        javax.swing.GroupLayout PayrollPaneLayout = new javax.swing.GroupLayout(PayrollPane);
        PayrollPane.setLayout(PayrollPaneLayout);
        PayrollPaneLayout.setHorizontalGroup(
            PayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PayrollPaneLayout.createSequentialGroup()
                .addGroup(PayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(riceSub)
                    .addComponent(basicSalary)
                    .addComponent(clothingAllowance, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(phoneAllowance, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(monthlyRate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hourlyRate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        PayrollPaneLayout.setVerticalGroup(
            PayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PayrollPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(riceSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(PayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(basicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(PayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(PayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clothingAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(PayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monthlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(PayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FinerDetailPane.setBackground(new java.awt.Color(54, 117, 136));
        FinerDetailPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, java.awt.Color.gray, java.awt.Color.gray));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Address:");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("First Name:");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Surname:");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Birthday:");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Phone No.:");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Status:");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Position:");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Immediate Supervisor:");

        empNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { empNumActionPerformed(evt); }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Employee No.:");

        javax.swing.GroupLayout FinerDetailPaneLayout = new javax.swing.GroupLayout(FinerDetailPane);
        FinerDetailPane.setLayout(FinerDetailPaneLayout);
        FinerDetailPaneLayout.setHorizontalGroup(
            FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinerDetailPaneLayout.createSequentialGroup()
                .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FinerDetailPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FinerDetailPaneLayout.createSequentialGroup()
                                .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(FinerDetailPaneLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FinerDetailPaneLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(birthday, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(3, 3, 3))
                            .addGroup(FinerDetailPaneLayout.createSequentialGroup()
                                .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(address)
                                    .addComponent(immediateSupervisor)
                                    .addComponent(empNum, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(FinerDetailPaneLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneNum, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(lastName)
                    .addComponent(position))
                .addContainerGap())
        );
        FinerDetailPaneLayout.setVerticalGroup(
            FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinerDetailPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(birthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(phoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(immediateSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(FinerDetailPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        CredentialsPane.setBackground(new java.awt.Color(54, 117, 136));
        CredentialsPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, java.awt.Color.gray, java.awt.Color.gray));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Calisto MT", 1, 14));
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Email:");

        emailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { emailTextFieldActionPerformed(evt); }
        });

        resetPassword.setBackground(new java.awt.Color(176, 196, 222));
        resetPassword.setText("Reset Password");
        resetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { resetPasswordActionPerformed(evt); }
        });

        javax.swing.GroupLayout CredentialsPaneLayout = new javax.swing.GroupLayout(CredentialsPane);
        CredentialsPane.setLayout(CredentialsPaneLayout);
        CredentialsPaneLayout.setHorizontalGroup(
            CredentialsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CredentialsPaneLayout.createSequentialGroup()
                .addGroup(CredentialsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CredentialsPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(CredentialsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(CredentialsPaneLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(resetPassword)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        CredentialsPaneLayout.setVerticalGroup(
            CredentialsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CredentialsPaneLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(resetPassword)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(FinerDetailPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(GovDetailPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(PayrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CredentialsPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 19, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PayrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(GovDetailPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(FinerDetailPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(CredentialsPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.setBackground(new java.awt.Color(54, 117, 136));

        jLabel24.setFont(new java.awt.Font("Segoe UI Black", 1, 36));
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Welcome, Admin!");

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setForeground(new java.awt.Color(176, 196, 222));
        jLabel25.setText("You can now manage, edit, and update employee records efficiently.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    // STUB HANDLERS (auto-generated, no logic needed)

    private void empNumActionPerformed(java.awt.event.ActionEvent evt) {
        // no-op
    }

    private void hourlyRateActionPerformed(java.awt.event.ActionEvent evt) {
        // no-op
    }

    private void riceSubActionPerformed(java.awt.event.ActionEvent evt) {
        // no-op
    }

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // no-op
    }

    // BUSINESS LOGIC HANDLERS

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            EmployeeService employeeService = new EmployeeService();
            String[][] employeeData = employeeService.loadEmployeeDataFromCSV(SystemIT.EMPLOYEE_CSV);

            DefaultTableModel model = (DefaultTableModel) updateTable.getModel();
            model.setRowCount(0);
            for (String[] rowData : employeeData) {
                model.addRow(rowData);
            }

            JOptionPane.showMessageDialog(this,
                "Employee details were refreshed successfully.",
                "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Failed to load employee data: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Shared validation used by both create and update operations.
     * @return null if validation passes, error message if it fails
     */
    private String validateEmployeeData() {
        Map<JTextField, ValidationRule> rules = new HashMap<>();

        rules.put(empNum,    ValidationRule.EMPLOYEE_ID);
        rules.put(lastName,  ValidationRule.NAME);
        rules.put(firstName, ValidationRule.NAME);
        rules.put(sss,       ValidationRule.SSS);
        rules.put(philHealth,ValidationRule.PHILHEALTH);
        rules.put(tin,       ValidationRule.TIN);
        rules.put(pagIBIG,   ValidationRule.PAGIBIG);
        rules.put(phoneNum,  ValidationRule.PHONE);
        rules.put(birthday,  ValidationRule.DATE_YYYYMMDD);
        rules.put(address,   ValidationRule.GENERIC_TEXT);
        rules.put(immediateSupervisor, ValidationRule.NAME);
        rules.put(position,  ValidationRule.REQUIRED);
        rules.put(status,    ValidationRule.REQUIRED);

        rules.put(basicSalary,       ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(riceSub,           ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(phoneAllowance,    ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(clothingAllowance, ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(monthlyRate,       ValidationRule.DECIMAL_NON_NEGATIVE);
        rules.put(hourlyRate,        ValidationRule.DECIMAL_NON_NEGATIVE);

        return InputValidator.validate(rules);
    }

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String error = validateEmployeeData();
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Admin admin = new Admin("", "");

        JTextField[] textFields = {
            empNum, lastName, firstName, sss, philHealth,
            tin, pagIBIG, birthday, address, phoneNum,
            status, position, immediateSupervisor, basicSalary, riceSub,
            phoneAllowance, clothingAllowance, monthlyRate, hourlyRate,
            emailTextField
        };

        admin.updateEmployeeDetails(updateTable, textFields, emailTextField, null);
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) updateTable.getModel();
        String searchTerm = search.getText().trim();

        if (searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a term to search",
                "Search Result", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean found = false;
        for (int i = 0; i < model.getRowCount(); i++) {
            String empNumber = model.getValueAt(i, 0).toString().trim();
            String last      = model.getValueAt(i, 1).toString().trim();
            String first     = model.getValueAt(i, 2).toString().trim();

            if (empNumber.equalsIgnoreCase(searchTerm) ||
                last.equalsIgnoreCase(searchTerm) ||
                first.equalsIgnoreCase(searchTerm)) {
                updateTable.getSelectionModel().setSelectionInterval(i, i);
                updateTable.scrollRectToVisible(updateTable.getCellRect(i, 0, true));
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this,
                "Employee not found!",
                "Search Result", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void backtoAdminActionPerformed(java.awt.event.ActionEvent evt) {
        AdminPortal frame1 = new AdminPortal();
        frame1.setVisible(true);
        dispose();
    }

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String error = validateEmployeeData();
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check for duplicate employee ID
        CTRL_SRVS.ValidationService validationService = new CTRL_SRVS.ValidationService();
        boolean isUnique = validationService.validateEmployeeIdUniqueness(empNum.getText().trim());
        if (!isUnique) {
            JOptionPane.showMessageDialog(this,
                "Employee ID already exists",
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String rawPassword    = passwordService.generatePassword(empNum.getText().trim());
        String hashedPassword = passwordService.hashPassword(rawPassword);

        String[] newRow = {
            empNum.getText().trim(),
            lastName.getText().trim(),
            firstName.getText().trim(),
            sss.getText().trim(),
            philHealth.getText().trim(),
            tin.getText().trim(),
            pagIBIG.getText().trim(),
            birthday.getText().trim(),
            address.getText().trim(),
            phoneNum.getText().trim(),
            status.getText().trim(),
            position.getText().trim(),
            immediateSupervisor.getText().trim(),
            basicSalary.getText().trim(),
            riceSub.getText().trim(),
            phoneAllowance.getText().trim(),
            clothingAllowance.getText().trim(),
            monthlyRate.getText().trim(),
            hourlyRate.getText().trim(),
            "",             // Email
            hashedPassword  // Hashed password
        };

        DefaultTableModel model = (DefaultTableModel) updateTable.getModel();
        model.addRow(newRow);
        saveDataToCSV(model, SystemIT.EMPLOYEE_CSV);

        JOptionPane.showMessageDialog(this,
            "Employee record added successfully!\n\n"
            + "Generated Password: " + rawPassword + "\n\n"
            + "IMPORTANT: This password will only be shown once.\n"
            + "Please inform the employee to use this password for their first login.",
            "Employee Created", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveDataToCSV(TableModel model, String csvFilePath) {
        try {
            EmployeeService employeeService = new EmployeeService();
            employeeService.saveEmployeeDataToCSV(model, csvFilePath);
            JOptionPane.showMessageDialog(this,
                "Employee data saved successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Failed to save data to CSV file: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = updateTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select a row to delete.",
                "Delete Row", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int response = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete the selected row?",
            "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) updateTable.getModel();
            model.removeRow(selectedRow);
            saveDataToCSV(model, SystemIT.EMPLOYEE_CSV);
            JOptionPane.showMessageDialog(this,
                "Row deleted successfully!",
                "Delete Row", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateTableMouseClicked(java.awt.event.MouseEvent evt) {
        Admin admin = new Admin("", "");

        JTextField[] textFields = {
            empNum, lastName, firstName, sss, philHealth,
            tin, pagIBIG, birthday, address, phoneNum,
            status, position, immediateSupervisor, basicSalary, riceSub,
            phoneAllowance, clothingAllowance, monthlyRate, hourlyRate,
            emailTextField
        };

        admin.populateFieldsFromTable(updateTable, textFields, emailTextField, null);
    }

    /**
     * FIX: Employee ID is now trimmed before being passed to PasswordService.
     * CSV-loaded table cells can contain leading/trailing whitespace, which caused
     * EmployeeDAO.findEmployeeById() to fail its exact-match lookup every time,
     * throwing "Employee not found" even for valid, existing employee IDs.
     */
    private void resetPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = updateTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select an employee row first.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) updateTable.getModel();

        // FIX: .trim() so whitespace from CSV load doesn't break the DAO lookup
        String employeeId   = model.getValueAt(selectedRow, 0).toString().trim();
        String employeeName = model.getValueAt(selectedRow, 2).toString().trim()
                            + " "
                            + model.getValueAt(selectedRow, 1).toString().trim();

        try {
            String newPassword = passwordService.resetEmployeePassword(employeeId);

            // Sync the new hashed password back into the table model
            String newHashed = passwordService.getEmployeePassword(employeeId);
            if (newHashed != null) {
                model.setValueAt(newHashed, selectedRow, 20);
            }

            JOptionPane.showMessageDialog(this,
                "Password reset successfully for " + employeeName + "!\n\n"
                + "New Password: " + newPassword + "\n\n"
                + "IMPORTANT: This password will only be shown once.\n"
                + "Please inform the employee to use this password for their next login.",
                "Password Reset", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error resetting password: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Legacy helper kept for backward compatibility.
     * PasswordService.generatePassword() should be preferred.
     */
    private String generatePassword(String employeeId) {
        int randomDigits = (int) (Math.random() * 9000) + 1000;
        return "EMP" + employeeId + randomDigits;
    }

    // ENTRY POINT

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHRPortal.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new AdminHRPortal().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel CredentialsPane;
    private javax.swing.JPanel FinerDetailPane;
    private javax.swing.JPanel GovDetailPane;
    private javax.swing.JPanel PayrollPane;
    private javax.swing.JTextField address;
    private javax.swing.JButton backtoAdmin;
    private javax.swing.JTextField basicSalary;
    private javax.swing.JTextField birthday;
    private javax.swing.JTextField clothingAllowance;
    private javax.swing.JButton createButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField empNum;
    private javax.swing.JTextField firstName;
    private javax.swing.JTextField hourlyRate;
    private javax.swing.JTextField immediateSupervisor;
    public  javax.swing.JLabel jLabel10;
    public  javax.swing.JLabel jLabel11;
    public  javax.swing.JLabel jLabel12;
    public  javax.swing.JLabel jLabel13;
    public  javax.swing.JLabel jLabel14;
    public  javax.swing.JLabel jLabel15;
    public  javax.swing.JLabel jLabel16;
    public  javax.swing.JLabel jLabel17;
    public  javax.swing.JLabel jLabel18;
    public  javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    public  javax.swing.JLabel jLabel20;
    public  javax.swing.JLabel jLabel21;
    public  javax.swing.JLabel jLabel22;
    public  javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    public  javax.swing.JLabel jLabel3;
    public  javax.swing.JLabel jLabel4;
    public  javax.swing.JLabel jLabel5;
    public  javax.swing.JLabel jLabel6;
    public  javax.swing.JLabel jLabel7;
    public  javax.swing.JLabel jLabel8;
    public  javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField lastName;
    private javax.swing.JTextField monthlyRate;
    private javax.swing.JTextField pagIBIG;
    private javax.swing.JTextField philHealth;
    private javax.swing.JTextField phoneAllowance;
    private javax.swing.JTextField phoneNum;
    private javax.swing.JTextField position;
    private javax.swing.JButton refresh;
    private javax.swing.JButton resetPassword;
    private javax.swing.JTextField riceSub;
    private javax.swing.JTextField search;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField sss;
    private javax.swing.JTextField status;
    private javax.swing.JTextField tin;
    private javax.swing.JButton updateButton;
    public  javax.swing.JTable updateTable;
    // End of variables declaration
}