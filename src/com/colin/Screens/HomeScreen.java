package com.colin.Screens;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
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

        JPanel[] appPanels = {createFeaturePanel("View Promoters","undraw_online_gallery_dmv3",e->{
            HomeScreen.this.setVisible(false);
            new ListPromotersScreen();
        }), createFeaturePanel("Create Promoters","undraw_Create_re_57a3",e->{
            //HomeScreen.this.setVisible(false);
            new CreatePromoterScreen();
        }), createFeaturePanel("Update Promoter","undraw_update_uxn2",e->{
            //HomeScreen.this.setVisible(false);
            new UpdatePromoterScreen();
        }), createFeaturePanel("Delete Promoter","undraw_Throw_away_re_x60k",e->{
            //HomeScreen.this.setVisible(false);
            new DeletePromoterScreen();
        })
        };

        setLayout(gridLayout);
        for (JPanel panel : appPanels)
        {
            panel.setBackground(Color.PINK);
            add(panel);
        }

        setVisible(true);
    }


    JPanel createFeaturePanel(String btnText, String imageName, ActionListener actionListener)
    {
        JPanel viewPromoterPanel = new JPanel();
        try
        {
            BufferedImage image = ImageIO.read(new File("./Assets/"+imageName+".png"));
            Image scaledImage = image.getScaledInstance(156,144,Image.SCALE_SMOOTH);
            JLabel textImage = new JLabel(new ImageIcon(scaledImage));
            viewPromoterPanel.add(textImage);
            JButton button = new JButton(btnText);
            button.addActionListener(actionListener);
            button.setBounds(500,500,100,100);
            viewPromoterPanel.add(button);

            GridLayout gridLayout = new GridLayout();
            gridLayout.setRows(2);
            gridLayout.setColumns(2);

            viewPromoterPanel.setLayout(gridLayout);
            viewPromoterPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            viewPromoterPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);
            viewPromoterPanel.setBorder(new EmptyBorder(50,50,50,50));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return viewPromoterPanel;
    }
}
