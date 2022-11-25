package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.VolleyballWindowDelegate;
import ca.ubc.cs304.model.GameSet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ca.ubc.cs304.model.Country;

public class NestedAggregationPanel extends JPanel implements ActionListener {

    JLabel titleLabel = new JLabel("Show countries with over 60 million people ");
    JButton runButton = new JButton("Run");
    static int LABEL_X_POS = 50;
    static int LABEL_WIDTH = 100;
    static int LABEL_HEIGHT = 30;
    static int INITIAL_Y = 30;
    static int HEIGHT_TO_NEXT = 20;
    static int TITLE_WIDTH = 300;
    private final VolleyballWindowDelegate delegate;

    public NestedAggregationPanel(VolleyballWindowDelegate delegate) {
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
    }

    public void addComponentsToContainer() {
        add(titleLabel);
        add(runButton);
    }

    public void addActionEvent() {
        runButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == runButton) {
            DataTable frame = new DataTable(delegate.getAggregationNested(), new Country(), new String[]{"Country", "Population"});
            frame.setTitle("Names of Countries with over 60 millions people");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 360);
        }
    }
}
