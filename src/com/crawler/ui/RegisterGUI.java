package com.crawler.ui;

import com.crawler.dao.UserDAO;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
public class RegisterGUI extends JFrame {

    JPanel background;
    JPanel card;

    JLabel title;

    JTextField usernameField;
    JPasswordField passwordField;

    JButton registerButton;
    JButton backButton;

    public RegisterGUI() {

        setTitle("Register");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        background = new GradientPanel();
        background.setLayout(null);
        add(background);

        card = new RoundedPanel(40);
        card.setLayout(null);
        card.setBounds(340, 70, 520, 600);
        background.add(card);

        title = new JLabel("Register", SwingConstants.CENTER);
        title.setBounds(0, 30, 520, 50);
        title.setFont(new Font("Segoe UI", Font.BOLD, 40));
        card.add(title);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(80,145,150,30);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        card.add(userLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        usernameField.setBounds(80, 180, 360, 50);
        usernameField.setBackground(Color.WHITE);
        card.add(usernameField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(80,255,150,30);
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        card.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(80, 290, 360, 50);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        passwordField.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
        passwordField.setBackground(Color.WHITE);
        card.add(passwordField);

        registerButton = new RoundedButton("Register", new Color(255,140,0));
        registerButton.setBounds(100, 385, 320, 55);
        registerButton.setBackground(new Color(255, 140, 0));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.add(registerButton);

        backButton = new RoundedButton("← Back", new Color(90,105,120));
        backButton.setBounds(100, 460, 320, 55);
        backButton.setBackground(new Color(90, 105, 120));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        card.add(backButton);

        registerButton.addActionListener(e -> {

            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please enter Username and Password");

                return;
            }

            UserDAO dao = new UserDAO();

            boolean result = dao.registerUser(username, password);

            if (result) {

                JOptionPane.showMessageDialog(this,
                        "Registration Successful");

                dispose();
                new LoginGUI();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Registration Failed");

            }

        });

        backButton.addActionListener(e -> {

            dispose();
            new ChooseOptionGUI();

        });

        setVisible(true);

    }

}