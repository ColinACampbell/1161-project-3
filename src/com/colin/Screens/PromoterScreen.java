package com.colin.Screens;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class PromoterScreen extends JFrame {

    JButton backButton = new JButton("<---");
    JButton sortByIDButton = new JButton("Sort By ID");
    JButton sortByBudgetButton = new JButton("Sort By Budget");
    JTextField searchByIDField = new JFormattedTextField();

    public PromoterScreen()
    {

        setTitle("Promoter");
        setSize(new Dimension(900,600));

        searchByIDField.setColumns(20);

        GridLayout gridLayout = new GridLayout();

        gridLayout.setRows(2);
        setLayout(gridLayout);

        JPanel topBarComponents = new JPanel();
        topBarComponents.setLayout(new FlowLayout());

        JPanel leftFloatPanel = new JPanel();
        FlowLayout leftFloatLayout = new FlowLayout();
        leftFloatLayout.setAlignment(FlowLayout.LEFT);
        leftFloatPanel.setLayout(leftFloatLayout);

        JPanel rightFloatPanel = new JPanel();
        FlowLayout rightFloatLayout = new FlowLayout();
        rightFloatLayout.setAlignment(FlowLayout.RIGHT);
        rightFloatPanel.setLayout(rightFloatLayout);

        leftFloatPanel.add(backButton);

        rightFloatPanel.add(searchByIDField);
        rightFloatPanel.add(sortByIDButton);
        rightFloatPanel.add(sortByBudgetButton);

        topBarComponents.add(leftFloatPanel);
        topBarComponents.add(rightFloatPanel);

        add(topBarComponents);

        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("My Column");
        
        setVisible(true);
    }

}