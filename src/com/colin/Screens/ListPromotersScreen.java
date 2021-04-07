package com.colin.Screens;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class ListPromotersScreen extends JFrame {

    private JButton backButton = new JButton("<---");
    private JButton sortByIDButton = new JButton("Sort By ID");
    private JButton sortByBudgetButton = new JButton("Sort By Budget");
    private JTextField searchByIDField = new JFormattedTextField();
    private JTable listPromotersTable = new JTable();

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
        JPanel rightFloatPanel = createFloatPanel(FlowLayout.RIGHT,searchByIDField,sortByBudgetButton,sortByBudgetButton);
        JPanel topBarComponents = createTopBarPanel(leftFloatPanel,rightFloatPanel);

        // Add to the top of the screen
        add(topBarComponents,BorderLayout.PAGE_START);

        // Now set up the table

        Object[] row = { "1", "Colin", "5000" };
        Object[][] data = {row};

        DefaultTableModel tableModel = new DefaultTableModel(data,new String[]{"Promoter ID","Promoter Name","Promoter Budget"});

        listPromotersTable.setModel(tableModel);
        listPromotersTable.setPreferredSize(new Dimension(1000,500));

        JScrollPane jScrollPane = new JScrollPane();

        jScrollPane.add(listPromotersTable);
        jScrollPane.setPreferredSize(new Dimension(1000,500));
        // Add to the bottom of the screen
        add(jScrollPane,BorderLayout.PAGE_END);

        setVisible(true);
        // Get's messy if the user can resize this screen, so no they won't resize anything
        setResizable(false);
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