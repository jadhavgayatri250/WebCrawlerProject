package com.crawler.ui;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        GradientPaint gp = new GradientPaint(
                0, 0, new Color(30,60,130),
                getWidth(), getHeight(),
                new Color(80,190,255));

        g2.setPaint(gp);
        g2.fillRect(0,0,getWidth(),getHeight());
    }
}