package state;

import java.util.List;

public class Agent
{
    public Coordinate position;
    public double direction = 0; // altijd tussen 0 en 2 * Math.PI
    public int type; //0 is sheep, 1 is wolf
    public int sheepVision = 100;
    public int wolfVision = 500;
    public int size = 24;
    final World world;
    private boolean alive = false;

    public Agent(Coordinate initialPosition, double direction, int t, World world)
    {
        this.world = world;
        this.position = initialPosition;
        this.direction = direction;
        this.type = t;
    }

    public boolean alive(){
           return alive;
    }

    public boolean kill(){
        return alive = false;
    }
    public void init(){
        alive = true;
    }

    public void move(Coordinate dir){
        Coordinate tempCord = position.add(dir);
        if(!tempCord.within(world.dimensions.lfup,world.dimensions.rgdn)){

            if(tempCord.x >= world.dimensions.rgdn.x){
                dir = new Coordinate(-dir.x,0);
                tempCord = position.add(dir);

            } if(tempCord.y <= world.dimensions.lfup.y){
                dir = new Coordinate(0,-dir.y);
                tempCord = position.add(dir);

            } if(tempCord.x <= world.dimensions.lfup.x){
                dir = new Coordinate(-dir.x,0);
                tempCord = position.add(dir);
            } if(tempCord.y >= world.dimensions.rgdn.y){
                dir = new Coordinate(0,-dir.y);
                tempCord = position.add(dir);
            }

        }
        position = tempCord;
    }

}


