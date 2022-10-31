// TODO: Finish settings window

package raytracer.ui;

import com.sun.tools.javac.Main;
import raytracer.Render;
import raytracer.colour.Material;
import raytracer.lights.PointLight;
import raytracer.math.Point;
import raytracer.scene.Scene;
import raytracer.shapes.Sphere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow
{
    JFrame f;

    public SettingsWindow()
    {
        // Create a new window
        f = new JFrame();
        f.setSize(800, 600);
        f.setLayout(new FlowLayout());

        // Create panels for adding lights and spheres
        JPanel addLight = new JPanel();
        addLight.setLayout(new FlowLayout());
        JPanel addSphere = new JPanel();
        addSphere.setLayout(new FlowLayout());

        JButton lightButton = new JButton("Add light");
        lightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scene.lights.add(new PointLight(
                                new Point(10, 5, 10),
                                100
                        )
                );
                ImageWindow.changeImg();
            }
        });
        addLight.add(lightButton);
        JButton sphereButton = new JButton("Add sphere");
        sphereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scene.shapes.add(new Sphere(
                        new Point(-10, 3, 10),
                        5,
                        Material.bluePlastic
                ));
                ImageWindow.changeImg();
                System.out.println(Scene.lights.size());
            }
        });
        addSphere.add(sphereButton);

        f.add(addLight);
        f.add(addSphere);

        f.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }


}
