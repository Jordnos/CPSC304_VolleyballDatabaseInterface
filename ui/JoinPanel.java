package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.VolleyballWindowDelegate;
import ca.ubc.cs304.model.Bmi;
import ca.ubc.cs304.model.Relation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JoinPanel extends JPanel implements ActionListener {

    static int LABEL_X_POS = 50;
    static int LABEL_WIDTH = 100;
    static int LABEL_HEIGHT = 30;
    static int TEXT_X_POS = LABEL_X_POS + LABEL_WIDTH;
    static int TEXT_WIDTH = 150;
    static int TEXT_HEIGHT = 30;
    static int INITIAL_Y = 30;
    static int HEIGHT_TO_NEXT = 20;
    static int TITLE_WIDTH = 300;
    static String[] comparators = {"=", "<>", ">", ">=", "<", "<="};
    static String[] comparatorsEnglish = {"EQUALS", "NOT EQUALS", "GREATER THAN", "GREATER OR EQUAL",
            "LESS THAN", "LESS THAN OR EQUAL"};
    Relation firstRelation;
    Relation secondRelation;
    JComboBox<String> firstRelationsComboBox;

    JComboBox<String> secondRelationsComboBox;
    ArrayList<JComboBox> firstAttributeComboBox = new ArrayList<>();
    ArrayList<JComboBox> secondAttributeComboBox = new ArrayList<>();
    ArrayList<JComboBox> comparatorComboBox = new ArrayList<>();
    JLabel titleLabel;
    JButton joinButton;
    JButton addConditionButton;
    int numConditions;
    private final VolleyballWindowDelegate delegate;

    public JoinPanel(VolleyballWindowDelegate delegate) {
        this.delegate = delegate;
        init();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void init(){
        firstRelation = new Bmi();
        secondRelation = new Bmi();

        titleLabel = new JLabel("Show table");
        joinButton = new JButton("Show");
        addConditionButton = new JButton("Add Condition");
        firstRelationsComboBox = new JComboBox<>(Relation.getAllRelationNames());
        secondRelationsComboBox = new JComboBox<>(Relation.getAllRelationNames());
        numConditions = 0;
    }

    public void setLayoutManager() {
        setLayout(null);
    }

    public void setLocationAndSize() {
        titleLabel.setBounds(LABEL_X_POS, INITIAL_Y, TITLE_WIDTH, LABEL_HEIGHT);
        firstRelationsComboBox.setBounds(LABEL_X_POS+TITLE_WIDTH, INITIAL_Y, TITLE_WIDTH, LABEL_HEIGHT);
        secondRelationsComboBox.setBounds(LABEL_X_POS+TITLE_WIDTH, INITIAL_Y + LABEL_HEIGHT, TITLE_WIDTH, LABEL_HEIGHT);
        joinButton.setBounds(LABEL_X_POS, INITIAL_Y + (2 * HEIGHT_TO_NEXT + LABEL_HEIGHT), LABEL_WIDTH, LABEL_HEIGHT);
        addConditionButton.setBounds(LABEL_X_POS+LABEL_WIDTH, INITIAL_Y + (2 * HEIGHT_TO_NEXT + LABEL_HEIGHT), LABEL_WIDTH*2, LABEL_HEIGHT);
    }

    public void addComponentsToContainer() {
        add(titleLabel);
        add(firstRelationsComboBox);
        add(secondRelationsComboBox);
        add(joinButton);
        add(addConditionButton);
    }

    public void addActionEvent() {
        joinButton.addActionListener(this);
        addConditionButton.addActionListener(this);
        firstRelationsComboBox.addActionListener(this);
        secondRelationsComboBox.addActionListener(this);
    }

    public void addNewConditionRow() {
        firstAttributeComboBox.add(new JComboBox<>(firstRelation.getAllAttributeNames()));
        comparatorComboBox.add(new JComboBox(comparatorsEnglish));
        secondAttributeComboBox.add(new JComboBox<>(secondRelation.getAllAttributeNames()));

        firstAttributeComboBox.get(numConditions).setBounds(LABEL_X_POS, INITIAL_Y + (2*HEIGHT_TO_NEXT + LABEL_HEIGHT)*(numConditions+1), LABEL_WIDTH, LABEL_HEIGHT);
        comparatorComboBox.get(numConditions).setBounds(LABEL_X_POS+LABEL_WIDTH, INITIAL_Y + (2*HEIGHT_TO_NEXT + LABEL_HEIGHT)*(numConditions+1), LABEL_WIDTH*2, LABEL_HEIGHT);
        secondAttributeComboBox.get(numConditions).setBounds(LABEL_X_POS+3*LABEL_WIDTH, INITIAL_Y + (2*HEIGHT_TO_NEXT + LABEL_HEIGHT)*(numConditions+1), LABEL_WIDTH, LABEL_HEIGHT);

        add(firstAttributeComboBox.get(numConditions));
        add(comparatorComboBox.get(numConditions));
        add(secondAttributeComboBox.get(numConditions));

        numConditions++;

        joinButton.setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(numConditions+1), LABEL_WIDTH, LABEL_HEIGHT);
        addConditionButton.setBounds(LABEL_X_POS+LABEL_WIDTH, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(numConditions+1), LABEL_WIDTH*2, LABEL_HEIGHT);

        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == joinButton) {
            ArrayList<String> conditions = new ArrayList<>();

            for(int i = 0; i < numConditions; i++){
                conditions.add("r1." + firstAttributeComboBox.get(i).getSelectedItem().toString() + " " +
                        comparators[comparatorComboBox.get(i).getSelectedIndex()] +
                        " r2." + secondAttributeComboBox.get(i).getSelectedItem().toString());
            }

            String[] c = new String[conditions.size()];

            ArrayList<String> attributes = new ArrayList<>();
            for(int i = 0; i < firstRelation.getAllAttributeNames().length; i++){
                attributes.add(firstRelation.getAllAttributeNames()[i]);
            }
            for(int i = 0; i < secondRelation.getAllAttributeNames().length; i++){
                attributes.add(secondRelation.getAllAttributeNames()[i]);
            }

            DataTable frame = new DataTable(delegate.getJoinInfo(firstRelation, secondRelation, conditions.toArray(c)), firstRelation, attributes.toArray());
            frame.setTitle(firstRelation.getRelationName());
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 360);
        }
        if (e.getSource() == addConditionButton) {
            addNewConditionRow();
        }
        if (e.getSource() == firstRelationsComboBox) {
            firstRelation = Relation.getAllRelations()[firstRelationsComboBox.getSelectedIndex()];
        }
        if (e.getSource() == secondRelationsComboBox) {
            secondRelation = Relation.getAllRelations()[secondRelationsComboBox.getSelectedIndex()];
            for(int i = 0; i < numConditions; i++){
                remove(firstAttributeComboBox.get(i));
                remove(comparatorComboBox.get(i));
                remove(secondAttributeComboBox.get(i));
            }
            numConditions = 0;
            firstAttributeComboBox.clear();
            secondAttributeComboBox.clear();
            comparatorComboBox.clear();

            joinButton.setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT), LABEL_WIDTH, LABEL_HEIGHT);
            addConditionButton.setBounds(LABEL_X_POS+LABEL_WIDTH, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT), LABEL_WIDTH*2, LABEL_HEIGHT);

            revalidate();
            repaint();
        }
    }
}
