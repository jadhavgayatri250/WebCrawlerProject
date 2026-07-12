package com.crawler.ui;

import com.crawler.dao.UserDAO;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public LoginGUI() {

        setTitle("Concurrent Web Crawler");
        setSize(1200,750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GradientPanel background = new GradientPanel();
        background.setLayout(null);
        add(background);

        RoundedPanel card = new RoundedPanel(40);
        card.setLayout(null);
        card.setBounds(340,70,520,600);
        card.setBackground(Color.WHITE);
        background.add(card);

        JLabel title = new JLabel("Login",SwingConstants.CENTER);
        title.setBounds(0,30,520,50);
        title.setFont(new Font("Segoe UI",Font.BOLD,40));
        card.add(title);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(65,110,150,25);
        userLabel.setFont(new Font("Segoe UI",Font.BOLD,16));
        card.add(userLabel);

        usernameField = new RoundedTextField(20);
        usernameField.setBounds(65,140,390,50);
        usernameField.setFont(new Font("Segoe UI",Font.PLAIN,18));
        card.add(usernameField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(65,220,150,25);
        passLabel.setFont(new Font("Segoe UI",Font.BOLD,16));
        card.add(passLabel);

        passwordField = new RoundedPasswordField(20);
        passwordField.setBounds(65,250,390,50);
        passwordField.setFont(new Font("Segoe UI",Font.PLAIN,18));
        card.add(passwordField);

        // Success / Error Message
        messageLabel = new JLabel("",SwingConstants.CENTER);
        messageLabel.setBounds(65,310,390,30);
        messageLabel.setFont(new Font("Segoe UI",Font.BOLD,18));
        card.add(messageLabel);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(65,350,390,50);
        loginButton.setBackground(new Color(255,140,0));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI",Font.BOLD,22));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.add(loginButton);

        JButton backButton = new JButton("← Back");
        backButton.setBounds(65,420,390,50);
        backButton.setBackground(new Color(90,105,120));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI",Font.BOLD,22));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.add(backButton);

        // LOGIN BUTTON

        loginButton.addActionListener(e -> {

            String username = usernameField.getText().trim();
            String password = String.valueOf(passwordField.getPassword());

            if(username.isEmpty() || password.isEmpty()){

                messageLabel.setForeground(Color.RED);
                messageLabel.setText("✖ Please enter Username and Password");
                return;
            }

            UserDAO dao = new UserDAO();

            if(dao.loginUser(username,password)){

                messageLabel.setForeground(new Color(0,170,0));
                messageLabel.setText("Login Successful!");

                Timer timer = new Timer(1000,ev->{

                    dispose();
                    new CrawlerGUI();

                });

                timer.setRepeats(false);
                timer.start();

            }else{

                messageLabel.setForeground(Color.RED);
                messageLabel.setText("✖ Invalid Username or Password");

            }

        });

        // BACK BUTTON

        backButton.addActionListener(e->{

            dispose();
            new ChooseOptionGUI();

        });

        setVisible(true);
    }
}