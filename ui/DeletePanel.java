package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.VolleyballWindowDelegate;
import ca.ubc.cs304.model.Country;
import ca.ubc.cs304.model.Relation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeletePanel extends JPanel implements ActionListener {

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
    String[] relationKeyNames;
    ArrayList<JLabel> keysLabel = new ArrayList<>();
    ArrayList<JTextField> keysText = new ArrayList<>();
    JLabel titleLabel;
    JButton deleteButton;
    private VolleyballWindowDelegate delegate;

    public DeletePanel(VolleyballWindowDelegate delegate) {
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
        titleLabel = new JLabel("Delete From " + relation.getRelationName());
        deleteButton = new JButton("Delete");

        for(String n: relationKeyNames){
            keysLabel.add(new JLabel("Where " + n + " ="));
            keysText.add(new JTextField());
        }

    }

    public void setLayoutManager() {
        setLayout(null);
    }

    public void setLocationAndSize() {
        titleLabel.setBounds(LABEL_X_POS, INITIAL_Y, TITLE_WIDTH, LABEL_HEIGHT);
        for(int i = 0; i < keysLabel.size(); i++){
            keysLabel.get(i).setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(i+1), LABEL_WIDTH, LABEL_HEIGHT);
            keysText.get(i).setBounds(TEXT_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(i+1), TEXT_WIDTH, TEXT_HEIGHT);
        }
        deleteButton.setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(keysLabel.size()+1), LABEL_WIDTH, LABEL_HEIGHT);
    }

    public void addComponentsToContainer() {
        for(int i = 0; i < keysLabel.size(); i++){
            add(keysLabel.get(i));
            add(keysText.get(i));
        }
        add(titleLabel);
        add(deleteButton);
    }

    public void addActionEvent() {
        deleteButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            delegate.deleteCountry(keysText.get(0).getText());
            // next line: shows new list of countries in terminal for testing purposes
            delegate.showCountry();
        }
    }
}