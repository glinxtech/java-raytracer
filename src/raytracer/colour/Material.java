package raytracer.colour;

public class Material
{
    public static final Material redPlastic = new Material(new Colour(1, 0, 0), new Colour(1, 1, 1), 40, 0.4);
    public static final Material greenPlastic = new Material(new Colour(0, 1, 0), new Colour(1, 1, 1), 40, 0.4);
    public static final Material bluePlastic = new Material(new Colour(0, 0, 1), new Colour(1, 1, 1), 20, 0.4);
    public static final Material whitePlastic = new Material(new Colour(1, 1, 1), new Colour(1, 1, 1), 20, 0.4);

    private Colour diffuse;
    private Colour specular;
    private double shine;
    private double reflectivity;

    public Material(Colour diffuse, Colour specular, double shine, double reflectivity)
    {
        this.diffuse = diffuse;
        this.specular = specular;
        this.shine = shine;
        this.reflectivity = reflectivity;
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

    public double getReflectivity()
    {
        return reflectivity;
    }
}
