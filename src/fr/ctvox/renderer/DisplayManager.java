package fr.ctvox.renderer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class DisplayManager
{
    public static void create(int width, int height, String title)
    {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(title);
            Display.setResizable(true);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_FRONT);
    }

    public static void update()
    {
        Display.update();
    }

    public static void clearBuffers()
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public static boolean isClosed()
    {
        return Display.isCloseRequested();
    }

    public static void dispose()
    {
        Display.destroy();
    }
}
