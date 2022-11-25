package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.VolleyballWindowDelegate;
import ca.ubc.cs304.model.GameSet;
import ca.ubc.cs304.model.Ref;
import ca.ubc.cs304.model.Relation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AggregationGroupByPanel extends JPanel implements ActionListener {

    JLabel titleLabel = new JLabel("Find average Salary of refs in each league:");
    JButton runButton = new JButton("Find");

    static int LABEL_X_POS = 50;
    static int LABEL_WIDTH = 100;
    static int LABEL_HEIGHT = 30;
    static int INITIAL_Y = 30;
    static int HEIGHT_TO_NEXT = 20;
    static int TITLE_WIDTH = 300;

    private VolleyballWindowDelegate delegate;

    public AggregationGroupByPanel(VolleyballWindowDelegate delegate) {
        this.delegate = delegate;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
    public void setLayoutManager() {
        setLayout(null);
    }

    public void setLocationAndSize() {
        titleLabel.setBounds(LABEL_X_POS, INITIAL_Y, TITLE_WIDTH, LABEL_HEIGHT);
        runButton.setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT), LABEL_WIDTH, LABEL_HEIGHT);

        // aggLabel.setBounds(LABEL_X_POS, INITIAL_Y+200, TITLE_WIDTH, LABEL_HEIGHT);
        //aggOutput.setBounds(LABEL_X_POS + 100, INITIAL_Y+200, TITLE_WIDTH+200, LABEL_HEIGHT+500);


    }

    public void addComponentsToContainer() {
        // this.delegate = delegate;
        // add(aggLabel);
        add(titleLabel);
        add(runButton);
        //add(aggOutput);
    }

    public void addActionEvent() {
        runButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == runButton) {
            DataTable frame = new DataTable(delegate.getAggregationGroup(), new Ref(), new String[]{"Leauge ID", "Average Salary"});
            frame.setTitle("Average Salary of refs in each league:");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 360);
        }

    }
}
