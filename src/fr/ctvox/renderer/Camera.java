package fr.ctvox.renderer;

import fr.ctvox.maths.Vec3;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import java.security.Key;

import static org.lwjgl.opengl.GL11.*;

public class Camera
{
    Vec3        pos;
    Vec3        rot;

    float       fov,
                zNear,
                zFar;

    public Camera()
    {
        this.pos = new Vec3();
        this.rot = new Vec3();
    }

    public void update()
    {
        rot.x += -Mouse.getDY() / 2;
        rot.y += Mouse.getDX() / 2;

        if(Keyboard.isKeyDown(Keyboard.KEY_Z)) pos.z += -0.1f;
        if(Keyboard.isKeyDown(Keyboard.KEY_S)) pos.z += 0.1f;
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)) pos.x += 0.1f;
        if(Keyboard.isKeyDown(Keyboard.KEY_D)) pos.x += -0.1f;
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) pos.y += -0.1f;
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) pos.y += 0.1f;



    }

    public void setPerspectiveProjection(float fov, float zNear, float zFar)
    {
        this.fov = fov;
        this.zNear = zNear;
        this.zFar = zFar;
    }

    public void getPespectiveProjection()
    {
        glPopMatrix();
        glEnable(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(fov, (float) Display.getWidth() / (float) Display.getHeight(), zNear, zFar);
        glEnable(GL_MODELVIEW);

        glPushAttrib(GL_PROJECTION);

        glRotatef(rot.x, 1, 0, 0);
        glRotatef(rot.y, 0, 1, 0);
        glRotatef(rot.z, 0, 0, 1);
        glTranslatef(-pos.x, -pos.y, -pos.z);

    }
}