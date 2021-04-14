package com.colin.Screens;

import com.colin.Comparators.PromoterBudgetComparator;
import com.colin.Comparators.PromoterIDComparator;
import com.colin.Models.Promoter;
import com.colin.Services.PromoterService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Collections;

public class ListPromotersScreen extends JFrame {

    private JPanel content = new JPanel();
    private JScrollPane jScrollPane = new JScrollPane();
    private JPanel topBar = new JPanel();

    private JButton backButton = new JButton("<---");
    private JButton sortByIDButton = new JButton("Sort By ID");
    private JButton sortByBudgetButton = new JButton("Sort By Budget");
    private JButton searchButton = new JButton("Search");
    private JTextField searchTextField = new JFormattedTextField();
    DefaultTableModel tableModel = new DefaultTableModel();

    private final PromoterService promoterService = new PromoterService();
    private final ArrayList<Promoter> promoters = promoterService.getPromoters();

    public ListPromotersScreen()
    {

        searchTextField.setColumns(20);

        setTitle("Promoters");
        setSize(new Dimension(900,600));

        // This layout makes everything looks how it should
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        // Methods are used to short hand setting up and creating components to reduce repeating code
        JPanel leftFloatPanel = createFloatPanel(FlowLayout.LEFT,backButton);
        JPanel rightFloatPanel = createFloatPanel(FlowLayout.RIGHT, searchTextField, searchButton,sortByIDButton,sortByBudgetButton);
        JPanel topBarComponents = createTopBarPanel(leftFloatPanel,rightFloatPanel);

        // Add to the top of the screen
        add(topBarComponents,BorderLayout.PAGE_START);

        // Now set up the table

        JTable table = new JTable(tableModel);

        setUpTableModel();

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


        searchButton.addActionListener(e->{
            clearTable();
            String query = searchTextField.getText();

            if (query.trim().isEmpty())
                initRows(promoters);
            else {
                // if the text field is not empty
                try {
                    // test to see if a number was entered
                    int id = Integer.parseInt(query);
                    Promoter promoter = promoterService.findPromoter(id);
                    if (promoter == null)
                        clearTable();
                    else {
                        clearTable();
                        ArrayList<Promoter> sample = new ArrayList<>();
                        sample.add(promoter);
                        initRows(sample); // just show that one promoter if exists
                    }
                } catch (NumberFormatException exception)
                {
                    ArrayList<Promoter> promoters = null;
                    // if a text was entered, it must be by name or email
                    if (query.contains("@")) // find by email
                        promoters = promoterService.findPromotersByEmail(query);
                    else // must be by name
                        promoters = promoterService.findPromotersByName(query);

                    assert promoters != null;
                    initRows(promoters);
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