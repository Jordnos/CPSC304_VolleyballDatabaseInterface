package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.VolleyballWindowDelegate;
import ca.ubc.cs304.model.Country;
import ca.ubc.cs304.model.Relation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InsertPanel extends JPanel implements ActionListener {

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
    String[] relationNonKeyNames;
    String[] relationKeyNames;
    ArrayList<JLabel> attributeLabel = new ArrayList<>();
    ArrayList<JTextField> attributeText = new ArrayList<>();
    JLabel titleLabel;
    JButton insertButton;
    private final VolleyballWindowDelegate delegate;

    public InsertPanel(VolleyballWindowDelegate delegate) {
        this.delegate = delegate;
        init();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void init(){
        relation = new Country("tmp", 0);
        relationKeyNames = relation.getKeyNames();
        relationNonKeyNames = relation.getNonKeyNames();
        titleLabel = new JLabel("Insert into " + relation.getRelationName());
        insertButton = new JButton("Insert");

        for(String n: relationKeyNames){
            attributeLabel.add(new JLabel(n));
            attributeText.add(new JTextField());
        }
        for(String n: relationNonKeyNames){
            attributeLabel.add(new JLabel(n));
            attributeText.add(new JTextField());
        }

    }

    public void setLayoutManager() {
        setLayout(null);
    }

    public void setLocationAndSize() {
        titleLabel.setBounds(LABEL_X_POS, INITIAL_Y, TITLE_WIDTH, LABEL_HEIGHT);
        for(int i = 0; i < attributeLabel.size(); i++){
            attributeLabel.get(i).setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(i+1), LABEL_WIDTH, LABEL_HEIGHT);
            attributeText.get(i).setBounds(TEXT_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(i+1), TEXT_WIDTH, TEXT_HEIGHT);
        }
        insertButton.setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(attributeLabel.size()+1), LABEL_WIDTH, LABEL_HEIGHT);
    }

    public void addComponentsToContainer() {
        for(int i = 0; i < attributeLabel.size(); i++){
            add(attributeLabel.get(i));
            add(attributeText.get(i));
        }
        add(titleLabel);
        add(insertButton);
    }

    public void addActionEvent() {
        insertButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insertButton) {
            Country c = new Country(attributeText.get(0).getText(), Integer.parseInt(attributeText.get(1).getText()));
            delegate.insertCountry(c);

            DataTable frame = new DataTable(delegate.getCountryTableData());
            frame.setTitle("Country");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 360);
        }
    }
}
