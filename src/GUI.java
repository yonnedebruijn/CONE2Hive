import state.Dimensions;
import view.RendererStack;
import view.renderers.Renderer;

import javax.swing.*;
import java.util.List;

// gui window
public class GUI extends JFrame
{

    // de viewer in het window (stack van renderers)
    final RendererStack viewer;

    public GUI(Dimensions dimensions, List<Renderer> renderers)
    {
        super("CONE2Hive");

        viewer = new RendererStack(renderers);

        setSize((int)dimensions.width + 32, (int)dimensions.height + 32);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setContentPane(viewer);

        setVisible(true);
    }
}
