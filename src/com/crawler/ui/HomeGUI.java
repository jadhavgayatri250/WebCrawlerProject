package com.crawler.ui;

import javax.swing.*;
import java.awt.*;

public class HomeGUI extends JFrame {

    private JButton startButton;
    private JButton logoutButton;

    public HomeGUI() {

        setTitle("Concurrent Web Crawler");
        setSize(1200,750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel background = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;

                GradientPaint gp = new GradientPaint(
                        0,0,
                        new Color(44,76,145),
                        getWidth(),
                        getHeight(),
                        new Color(96,196,235));

                g2.setPaint(gp);
                g2.fillRect(0,0,getWidth(),getHeight());

            }

        };

        background.setLayout(null);

        add(background);


        JPanel card = new JPanel();

        card.setLayout(null);
        card.setBounds(260,90,680,520);
        card.setBackground(Color.WHITE);

        background.add(card);


        JLabel title = new JLabel(
                "Concurrent Web Crawler",
                SwingConstants.CENTER);

        title.setBounds(0,40,680,45);
        title.setFont(new Font(
                "Segoe UI",
                Font.BOLD,
                36));

        title.setForeground(new Color(30,50,90));

        card.add(title);

        JLabel subTitle = new JLabel(
                "Fast • Multi Threaded • URL Analysis",
                SwingConstants.CENTER);

        subTitle.setBounds(0,90,680,30);

        subTitle.setFont(new Font(
                "Segoe UI",
                Font.PLAIN,
                18));

        subTitle.setForeground(Color.GRAY);

        card.add(subTitle);

        startButton = new JButton("START CRAWLING");

        startButton.setBounds(170,220,340,60);

        startButton.setBackground(new Color(255,120,0));
        startButton.setForeground(Color.WHITE);

        startButton.setFocusPainted(false);

        startButton.setBorderPainted(false);

        startButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22));

        card.add(startButton);

        logoutButton = new JButton("LOGOUT");

        logoutButton.setBounds(170,320,340,60);

        logoutButton.setBackground(
                new Color(90,105,120));

        logoutButton.setForeground(Color.WHITE);

        logoutButton.setFocusPainted(false);

        logoutButton.setBorderPainted(false);

        logoutButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22));

        card.add(logoutButton);

        startButton.addActionListener(e -> {

            new CrawlerGUI();

            dispose();

        });


        logoutButton.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to Logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {

                dispose();

                new LoginGUI();

            }

        });

        setVisible(true);

    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());

            } catch (Exception e) {

                e.printStackTrace();

            }

            new HomeGUI();

        });

    }

}