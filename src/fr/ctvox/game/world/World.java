package fr.ctvox.game.world;

import fr.ctvox.game.world.blocks.Block;

public class World
{
    public static final int SIZE = 10;

    Chunk chunks[][];
    Noise noise;

    public World()
    {
        chunks = new Chunk[SIZE][SIZE];
        noise = new Noise(1265413, 25, 6 );

        for(int x = 0; x < SIZE; x++)
        {
            for(int z = 0; z < SIZE; z++)
            {
                chunks[x][z] = new Chunk(x, 0, z, noise);
            }
        }

        for(int x = 0; x < SIZE; x++)
        {
            for(int z = 0; z < SIZE; z++)
            {
                chunks[x][z].generateVegetation(this);
            }
        }

        for(int x = 0; x < SIZE; x++)
        {
            for(int z = 0; z < SIZE; z++)
            {
                chunks[x][z].create(this);
            }
        }
    }

    public void update()
    {
        for(int x = 0; x < SIZE; x++)
        {
            for(int z = 0; z < SIZE; z++)
            {
                chunks[x][z].update();
            }
        }
    }

    public void render()
    {
        for(int x = 0; x < SIZE; x++)
        {
            for(int z = 0; z < SIZE; z++)
            {
                chunks[x][z].render();
            }
        }
    }

    public Block getBlock(int x, int y, int z)
    {
        int xc = x / Chunk.SIZE;
        int zc = z / Chunk.SIZE;

        if(xc < 0 || zc < 0 || xc >= SIZE || zc >= SIZE)
            return null;

        Chunk chunk = chunks[xc][zc];

        int xx = x % Chunk.SIZE;
        int yy = y % Chunk.SIZE;
        int zz = z % Chunk.SIZE;

        return chunk.getBlock(xx, yy, zz);
    }

    public void setBlock(int x, int y, int z, Block block)
    {
        int xc = x / Chunk.SIZE;
        int zc = z / Chunk.SIZE;

        if(xc < 0 || zc < 0 || xc >= SIZE || zc >= SIZE)
            return;

        Chunk chunk = chunks[xc][zc];

        int xx = x % Chunk.SIZE;
        int yy = y % Chunk.SIZE;
        int zz = z % Chunk.SIZE;

        chunk.setBlock(xx, yy, zz, block);
    }
}
