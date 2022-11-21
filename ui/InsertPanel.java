package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertPanel extends JPanel implements ActionListener {

    JLabel[] attributeLabel = {new JLabel("Attribute 1"), new JLabel("Attribute 2"), new JLabel("Attribute 3")};
    JTextField[] attributeText = {new JTextField(), new JTextField(), new JTextField()};
    JLabel titleLabel = new JLabel("Insert Into Relation Name");
    JButton insertButton = new JButton("Insert");
    static int LABEL_X_POS = 50;
    static int TEXT_X_POS = 150;
    static int LABEL_WIDTH = 100;
    static int TEXT_WIDTH = 150;
    static int LABEL_HEIGHT = 30;
    static int TEXT_HEIGHT = 30;
    static int INITIAL_Y = 30;
    static int HEIGHT_TO_NEXT = 20;
    static int TITLE_WIDTH = 300;

    public InsertPanel() {
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
        for(int i = 0; i < attributeLabel.length; i++){
            attributeLabel[i].setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(i+1), LABEL_WIDTH, LABEL_HEIGHT);
            attributeText[i].setBounds(TEXT_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(i+1), TEXT_WIDTH, TEXT_HEIGHT);
        }
        insertButton.setBounds(LABEL_X_POS, INITIAL_Y + (HEIGHT_TO_NEXT + LABEL_HEIGHT)*(attributeLabel.length+1), LABEL_WIDTH, LABEL_HEIGHT);
    }

    public void addComponentsToContainer() {
        for(int i = 0; i < attributeLabel.length; i++){
            add(attributeLabel[i]);
            add(attributeText[i]);
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
            // TODO: create relation model and call insert function through delegates
        }
    }
}
