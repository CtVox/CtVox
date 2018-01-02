package fr.ctvox.game;

import fr.ctvox.game.world.World;
import fr.ctvox.renderer.Camera;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

public class Game
{
    int vbo;

    Camera cam;
    World world;

    public Game()
    {
        cam = new Camera();
        cam.setPerspectiveProjection(70.0f, 0.1f, 1000.0f);

        world = new World();

        vbo = glGenBuffers(); // Generation id du VBO

        float vertices[] = {
             0, 0, -6,
             0, 1, -6,
             1, 0, -6,
        };

        FloatBuffer buffer = BufferUtils.createFloatBuffer(9); // Put un tableau de float dans un buffer
        buffer.put(vertices); // Push ton tab de vertex dans le buffer
        buffer.flip(); // Envoie des données

        glBindBuffer(GL_ARRAY_BUFFER, vbo); // Utilisation du vbo avec l'id : vbo
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW); // Remplissage du VBO avec notre buffer

    }

    public void update()
    {
        if(Mouse.isButtonDown(0)) Mouse.setGrabbed(true);
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) Mouse.setGrabbed(false);
        if(!Mouse.isGrabbed()) return;

        if(Display.wasResized()) glViewport(0, 0, Display.getWidth(), Display.getHeight());

        cam.update();

        world.update();
    }

    public void render()
    {

        cam.getPespectiveProjection();

        world.render();

        glBindBuffer(GL_ARRAY_BUFFER, vbo);// Utilisation du vbo

        glEnableClientState(GL_VERTEX_ARRAY); // Utilisation des vertices contenu dans le vbo
        glVertexPointer(3, GL_FLOAT, 0, 0); // Emplacement des vertices

        glDrawArrays(GL_TRIANGLES, 0, 3);

        glBindBuffer(GL_ARRAY_BUFFER, 0); // Arret d'utilisation du vbo
    }
}
