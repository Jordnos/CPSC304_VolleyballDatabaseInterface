package ca.ubc.cs304.ui;

import ca.ubc.cs304.model.Relation;

import javax.swing.*;
import java.awt.*;
import java.util.*;

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


    public DataTable(Object[][] data, Relation relation, Object[] attributes) {
        this.data = new Relation[1];
        this.data[0] = relation;
        this.dataArray = data;
        initProjectionDataTable(attributes);
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

    private void initProjectionDataTable(Object[] attributes) {
        if(data.length != 0){
            String[] stringArray = Arrays.copyOf(attributes, attributes.length, String[].class);

            table = new JTable(dataArray, stringArray);
            scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);

            container.add(scrollPane);
        }
        table.setDefaultEditor(Object.class, null);
    }
}