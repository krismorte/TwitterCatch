/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.view.swing;

import com.tweetcatch.dto.ReTweetQuery;
import com.tweetcatch.model.ReTweet;
import com.tweetcatch.model.TwitterAccount;
import com.tweetcatch.model.TwitterUser;
import com.tweetcatch.service.ReTweetService;
import com.tweetcatch.view.util.ScreenUtil;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import twitter4j.TwitterException;
import twitter4j.api.HelpResources;

/**
 *
 * @author krisnamourtscf
 */
public class RetweetScreen extends javax.swing.JDialog {

    //private TweetService tweetService = new TweetService();
    private ReTweetService reTweetService = new ReTweetService();
    private List<TwitterAccount> twitterAccounts;
    private List<TwitterUser> twitterUsers;
    private List<HelpResources.Language> languages;
    private List listaTweets;
    private ButtonGroup grp = new ButtonGroup();
    private ScreenUtil screenUtil = new ScreenUtil();
    private MyJTable tabela;

    /**
     * Creates new form RetweetScreen
     */
    public RetweetScreen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initParam();
        initRadio();
        initCheck();
        initBox();
    }

    private void initParam() {
        boxProfile.setEnabled(false);
        boxLanguages.setEnabled(false);
        twitterAccounts = reTweetService.getAccounts();
        if (twitterAccounts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You can't retweet, yet. You need registry a valid Twitter account.");
            btnRetweet.setEnabled(false);
        } else {
            reTweetService.setAccount(twitterAccounts.get(0));
            twitterUsers = reTweetService.getUsers();
            try {
                languages = reTweetService.getLanguages();
            } catch (TwitterException tEx) {
                JOptionPane.showMessageDialog(null, tEx.getErrorMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void initRadio() {
        radPublic.setSelected(true);
        grp.add(radMyTweets);
        grp.add(radSpecific);
        grp.add(radPublic);

        radPublic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boxProfile.setEnabled(false);
            }
        });

        radMyTweets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boxProfile.setEnabled(false);
            }
        });

        radSpecific.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radSpecific.isSelected()) {
                    boxProfile.setEnabled(true);
                    boxProfile.grabFocus();
                } else {
                    boxProfile.setEnabled(false);
                }
            }
        });
    }

    private void initCheck() {
        chkLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chkLanguage.isSelected()) {
                    boxLanguages.setEnabled(true);
                } else {
                    boxLanguages.setEnabled(false);
                }
            }
        });
    }

    private void initBox() {
        twitterAccounts.forEach(twitterAccount -> boxAccounts.addItem(twitterAccount.getProfileName()));

        initBoxUsers();

        languages.forEach(language -> boxLanguages.addItem(language.getCode()));
        boxAccounts.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    reTweetService.setAccount(getChooseTwitterAccount());
                    twitterUsers = reTweetService.getUsers();
                    initBoxUsers();
                }
            }
        });
    }

    private void initBoxUsers() {
        boxProfile.removeAllItems();
        boxProfile.addItem("");
        twitterUsers.forEach(twitterUser -> boxProfile.addItem(twitterUser.getProfileName()));
    }

    private TwitterAccount getChooseTwitterAccount() {
        TwitterAccount account = null;
        for (TwitterAccount a : twitterAccounts) {
            if (a.getProfileName().equals(boxAccounts.getSelectedItem().toString())) {
                account = a;
                break;
            }
        }
        return account;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        boxAccounts = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        chkStream = new javax.swing.JCheckBox();
        boxLanguages = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQuery = new javax.swing.JTextArea();
        chkDownload = new javax.swing.JCheckBox();
        chkLanguage = new javax.swing.JCheckBox();
        panelTweets = new javax.swing.JPanel();
        btnRetweet = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        radPublic = new javax.swing.JRadioButton();
        radMyTweets = new javax.swing.JRadioButton();
        radSpecific = new javax.swing.JRadioButton();
        boxProfile = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search and ReTweet");

        jLabel1.setText("Twitter Account:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Query Conditions"));

        chkStream.setText("With Stream");

        jLabel3.setText("Query:");

        txtQuery.setColumns(20);
        txtQuery.setRows(3);
        jScrollPane1.setViewportView(txtQuery);

        chkDownload.setText("download streams");

        chkLanguage.setText("With Language:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkStream)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkLanguage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxLanguages, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkDownload))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkStream)
                    .addComponent(boxLanguages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkDownload)
                    .addComponent(chkLanguage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panelTweets.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelTweetsLayout = new javax.swing.GroupLayout(panelTweets);
        panelTweets.setLayout(panelTweetsLayout);
        panelTweetsLayout.setHorizontalGroup(
            panelTweetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelTweetsLayout.setVerticalGroup(
            panelTweetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 181, Short.MAX_VALUE)
        );

        btnRetweet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/if_Twitter_UI-14_2310220.png"))); // NOI18N
        btnRetweet.setToolTipText("retweet");
        btnRetweet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetweetActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Query Targets"));

        radPublic.setText("Public Queue");

        radMyTweets.setText("My Tweets");

        radSpecific.setText("Specific Profile");

        boxProfile.setEditable(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radPublic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radMyTweets)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radSpecific)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(boxProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radMyTweets)
                    .addComponent(radSpecific)
                    .addComponent(radPublic))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/load.png"))); // NOI18N
        btnSearch.setText("LOAD");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel2.setText("total:");

        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boxAccounts, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRetweet))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSearch))
                            .addComponent(panelTweets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(boxAccounts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRetweet))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSearch)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTweets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        //TwitterAccount account = getChooseTwitterAccount();
        try {

            if (radPublic.isSelected() & txtQuery.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Type your query!");
                txtQuery.grabFocus();
                return;
            }

            ReTweetQuery reTweetQuery = new ReTweetQuery(radPublic.isSelected(), radMyTweets.isSelected(),
                    radSpecific.isSelected(), chkStream.isSelected(), chkLanguage.isSelected(), chkDownload.isSelected(),
                    getBoxSpecific(), boxLanguages.getSelectedItem().toString(), txtQuery.getText());

            listaTweets = reTweetService.searhTweets(reTweetQuery);

            showResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRetweetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetweetActionPerformed
        List<ReTweet> chooseRetweets = new ArrayList<>();
        List<ReTweet> reTweets = tabela.getRows();
        for (ReTweet reTweet : reTweets) {
            if (reTweet.getMakeRetweet()) {
                chooseRetweets.add(reTweet);
            }
        }
        if (chooseRetweets.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Choose tweets to retweet then.");
        } else {
            try {
                reTweetService.makeReTweets(chooseRetweets);
                JOptionPane.showMessageDialog(null, chooseRetweets.size() + " retweets done with Success!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnRetweetActionPerformed

    private String getBoxSpecific() {
        return boxProfile.getSelectedItem() == null ? "" : boxProfile.getSelectedItem().toString();
    }

    private void showResult() {
        if (listaTweets != null) {
            panelTweets.removeAll();
            panelTweets.setLayout(new GridLayout(1, 1));
            tabela = reTweetService.getJTable(listaTweets);
            panelTweets.add(screenUtil.getJScroll(tabela), BorderLayout.CENTER);
            panelTweets.validate();
            txtTotal.setText("" + listaTweets.size());
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
            java.util.logging.Logger.getLogger(RetweetScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RetweetScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RetweetScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RetweetScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RetweetScreen dialog = new RetweetScreen(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxAccounts;
    private javax.swing.JComboBox<String> boxLanguages;
    private javax.swing.JComboBox<String> boxProfile;
    private javax.swing.JButton btnRetweet;
    private javax.swing.JButton btnSearch;
    private javax.swing.JCheckBox chkDownload;
    private javax.swing.JCheckBox chkLanguage;
    private javax.swing.JCheckBox chkStream;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelTweets;
    private javax.swing.JRadioButton radMyTweets;
    private javax.swing.JRadioButton radPublic;
    private javax.swing.JRadioButton radSpecific;
    private javax.swing.JTextArea txtQuery;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
