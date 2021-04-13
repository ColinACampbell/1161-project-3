package com.colin.Screens;

import com.colin.Models.*;
import com.colin.Services.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CreatePromoterScreen extends JFrame
{
    private JTextField txtName;
    private JTextField txtBudget;
    private JTextField txtPhoneNumber;
    private JTextField txtEmailAddress;
    private JTextField txtAddressLine1, txtAddressLine2, txtAddressLine3;

    private JButton cmdSave;
    private JButton cmdClose; // make it disappear and go back to HomeScreen

    private JPanel pnlCommand;
    private JPanel pnlDisplay;

    private CreatePromoterScreen thisForm;
    
    // 
    Ministry ministry = new Ministry("HEALTH", 2);
    ArrayList<Venue> vlist = new ArrayList<>();
    //
    
    public CreatePromoterScreen()
	{
        thisForm = this;
        ButtonListener buttonListener = new ButtonListener();
    
        setTitle("CREATE PROMOTER");
        setPreferredSize(new Dimension(450, 400));

        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        
        //
        pnlDisplay.setLayout(new GridLayout(12, 2));
        pnlDisplay.setBackground(Color.pink); 
		
        pnlCommand.setLayout(new FlowLayout());
        pnlCommand.setBackground(Color.pink); 

        /*
        GridBagConstraints gbc = new GridBagConstraints();
 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 0;
        */
       
        pnlDisplay.add(new JLabel("Name:")); 
        txtName = new JTextField(10);
        pnlDisplay.add(txtName);

        pnlDisplay.add(new JLabel("Budget:"));
        txtBudget = new JTextField(10);  
        pnlDisplay.add(txtBudget);

        pnlDisplay.add(new JLabel("Phone Number:"));
        txtPhoneNumber = new JTextField(10);  
        pnlDisplay.add(txtPhoneNumber);

        pnlDisplay.add(new JLabel("Email Address:"));
        txtEmailAddress = new JTextField(15);  
        pnlDisplay.add(txtEmailAddress);

        pnlDisplay.add(new JLabel("Home Address:"));
        pnlDisplay.add(new JLabel(" "));

        pnlDisplay.add(new JLabel("       Address Line 1:"));
        txtAddressLine1 = new JTextField(20);  
        pnlDisplay.add(txtAddressLine1);

        pnlDisplay.add(new JLabel("       Address Line 2:"));
        txtAddressLine2 = new JTextField(20);  
        pnlDisplay.add(txtAddressLine2);

        pnlDisplay.add(new JLabel("       Address Line 3:"));
        txtAddressLine3 = new JTextField(20);  
        pnlDisplay.add(txtAddressLine3);

        cmdSave = new JButton("Save");
        cmdSave.addActionListener(buttonListener);
        pnlCommand.add(cmdSave);

        cmdClose = new JButton("Close");
        cmdClose.addActionListener(buttonListener);
        pnlCommand.add(cmdClose);
		
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
	}

    private class ButtonListener implements ActionListener
    {
        PromoterService promoterService = new PromoterService();
        
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
                        String phoneNumber = txtPhoneNumber.getText();
                        String emailAddress = txtEmailAddress.getText();
                        String homeAddress = txtAddressLine1.getText()+", "+txtAddressLine2.getText()+", "+txtAddressLine3.getText();

                        Promoter pr = new Promoter(name, phoneNumber, emailAddress, homeAddress, budget, ministry, vlist);
                        promoterService.addPromoter(pr);

                        add(new JLabel("Successfully Created!"));
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
