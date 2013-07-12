package state;

import cone.Controller;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import cone.Genotype;

public class Wolf extends Agent
{
    public double speed = 30;
    public Controller controller;
    public double[] motors;
    public double[] sensors;
    public double distance;

    Shape vision = new Ellipse2D.Float((int)(position.x-(size/2))-(int)((wolfVision-size)/2),(int)(position.y-(size/2))-(int)((wolfVision-size)/2),wolfVision,wolfVision);
    public Wolf(Coordinate initialPosition, double direction, int t, World world)
    {
        super(initialPosition, direction,1,world);
        controller = new Controller();
        motors = new double[controller.getNoutputs()];
            for (int i=0; i<motors.length; i++)
                motors[i]=0;
        sensors = new double[controller.getNinputs()];
        distance = 0;
    }

    public boolean detected(){
        return (vision.contains(world.sheep.position.x,world.sheep.position.y));
    }
    public void action(){
        getSensors();
        getMotors();
        Coordinate dir = new Coordinate(motors[0]*speed,motors[1]*speed);
        move(dir);
    }
    public void getSensors(){
        // Fills up the sensor-array with the relative distance to the sheep, scanned in 4 directions (4 sensors)

        sensors[0] = distance(detectSheep(1));
        sensors[1] = distance(detectSheep(2));
        sensors[2] = distance(detectSheep(3));
        sensors[3] = distance(detectSheep(3));
        // Fills up the sensor-array with the relative distance to the other agents, scanned in 4 directions (4 sensors)

        // Fills up the sensor-array with the relative distance to the walls, scanned in 4 directions (4 sensors)

        // Recurrent inputs from previous motor outputs



    }
    public void getMotors(){
        motors = controller.getOutputs(sensors);
        System.out.println(motors[0]);
        System.out.println(motors[1]);
    }

    public double distance(Agent a){
        if (a==null) return 0d;
         return Math.sqrt((Math.pow((a.position.y - this.position.y),2)+(Math.pow((a.position.y - this.position.y),2))));
    }

    public double distance(Coordinate c){
        return Math.sqrt((Math.pow((c.y - this.position.y),2)+(Math.pow((c.y - this.position.y),2))));
    }

    private Agent detectSheep(int quadrant){
        if(quadrant == 1){
                if(this.position.x <= world.sheep.position.x && this.position.y < world.sheep.position.y) return world.sheep;
            }

        if(quadrant == 2){

                if(this.position.x < world.sheep.position.x && this.position.y >= world.sheep.position.y) return world.sheep;
            }


        if(quadrant == 3){
                if(this.position.x >= world.sheep.position.x && this.position.y > world.sheep.position.y) return world.sheep;
            }

        if(quadrant == 4){
                if(this.position.x > world.sheep.position.x && this.position.y <= world.sheep.position.y) return world.sheep;
            }

        return null;
    }


    public void controllerUpdate(Genotype genotype){
        controller.update(genotype);
        System.out.println("controllerupdate completed");
    }




}
