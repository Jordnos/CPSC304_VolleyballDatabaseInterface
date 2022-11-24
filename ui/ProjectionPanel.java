package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.VolleyballWindowDelegate;
import ca.ubc.cs304.model.Bmi;
import ca.ubc.cs304.model.Relation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProjectionPanel extends JPanel implements ActionListener {

    static int LABEL_X_POS = 50;
    static int LABEL_WIDTH = 100;
    static int LABEL_HEIGHT = 30;
    static int TEXT_X_POS = LABEL_X_POS + LABEL_WIDTH;
    static int TEXT_WIDTH = 150;
    static int TEXT_HEIGHT = 30;
    static int INITIAL_Y = 30;
    static int HEIGHT_TO_NEXT = 20;
    static int TITLE_WIDTH = 300;

    Relation relation;
    JComboBox<String> relationsComboBox;
    ArrayList<JComboBox> attributeComboBox = new ArrayList<>();

    JLabel titleLabel;
    JButton projectButton;
    JButton addAttributeButton;
    int numAttributes;
    private final VolleyballWindowDelegate delegate;

    public ProjectionPanel(VolleyballWindowDelegate delegate) {
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
        projectButton = new JButton("Show");
        addAttributeButton = new JButton("Add attribute");

        relationsComboBox = new JComboBox<>(Relation.getAllRelationNames());
        numAttributes = 0;
    }

    public void setLayoutManager() {
        setLayout(null);
    }

    public void setLocationAndSize() {
        titleLabel.setBounds(LABEL_X_POS, INITIAL_Y, TITLE_WIDTH, LABEL_HEIGHT);
        relationsComboBox.setBounds(LABEL_X_POS+TITLE_WIDTH, INITIAL_Y, TITLE_WIDTH, LABEL_HEIGHT);
        projectButton.setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT), LABEL_WIDTH, LABEL_HEIGHT);
        addAttributeButton.setBounds(LABEL_X_POS+LABEL_WIDTH, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT), LABEL_WIDTH*2, LABEL_HEIGHT);
    }

    public void addComponentsToContainer() {
        add(titleLabel);
        add(relationsComboBox);
        add(projectButton);
        add(addAttributeButton);
    }

    public void addActionEvent() {
        projectButton.addActionListener(this);
        addAttributeButton.addActionListener(this);
        relationsComboBox.addActionListener(this);
    }

    public void addNewAttributeRow() {
        attributeComboBox.add(new JComboBox<>(relation.getAllAttributeNames()));

        attributeComboBox.get(numAttributes).setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(numAttributes +1), LABEL_WIDTH, LABEL_HEIGHT);

        add(attributeComboBox.get(numAttributes));

        numAttributes++;

        projectButton.setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(numAttributes +1), LABEL_WIDTH, LABEL_HEIGHT);
        addAttributeButton.setBounds(LABEL_X_POS+LABEL_WIDTH, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(numAttributes +1), LABEL_WIDTH*2, LABEL_HEIGHT);

        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == projectButton) {
            ArrayList<String> attributes = new ArrayList<>();

            if (numAttributes == 0) {
                DataTable frame = new DataTable(delegate.getCountryTableData()); //only projects on country
                frame.setTitle("Country");
                frame.setVisible(true);
                frame.setBounds(10, 10, 370, 360);
            } else {
                for(int i = 0; i < numAttributes; i++){
                    if(!attributeComboBox.isEmpty()){
                        attributes.add(attributeComboBox.get(i).getSelectedItem().toString());
                    }
                }

                String[] c = new String[attributes.size()];

                DataTable frame = new DataTable(delegate.getProjectionInfo(relation, attributes.toArray(c)), relation, attributes.toArray());
                frame.setTitle(relation.getRelationName());
                frame.setVisible(true);
                frame.setBounds(10, 10, 370, 360);
            }
        }
        if (e.getSource() == addAttributeButton) {
            addNewAttributeRow();
        }
        if (e.getSource() == relationsComboBox) {
            relation = Relation.getAllRelations()[relationsComboBox.getSelectedIndex()];
            for(int i = 0; i < numAttributes; i++){
                remove(attributeComboBox.get(i));

            }
            numAttributes = 0;
            attributeComboBox.clear();


            projectButton.setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT), LABEL_WIDTH, LABEL_HEIGHT);
            addAttributeButton.setBounds(LABEL_X_POS+LABEL_WIDTH, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT), LABEL_WIDTH*2, LABEL_HEIGHT);

            revalidate();
            repaint();
        }
    }
}
