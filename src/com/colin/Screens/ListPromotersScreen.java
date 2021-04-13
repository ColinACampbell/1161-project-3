package com.colin.Screens;

import com.colin.Comparators.PromoterBudgetComparator;
import com.colin.Comparators.PromoterIDComparator;
import com.colin.Models.Promoter;
import com.colin.Services.PromoterService;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class ListPromotersScreen extends JFrame {

    private JPanel content = new JPanel();
    private JScrollPane jScrollPane = new JScrollPane();
    private JPanel topBar = new JPanel();

    private JButton backButton = new JButton("<---");
    private JButton sortByIDButton = new JButton("Sort By ID");
    private JButton sortByBudgetButton = new JButton("Sort By Budget");
    private JButton searchByIDButton = new JButton("Search By ID");
    private JTextField searchByIDField = new JFormattedTextField();
    DefaultTableModel tableModel = new DefaultTableModel();

    private final PromoterService promoterService = new PromoterService();
    private final ArrayList<Promoter> promoters = promoterService.getPromoters();

    public ListPromotersScreen()
    {

        searchByIDField.setColumns(20);

        setTitle("Promoters");
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

        JTable table = new JTable(tableModel);

        setUpTableModel();
        //jScrollPane.add(table);
        //jScrollPane.setPreferredSize(new Dimension(1000,500));
        // Add to the bottom of the screen
        add(new JScrollPane(table));

        setVisible(true);

        backButton.addActionListener(e->{
            ListPromotersScreen.this.setVisible(false);
            new HomeScreen();
        });

        sortByIDButton.addActionListener(e->{
            clearTable();
            Collections.sort(promoters,new PromoterIDComparator());
            initRows(promoters);
        });
        
        sortByBudgetButton.addActionListener(e->{
            clearTable();
            Collections.sort(promoters,new PromoterBudgetComparator());
            initRows(promoters);
        });

        searchByIDButton.addActionListener(e->{
            clearTable();
            String idText = searchByIDField.getText();

            if (idText.trim().isEmpty())
                initRows(promoters);
            else {
                Promoter promoter = promoterService.findPromoter(Integer.parseInt(idText));
                System.out.println(promoter);

                if (promoter == null)
                    clearTable();
                else {
                    clearTable();
                    ArrayList<Promoter> sample = new ArrayList<>();
                    sample.add(promoter);
                    initRows(sample); // just show that one promoter if exists
                }
            }
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

    private void setUpTableModel()
    {
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Budget");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Email");
        tableModel.addColumn("Address");

        initRows(promoters);
    }

    private void initRows(ArrayList<Promoter> promoters)
    {
        for (Promoter promoter : promoters)
        {
            Object[] row = new Object[]{promoter.getId(),promoter.getName(),promoter.getBudget(),
                    promoter.getPhone(),promoter.getEmail(),promoter.getAddress()};

            tableModel.addRow(row);
        }
    }


    private void clearTable()
    {
        tableModel.setRowCount(0);
    }

}