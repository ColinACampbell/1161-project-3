package com.colin.Screens;

import javax.swing.*;

import com.colin.Models.*;
import com.colin.Services.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UpdatePromoterScreen extends JFrame
{
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtBudget;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JTextField txtAddressLine1, txtAddressLine2, txtAddressLine3;

    private JButton cmdFind;
    private JButton cmdSave;
    private JButton cmdClose; 
    private JButton cmdCancel;

    private JPanel pnlCommand;
    private JPanel pnlDisplay;

    private JPanel pnlFoundCommand;
    private JPanel pnlFoundDisplay;
    // private JPanel pnlNotFoundCommand;
    private JPanel pnlNotFoundDisplay;

    private UpdatePromoterScreen thisForm;

    //ArrayList<Promoter> prlist;
    ArrayList<Venue> vlist;
    Ministry ministry = new Ministry("HEALTH", 2);
    PromoterService promoterService;

    public UpdatePromoterScreen() // CHANGE
    {
        thisForm = this;
        ButtonListener buttonListener = new ButtonListener();

        //prlist.add(new Promoter("Pro2","","","", 1000.0, ministry, vlist));

        setSize(new Dimension(900,600));
       
        setTitle("UPDATE PROMOTER");

        pnlDisplay = new JPanel();
        pnlCommand = new JPanel();
        pnlFoundDisplay = new JPanel();
        pnlFoundCommand = new JPanel();
        pnlNotFoundDisplay = new JPanel();
        
        // each panel's layout
        pnlDisplay.setLayout(new GridLayout(2, 1));
        pnlCommand.setLayout(new FlowLayout());
        pnlFoundDisplay.setLayout(new GridLayout(2, 1));
        pnlFoundCommand.setLayout(new FlowLayout());
        pnlNotFoundDisplay.setLayout(new FlowLayout());

        // the main panel's display
        pnlDisplay.add(new JLabel("Enter ID:")); 
        txtID = new JTextField(20);
        pnlDisplay.add(txtID);

        // the main panel's command buttons
        cmdFind = new JButton("Find");
        cmdFind.addActionListener(buttonListener);
        pnlCommand.add(cmdFind);

        cmdClose = new JButton("Close");
        cmdClose.addActionListener(buttonListener);
        pnlCommand.add(cmdClose);

        // the found pannel's display
        pnlFoundDisplay.add(new JLabel("Promoter Found!"));

        // pnlFoundDisplay.add(new JLabel("")); 
        // pnlFoundDisplay.add(new JLabel("")); 

        pnlFoundDisplay.add(new JLabel("New Name:")); 
        txtName = new JTextField(20);
        pnlFoundDisplay.add(txtName);

        pnlFoundDisplay.add(new JLabel("New Budget:"));
        txtBudget = new JTextField(10);  
        pnlFoundDisplay.add(txtBudget);

        pnlFoundDisplay.add(new JLabel("New Phone Number:"));
        txtPhone = new JTextField(10);  
        pnlFoundDisplay.add(txtPhone);

        pnlFoundDisplay.add(new JLabel("New Email Address:"));
        txtEmail = new JTextField(15); 
        pnlFoundDisplay.add(txtEmail);

        pnlFoundDisplay.add(new JLabel("New Home Address:"));

        pnlFoundDisplay.add(new JLabel("Address Line 1:"));
        txtAddressLine1 = new JTextField(20); 
        pnlFoundDisplay.add(txtAddressLine1);

        pnlFoundDisplay.add(new JLabel("Address Line 2:"));
        txtAddressLine2 = new JTextField(20); 
        pnlFoundDisplay.add(txtAddressLine2);

        pnlFoundDisplay.add(new JLabel("Address Line 3:"));
        txtAddressLine3 = new JTextField(10); 
        pnlFoundDisplay.add(txtAddressLine3);

        // the found panel's command buttons
        cmdSave = new JButton("Save");
        cmdSave.addActionListener(buttonListener);
        pnlFoundCommand.add(cmdSave);

        cmdCancel = new JButton("Cancel");
        cmdCancel.addActionListener(buttonListener);
        pnlFoundCommand.add(cmdCancel);
    
        // the not found panel's diplay (no command buttons)
        pnlNotFoundDisplay.add(new JLabel("Promoter Not Found!"));
        pnlNotFoundDisplay.add(new JLabel("Try Again!"));

        pnlFoundDisplay.setVisible(false);
        pnlFoundCommand.setVisible(false);
        pnlNotFoundDisplay.setVisible(false);

        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        add(pnlFoundDisplay, BorderLayout.CENTER);
        add(pnlFoundCommand, BorderLayout.SOUTH);
        add(pnlNotFoundDisplay, BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);    
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == "Find")
            {
                try
                {
                    if(txtID.getText().length() > 0)
                    {
                        int pid = Integer.parseInt(txtID.getText());
                        Promoter pr = promoterService.findPromoter(pid);

                        if(pr != null)
                        {
                            pnlDisplay.setVisible(false);
                            pnlCommand.setVisible(false);
                            
                            pnlFoundDisplay.setVisible(true);
                            pnlFoundCommand.setVisible(true);
                        }
                        else
                        {
                            pnlNotFoundDisplay.setVisible(true);
                        }     
                    }
                }
                catch(NumberFormatException nf) {}
            }

            else if(event.getSource() == "Save")
            {
                int id = Integer.parseInt(txtID.getText());
                String name = txtName.getText();
                double budget = Double.parseDouble(txtBudget.getText());
                String phone = txtPhone.getText();
                String email = txtEmail.getText();
                String address = txtAddressLine1.getText()+", "+txtAddressLine2.getText()+", "+txtAddressLine3.getText();

                promoterService.updatePromoter(id, name, phone, email, address, budget);

                // add(new JLabel("Saved!"), BorderLayout.CENTER);

                UpdatePromoterScreen.this.thisForm.setVisible(false);
            }

            else if(event.getSource() == "Close" || event.getSource() == "Cancel" )
            {
                UpdatePromoterScreen.this.thisForm.setVisible(false);
            }
        }
    }

}
