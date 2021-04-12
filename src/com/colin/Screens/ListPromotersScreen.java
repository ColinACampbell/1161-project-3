package com.colin.Screens;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class ListPromotersScreen extends JFrame {


    private JPanel content = new JPanel();
    private JScrollPane jScrollPane = new JScrollPane();
    private JPanel topBar = new JPanel();

    private JButton backButton = new JButton("<---");
    private JButton sortByIDButton = new JButton("Sort By ID");
    private JButton sortByBudgetButton = new JButton("Sort By Budget");
    private JButton searchByIDButton = new JButton("Search By ID");
    private JTextField searchByIDField = new JFormattedTextField();

    public ListPromotersScreen()
    {

        searchByIDField.setColumns(20);

        setTitle("Promoter");
        setSize(new Dimension(900,600));

        // This layout makes everything looks how it should
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        // Methods are used to short hand setting up and creating components to reduce repeating code
        JPanel leftFloatPanel = createFloatPanel(FlowLayout.LEFT,backButton);
        JPanel rightFloatPanel = createFloatPanel(FlowLayout.RIGHT,searchByIDField,searchByIDButton,sortByIDButton,sortByBudgetButton);
        JPanel topBarComponents = createTopBarPanel(leftFloatPanel,rightFloatPanel);

        // Add to the top of the screen
        add(topBarComponents,BorderLayout.PAGE_START);

        // Now set up the table
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);

        tableModel.addColumn("Languages");
        tableModel.insertRow(0, new Object[] { "CSS" });
        tableModel.insertRow(0, new Object[] { "HTML5" });
        tableModel.insertRow(0, new Object[] { "JavaScript" });
        tableModel.insertRow(0, new Object[] { "jQuery" });
        tableModel.insertRow(0, new Object[] { "AngularJS" });
        tableModel.insertRow(tableModel.getRowCount(), new Object[] { "ExpressJS" });

        //jScrollPane.add(table);
        //jScrollPane.setPreferredSize(new Dimension(1000,500));
        // Add to the bottom of the screen
        add(new JScrollPane(table));

        setVisible(true);

        backButton.addActionListener(e->{
            ListPromotersScreen.this.setVisible(false);
            new HomeScreen();
        });
    }

    /**
     * @param containers List of Containers to add to the top panel of the screen
     * @return A JPanel that contains the widgets for the top screen
     */
    private JPanel createTopBarPanel(Container ...containers)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        for (Container container : containers)
            panel.add(container);
        return panel;
    }

    private JPanel createFloatPanel(int floatDirection,Container ...containers)
    {
        JPanel panel = new JPanel();
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(floatDirection);
        panel.setLayout(layout);

        for (Container container : containers)
            panel.add(container);

        return panel;
    }
}