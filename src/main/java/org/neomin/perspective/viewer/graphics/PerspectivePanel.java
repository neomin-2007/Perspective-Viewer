package org.neomin.perspective.viewer.graphics;

import lombok.AllArgsConstructor;
import org.neomin.perspective.viewer.objects.Geometry;

import javax.swing.*;
import java.awt.*;

@AllArgsConstructor
public class PerspectivePanel
extends JPanel {

    private final Geometry geometry;

    public int[] project(int x, int y, int z) {

        final int scale = 500;
        final int distance = 500;

        int x2D = (x * scale) / (z + distance) + (getWidth()/2);
        int y2D = (y * scale) / (z + distance) + (getWidth()/2);

        return new int[]{x2D, y2D};
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        final Graphics2D g2D = (Graphics2D) graphics;

        for (int[] edge : geometry.getEdges()) {

            int[] point1 = project(geometry.getVertex()[edge[0]][0], geometry.getVertex()[edge[0]][1], geometry.getVertex()[edge[0]][2]);
            int[] point2 = project(geometry.getVertex()[edge[1]][0], geometry.getVertex()[edge[1]][1], geometry.getVertex()[edge[1]][2]);

            g2D.drawLine(point1[0], point1[1], point2[0], point2[1]);
        }
    }
}
