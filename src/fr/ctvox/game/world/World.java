package fr.ctvox.game.world;

public class World
{
    public static final int SIZE = 1;

    Chunk chunks[][];

    public World()
    {
        chunks = new Chunk[SIZE][SIZE];

        for(int x = 0; x < SIZE; x++)
        {
            for(int z = 0; z < SIZE; z++)
            {
                chunks[x][z] = new Chunk(x, 0, z);
            }
        }

        for(int x = 0; x < SIZE; x++)
        {
            for(int z = 0; z < SIZE; z++)
            {
                chunks[x][z].create();
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
}
