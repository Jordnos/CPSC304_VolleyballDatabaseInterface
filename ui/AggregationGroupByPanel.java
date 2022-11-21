package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AggregationGroupByPanel extends JPanel implements ActionListener {

    JLabel titleLabel = new JLabel("Do aggregation group by");
    JButton runButton = new JButton("Run");
    static int LABEL_X_POS = 50;
    static int LABEL_WIDTH = 100;
    static int LABEL_HEIGHT = 30;
    static int INITIAL_Y = 30;
    static int HEIGHT_TO_NEXT = 20;
    static int TITLE_WIDTH = 300;

    public AggregationGroupByPanel() {
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
            // TODO:
        }
    }
}
