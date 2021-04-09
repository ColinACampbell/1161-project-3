package com.colin.Screens;

import javax.swing.*;

public class ListPromotersScreen extends JDialog {
    private JPanel contentPane;
    private JTable table1;
    private JButton backButton;
    private JTextField searchByIDTextField;
    private JButton searchIDButton;
    private JButton sortByIDButton;
    private JButton sortByNameButton;
    private JButton buttonOK;
    private JButton buttonCancel;

    public ListPromotersScreen() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        setSize(1080,720);
        setVisible(true);
    }

    public static void main(String[] args) {
        ListPromotersScreen dialog = new ListPromotersScreen();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
