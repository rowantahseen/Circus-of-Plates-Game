

 

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Omar Elfarouk
 */

public class LoadShapes {

    public LoadShapes() {
    }

    public Constructor<?> getConstructor(File file, String name) {
        Constructor<?> c = null;
        Class<?> k;
        URL url = null;
        try {
            url = file.toURI().toURL();
        } catch (MalformedURLException ex) {}

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});

        try {
            k = Class.forName(name, true,
                    classLoader);
            c = k.getConstructor();
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalArgumentException ex) {
            
        }
       // System.out.println(c.getName());
        return c;
    }
}
