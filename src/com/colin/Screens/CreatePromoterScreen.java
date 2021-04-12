package com.colin.Screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreatePromoterScreen extends JFrame
{
    private JTextField txtName;
    private JTextField txtBudget;

    private JButton cmdSave;
    private JButton cmdClose; // make it disappear and go back to HomeScreen

    private JPanel pnlCommand;
    private JPanel pnlDisplay;

    private CreatePromoterScreen thisForm;
    
    // ArrayList<Promoter> prlist;
    // Ministry ministry = new Minstry("HEALTH", 2);
    // ArrayList<Venue> vlist;
    
    public CreatePromoterScreen()
	{
        setSize(300, 400);

        thisForm = this;
        ButtonListener buttonListener = new ButtonListener();

        setTitle("CREATE PROMOTER");

        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        pnlDisplay.add(new JLabel("Name:")); 
        txtName = new JTextField(20);
        pnlDisplay.add(txtName);

        pnlDisplay.add(new JLabel("Budget:"));
        txtBudget = new JTextField(10);  
        pnlDisplay.add(txtBudget);

        pnlDisplay.add(new JLabel("Phone Number:"));
        txtBudget = new JTextField(10);  
        pnlDisplay.add(txtBudget);

        pnlDisplay.add(new JLabel("Email Address:"));
        txtBudget = new JTextField(10);  
        pnlDisplay.add(txtBudget);

        pnlDisplay.setLayout(new GridLayout(8, 1));

        cmdSave = new JButton("Save");
        cmdSave.addActionListener(buttonListener);
        pnlCommand.add(cmdSave);

        cmdClose = new JButton("Close");
        cmdClose.addActionListener(buttonListener);
        pnlCommand.add(cmdClose);

        pnlCommand.setLayout(new GridLayout(1, 2));

        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
		
        pack();
        setBackground(Color.pink); // CHANGE
        setVisible(true);
	}

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == cmdSave)
            {
                try
                {
                    // txtName.getText().contains(" ");
                    if(txtName.getText().length() > 0 && txtBudget.getText().length() > 0)
                    {
                        String name = txtName.getText();
                        double budget = Double.parseDouble(txtBudget.getText());
                        // String phoneNumber =
                        // String emailAddress = 

                        // Promoter pr = new Promoter(name, phoneNumber, emailAddress, budget, ministry, vlist);
                        // prlist.add(pr);
                        // a service class
                    }
                }
		        catch(NumberFormatException nf) {}
            }

            else if(event.getSource() == cmdClose)
            {
                CreatePromoterScreen.this.thisForm.setVisible(false);
            }
        }
    }
}
