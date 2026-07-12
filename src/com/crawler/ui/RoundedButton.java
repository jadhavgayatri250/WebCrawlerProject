package com.crawler.ui;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    private Color color;

    public RoundedButton(String text, Color color) {
        super(text);
        this.color = color;

        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 22));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(color);

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 45, 45);

        super.paintComponent(g2);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
    }
}
