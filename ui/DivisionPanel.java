package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.VolleyballWindowDelegate;
import ca.ubc.cs304.model.GameSet;
import ca.ubc.cs304.model.Ref;
import ca.ubc.cs304.model.Relation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DivisionPanel extends JPanel implements ActionListener {

    JLabel titleLabel = new JLabel("Find Referees that Ref in all Leagues:");
    JButton runButton = new JButton("Find");

    //    JLabel divisionLabel = new JLabel("Refs:");
//    JTextField divisionOutput = new JTextField("");
    static int LABEL_X_POS = 50;
    static int LABEL_WIDTH = 100;
    static int LABEL_HEIGHT = 30;
    static int INITIAL_Y = 30;
    static int HEIGHT_TO_NEXT = 20;
    static int TITLE_WIDTH = 300;


    private VolleyballWindowDelegate delegate;

    public DivisionPanel(VolleyballWindowDelegate delegate) {
        this.delegate = delegate;
        //  init();
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

//        divisionLabel.setBounds(LABEL_X_POS, INITIAL_Y+200, TITLE_WIDTH, LABEL_HEIGHT);
//        divisionOutput.setBounds(LABEL_X_POS + 100, INITIAL_Y+200, TITLE_WIDTH, LABEL_HEIGHT+50);
//


    }

    public void addComponentsToContainer() {

        // add(divisionLabel);
        add(titleLabel);
        add(runButton);
        // add(divisionOutput);
    }



    public void addActionEvent() {
        runButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == runButton) {



            DataTable frame = new DataTable(delegate.getDivisionRef(), new Ref(), new String[]{"RID", "Name"});
            frame.setTitle("RIDS ");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 360);
            // divisionOutput.setText(r2[0].getName());


        }
    }
}
