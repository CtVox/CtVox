package fr.ctvox.maths;

public class Color4f {

	public float r, g, b, a;

	public Color4f(int r, int g, int b) {
		this.r = r / 255;
		this.g = g / 255;
		this.b = b / 255;
		this.a = 1;
	}
	
	public Color4f(int r, int g, int b, int a) {
		this.r = r / 255;
		this.g = g / 255;
		this.b = b / 255;
		this.a = a / 255;
	}
	
	public Color4f(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 1;
	}
	
	public Color4f(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public Color4f add(float v) {
		r += v;
		g += v;
		b += v;
		return this;
	}

	public Color4f add(Color4f v) {
		r += v.r;
		g += v.g;
		b += v.b;
		return this;
	}

	public Color4f sub(float v) {
		r -= v;
		g -= v;
		b -= v;
		return this;
	}

	public Color4f sub(Color4f v) {
		r -= v.r;
		g -= v.g;
		b -= v.b;
		return this;
	}

	public Color4f mul(float v) {
		r *= v;
		g *= v;
		b *= v;
		return this;
	}

	public Color4f mul(Color4f v) {
		r *= v.r;
		g *= v.g;
		b *= v.b;
		return this;
	}
	
	public Color4f copy() {
		return new Color4f(r, g, b, a);
	}

}
