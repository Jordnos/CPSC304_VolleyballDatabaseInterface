package ca.ubc.cs304.ui;

import ca.ubc.cs304.model.Relation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DataTable extends JFrame {

    Container container = getContentPane();
    Relation[] data;
    String[] columnNames;
    Object[][] dataArray;
    JScrollPane scrollPane;
    JTable table;

    public DataTable(Relation[] data) {
        this.data = data;
        setupDataArr();
        init();
    }
    public DataTable(Object[][] data, Relation relation) {
        this.data = new Relation[1];
        this.data[0] = relation;
        this.dataArray = data;
        init();
    }

    private void setupDataArr() {
        ArrayList<ArrayList<Object>> a = new ArrayList<>();
        for(int i = 0; i < data.length; i++){
            a.add(new ArrayList<>());
            Object[] d = data[i].getData();
            for(Object o : d){
                a.get(i).add(o);
            }
        }

        dataArray = new Object[a.size()][a.get(0).size()];
        for(int i = 0; i < a.size(); i++){
            dataArray[i] = a.get(i).toArray();
        }
    }

    private void init() {
        if(data.length != 0){
            columnNames = data[0].getAllAttributeNames();

            table = new JTable(dataArray, columnNames);
            scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);

            container.add(scrollPane);
        }
        table.setDefaultEditor(Object.class, null);
    }
}