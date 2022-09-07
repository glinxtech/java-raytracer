package raytracer;

public class Colour
{
    private double red;
    private double green;
    private double blue;

    public Colour(double red, double green, double blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public double getRed()
    {
        return red;
    }

    public double getGreen()
    {
        return green;
    }

    public double getBlue()
    {
        return blue;
    }

    public Colour multiply(double multiplier)
    {
        return new Colour(multiplier * this.red, multiplier * this.green, multiplier * this.blue);
    }

    public static Colour multiply(Colour c1, Colour c2)
    {
        return new Colour(c1.getRed() * c2.getRed(), c1.getGreen() * c2.getGreen(), c1.getBlue() * c2.getBlue());
    }
}
