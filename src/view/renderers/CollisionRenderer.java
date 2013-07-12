package view.renderers;

import state.Sheep;
import state.Wolf;
import state.World;
import view.ColorTheme;
import view.GraphicsUtils;

import java.awt.*;


public class CollisionRenderer implements Renderer
{
    World world;



    public CollisionRenderer(World world)
    {
        this.world = world;
    }

    public void render(Graphics2D gr)
    {
        gr.setBackground(ColorTheme.wolfBg);
        gr.setColor(ColorTheme.wolfFg);

        for(Wolf w : world.wolfs)
            GraphicsUtils.drawAgent(gr, w);

        gr.setBackground(ColorTheme.sheepBg);
        gr.setColor(ColorTheme.sheepFg);

            GraphicsUtils.drawAgent(gr, world.sheep);

    }
}
