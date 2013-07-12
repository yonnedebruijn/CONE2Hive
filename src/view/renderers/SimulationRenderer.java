package view.renderers;

import simulatie.Simulator;

import java.awt.*;

public class SimulationRenderer implements Renderer
{
    final Simulator sim;


    public SimulationRenderer(Simulator sim)
    {
        this.sim = sim;
    }

    public void render(Graphics2D gr)
    {
        gr.setColor(Color.white);
        gr.drawString(Integer.toString(sim.world.iteration) + " - " + sim.world.lifetime, 100, 60);
        gr.drawString("Iterations", 100, 30);
        gr.drawString(Integer.toString(sim.world.trials) + " - " + sim.world.nTrials, 200,60);
        gr.drawString("Trials", 200, 30);
        gr.drawString(Integer.toString(sim.world.currentGen) + " - " + sim.world.generations, 300,60);
        gr.drawString("Generations", 300, 30);


    }
}
