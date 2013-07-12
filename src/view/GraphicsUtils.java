package view;

import state.Agent;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GraphicsUtils
{


    public static void drawLineAtAngle(Graphics2D gr, double x, double y, double length, double angle)
    {
        final double px =  StrictMath.cos(angle) * length;
        final double py = -StrictMath.sin(angle) * length;

        final int xx = (int)x;
        final int yy = (int)y;

        gr.drawOval(xx - 1, yy - 1, 3, 3);
        gr.drawLine(xx, yy, xx + (int)px, yy + (int)py);
    }

    public static void drawAgent(Graphics2D gr, Agent agent)
    {
        final double ax = agent.position.x;
        final double ay = agent.position.y;

        final double hsize = agent.size / 2;
        final double svsize = (agent.sheepVision-agent.size)/2;
        final double wvsize = (agent.wolfVision-agent.size)/2;

//        gr.fillOval((int)(ax - hsize), (int)(ay - hsize), (int)size, (int)size);
        drawLineAtAngle(gr, ax, ay, agent.size - 2, agent.direction);
        gr.drawOval((int)(ax - hsize), (int)(ay - hsize), (int)agent.size, (int)agent.size);
        /*switch (agent.type){
        case 0: gr.drawOval((int)(ax-hsize)-(int)svsize,(int)(ay-hsize)-(int)svsize ,agent.sheepVision, agent.sheepVision);
            break;
        case 1:gr.drawOval((int)(ax-hsize)-(int)wvsize,(int)(ay-hsize)-(int)wvsize,agent.wolfVision,agent.wolfVision);
            break;
        }       */



    }
}
