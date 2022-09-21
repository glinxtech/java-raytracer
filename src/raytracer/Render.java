package raytracer;

import java.awt.image.BufferedImage;

import raytracer.colour.*;
import raytracer.math.*;
import raytracer.scene.*;
import raytracer.shapes.*;
import raytracer.lights.*;

public class Render
{
    public BufferedImage draw(Scene scene, Camera camera)
    {
        // Image we're going to display in our Image Window
        BufferedImage img = new BufferedImage(camera.getWidth(), camera.getHeight(), BufferedImage.TYPE_INT_RGB);

        // Loop through all the pixels we're going to colour
        for (int y = 0; y < camera.getHeight(); y++)
        {
            for (int x = 0; x < camera.getWidth(); x++)
            {
                Colour outputColour = new Colour(0, 0, 0);

                for (double ax = x; ax < x + 1; ax += 0.5)
                {
                    for (double ay = y; ay < y + 1; ay += 0.5)
                    {
                        // Find the ray that goes through our image plane at this pixel
                        Ray viewRay = camera.getRay(ax, ay);

                        // Colour of our pixel
                        Colour colour = trace(viewRay, scene);

                        // Each sample contributes to 1/4th of the colour
                        double sampleRatio = 0.25;
                        outputColour.add(colour.multiply(sampleRatio));
                    }
                }

                // Fill in the pixel with the given colour
                img.setRGB(x, y, outputColour.toPixel());
            }
        }
        return img;
    }

    public BufferedImage draw()
    {
        return this.draw(new Scene(), new Camera(new Point(0, 0, 0), new Vector(0, 0, 1),70, 1280, 720));
    }

    private Colour trace(Ray ray, Scene scene)
    {
        // Background colour
        Colour outputColour = new Colour(0, 0, 0);

        // Render distance
        double distance = 5000.0;
        HitResult hit = null;

        // Check for intersection with every shape,
        // except if the distance is shorter than
        // the previous hit result
        for (Shape s : scene.getShapes())
        {
            HitResult h = s.hitShape(ray, distance);

            if (h != null)
            {
                hit = h;
                distance = hit.getDistance();
            }
        }

        // No shape to draw, next pixel
        if (hit == null)
            return outputColour;

        Material currentMaterial = hit.getShape().getMaterial();

        for (Light l : scene.getLights())
        {
            Ray lightRay = new Ray(
                    hit.getHitPoint(),
                    new Vector(hit.getHitPoint(), l.getPosition())
            );

            // Length of light ray projected onto the normal
            double lightProjection = Vector.dotProduct(lightRay.getDirection(), hit.getNormal());

            // If true, light is on the other side of the surface
            if (lightProjection <= 0.0)
                continue;

            // Distance light traveled
            double lightDistance = lightRay.getDirection().norm();
            lightRay.getDirection().normalize();

            boolean shadowRay = false;

             // If a shape is between this light and
             // our intersection point, this is a shadowRay.
             // Move onto the next light.

            for (Shape s : scene.getShapes())
            {
                HitResult h = s.hitShape(lightRay, lightDistance);

                if (h != null)
                {
                    shadowRay = true;
                }
            }


            // This light ray reaches our shape
            if (!shadowRay)
            {

                // Lambert/diffuse shading
                // The amount of light the surface receives is directly
                // proportional to the angle at which light hits the surface
                // Dot product gives us the cosine of this angle, range 0-1
                double lambert = Math.max(Vector.dotProduct(lightRay.getDirection(), hit.getNormal()), 0.0);

                if (lambert == 0.0)
                    continue;

                // Adjusted light intensity for distance to surface
                double d2 = lightDistance * lightDistance;
                double lightPower = l.getIntensity() / d2;

                // Total diffuse colour this light source contributed to this pixel
                Colour diffuse = Colour.multiply((lambert * lightPower), currentMaterial.getDiffuse(), l.getColour());
                outputColour.add(diffuse);

                // Blinn-Phong/Specular shading, intensity based on both
                // the angle at which we view the surface and
                // the angle the light falls on the surface

                // Halfway vector between the light ray and our view ray
                Vector halfDir = lightRay.getDirection()
                                            .subtract(ray.getDirection())
                                            .unitVector();

                // Cosine of the angle between our halfway vector and the surface normal
                // Ranges from 0-1
                double specAngle = Math.max(halfDir.dotProduct(hit.getNormal()), 0.0);

                // Amount of reflected light in our view direction
                double specPower = Math.pow(specAngle, currentMaterial.getShine());

                // Total specular colour this light source contributed to this pixel
                Colour specular = Colour.multiply((specPower * lightPower), currentMaterial.getSpecular(), l.getColour());
                outputColour.add(specular);
            }
        }
        // Primitive ambient lighting
        Colour ambientLight = new Colour(0.05, 0.05, 0.05);
        outputColour.add(Colour.multiply(currentMaterial.getDiffuse(), ambientLight));

        return outputColour;
    }
}
