package view.renderers;

import state.World;
import view.ColorTheme;

import java.awt.*;

public class WorldRenderer implements Renderer
{
    final World world;



    public WorldRenderer(World world)
    {
        this.world = world;
    }

    public void render(Graphics2D gr)
    {
        final double w = world.dimensions.width;
        final double h = world.dimensions.height;

        gr.setBackground(ColorTheme.worldBg);
        gr.fillRect(0, 0, (int)w, (int)h);

        gr.setBackground(ColorTheme.worldFg);
        gr.drawRect(0, 0, (int)w, (int)h);
    }
}
