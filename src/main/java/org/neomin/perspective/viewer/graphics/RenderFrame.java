package org.neomin.perspective.viewer.graphics;

import com.google.gson.Gson;
import org.neomin.perspective.viewer.objects.Geometry;

import javax.swing.*;

public class RenderFrame
extends JFrame {

    public RenderFrame(String text) {
        setTitle("JG-Render - View");
        setSize(800, 600);
        setVisible(true);

        final Gson gson = new Gson();
        final Geometry geometry = gson.fromJson(text, Geometry.class);

        final RenderPanel render = new RenderPanel(geometry);
        add(render);
        addKeyListener(render);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
