package com.crawler.ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class ChooseOptionGUI extends JFrame {

    public ChooseOptionGUI() {

        setTitle("Concurrent Web Crawler");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel background = new JPanel(null);
        background.setBackground(new Color(242, 239, 235));

        JPanel card = new JPanel(null);
        card.setBounds(220, 55, 460, 450);
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Choose Option");
        title.setBounds(80, 50, 320, 60);
        title.setFont(new Font("Segoe UI", Font.BOLD, 38));
        title.setForeground(new Color(35, 35, 35));
        card.add(title);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(110, 170, 240, 55);
        registerBtn.setBackground(new Color(255, 120, 0));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        registerBtn.setBorderPainted(false);
        registerBtn.setFocusPainted(false);
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(110, 260, 240, 55);
        loginBtn.setBackground(new Color(73, 96, 115));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        loginBtn.setBorderPainted(false);
        loginBtn.setFocusPainted(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        card.add(registerBtn);
        card.add(loginBtn);

        background.add(card);
        add(background);

        // Register Button
        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterGUI();
        });

        // Login Button
        loginBtn.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });

        setVisible(true);
    }
}
