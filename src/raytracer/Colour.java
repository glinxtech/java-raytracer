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

    public void add(Colour colour)
    {
        this.red = this.red + colour.getRed();
        this.green = this.green + colour.getGreen();
        this.blue = this.blue + colour.getBlue();
    }

    public int toPixel()
    {
        int r = (int) Math.min(this.red * 255, 255);
        int g = (int) Math.min(this.green * 255, 255);
        int b = (int) Math.min(this.blue * 255, 255);

        return (r << 16) | (g << 8) | b;
    }

    public Colour multiply(double multiplier)
    {
        return new Colour(multiplier * this.red, multiplier * this.green, multiplier * this.blue);
    }

    public Colour multiply(Colour colour)
    {
        return new Colour(this.red * colour.getRed(), this.green * colour.getGreen(), this.blue * colour.getBlue());
    }

    public static Colour multiply(double multiplier, Colour c1, Colour... colours)
    {
        Colour newColour = c1;

        for (Colour c2 : colours)
        {
            newColour = newColour.multiply(c2);
        }

        return newColour.multiply(multiplier);
    }

    public static Colour multiply(Colour c1, Colour... colours)
    {
        return Colour.multiply(1, c1, colours);
    }

    public static Colour multiply(Colour colour, double multiplier)
    {
        return colour.multiply(multiplier);
    }

}
