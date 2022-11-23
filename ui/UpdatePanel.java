package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.VolleyballWindowDelegate;
import ca.ubc.cs304.model.Country;
import ca.ubc.cs304.model.Relation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdatePanel extends JPanel implements ActionListener {

    static int LABEL_X_POS = 50;
    static int LABEL_WIDTH = 150;
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
    ArrayList<JLabel> keyLabel = new ArrayList<>();
    ArrayList<JLabel> nonKeyLabel = new ArrayList<>();
    ArrayList<JTextField> keyText = new ArrayList<>();
    ArrayList<JTextField> nonKeyText = new ArrayList<>();
    JLabel titleLabel;
    JButton updateButton;
    private VolleyballWindowDelegate delegate;

    public UpdatePanel(VolleyballWindowDelegate delegate) {
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
        titleLabel = new JLabel("Update table " + relation.getRelationName());
        updateButton = new JButton("Update");

        for(String n : relationKeyNames){
            keyLabel.add(new JLabel("Where " + n + " ="));
            keyText.add(new JTextField());
        }
        for(String n : relationNonKeyNames){
            nonKeyLabel.add(new JLabel("New " + n + " value ="));
            nonKeyText.add(new JTextField());
        }

    }

    public void setLayoutManager() {
        setLayout(null);
    }

    public void setLocationAndSize() {
        titleLabel.setBounds(LABEL_X_POS, INITIAL_Y, TITLE_WIDTH, LABEL_HEIGHT);
        for(int i = 0; i < keyLabel.size(); i++){
            keyLabel.get(i).setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(i+1), LABEL_WIDTH, LABEL_HEIGHT);
            keyText.get(i).setBounds(TEXT_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(i+1), TEXT_WIDTH, TEXT_HEIGHT);
        }
        for(int i = 0; i < nonKeyLabel.size(); i++){
            nonKeyLabel.get(i).setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(keyLabel.size()+i+1), LABEL_WIDTH, LABEL_HEIGHT);
            nonKeyText.get(i).setBounds(TEXT_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(keyLabel.size()+i+1), TEXT_WIDTH, TEXT_HEIGHT);
        }
        updateButton.setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(keyLabel.size()+nonKeyLabel.size()+1), LABEL_WIDTH, LABEL_HEIGHT);
    }

    public void addComponentsToContainer() {
        for(int i = 0; i < keyLabel.size(); i++){
            add(keyLabel.get(i));
            add(keyText.get(i));
        }
        for(int i = 0; i < nonKeyLabel.size(); i++){
            add(nonKeyLabel.get(i));
            add(nonKeyText.get(i));
        }
        add(titleLabel);
        add(updateButton);
    }

    public void addActionEvent() {
        updateButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            delegate.updateCountry(keyText.get(0).getText(), Integer.parseInt(nonKeyText.get(0).getText()));

            DataTable frame = new DataTable(delegate.getCountryTableData());
            frame.setTitle("Country");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 360);
        }
    }
}
