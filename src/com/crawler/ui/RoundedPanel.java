package com.crawler.ui;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {

    private int cornerRadius;

    public RoundedPanel(int radius) {
        super();
        cornerRadius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(0,0,0,40));
        g2.fillRoundRect(8,8,
                getWidth()-8,
                getHeight()-8,
                cornerRadius,
                cornerRadius);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(
                0,
                0,
                getWidth()-10,
                getHeight()-10,
                cornerRadius,
                cornerRadius);

        g2.dispose();

        super.paintComponent(g);
    }
}