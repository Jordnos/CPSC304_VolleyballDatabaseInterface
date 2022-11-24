package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.VolleyballWindowDelegate;
import ca.ubc.cs304.model.Ref;
import ca.ubc.cs304.model.Relation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AggregationGroupByPanel extends JPanel implements ActionListener {

    JLabel titleLabel = new JLabel("Find average Salary of refs in each league:");
    JButton runButton = new JButton("Find");
    JLabel aggLabel = new JLabel("Refs:");
    JTextField aggOutput = new JTextField("");
    static int LABEL_X_POS = 50;
    static int LABEL_WIDTH = 100;
    static int LABEL_HEIGHT = 30;
    static int INITIAL_Y = 30;
    static int HEIGHT_TO_NEXT = 20;
    static int TITLE_WIDTH = 300;

    Relation relation;
    String[] relationNonKeyNames;
    String[] relationKeyNames;
    //JTextField textField = new JTextField("This is a text");
    JTextField textField = new JTextField(20);

    ArrayList<JLabel> keyLabel = new ArrayList<>();
    ArrayList<JLabel> nonKeyLabel = new ArrayList<>();
    ArrayList<JTextField> keyText = new ArrayList<>();
    ArrayList<JTextField> nonKeyText = new ArrayList<>();
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

        aggLabel.setBounds(LABEL_X_POS, INITIAL_Y+200, TITLE_WIDTH, LABEL_HEIGHT);
        aggOutput.setBounds(LABEL_X_POS + 100, INITIAL_Y+200, TITLE_WIDTH+200, LABEL_HEIGHT+500);


    }

    public void addComponentsToContainer() {
        this.delegate = delegate;
        add(aggLabel);
        add(titleLabel);
        add(runButton);
        add(aggOutput);
    }

    public void addActionEvent() {
        runButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == runButton) {

            //Ref[] r2 = delegate.getRefTableData();
            int[][] out = delegate.getPopMax();

            String s = "";


            for (int i = 0; i<out.length; i++) {
                for (int j = 0; j < out[0].length; j++)

                    if (i == 0) {
                        s += " " + out[i][j];
                    } else {
                        s += " " + out[i][j] + "\n";
                    }
                System.out.println(out[i][0]);
                System.out.println(out[i][1]);
            }
            aggOutput.setText(s);







//            System.out.println(r2[0].getName());
//            // divisionOutput.setText(r2[0].getName());
//            String s = "";
//            for (int i=0; i < r2.length; i++) {
//                s += r2[0].getName() + " ";
//            }

           // aggOutput.setText(s);
        }
    }
}
