package state;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Sheep extends Agent
{
       public double speed = 5;
    public boolean captured;
    Shape vision = new Ellipse2D.Float((int)(position.x-(size/2))-(int)((sheepVision-size)/2),(int)(position.y-(size/2))-(int)((sheepVision-size)/2),sheepVision,sheepVision);
    public Sheep(Coordinate initialPosition, double direction, int t, World world)
    {
        super(initialPosition, direction,0,world);
        captured = false;
    }

    public boolean captured(){
        for(Wolf w : world.wolfs)
           if(vision.contains(w.position.x, w.position.y)){
               captured = true;
                System.out.println("captured");
           } else captured = false;

        return captured;

    }


        }

