package fr.ctvox.game.world.blocks;

import fr.ctvox.maths.Color4f;

public class Block
{
    private Color4f color;

    public Block(Color4f color)
    {
        this.color = color;
    }

    public float[] BlockDataFront(float x, float y, float z, Color4f color2) {

        float r, g, b, a;
        r = color.r;
        g = color.g;
        b = color.b;
        a = color.a;
        return new float[] {

                x, y, z, /* ////////////// */r * 0.8f * color2.g, g * 0.8f * color2.g, b * 0.8f * color2.g, a, //////
                x + 1, y, z, /* ////////// */r * 0.8f * color2.r, g * 0.8f * color2.r, b * 0.8f * color2.r, a, ///
                x + 1, y + 1, z, /* ////// */r * 0.8f * color2.a, g * 0.8f * color2.a, b * 0.8f * color2.a, a, // 5
                x, y + 1, z, /* ////////// */r * 0.8f * color2.b, g * 0.8f * color2.b, b * 0.8f * color2.b, a, /////

        };

    }

    public float[] BlockDataBack(float x, float y, float z, Color4f color2) {
        float r, g, b, a;
        r = color.r;
        g = color.g;
        b = color.b;
        a = color.a;

        return new float[] {

                x + 1, y, z + 1, /* ////// */r * 0.8f * color2.g, g * 0.8f * color2.g, b * 0.8f * color2.g, a, //////
                x, y, z + 1, /* ////////// */r * 0.8f * color2.r, g * 0.8f * color2.r, b * 0.8f * color2.r, a, ///
                x, y + 1, z + 1, /* ////// */r * 0.8f * color2.a, g * 0.8f * color2.a, b * 0.8f * color2.a, a, // 5
                x + 1, y + 1, z + 1, /* // */r * 0.8f * color2.b, g * 0.8f * color2.b, b * 0.8f * color2.b, a, /////

        };

    }

    public float[] BlockDataRight(float x, float y, float z, Color4f color2) {

        float r, g, b, a;
        r = color.r;
        g = color.g;
        b = color.b;
        a = color.a;

        return new float[] {

                x + 1, y + 1, z, /* ////// */r * 0.75f * color2.g, g * 0.75f * color2.g, b * 0.75f * color2.g, a, //////
                x + 1, y, z, /* ////////// */r * 0.75f * color2.r, g * 0.75f * color2.r, b * 0.75f * color2.r, a, ///
                x + 1, y, z + 1, /* ////// */r * 0.75f * color2.a, g * 0.75f * color2.a, b * 0.75f * color2.a, a, // 5
                x + 1, y + 1, z + 1, /* // */r * 0.75f * color2.b, g * 0.75f * color2.b, b * 0.75f * color2.b, a, /////
        };

    }

    public float[] BlockDataLeft(float x, float y, float z, Color4f color2) {

        float r, g, b, a;
        r = color.r;
        g = color.g;
        b = color.b;
        a = color.a;

        return new float[] {

                x, y, z, /* //////////// */r * 0.75f * color2.g, g * 0.75f * color2.g, b * 0.75f * color2.g, a, //////
                x, y + 1, z, /* //////// */r * 0.75f * color2.r, g * 0.75f * color2.r, b * 0.75f * color2.r, a, ///
                x, y + 1, z + 1, /* //// */r * 0.75f * color2.a, g * 0.75f * color2.a, b * 0.75f * color2.a, a, // 5
                x, y, z + 1, /* //////// */r * 0.75f * color2.b, g * 0.75f * color2.b, b * 0.75f * color2.b, a, /////

        };

    }

    public float[] BlockDataUp(float x, float y, float z, Color4f color2) {

        float r, g, b, a;
        r = color.r;
        g = color.g;
        b = color.b;
        a = color.a;

        return new float[] {

                x, y + 1, z, /* //////////// */r * 0.7f * color2.g, g * 0.7f * color2.g, b * 0.7f * color2.g, a, //////
                x + 1, y + 1, z, /* //////// */r * 0.7f * color2.r, g * 0.7f * color2.r, b * 0.7f * color2.r, a, ///
                x + 1, y + 1, z + 1, /* //// */r * 0.7f * color2.a, g * 0.7f * color2.a, b * 0.7f * color2.a, a, // 5
                x, y + 1, z + 1, /* //////// */r * 0.7f * color2.b, g * 0.7f * color2.b, b * 0.7f * color2.b, a, /////
        };

    }

    public float[] BlockDataDown(float x, float y, float z, Color4f color2) {

        float r, g, b, a;
        r = color.r;
        g = color.g;
        b = color.b;
        a = color.a;

        return new float[] {

                x + 1, y, z, /* //////// */r * 0.7f * color2.g, g * 0.7f * color2.g, b * 0.7f * color2.g, a, //
                x, y, z, /* //////////// */r * 0.7f * color2.r, g * 0.7f * color2.r, b * 0.7f * color2.r, a, //
                x, y, z + 1, /* //////// */r * 0.7f * color2.a, g * 0.7f * color2.a, b * 0.7f * color2.a, a, //
                x + 1, y, z + 1, /* //// */r * 0.7f * color2.b, g * 0.7f * color2.b, b * 0.7f * color2.b, a, //

        };

    }

}
