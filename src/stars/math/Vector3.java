package stars.math;

public class Vector3 implements Cloneable, Tuple3 {
    private double x = 0.0d;
    private double y = 0.0d;
    private double z = 0.0d;

    public Vector3() {
    }

    public Vector3(Tuple3 v) {
        set(v);
    }

    public Vector3(double x, double y, double z) {
        set(x, y, z);
    }

    // SETs/GETs ...
    public void set(Tuple3 v) {
        set(v.getX(), v.getY(), v.getZ());
    }

    public void set(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    // END SETs / GETs

    /**
     * Sets each vector component to a random value between the range of
     * [-scale, scale].
     * 
     * @param double d - The scale component
     */
    public Vector3 setRandom(double d) {
        double doubleScale = d * 2;

        x = (Math.random() * doubleScale) - d;
        y = (Math.random() * doubleScale) - d;
        z = (Math.random() * doubleScale) - d;

        return this;
    }

    /**
     * Gets the scalar distance to position v. <br>
     * d = sqrt( (x1 - x2)^2 + (y1 - y2)^2 + (z1 - z2)^2 )
     * 
     * @param v
     *            - Position Vector
     * @return Scalar distance to V
     */
    public double getDistance(Tuple3 v) {
        double xx = this.x - v.getX();
        double yy = this.y - v.getY();
        double zz = this.z - v.getZ();

        xx = xx * xx;
        yy = yy * yy;
        zz = zz * zz;

        xx = (xx + yy + zz);

        return Math.sqrt(xx);
    }

    /**
     * Gets the vector distance to position v.
     * 
     * @param v
     * @return Distance Vector
     */
    public Vector3 getDistanceVector(Tuple3 v) {
        return getDistanceVector(v, new Vector3());
    }

    /**
     * Gets the vector distance to position v and places results in
     * <code>target</code>.
     * 
     * @param v
     * @param target
     * @return
     */
    public Vector3 getDistanceVector(Tuple3 v, Vector3 target) {
        double xx = v.getX() - x;
        double yy = v.getY() - y;
        double zz = v.getZ() - z;
        target.set(xx, yy, zz);
        return target;
    }

    /**
     * Returns the scalar distance to the coordinates (0,0,0).
     * 
     * @return Distance to (0,0,0)
     */
    public double getMagnitude() {
        return Math.sqrt((x * x) + (y * y) + (z * z));
    }

    /**
     * Calculates the unit vector and returns a new Vector3 object.
     * 
     * @return Unit Vector
     */
    public Vector3 getUnitVector() {
        return getUnitVector(new Vector3());
    }

    /**
     * Calculates the unit vector and places it in the <code>unitVector</code>
     * object.
     * 
     * @param unitVector
     * @return
     */
    public Vector3 getUnitVector(Vector3 unitVector) {
        double sum = 1.0d / getMagnitude();

        unitVector.x = x * sum;
        unitVector.y = y * sum;
        unitVector.z = z * sum;

        return unitVector;
    }

    /**
     * Calculates the dot product of the current vector and a target vector.
     * 
     * @param Vector3
     *            v - The target vector
     * @return double - The value of the dot product.
     */
    public double getDotProduct(Tuple3 v) {
        return (v.getX() * x) + (v.getY() * y) + (v.getZ() * z);
    }

    /**
     * 
     * @param v
     * @param result
     * @return
     */
    public Vector3 getCrossProduct(Tuple3 v, Vector3 result) {
        result.x = (y * v.getZ()) - (z * v.getY());
        result.y = (z * v.getX()) - (x * v.getZ());
        result.z = (x * v.getY()) - (y * v.getX());

        return result;
    }

    /**
     * 
     * 
     * @param v
     * @return
     */
    public Vector3 getCrossProduct(Tuple3 v) {
        return getCrossProduct(v, new Vector3());
    }

    public Vector3 add(Tuple3 v) {
        x = x + v.getX();
        y = y + v.getY();
        z = z + v.getZ();
        return this;
    }

    public Vector3 sub(Tuple3 v) {
        x = x - v.getX();
        y = y - v.getY();
        z = z - v.getZ();
        return this;
    }

    public Vector3 mul(Tuple3 v) {
        x = x * v.getX();
        y = y * v.getY();
        z = z * v.getZ();
        return this;
    }

    public Vector3 div(Tuple3 v) {
        x = x / v.getX();
        y = y / v.getY();
        z = z / v.getZ();
        return this;
    }

    public Vector3 scale(double val) {
        return scale(val, val, val);
    }

    public Vector3 scale(double xS, double yS, double zS) {
        x = x * xS;
        y = y * yS;
        z = z * zS;
        return this;
    }

    public Vector2 projectOrthgraphicZ(double scale) {
        return new Vector2(x * scale, y * scale);
    }

    public Object clone() {
        return new Vector3(this);
    }

    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]";
    }
}
