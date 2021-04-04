package com.colin.Screens;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomeScreen extends JFrame {

    public HomeScreen()
    {
        setSize(1080,720);

        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(2);
        gridLayout.setColumns(2);


        JPanel editPromoterPanel = new JPanel();
        editPromoterPanel.add(new JLabel("Edit Promoters"));
        JPanel deletePromoterPanel = new JPanel();
        deletePromoterPanel.add(new JLabel("Delete Promoters"));
        JPanel createPromoterPanel = new JPanel();
        createPromoterPanel.add(new JLabel("Create Promoters"));

        JPanel[] appPanels = {createFeaturePanel("View Promoters","undraw_online_gallery_dmv3"),
                createFeaturePanel("Create Promoters","undraw_Create_re_57a3"),
                createFeaturePanel("Update Promoter","undraw_update_uxn2"),
                createFeaturePanel("Delete Promoter","undraw_Throw_away_re_x60k")
        };

        setLayout(gridLayout);
        for (JPanel panel : appPanels)
            add(panel);

        setVisible(true);


    }


    JPanel createFeaturePanel(String btnText, String imagePath)
    {
        JPanel viewPromoterPanel = new JPanel();
        try
        {
            BufferedImage image = ImageIO.read(new File("./Assets/"+imagePath+".png"));
            Image scaledImage = image.getScaledInstance(156,144,Image.SCALE_SMOOTH);
            JLabel textImage = new JLabel(new ImageIcon(scaledImage));
            viewPromoterPanel.add(textImage);
            JButton button = new JButton(btnText);
            button.setSize(200,50);
            button.setBounds(500,500,100,100);
            viewPromoterPanel.add(button);

            GridLayout gridLayout = new GridLayout();
            gridLayout.setRows(2);
            gridLayout.setColumns(2);

            viewPromoterPanel.setLayout(gridLayout);
            viewPromoterPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            viewPromoterPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);
            viewPromoterPanel.setBorder(new EmptyBorder(10,10,10,10));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return viewPromoterPanel;
    }
}
