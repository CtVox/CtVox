package fr.ctvox.game.world;

import fr.ctvox.game.world.blocks.Block;
import fr.ctvox.game.world.blocks.GrassBlock;
import fr.ctvox.game.world.blocks.WoodBlock;
import fr.ctvox.maths.Color4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.Random;

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
    Noise                           noise;

    public Chunk(int x, int y, int z, Noise noise)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.noise = noise;

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
                    int xx = this.x * SIZE + x;
                    int yy = this.y * SIZE + y;
                    int zz = this.z * SIZE + z;

                    if(noise.getNoise(xx, zz) > yy - 1)
                        blocks[x][y][z] = new GrassBlock();
                }
            }
        }
    }

    public void generateVegetation(World world)
    {
        for(int x = 0; x < SIZE; x++)
        {
            for(int y = 0; y < SIZE; y++)
            {
                for(int z = 0; z < SIZE; z++)
                {
                    int xx = this.x * SIZE + x;
                    int yy = this.y * SIZE + y;
                    int zz = this.z * SIZE + z;

                    boolean grounded = noise.getNoise(xx, zz) > yy - 1 && noise.getNoise(xx, zz) < yy;

                    if(grounded && Math.random() < 0.01f)
                        Tree.createTree(xx, yy, zz, world);
                }
            }
        }
    }


    public void create(World world)
    {
        buffer = BufferUtils.createFloatBuffer(SIZE * SIZE * SIZE * 6 * 4 * (3 + 4));

        for(int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {

                    Block block = blocks[x][y][z];

                    if(block == null)
                        continue;

                    int xx = this.x * SIZE + x;
                    int yy = this.y * SIZE + y;
                    int zz = this.z * SIZE + z;

                    boolean up = world.getBlock(xx, yy + 1, zz) == null;
                    boolean down = world.getBlock(xx, yy - 1, zz) == null;
                    boolean right = world.getBlock(xx + 1, yy, zz) == null;
                    boolean left = world.getBlock(xx - 1, yy, zz) == null;
                    boolean front = world.getBlock(xx, yy, zz - 1) == null;
                    boolean back = world.getBlock(xx, yy, zz + 1) == null;

                    if(!up && !down && !right && !left && !front && !back)
                        continue;

                    int size = 0;

                    if(yy == 0) continue;
                    if(xx == 0) continue;
                    if(zz == 0) continue;
                    if(xx == World.SIZE * SIZE - 1) continue;
                    if(zz == World.SIZE * SIZE - 1) continue;

                    if(front)
                    {
                        buffer.put(block.BlockDataFront(xx, yy, zz, new Color4f(1f, 1f, 1f, 1f)));
                        size++;
                    }
                    if(back)
                    {
                        buffer.put(block.BlockDataBack(xx, yy, zz, new Color4f(1f, 1f, 1f, 1f)));
                        size++;
                    }
                    if(up)
                    {
                        buffer.put(block.BlockDataUp(xx, yy, zz, new Color4f(1f, 1f, 1f, 1f)));
                        size++;
                    }
                    if(down)
                    {
                        buffer.put(block.BlockDataDown(xx, yy, zz, new Color4f(1f, 1f, 1f, 1f)));
                        size++;
                    }
                    if(right)
                    {
                        buffer.put(block.BlockDataRight(xx, yy, zz, new Color4f(1f, 1f, 1f, 1f)));
                        size++;
                    }
                    if(left)
                    {
                        buffer.put(block.BlockDataLeft(xx, yy, zz, new Color4f(1f, 1f, 1f, 1f)));
                        size++;
                    }

                    bufferSize += size * 4;
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

    public Block getBlock(int x, int y, int z)
    {
        if(x < 0 || y < 0 || z < 0 || x >= SIZE || y >= SIZE || z >= SIZE)
            return null;
        return blocks[x][y][z];
    }

    public void setBlock(int x, int y, int z, Block block)
    {
        if(x < 0 || y < 0 || z < 0 || x >= SIZE || y >= SIZE || z >= SIZE)
            return;
        blocks[x][y][z] = block;
    }
}
