/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch;

import com.alee.laf.WebLookAndFeel;
import com.tweetcatch.service.ProxyService;
import com.tweetcatch.view.swing.MainScreen;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krisnamourtscf
 */
public class Main {

    private void startSwingGUI() {

        try {
            WebLookAndFeel.install();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        ProxyService.configure();
        MainScreen screen = new MainScreen();
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);
    }

    public static void main(String[] args) {
        Main mainClass = new Main();
        //mainClass.teste();
        mainClass.startSwingGUI();

    }

}
