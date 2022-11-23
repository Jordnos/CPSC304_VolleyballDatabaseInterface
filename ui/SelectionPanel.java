package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.VolleyballWindowDelegate;
import ca.ubc.cs304.model.Bmi;
import ca.ubc.cs304.model.Relation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectionPanel extends JPanel implements ActionListener {

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
    Relation relation;
    JComboBox<String> relationsComboBox;
    ArrayList<JComboBox> attributeComboBox = new ArrayList<>();
    ArrayList<JComboBox> comparatorComboBox = new ArrayList<>();
    ArrayList<JTextField> attributeText = new ArrayList<>();
    JLabel titleLabel;
    JButton selectButton;
    JButton addConditionButton;
    int numConditions;
    private final VolleyballWindowDelegate delegate;

    public SelectionPanel(VolleyballWindowDelegate delegate) {
        this.delegate = delegate;
        init();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void init(){
        relation = new Bmi();

        titleLabel = new JLabel("Show table");
        selectButton = new JButton("Show");
        addConditionButton = new JButton("Add Condition");

        relationsComboBox = new JComboBox<>(Relation.getAllRelationNames());
        numConditions = 0;
    }

    public void setLayoutManager() {
        setLayout(null);
    }

    public void setLocationAndSize() {
        titleLabel.setBounds(LABEL_X_POS, INITIAL_Y, TITLE_WIDTH, LABEL_HEIGHT);
        relationsComboBox.setBounds(LABEL_X_POS+TITLE_WIDTH, INITIAL_Y, TITLE_WIDTH, LABEL_HEIGHT);
        selectButton.setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT), LABEL_WIDTH, LABEL_HEIGHT);
        addConditionButton.setBounds(LABEL_X_POS+LABEL_WIDTH, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT), LABEL_WIDTH*2, LABEL_HEIGHT);
    }

    public void addComponentsToContainer() {
        add(titleLabel);
        add(relationsComboBox);
        add(selectButton);
        add(addConditionButton);
    }

    public void addActionEvent() {
        selectButton.addActionListener(this);
        addConditionButton.addActionListener(this);
        relationsComboBox.addActionListener(this);
    }

    public void addNewConditionRow() {
        attributeComboBox.add(new JComboBox<>(relation.getAllAttributeNames()));
        comparatorComboBox.add(new JComboBox(comparatorsEnglish));
        attributeText.add(new JTextField());

        attributeComboBox.get(numConditions).setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(numConditions+1), LABEL_WIDTH, LABEL_HEIGHT);
        comparatorComboBox.get(numConditions).setBounds(LABEL_X_POS+LABEL_WIDTH, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(numConditions+1), LABEL_WIDTH*2, LABEL_HEIGHT);
        attributeText.get(numConditions).setBounds(TEXT_X_POS+LABEL_WIDTH*2, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(numConditions+1), TEXT_WIDTH, TEXT_HEIGHT);

        add(attributeComboBox.get(numConditions));
        add(comparatorComboBox.get(numConditions));
        add(attributeText.get(numConditions));

        numConditions++;

        selectButton.setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(numConditions+1), LABEL_WIDTH, LABEL_HEIGHT);
        addConditionButton.setBounds(LABEL_X_POS+LABEL_WIDTH, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(numConditions+1), LABEL_WIDTH*2, LABEL_HEIGHT);

        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectButton) {
            ArrayList<String> conditions = new ArrayList<>();

            for(int i = 0; i < numConditions; i++){
                if(!attributeText.get(i).getText().isEmpty()){
                    conditions.add(attributeComboBox.get(i).getSelectedItem().toString() + " " +
                            comparators[comparatorComboBox.get(i).getSelectedIndex()] + " '" +
                            attributeText.get(i).getText() + "'");
                }
            }

            String[] c = new String[conditions.size()];

            DataTable frame = new DataTable(delegate.getTableData(relation, conditions.toArray(c)), relation);
            frame.setTitle(relation.getRelationName());
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 360);
        }
        if (e.getSource() == addConditionButton) {
            addNewConditionRow();
        }
        if (e.getSource() == relationsComboBox) {
            relation = Relation.getAllRelations()[relationsComboBox.getSelectedIndex()];
            for(int i = 0; i < numConditions; i++){
                remove(attributeComboBox.get(i));
                remove(comparatorComboBox.get(i));
                remove(attributeText.get(i));
            }
            numConditions = 0;
            attributeComboBox.clear();
            comparatorComboBox.clear();
            attributeText.clear();

            selectButton.setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT), LABEL_WIDTH, LABEL_HEIGHT);
            addConditionButton.setBounds(LABEL_X_POS+LABEL_WIDTH, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT), LABEL_WIDTH*2, LABEL_HEIGHT);

            revalidate();
            repaint();
        }
    }
}
