package view;

import state.World;
import view.renderers.CollisionRenderer;
import view.renderers.Renderer;
import view.renderers.SimulationRenderer;
import view.renderers.WorldRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class RendererStack extends JPanel
{
    final List<Renderer> renderers;

    public RendererStack(List<Renderer> renderers)
    {
        this.renderers = renderers;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        final Graphics2D gr = (Graphics2D)g;
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for(Renderer r : renderers)
            r.render(gr);
    }
}
