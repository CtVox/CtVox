package fr.ctvox.renderer;

import fr.ctvox.maths.Mathf;
import fr.ctvox.maths.Vec3;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import java.security.Key;

import static org.lwjgl.opengl.GL11.*;

public class Camera
{
    public Vec3        pos;
    public Vec3        rot;

    float       fov,
                zNear,
                zFar;

    public Camera()
    {
        this.pos = new Vec3(-10, 19, 6);
        this.rot = new Vec3(35, 113, 0);
    }

    float xDir, yDir, zDir;
    float xa, ya, za;
    float speed = 0.01f;

    public void update()
    {
        rot.x += -Mouse.getDY() / 2;
        rot.y += Mouse.getDX() / 2;

        if(rot.x > 90) rot.x = 90;
        if(rot.x < -90) rot.x = -90;

        if(Keyboard.isKeyDown(Keyboard.KEY_Z)) zDir = -speed;
        if(Keyboard.isKeyDown(Keyboard.KEY_S)) zDir = speed;
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)) xDir = -speed;
        if(Keyboard.isKeyDown(Keyboard.KEY_D)) xDir = speed;
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) yDir = speed;
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) yDir = -speed;

        xa += xDir * Mathf.cos(Mathf.toRadians(rot.y)) - zDir * Mathf.sin(Mathf.toRadians(rot.y));
        za += zDir * Mathf.cos(Mathf.toRadians(rot.y)) + xDir * Mathf.sin(Mathf.toRadians(rot.y));
        ya += yDir;

        pos.x += xa;
        pos.y += ya;
        pos.z += za;

        xa *= 0.9f;
        ya *= 0.9f;
        za *= 0.9f;

        xDir = 0;
        yDir = 0;
        zDir = 0;


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