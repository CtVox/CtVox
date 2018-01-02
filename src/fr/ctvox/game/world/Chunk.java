package fr.ctvox.game.world;

import fr.ctvox.game.world.blocks.Block;
import fr.ctvox.game.world.blocks.GrassBlock;
import fr.ctvox.maths.Color4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class Chunk
{
    public static final int         SIZE = 16;
    private static FloatBuffer      buffer;

    private int                     vbo;

    private int                     x,
                                    y,
                                    z,
                                    bufferSize;

    Block                           blocks[][][];

    public Chunk(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;

        blocks = new Block[SIZE][SIZE][SIZE];

        generate();
    }

    private void generate()
    {
        for(int x = 0; x < SIZE; x++)
        {
            for(int y = 0; y < SIZE; y++)
            {
                for(int z = 0; z < SIZE; z++)
                {
                    blocks[x][y][z] = new GrassBlock();
                }
            }
        }
    }

    public void create()
    {
        buffer = BufferUtils.createFloatBuffer(SIZE * SIZE * SIZE * 6 * 4 * (3 + 4));

        for(int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {

                    Block block = blocks[x][y][z];

                    int xx = this.x * SIZE + x;
                    int yy = this.y * SIZE + y;
                    int zz = this.z * SIZE + z;

                    buffer.put(block.BlockDataFront(xx, yy, zz, new Color4f(1f, 1f, 1f, 1f)));
                    buffer.put(block.BlockDataBack(xx, yy, zz, new Color4f(1f, 1f, 1f, 1f)));
                    buffer.put(block.BlockDataUp(xx, yy, zz, new Color4f(1f, 1f, 1f, 1f)));
                    buffer.put(block.BlockDataDown(xx, yy, zz, new Color4f(1f, 1f, 1f, 1f)));
                    buffer.put(block.BlockDataRight(xx, yy, zz, new Color4f(1f, 1f, 1f, 1f)));
                    buffer.put(block.BlockDataBack(xx, yy, zz, new Color4f(1f, 1f, 1f, 1f)));

                    bufferSize += 6 * 4;
                }
            }
        }

        buffer.flip();

        createVBO();
    }

    private void createVBO()
    {
        vbo = glGenBuffers();

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
    }

    public void update()
    {

    }

    public void render()
    {
        glBindBuffer(GL_ARRAY_BUFFER, vbo);

        glEnableClientState(GL_VERTEX_ARRAY);
        glVertexPointer(3, GL_FLOAT, 7 * 4, 0);

        glEnableClientState(GL_COLOR_ARRAY);
        glColorPointer(4, GL_FLOAT, 7 * 4, 12);

        glDrawArrays(GL_QUADS, 0, bufferSize);

        glBindBuffer(GL_ARRAY_BUFFER, 0);

    }
}
