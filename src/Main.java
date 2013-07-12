import cone.Parameters;
import simulatie.Simulator;
import state.Dimensions;
import state.World;
import view.renderers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main implements Runnable
{

    public static void main(String[] args)
    {
        new Main();
    }


    // random number seed
    final long seed = 1234568;

    final double agentSize = 24;

    final Random random = new Random(seed);

    final Dimensions d = new Dimensions(1000, 1000);
    final Dimensions neuralD = new Dimensions(400,400);

    final World world = new World(random, d, agentSize);
    final Simulator sim = new Simulator(world);

    final List<Renderer> renderers = new ArrayList<Renderer>();

    {
        renderers.add(new WorldRenderer(world));
        renderers.add(new CollisionRenderer(world));
        renderers.add(new SimulationRenderer(sim));
    }


    final GUI gui = new GUI(world.dimensions, renderers);

    {
        new Thread(this).start();
        sim.start();

    }

    @Override
    public void run()
    {

        while(true)
        {
            try
            {
                // causes a refresh
                gui.viewer.repaint();

                // geef CPU wat tijd om te repainten
                Thread.sleep(10);




            }
            catch (InterruptedException e)
            {
                return;
            }
        }
    }
}
