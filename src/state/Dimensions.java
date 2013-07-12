package state;

public class Dimensions
{
    final public double width;
    final public double height;
    public Coordinate lfup = new Coordinate();
    public Coordinate rgdn = new Coordinate();





    public Dimensions(double width, double height)
    {
        this.width = width;
        this.height = height;
        lfup = new Coordinate(0,0);
        rgdn = new Coordinate(width,height);

    }
}
