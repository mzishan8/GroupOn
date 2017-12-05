package ChatClient;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ZISHAN
 */
public class Login extends javax.swing.JFrame {
    Client cl=null;
    /**
     * Creates new form Login
     */
    public Login() {
        
        cl=new Client();
        System.out.println("connection is creating");
        if(!cl.connection("localhost",2001)) {
            System.out.println("connection not created");
        } else {
            JOptionPane.showMessageDialog(null, "Conection Created");
        }
        initComponents();
        registerPanel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registerPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rFirstName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        rPassword = new javax.swing.JPasswordField();
        submit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        rePassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        mobile = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rUserName = new javax.swing.JTextField();
        rLastName = new javax.swing.JTextField();
        loginPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        login = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        registerPanel.setBackground(new java.awt.Color(0, 0, 0));
        registerPanel.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Register");
        registerPanel.add(jLabel5);
        jLabel5.setBounds(30, 0, 131, 37);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Username");
        registerPanel.add(jLabel7);
        jLabel7.setBounds(80, 190, 150, 30);
        registerPanel.add(rFirstName);
        rFirstName.setBounds(260, 40, 170, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Password");
        registerPanel.add(jLabel8);
        jLabel8.setBounds(80, 230, 110, 40);
        registerPanel.add(rPassword);
        rPassword.setBounds(260, 240, 170, 30);

        submit.setBackground(new java.awt.Color(102, 102, 102));
        submit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        submit.setForeground(new java.awt.Color(102, 102, 102));
        submit.setText("Submit");
        submit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        registerPanel.add(submit);
        submit.setBounds(90, 410, 100, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Confirm Password");
        registerPanel.add(jLabel1);
        jLabel1.setBounds(80, 290, 180, 22);
        registerPanel.add(rePassword);
        rePassword.setBounds(260, 290, 170, 30);

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(102, 102, 102));
        jButton1.setText("Back");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        registerPanel.add(jButton1);
        jButton1.setBounds(290, 410, 100, 40);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Mobile Number");
        registerPanel.add(jLabel9);
        jLabel9.setBounds(80, 340, 160, 22);
        registerPanel.add(mobile);
        mobile.setBounds(260, 340, 170, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Email Id");
        registerPanel.add(jLabel10);
        jLabel10.setBounds(80, 140, 140, 30);
        registerPanel.add(email);
        email.setBounds(260, 140, 170, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("First Name");
        registerPanel.add(jLabel11);
        jLabel11.setBounds(80, 40, 150, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Last Name");
        registerPanel.add(jLabel12);
        jLabel12.setBounds(80, 90, 150, 30);
        registerPanel.add(rUserName);
        rUserName.setBounds(260, 190, 170, 30);
        registerPanel.add(rLastName);
        rLastName.setBounds(260, 90, 170, 30);

        loginPanel.setBackground(new java.awt.Color(0, 0, 0));
        loginPanel.setMinimumSize(new java.awt.Dimension(500, 500));
        loginPanel.setPreferredSize(new java.awt.Dimension(200, 250));
        loginPanel.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(0, 51, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Login");
        loginPanel.add(jLabel2);
        jLabel2.setBounds(110, 50, 110, 60);

        userName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userName.setForeground(new java.awt.Color(102, 102, 102));
        loginPanel.add(userName);
        userName.setBounds(200, 150, 180, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("User Name");
        loginPanel.add(jLabel3);
        jLabel3.setBounds(30, 150, 120, 30);

        login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ChatClient/login2.jpg"))); // NOI18N
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMouseClicked(evt);
            }
        });
        loginPanel.add(login);
        login.setBounds(20, 360, 170, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Password");
        loginPanel.add(jLabel4);
        jLabel4.setBounds(30, 240, 120, 30);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ChatClient/register_now_button (1).jpg"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        loginPanel.add(jLabel6);
        jLabel6.setBounds(260, 350, 210, 50);
        loginPanel.add(password);
        password.setBounds(200, 230, 180, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseClicked
        // TODO add your handling code here:
        System.out.println("Login bitton pressed");
        cl.login(userName.getText(),new String(password.getPassword()));
    }//GEN-LAST:event_loginMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        loginPanel.setVisible(false);
        registerPanel.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
        String p1 = new String(rPassword.getPassword());
        String p2 = new String(rePassword.getPassword());
        String res=null;
        if(!p1.equals(p2)){
            JOptionPane.showMessageDialog(null, "Password Not Matched");
        }
       else if(p1.length()<8){
            JOptionPane.showMessageDialog(null, "Password Length should be minimum 8 characters");
        }
       else{
            try {
                cl.write("NEWUSER: "+rFirstName.getText()+","+rLastName.getText()+","+email.getText()+","+rUserName.getText()+","+p1+","+mobile.getText());
                res = cl.read();
                if(res.equals("USEREXIST"))
                     JOptionPane.showMessageDialog(null, "User Exist");
                else{
                     JOptionPane.showMessageDialog(null, "User Created");
                     registerPanel.setVisible(false);
                     loginPanel.setVisible(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            //dispose();
       }
    }//GEN-LAST:event_submitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        registerPanel.setVisible(false);
        loginPanel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField email;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel login;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JTextField mobile;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField rFirstName;
    private javax.swing.JTextField rLastName;
    private javax.swing.JPasswordField rPassword;
    private javax.swing.JTextField rUserName;
    private javax.swing.JPasswordField rePassword;
    private javax.swing.JPanel registerPanel;
    private javax.swing.JButton submit;
    private javax.swing.JTextField userName;
    // End of variables declaration//GEN-END:variables
}
