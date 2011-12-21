package stars.physics;

public class Vector1x3 implements Cloneable  {
    public double x = 0.0d,
                  y = 0.0d,
                  z = 0.0d;

    
    public Vector1x3() {  }
    
    public Vector1x3(Vector1x3 v) {
        set(v);
    }
    
    public Vector1x3(double x, double y, double z) {
        set(x,y,z);
    }
    
    
    // SETs/GETs ...
    public void set(Vector1x3 v) { set(v.x, v.y, v.z); }
    public void set(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setZ(double z) { this.z = z; }
    
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
    // END SETs / GETs 
        
    
    /**
     * Sets each vector component to a random value between the range
     * of [-scale, scale].
     * 
     * @param double d - The scale component
     */
    public void setRandom(double d) {
        double doubleScale = d * 2;
        
        x = (Math.random() * doubleScale) - d;
        y = (Math.random() * doubleScale) - d;
        z = (Math.random() * doubleScale) - d;
    }
    
    /**
     * Gets the scalar distance to position v.
     * <br>d = sqrt( (x1 - x2)^2 + (y1 - y2)^2 + (z1 - z2)^2 )
     * 
     * @param v - Position Vector
     * @return Scalar distance to V
     */
    public double getDistance(Vector1x3 v) {
        double xx = this.x - v.x,
               yy = this.y - v.y,
               zz = this.z - v.z;
        
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
    public Vector1x3 getDistanceVector(Vector1x3 v) {
        return getDistanceVector(v, new Vector1x3());
    }
    
    /**
     * Gets the vector distance to position v and places results in <code>target</code>.
     * 
     * @param v
     * @param target
     * @return
     */
    public Vector1x3 getDistanceVector(Vector1x3 v, Vector1x3 target) {
        double xx = v.x - x,
               yy = v.y - y,
               zz = v.z - z;
        target.set(xx, yy, zz);
        return target;
    }
    
    /**
     * Returns the scalar distance to the coordinates (0,0,0).
     * 
     * @return Distance to (0,0,0)
     */
    public double getMagnitude() {
        return Math.sqrt((x*x) + (y*y) + (z*z));
    }
    
    /**
     * Calculates the unit vector and returns a new Vector3 object.
     * 
     * @return Unit Vector
     */
    public Vector1x3 getUnitVector() {
        return getUnitVector(new Vector1x3());
    }
    
    /**
     * Calculates the unit vector and places it in the <code>unitVector</code> object.
     * 
     * @param unitVector
     * @return
     */
    public Vector1x3 getUnitVector(Vector1x3 unitVector) {
        double sum = 1.0d / Math.sqrt(x*x + y*y + z*z);
        
        unitVector.x = x * sum;
        unitVector.y = y * sum;
        unitVector.z = z * sum;

        return unitVector;
    }
    
    /**
     * Calculates the dot product of the current vector and 
     * a target vector.
     * 
     * @param Vector1x3 v - The target vector 
     * @return double - The value of the dot product.
     */
    public double getDotProduct(Vector1x3 v) {
        return (v.x * x) + (v.y * y) + (v.z * z);
    }
    
    /**
     * 
     * @param v
     * @param result
     * @return
     */
    public Vector1x3 getCrossProduct(Vector1x3 v, Vector1x3 result) {
        result.x = (y * v.z) - (z * v.y);
        result.y = (z * v.x) - (x * v.z);
        result.z = (x * v.y) - (y * v.x);

        return result;
    }
    
    /**
     * 
     * 
     * @param v
     * @return 
     */
    public Vector1x3 getCrossProduct(Vector1x3 v) {
        return getCrossProduct(v, new Vector1x3());
    }

    
    public void add(Vector1x3 v) {
        x = x + v.x;
        y = y + v.y;
        z = z + v.z;
    }

    public void sub(Vector1x3 v) {
        x = x - v.x;
        y = y - v.y;
        z = z - v.z;
    }
    
    public void mul(Vector1x3 v) {
        x = x * v.x;
        y = y * v.y;
        z = z * v.z;
    }
    
    public void div(Vector1x3 v) {
        x = x / v.x;
        y = y / v.y;
        z = z / v.z;
    }
        
    public void scale(double val) {
        scale(val, val, val);
    }
    
    public void scale(double xS, double yS, double zS) {
        x = x * xS;
        y = y * yS;
        z = z * zS;
    }

    public Object clone() {
        return new Vector1x3(this);
    }
    
    public String toString() {
        return "{x="+x+", y="+y+", z="+z+"}";
    }
}
