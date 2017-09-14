/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.view.util;

import java.awt.Image;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author c007329
 */
public class ScreenUtil {

    private ClearField clearField;

    public JScrollPane getJScroll(JTable table) {
        table.setFillsViewportHeight(true);
        JScrollPane scollPane = new JScrollPane(table);
        return scollPane;
    }

    public void clearSwing(java.awt.Container container) {
        clearField = new ClearFieldSwing();
        clearField.erase(container);
    }

    public Image getImage(String path) {
        return new javax.swing.ImageIcon(getClass().getResource(path)).getImage(); //To change body of generated methods, choose Tools | Templates.
    }

}
