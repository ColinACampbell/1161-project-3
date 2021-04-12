package com.colin.Screens;

import javax.swing.*;

import com.colin.Models.*;

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

    private JButton cmdFind;
    private JButton cmdSave;
    private JButton cmdClose; 

    private JPanel pnlCommand;
    private JPanel pnlDisplay;

    private JPanel pnlFoundCommand;
    private JPanel pnlFoundDisplay;
    // private JPanel pnlNotFoundCommand;
    private JPanel pnlNotFoundDisplay;

    private UpdatePromoterScreen thisForm;

    ArrayList<Promoter> prlist;
    ArrayList<Venue> vlist;
    Ministry ministry = new Ministry("HEALTH", 2);

    public UpdatePromoterScreen() // CHANGE
    {
        thisForm = this;
        ButtonListener buttonListener = new ButtonListener();

        prlist.add(new Promoter("Pro2", 1000.0, ministry, vlist));

        // setSize(500, 500);

        setTitle("UPDATE PROMOTER");

        pnlDisplay = new JPanel();
        pnlCommand = new JPanel();
        pnlFoundDisplay = new JPanel();
        pnlFoundCommand = new JPanel();
        pnlNotFoundDisplay = new JPanel();
        // pnlNotFoundCommand = new JPanel();
        
        pnlDisplay.add(new JLabel("Enter ID:")); 
        txtID = new JTextField(20);
        pnlDisplay.add(txtID);

        pnlDisplay.setLayout(new GridLayout(2, 1));

        cmdFind = new JButton("Find");
        cmdFind.addActionListener(buttonListener);
        pnlCommand.add(cmdFind);

        cmdClose = new JButton("Close");
        cmdClose.addActionListener(buttonListener);
        pnlCommand.add(cmdClose);

        pnlCommand.setLayout(new GridLayout(1, 2));

        //
        // prlist.get(pdx);
    
        // pnlFoundDisplay.add(new JLabel("")); 
        // pnlFoundDisplay.add(new JLabel("")); 
        
        pnlFoundDisplay.add(new JLabel("New Name:")); 
        txtName = new JTextField(20);
        pnlFoundDisplay.add(txtName);

        pnlFoundDisplay.add(new JLabel("New Budget:"));
        txtBudget = new JTextField(10);  // CHANGE 
        pnlFoundDisplay.add(txtBudget);

        pnlFoundDisplay.add(new JLabel("New Phone Number:"));
        txtPhone = new JTextField(10);  
        pnlFoundDisplay.add(txtPhone);

        pnlFoundDisplay.add(new JLabel("New Email Address:"));
        txtEmail = new JTextField(10); 
        pnlFoundDisplay.add(txtEmail);

        cmdSave = new JButton("Save");
        cmdSave.addActionListener(buttonListener);
        pnlFoundCommand.add(cmdSave);

        cmdClose = new JButton("Close");
        cmdClose.addActionListener(buttonListener);
        pnlFoundCommand.add(cmdClose);

        pnlNotFoundDisplay.add(new JLabel("Promoter Found"));
        pnlFoundDisplay.setVisible(false);
        pnlFoundCommand.setVisible(false);
        //

        //
        pnlNotFoundDisplay.add(new JLabel("Promoter Not Found"));
        pnlNotFoundDisplay.setVisible(false);
        //

        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);

        // pack();
        setBackground(Color.pink); // CHANGE
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
                        // pdx = findPromoter(prlist, pid);

                        if(pid == 0) // ADJUST
                        {
                            pnlDisplay.setVisible(false);
                            pnlCommand.setVisible(false);
                            
                            pnlFoundDisplay.setVisible(true);
                            add(pnlFoundDisplay, BorderLayout.CENTER);
                            pnlFoundCommand.setVisible(true);
                            add(pnlFoundCommand, BorderLayout.SOUTH);
                        }
                        else
                        {
                            add(pnlNotFoundDisplay, BorderLayout.CENTER);
                            pnlNotFoundDisplay.setVisible(true);
                        }
                            
                    }
                }
                catch(NumberFormatException nf) {}
            }

            else if(event.getSource() == "Save") {}

            else if(event.getSource() == "Close")
            {
                UpdatePromoterScreen.this.thisForm.setVisible(false);
            }
        }
    }

}
