package fr.ctvox.maths;

public class Vec3 {

	public static final Vec3 UP = new Vec3(0.0f, 1.0f, 0.0f);
	public static final Vec3 FRONT = new Vec3(0.0f, 0.0f, 1.0f);
	public static final Vec3 RIGHT = new Vec3(1.0f, 0.0f, 0.0f);

	public float x, y, z;

	public Vec3() {
		this(0, 0, 0);
	}

	public Vec3(float value) {
		this(value, value, value);
	}

	public Vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3(Vec3 value) {
		this.x = value.x;
		this.y = value.y;
		this.z = value.z;
	}

	public Vec3 add(float value) {
		this.x += value;
		this.y += value;
		this.z += value;
		return this;
	}

	public Vec3 add(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vec3 add(Vec3 value) {
		this.x += value.x;
		this.y += value.y;
		this.z += value.z;
		return this;
	}

	public Vec3 sub(float value) {
		this.x -= value;
		this.y -= value;
		this.z -= value;
		return this;
	}

	public Vec3 sub(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public Vec3 sub(Vec3 value) {
		this.x -= value.x;
		this.y -= value.y;
		this.z -= value.z;
		return this;
	}

	public Vec3 mul(float value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
		return this;
	}

	public Vec3 mul(float x, float y, float z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	public Vec3 mul(Vec3 value) {
		this.x *= value.x;
		this.y *= value.y;
		this.z *= value.z;
		return this;
	}

	public Vec3 div(float value) {
		this.x /= value;
		this.y /= value;
		this.z /= value;
		return this;
	}

	public Vec3 div(float x, float y, float z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}

	public Vec3 div(Vec3 value) {
		this.x /= value.x;
		this.y /= value.y;
		this.z /= value.z;
		return this;
	}

	public void addX(float x) {
		this.x += x;
	}

	public void addY(float y) {
		this.y += y;
	}

	public void addZ(float z) {
		this.z += z;
	}

	public Vec3 normalize() {
		float mag = magnitude();
		return new Vec3(x / mag, y / mag, z / mag);
	}

	public float magnitude() {
		return (float) Math.sqrt(Mathf.pow(x, 2.0F) + Mathf.pow(y, 2.0F) + Mathf.pow(z, 2.0F));
	}

	public Vec3 cross(Vec3 r) {
		float nx = y * r.z - z * r.y;
		float ny = z * r.x - x * r.z;
		float nz = x * r.y - y * r.x;
		return new Vec3(nx, ny, nz);
	}

	public float dot(Vec3 v) {
		return x * v.x + y * v.y + z * v.z;
	}

	public Vec3 negate() {
		x = -x;
		y = -y;
		z = -z;

		return this;
	}

	public Vec3 copy() {
		return new Vec3(x, y, z);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

}
