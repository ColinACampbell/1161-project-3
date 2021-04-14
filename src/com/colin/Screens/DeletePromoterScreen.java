package com.colin.Screens;

import com.colin.Models.Promoter;
import com.colin.Services.PromoterService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePromoterScreen  extends JPanel {
    int PREF_W = 700;
    int PREF_H = 550;

    protected JFrame frame = new JFrame();

    public DeletePromoterScreen() {
        setLayout(new GridBagLayout());
        add(new MyContentPane());

        //JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    private class MyContentPane extends JPanel {
        int PREF_W = 450;
        int PREF_H = PREF_W;
        private JButton findPerson;
        private JButton delPerson;
        private JButton cancelButton;// initialization of new button
        private JTextField idEntry;
        private JLabel label;
        private JLabel addP;
        private JLabel found;
        private JLabel notFound;
        private JLabel tryAgain;
        private JLabel succDeleted;
        private JLabel pNameBudg;

        public MyContentPane() {
            setLayout(null);

            label = new JLabel("DELETE A PROMOTER");
            label.setBounds(120, 30, 220, 40);
            label.setFont(label.getFont().deriveFont(18f));

            addP = new JLabel("Please Enter ID:");
            addP.setBounds(155, 90, 200, 40);
            addP.setFont(addP.getFont().deriveFont(15f));

            idEntry = new JTextField();
            idEntry.setBounds(150, 120, 150, 20);

            findPerson = new JButton("FIND");
            findPerson.setBounds(175, 155, 100, 35);
            delPerson = new JButton("DELETE?");
            delPerson.setBounds(175, 300, 100, 35);
            cancelButton = new JButton("Cancel & Go Back");// Naming of button
            cancelButton.setBounds(145, 360, 150, 35);// placement of button.. might need to change to fit properly
            findPerson.addActionListener(new FindPersonListener());
            delPerson.addActionListener(new DeletePersonListener());
            cancelButton.addActionListener(new CancelScreenListener());// action listener for cancel button


            found = new JLabel("Promoter Found!");
            found.setBounds(150, 195, 150, 50);
            found.setFont(found.getFont().deriveFont(15f));


            notFound = new JLabel("Promoter Not Found!");
            notFound.setBounds(140, 195, 200, 50);
            notFound.setFont(notFound.getFont().deriveFont(15f));

            tryAgain = new JLabel("Try Again!");
            tryAgain.setBounds(180, 230, 100, 35);
            tryAgain.setFont(tryAgain.getFont().deriveFont(15f));


            succDeleted = new JLabel("Promoter Successfully Deleted!");
            succDeleted.setBounds(110, 340, 250, 50);
            succDeleted.setFont(succDeleted.getFont().deriveFont(15f));

            pNameBudg = new JLabel("Promoter Name -- Promoter Budget");
            //pNameBudg.setBounds(120,250,350,50);
            pNameBudg.setFont(pNameBudg.getFont().deriveFont(15f));


            add(label);
            add(addP);
            add(idEntry);
            add(findPerson);
            add(found);
            add(notFound);
            add(tryAgain);
            add(delPerson);
            add(succDeleted);
            add(cancelButton); //cancel button added to content pane
            delPerson.setVisible(false);
            succDeleted.setVisible(false);
            found.setVisible(false);
            notFound.setVisible(false);
            tryAgain.setVisible(false);

            setBackground(Color.pink);

        }

        public class FindPersonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                PromoterService promoterService = new PromoterService();
                if (promoterService.findPromoter(Integer.parseInt(idEntry.getText())) != null) // if statement to test findPromoter method
                {
                    Promoter currPromoter = promoterService.findPromoter(Integer.parseInt(idEntry.getText())); // intialize promoter from id in text field
                    found.setVisible(true);
                    delPerson.setVisible(true);
                    notFound.setVisible(false);
                    tryAgain.setVisible(false);
                    pNameBudg = new JLabel("" + currPromoter.getName() + "/" + currPromoter.getBudget());
                    pNameBudg.setBounds(160, 230, 400, 70);
                    pNameBudg.setFont(pNameBudg.getFont().deriveFont(15f));
                    add(pNameBudg);
                } else {
                    notFound.setVisible(true);
                    tryAgain.setVisible(true);
                    found.setVisible(false);
                    delPerson.setVisible(false);
                    succDeleted.setVisible(false);
                    pNameBudg.setVisible(false);

                }

            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(PREF_W, PREF_H);
        }

        public class DeletePersonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                PromoterService promoterService = new PromoterService();
                promoterService.deletePromoter(Integer.parseInt(idEntry.getText()));
                succDeleted.setVisible(true);
            }
        }

        public class CancelScreenListener implements ActionListener// cancel button action listener
        {
            public void actionPerformed(ActionEvent e) {
                DeletePromoterScreen.this.frame.setVisible(false);
            }
        }
    }
}

