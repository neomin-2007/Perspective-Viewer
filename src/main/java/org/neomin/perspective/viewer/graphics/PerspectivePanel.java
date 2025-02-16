package org.neomin.perspective.viewer.graphics;

import lombok.AllArgsConstructor;
import org.neomin.perspective.viewer.objects.Geometry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@AllArgsConstructor
public class PerspectivePanel
extends JPanel implements KeyListener {

    private final Geometry geometry;
    private int x, y, rotX, rotY, dist;

    public PerspectivePanel(Geometry geometry) {
        this.geometry = geometry;

        final Timer timer = new Timer(1000/60, e -> {
           repaint();
        });

        timer.start();
    }

    public int[] project(int x, int y, int z) {

        final int scale = 500 - dist;
        final int distance = 500;

        int x2D = (x * scale) / (z + distance) + (getWidth()/2);
        int y2D = (y * scale) / (z + distance) + (getWidth()/2);

        return new int[]{x2D, y2D};
    }

    private int[] rotateY(int x, int y, int z, int angleY) {
        double angleInRadians = Math.toRadians(angleY);
        double cos = Math.cos(angleInRadians);
        double sin = Math.sin(angleInRadians);

        int xRotated = (int) (x * cos + z * sin);
        int yRotated = y;
        int zRotated = (int) (-x * sin + z * cos);

        return new int[]{xRotated, yRotated, zRotated};
    }

    private int[] rotateX(int x, int y, int z, int angleX) {
        double angleInRadians = Math.toRadians(angleX);  // Converter para radianos
        double cos = Math.cos(angleInRadians);
        double sin = Math.sin(angleInRadians);

        int yRotated = (int) (y * cos - z * sin);
        int zRotated = (int) (y * sin + z * cos);

        return new int[]{x, yRotated, zRotated};
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        final Graphics2D g2D = (Graphics2D) graphics;

        g2D.setFont(new Font("BOLD", Font.BOLD, 18));

        g2D.drawString("(↑) To up", 675, 500);
        g2D.drawString("(↓) To Down", 675, 525);
        g2D.drawString("(→) To Right", 675, 550);
        g2D.drawString("(←) To Left", 675, 575);

        g2D.drawString("(O - ↺) Rotate X", 520, 500);
        g2D.drawString("(K - ↻) Rotate Y", 520, 525);

        g2D.drawString("(U - ⨁) Zoom out", 520, 550);
        g2D.drawString("(H - ⨂) Zoom in", 520, 575);

        for (int[] edge : geometry.getEdges()) {

            int[] rotX1 = rotateX(geometry.getVertex()[edge[0]][0], geometry.getVertex()[edge[0]][1], geometry.getVertex()[edge[0]][2], rotX);
            int[] rotX2 = rotateX(geometry.getVertex()[edge[1]][0], geometry.getVertex()[edge[1]][1], geometry.getVertex()[edge[1]][2], rotX);

            int[] rotY1 = rotateY(rotX1[0], rotX1[1], rotX1[2], rotY);
            int[] rotY2 = rotateY(rotX2[0], rotX2[1], rotX2[2], rotY);

            int[] point1 = project(rotY1[0], rotY1[1], rotY1[2]);
            int[] point2 = project(rotY2[0], rotY2[1], rotY2[2]);

            g2D.drawLine(point1[0] + x, point1[1] + y, point2[0] + x, point2[1] + y);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                y--;
                break;
            case KeyEvent.VK_DOWN:
                y++;
                break;
            case KeyEvent.VK_RIGHT:
                x++;
                break;
            case KeyEvent.VK_LEFT:
                x--;
                break;
            case KeyEvent.VK_O:
                rotX++;
                break;
            case KeyEvent.VK_P:
                rotX--;
                break;
            case KeyEvent.VK_K:
                rotY++;
                break;
            case KeyEvent.VK_L:
                rotY--;
                break;
            case KeyEvent.VK_U:
                dist++;
                break;
            case KeyEvent.VK_H:
                dist--;
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
