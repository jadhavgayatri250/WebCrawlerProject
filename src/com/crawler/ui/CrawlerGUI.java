package com.crawler.ui;

import com.crawler.crawler.ConcurrentCrawler;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrawlerGUI extends JFrame {

    private JTextField urlField;
    private JTextField depthField;
    private JTextField workerField;

    private JTextArea outputArea;

    private JButton crawlButton;
    private JButton clearButton;
    private JButton resetButton;
    private JButton logoutButton;

    private JLabel totalUrlsLabel;
    private JLabel statusLabel;
    private JLabel timeLabel;

    private JProgressBar progressBar;

    private long startTime;
    private long endTime;

    public CrawlerGUI() {

        setTitle("Concurrent Web Crawler");
        setSize(1250,760);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        GradientPanel background = new GradientPanel();
        background.setLayout(new BorderLayout());
        add(background);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(25,118,210));
        header.setPreferredSize(new Dimension(1200,120));
        header.setBorder(new EmptyBorder(20,30,20,30));

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Concurrent Web Crawler");
        title.setFont(new Font("Segoe UI",Font.BOLD,34));
        title.setForeground(Color.WHITE);

        JLabel subTitle = new JLabel("Fast • Smart • Multi Threaded URL Crawling");
        subTitle.setFont(new Font("Segoe UI",Font.PLAIN,17));
        subTitle.setForeground(Color.WHITE);

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(8));
        titlePanel.add(subTitle);

        logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(110,40));

        logoutButton.setBackground(new Color(90,105,120));

        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD,15));
        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        logoutButton.setFocusPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        header.add(titlePanel,BorderLayout.WEST);
        header.add(logoutButton,BorderLayout.EAST);

        background.add(header,BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout(20,20));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(20,20,20,20));

        JPanel topPanel = new JPanel(new BorderLayout(20,0));
        topPanel.setOpaque(false);

        JPanel settingsPanel = new JPanel(new GridBagLayout());
        settingsPanel.setBackground(Color.WHITE);

        settingsPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220,220,220),1,true),
                new EmptyBorder(15,15,15,15)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12,12,12,12);
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Segoe UI",Font.BOLD,17);

        JLabel urlLabel = new JLabel("Website URL");
        JLabel depthLabel = new JLabel("Crawl Depth");
        JLabel workerLabel = new JLabel("Worker Threads");

        urlLabel.setFont(labelFont);
        depthLabel.setFont(labelFont);
        workerLabel.setFont(labelFont);

        urlField = new JTextField(30);
        depthField = new JTextField(30);
        workerField = new JTextField(30);

        Font tfFont = new Font("Segoe UI",Font.PLAIN,15);

        urlField.setFont(tfFont);
        depthField.setFont(tfFont);
        workerField.setFont(tfFont);

        Border fieldBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180,180,180)),
                BorderFactory.createEmptyBorder(8,10,8,10));

        urlField.setBorder(fieldBorder);
        depthField.setBorder(fieldBorder);
        workerField.setBorder(fieldBorder);

        gbc.gridx=0;
        gbc.gridy=0;
        settingsPanel.add(urlLabel,gbc);

        gbc.gridx=1;
        settingsPanel.add(urlField,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        settingsPanel.add(depthLabel,gbc);

        gbc.gridx=1;
        settingsPanel.add(depthField,gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        settingsPanel.add(workerLabel,gbc);

        gbc.gridx=1;
        settingsPanel.add(workerField,gbc);

        crawlButton = new JButton("Start Crawl");
        clearButton = new JButton("Clear");
        resetButton = new JButton("Reset");

        Font btnFont = new Font("Segoe UI",Font.BOLD,16);

        Dimension btnSize = new Dimension(130,42);

        crawlButton.setPreferredSize(btnSize);
        clearButton.setPreferredSize(btnSize);
        resetButton.setPreferredSize(btnSize);

        crawlButton.setFont(btnFont);
        clearButton.setFont(btnFont);
        resetButton.setFont(btnFont);

        crawlButton.setBackground(new Color(46,204,113));
        clearButton.setBackground(new Color(52,73,94));
        resetButton.setBackground(new Color(243,156,18));

        crawlButton.setForeground(Color.WHITE);
        clearButton.setForeground(Color.WHITE);
        resetButton.setForeground(Color.WHITE);

        crawlButton.setFocusPainted(false);
        clearButton.setFocusPainted(false);
        resetButton.setFocusPainted(false);

        crawlButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
        buttonPanel.setOpaque(false);

        buttonPanel.add(crawlButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(resetButton);

        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=2;

        settingsPanel.add(buttonPanel,gbc);

        topPanel.add(settingsPanel,BorderLayout.CENTER);

        JPanel statusPanel = new JPanel();
        statusPanel.setPreferredSize(new Dimension(260,260));
        statusPanel.setBackground(new Color(248,249,250));
        statusPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220,220,220),1,true),
                new EmptyBorder(20,20,20,20)));

        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));

        JLabel infoTitle = new JLabel("Crawler Status");
        infoTitle.setFont(new Font("Segoe UI", Font.BOLD,20));
        infoTitle.setForeground(new Color(25,118,210));
        infoTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        totalUrlsLabel = new JLabel("Total URLs : 0");
        totalUrlsLabel.setFont(new Font("Segoe UI",Font.BOLD,17));
        totalUrlsLabel.setForeground(new Color(46,204,113));
        totalUrlsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        statusLabel = new JLabel("Status : Ready");
        statusLabel.setFont(new Font("Segoe UI",Font.BOLD,17));
        statusLabel.setForeground(new Color(41,128,185));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        timeLabel = new JLabel("<html>Start : --<br>End : --<br>Total : 0 sec</html>");
        timeLabel.setFont(new Font("Segoe UI",Font.BOLD,16));
        timeLabel.setForeground(new Color(52,73,94));
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        statusPanel.add(infoTitle);
        statusPanel.add(Box.createVerticalStrut(25));
        statusPanel.add(totalUrlsLabel);
        statusPanel.add(Box.createVerticalStrut(20));
        statusPanel.add(statusLabel);
        statusPanel.add(Box.createVerticalStrut(20));
        statusPanel.add(timeLabel);

        topPanel.add(statusPanel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setMargin(new Insets(10,10,10,10));

        outputArea.setFont(new Font("Consolas",Font.PLAIN,15));
        outputArea.setBackground(Color.WHITE);
        outputArea.setForeground(new Color(40,40,40));

        JScrollPane scrollPane = new JScrollPane(outputArea);

        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(210,210,210),1),
                "Crawler Output"));

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        progressBar = new JProgressBar();

        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(46,204,113));
        progressBar.setFont(new Font("Segoe UI",Font.BOLD,14));
        progressBar.setPreferredSize(new Dimension(1000,25));

        mainPanel.add(progressBar, BorderLayout.SOUTH);

        background.add(mainPanel, BorderLayout.CENTER);

        crawlButton.addActionListener(e -> {
            String url = urlField.getText().trim();

            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://" + url;
            }

            if (url.isEmpty() ||
                    depthField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please enter URL and Crawl Depth.");
                return;
            }

            int depth;

            try {
                depth = Integer.parseInt(depthField.getText().trim());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid Crawl Depth");
                return;
            }

            outputArea.setText("");

            startTime = System.currentTimeMillis();

            progressBar.setValue(5);

            statusLabel.setText("Status : Crawling...");
            totalUrlsLabel.setText("Total URLs : 0");

            SimpleDateFormat sdf =
                    new SimpleDateFormat("HH:mm:ss");

            timeLabel.setText(
                    "<html>Start : "
                            + sdf.format(new Date(startTime))
                            + "<br>Running...</html>");

            ConcurrentCrawler crawler =
                    new ConcurrentCrawler(
                            url,
                            depth,
                            new ConcurrentCrawler.CrawlListener() {

                                @Override
                                public void onUrlFound(String foundUrl) {

                                    SwingUtilities.invokeLater(() -> {

                                        outputArea.append(foundUrl + "\n");
                                        outputArea.setCaretPosition(
                                                outputArea.getDocument().getLength());
                                    });

                                }
                                @Override
                                public void onStatus(String status) {

                                    SwingUtilities.invokeLater(() ->

                                            statusLabel.setText("Status : " + status)

                                    );

                                }

                                @Override
                                public void onCompleted(int totalUrls) {

                                    SwingUtilities.invokeLater(() -> {

                                        endTime = System.currentTimeMillis();

                                        long totalSeconds =
                                                (endTime - startTime) / 1000;

                                        SimpleDateFormat sdf =
                                                new SimpleDateFormat("HH:mm:ss");

                                        totalUrlsLabel.setText(
                                                "Total URLs : " + totalUrls);

                                        statusLabel.setText(
                                                "Status : Completed");

                                        progressBar.setValue(100);

                                        timeLabel.setText(
                                                "<html>Start : "
                                                        + sdf.format(new Date(startTime))
                                                        + "<br>End : "
                                                        + sdf.format(new Date(endTime))
                                                        + "<br>Total : "
                                                        + totalSeconds
                                                        + " sec</html>");

                                    });

                                }

                            });

            new Thread(crawler).start();

        });

        clearButton.addActionListener(e -> {

            outputArea.setText("");

        });

        resetButton.addActionListener(e -> {

            urlField.setText("");
            depthField.setText("");
            workerField.setText("");

            outputArea.setText("");

            totalUrlsLabel.setText("Total URLs : 0");
            statusLabel.setText("Status : Ready");

            timeLabel.setText(
                    "<html>Start : --<br>End : --<br>Total : 0 sec</html>");

            progressBar.setValue(0);

        });

        logoutButton.addActionListener(e -> {

            dispose();
            new LoginGUI();

        });

        setVisible(true);

    }
        }