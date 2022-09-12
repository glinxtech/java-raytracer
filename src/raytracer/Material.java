package raytracer;

public class Material
{
    public static final Material redPlastic = new Material(new Colour(1, 0, 0), new Colour(1, 1, 1), 20);
    public static final Material greenPlastic = new Material(new Colour(0, 1, 0), new Colour(1, 1, 1), 20);
    public static final Material bluePlastic = new Material(new Colour(0, 0, 1), new Colour(1, 1, 1), 20);
    public static final Material whitePlastic = new Material(new Colour(1, 1, 1), new Colour(1, 1, 1), 20);

    private Colour diffuse;
    private Colour specular;
    private double shine;

    public Material(Colour diffuse, Colour specular, double power)
    {
        this.diffuse = diffuse;
        this.specular = specular;
        this.shine = power;
    }

    public Colour getDiffuse()
    {
        return this.diffuse;
    }

    public Colour getSpecular()
    {
        return this.specular;
    }

    public double getShine()
    {
        return this.shine;
    }
}
