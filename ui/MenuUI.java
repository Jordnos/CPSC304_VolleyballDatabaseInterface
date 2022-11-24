package ca.ubc.cs304.ui;
import ca.ubc.cs304.delegates.VolleyballWindowDelegate;

import javax.swing.*;
import java.awt.*;

public class MenuUI extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private VolleyballWindowDelegate delegate;

    public MenuUI(VolleyballWindowDelegate delegate) {
        super();
        this.delegate = delegate;
        setSize(WIDTH, HEIGHT);

        addMenus();

        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    // initializes all the different tabs and adds it to the main frame
    private void addMenus() {
        JTabbedPane menus = new JTabbedPane();
        menus.addTab("Insert", makeInsertPanel());
        menus.addTab("Delete", makeDeletePanel());
        menus.addTab("Update", makeUpdatePanel());
        menus.addTab("Selection", makeSelectionPanel());
        menus.addTab("Projection", makeProjectionPanel());
        menus.addTab("Join", makeJoinPanel());
        menus.addTab("Division", makeDivisionPanel());
        menus.addTab("Aggregation Group By", makeAggregationGroupByPanel());
        menus.addTab("Aggregation Having", makeAggregationHavingPanel());
        menus.addTab("Nested Aggregation", makeNestedAggregationPanel());
        this.add(menus);
    }

    private JComponent makeInsertPanel() {
        return new InsertPanel(delegate);
    }
    private JComponent makeDeletePanel() {
        return new DeletePanel(delegate);
    }
    private JComponent makeUpdatePanel() { return new UpdatePanel(delegate); }
    private JComponent makeSelectionPanel() { return new SelectionPanel(delegate); }
    private JComponent makeProjectionPanel() {
        return new ProjectionPanel(delegate);
    }
    private JComponent makeJoinPanel() { return new JoinPanel(delegate);}
    private JComponent makeDivisionPanel() {
        return new DivisionPanel(delegate);
    }
    private JComponent makeAggregationGroupByPanel() {
        return new AggregationGroupByPanel(delegate);
    }
    private JComponent makeAggregationHavingPanel() {
        return new AggregationHavingPanel();
    }
    private JComponent makeNestedAggregationPanel() {
        return new NestedAggregationPanel();
    }
}
