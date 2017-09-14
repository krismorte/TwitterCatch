/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.view.util;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author krismorte
 */
public class JPanelImage extends Canvas {

    private Image image;
    private int iHeight, iWidth;

    private JPanelImage(Image img, int width, int height) {
        image = img;
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(image, 0);
        try {
            tracker.waitForID(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        iWidth = (width == 0 ? image.getWidth(this) : width);
        iHeight = (height == 0 ? image.getHeight(this) : height);
        setBackground(SystemColor.control);
        setSize(iWidth, iHeight);
    }

    private static Image getImageFrom(String filePath) {
        return Toolkit.getDefaultToolkit().createImage(filePath);
    }

    public static void show(JPanel panel, String filePath) {
        Image i = getImageFrom(filePath);
        JPanelImage image = new JPanelImage(i, panel.getWidth(), panel.getHeight());
        panel.removeAll();
        panel.add(image);
        panel.validate();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, iWidth, iHeight, this);
    }

}
