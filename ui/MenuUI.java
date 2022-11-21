package ca.ubc.cs304.ui;
import javax.swing.*;

public class MenuUI extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public MenuUI() {
        super();
        setSize(WIDTH, HEIGHT);

        addMenus();

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
        return new InsertPanel();
    }
    private JComponent makeDeletePanel() {
        // TODO:
        return new JPanel();
    }
    private JComponent makeUpdatePanel() {
        // TODO:
        return new JPanel();
    }
    private JComponent makeSelectionPanel() {
        // TODO:
        return new JPanel();
    }
    private JComponent makeProjectionPanel() {
        // TODO:
        return new JPanel();
    }
    private JComponent makeJoinPanel() {
        // TODO:
        return new JPanel();
    }
    private JComponent makeDivisionPanel() {
        return new DivisionPanel();
    }
    private JComponent makeAggregationGroupByPanel() {
        return new AggregationGroupByPanel();
    }
    private JComponent makeAggregationHavingPanel() {
        return new AggregationHavingPanel();
    }
    private JComponent makeNestedAggregationPanel() {
        return new NestedAggregationPanel();
    }

    // main is here to be able to test the gui - remove later
    public static void main(String[] args) { new MenuUI(); }


}
