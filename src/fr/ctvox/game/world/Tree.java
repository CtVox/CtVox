package fr.ctvox.game.world;

import fr.ctvox.game.world.blocks.GrassBlock;
import fr.ctvox.game.world.blocks.WoodBlock;
import fr.ctvox.maths.Mathf;

public class Tree
{
    public static void createTree(int x, int y, int z, World world)
    {
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                for(int k = 0; k < 7; k++)
                {
                    float ii = i - 3.5f;
                    float jj = j - 3.5f;
                    float kk = k - 3.5f;
                    float l = Mathf.sqrt(ii * ii + jj * jj + kk * kk);

                    if(l < 3.5f)
                        world.setBlock(x + (int) ii, y + (int) jj + 3, z + (int) kk, new GrassBlock());
                }
            }
        }

        for(int i = 0; i < 3; i++) world.setBlock(x, y + i, z, new WoodBlock());
    }
}
