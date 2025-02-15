package org.neomin.perspective.viewer.graphics;

import com.google.gson.Gson;
import org.neomin.perspective.viewer.objects.Geometry;

import javax.swing.*;

public class PerspectiveFrame
extends JFrame {

    public PerspectiveFrame(String text) {
        setTitle("Perspective View - Viewer");
        setSize(800, 600);
        setVisible(true);

        final Gson gson = new Gson();
        final Geometry geometry = gson.fromJson(text, Geometry.class);

        add(new PerspectivePanel(geometry));

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
