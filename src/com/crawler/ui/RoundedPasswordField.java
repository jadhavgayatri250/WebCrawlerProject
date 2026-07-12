package com.crawler.ui;

import javax.swing.*;
import java.awt.*;

public class RoundedPasswordField extends JPasswordField {

    public RoundedPasswordField(int columns) {

        super(columns);

        setOpaque(false);
        setFont(new Font("Segoe UI", Font.PLAIN, 18));
        setForeground(Color.BLACK);
        setCaretColor(Color.BLACK);

        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(210,210,210));
        g2.setStroke(new BasicStroke(1.5f));

        g2.drawRoundRect(0,0,getWidth()-1,getHeight()-1,30,30);

        g2.dispose();
    }
}