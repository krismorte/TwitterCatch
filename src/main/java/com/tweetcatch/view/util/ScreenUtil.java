/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.view.util;

import java.awt.Image;

/**
 *
 * @author c007329
 */
public class ScreenUtil {
 
    private ClearField clearField;
    
    
    public void clearSwing(java.awt.Container container){
        clearField = new ClearFieldSwing();
        clearField.erase(container);
    }
    
    public Image getImage(String path) {
        return new javax.swing.ImageIcon(getClass().getResource(path)).getImage(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
