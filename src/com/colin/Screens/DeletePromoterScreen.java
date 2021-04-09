package com.colin.Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  DeletePromoterScreen  extends JPanel {
    int PREF_W = 700;
    int PREF_H = 550;

    public DeletePromoterScreen() {
        setLayout(new GridBagLayout());
        add(new MyContentPane());

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}

class MyContentPane extends JPanel {
    int PREF_W = 450;
    int PREF_H = PREF_W;
    private JButton findPerson;
    private JButton delPerson;
    private JTextField idEntry;
    private JLabel label;
    private JLabel addP;
    private JLabel found;
    private JLabel notFound;
    private JLabel tryAgain;
    private JLabel succDeleted;
    private JLabel pNameBudg;

    public MyContentPane()
    {
        setLayout(null);

        label = new JLabel("DELETE A PROMOTER");
        label.setBounds(120,30,220,40);
        label.setFont(label.getFont().deriveFont(18f));

        addP = new JLabel("Please Enter ID:");
        addP.setBounds(140,90,200,40);
        addP.setFont(addP.getFont().deriveFont(15f));

        idEntry = new JTextField();
        idEntry. setBounds(140,120,150,20);

        findPerson = new JButton("FIND");
        findPerson.setBounds(160,155,100,35);
        delPerson = new JButton("DELETE?");
        delPerson.setBounds(160,300,100,35);
        findPerson.addActionListener(new FindPersonListener());
        delPerson.addActionListener(new DeletePersonListener());


        found = new JLabel("Promoter Found!");
        found.setBounds(150,195,150,50);
        found.setFont(found.getFont().deriveFont(15f));


        notFound = new JLabel("Promoter Not Found!");
        notFound.setBounds(140,195,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,230,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));


        succDeleted = new JLabel ("Promoter Successfully Deleted!");
        succDeleted.setBounds(110,340,250,50);
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
        delPerson.setVisible(false);
        succDeleted.setVisible(false);
        found.setVisible(false);
        notFound.setVisible(false);
        tryAgain.setVisible(false);

        setBackground(Color.MAGENTA);

    }

    public class FindPersonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (idEntry.getText().equals("10"))
            {
                found.setVisible(true);
                delPerson.setVisible(true);
                notFound.setVisible(false);
                tryAgain.setVisible(false);
                pNameBudg = new JLabel("Name/Budget");
                pNameBudg.setBounds(160,230,400,70);
                pNameBudg.setFont(pNameBudg.getFont().deriveFont(15f));
                add(pNameBudg);
            }else{
                notFound.setVisible(true);
                tryAgain.setVisible(true);
                found.setVisible(false);
                delPerson.setVisible(false);
                succDeleted.setVisible(false);
                pNameBudg.setVisible(false);

            }

        }
    }

    public class DeletePersonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){

            succDeleted.setVisible(true);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}