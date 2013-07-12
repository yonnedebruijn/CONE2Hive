package state;

import java.util.Random;

public class Coordinate
{
    public double x;
    public double y;

    /** Default constructor sets {@code x = 0, y = 0} */
    public Coordinate()
    {
        this(0, 0);
    }

    /**
     * Initialize coordinate with random values within the given dimensions
     *
     * @param random Random number generator to take values from
     * @param dimensions
     */
    public Coordinate(Random random, Dimensions dimensions)
    {
        setRandomCoordinate(random, dimensions);
    }

    public Coordinate(double initial_x, double initial_y)
    {
        this.x = initial_x;
        this.y = initial_y;
    }

    public void setRandomCoordinate(Random random, Dimensions dimensions)
    {
        x = random.nextDouble() * dimensions.width;
        y = random.nextDouble() * dimensions.height;
    }

    public boolean within(final Coordinate lfup, final Coordinate rgdn) {
        return x > lfup.x && x < rgdn.x && y > lfup.y && y < rgdn.y;
    }
    public Coordinate add(Coordinate c){
         return new Coordinate(x + c.x, y+c.y);
    }

}
