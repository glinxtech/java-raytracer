package raytracer.render.scene;

import raytracer.render.Point;
import raytracer.render.Ray;
import raytracer.render.Vector;

public class Camera
{
    private Point eye;
    private Vector viewDir;
    private double fov;
    private int width;
    private int height;
    private double aspectRatio;

    private Vector side;
    private Vector up;

    private double[][] camToWorld = new double[4][4];

    public Camera(Point eye, Vector viewDir, double fov, int width, int height)
    {
        this.eye = eye;
        this.viewDir = viewDir;
        this.width = width;
        this.height = height;
        this.fov = fov;
        this.aspectRatio = (double)width / height;

        setCTWMatrix();
    }

    public Camera(Point eye, int width, int height)
    {
        this(eye, new Vector(0, 0, 1), 30, width, height);
    }

    public Camera(int width, int height)
    {
        this(new Point(0, 0, 0), new Vector(0, 0, 1), 30, width, height);
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public Ray getRay(double x, double y)
    {
        // Vector from the eye (0,0,0) to the pixel in camera space
        Vector camToPixel = new Vector(getPixel(x, y));
        camToPixel.unitVector();

        // Camera to World translation to retrieve the X, Y, Z of the vector in absolute co-ordinate values
        double dirX = camToPixel.getX() * side.getX() + camToPixel.getY() * side.getY() + camToPixel.getZ()* side.getZ();
        double dirY = camToPixel.getX() * up.getX() + camToPixel.getY() * up.getY() + camToPixel.getZ()* up.getZ();
        double dirZ = camToPixel.getX() * viewDir.getX() + camToPixel.getY() * viewDir.getY() + camToPixel.getZ()* viewDir.getZ();

        // Create a new vector with these values that will be the direction of our ray
        Vector rayDir = new Vector(dirX, dirY, dirZ);
        // Normalize vector
        rayDir.unitVector();

        // Return ray with the origin and direction in absolute co-ords
        return new Ray(eye, rayDir);
    }

    private Point getPixel(double x, double y)
    {
        // Shift pixel by 0.5 to let it pass through the middle of a square pixel, then normalize
        double normx = (x + 0.5) / width;
        double normy = (y + 0.5) / height;

        // Map pixels to view plane position [-1, 1]
        double planex = (2 * normx - 1);
        double planey = 1 - 2 * normy;

        // Adjust for fov
        double fovScale = Math.tan(fov / 2 * Math.PI / 180);
        double pixelx = planex * fovScale * aspectRatio;
        double pixely = planey * fovScale;

        // Return co-ordinate of the pixel in cam co-ords with the viewplane x/y and sitting at 1 unit away from the camera eye
        return new Point(pixelx, pixely, 1);
    }

    /**
     * Translation matrix for camera space to absolute
     */
    private void setCTWMatrix()
    {
        viewDir.unitVector();

        // Calculate the vector to the right of the camera, perpendicular to the viewdirection
        side = viewDir.crossProduct(new Vector(0, 1, 0));
        side.unitVector();

        // Calculates the vector upwards of the camera, perpendicular to the plane given by the view direction and side vectors
        up = side.crossProduct(viewDir);
        up.unitVector();

        /* Sadly no reason to have this as we don't have a matrix class for such math
        camToWorld[0][0] = side.getX();
        camToWorld[1][0] = side.getY();
        camToWorld[2][0] = side.getZ();
        camToWorld[0][1] = up.getX();
        camToWorld[1][1] = up.getY();
        camToWorld[2][1] = up.getZ();
        camToWorld[0][2] = viewDir.getX();
        camToWorld[1][2] = viewDir.getY();
        camToWorld[2][2] = viewDir.getZ();

        camToWorld[0][3] = eye.getX();
        camToWorld[1][3] = eye.getY();
        camToWorld[2][3] = eye.getZ();

        camToWorld[3][0] = 0;
        camToWorld[3][0] = 0;
        camToWorld[3][0] = 0;
        camToWorld[3][3] = 1;
        */

    }

}
