/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.view.swing;

import com.tweetcatch.view.util.BeanTableModel;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author krismorte
 */
public class MyJTable extends JTable {

    private BeanTableModel model;

    public MyJTable(BeanTableModel model) {
        this.model = model;
        setModel(model);
    }

    public List getRows() {
        return model.getRows();
    }

    public void addRow(Object o) {
        model.addRow(o);
    }

}
