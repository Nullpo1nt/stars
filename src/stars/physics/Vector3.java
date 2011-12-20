package stars.physics;

public class Vector3 implements Cloneable {
    public double x = 0,
                  y = 0,
                  z = 0;

    
    public Vector3() {  }
    
    public Vector3(Vector3 v) {
        set(v);
    }
    
    public Vector3(double x, double y, double z) {
        set(x,y,z);
    }
    
    
    // SETs/GETs ...
    public void set(Vector3 v) { set(v.x, v.y, v.z); }
    public void set(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }
    public void setX(double x) { this.x = x; }
    public double getX() { return x; }
    public void setY(double y) { this.y = y; }
    public double getY() { return y; }
    public void setZ(double z) { this.z = z; }
    public double getZ() { return z; }
    // END SETs / GETs 
        
    
    public void setRandom(double scale) {
        double halfScale = scale / 2;
        x = (Math.random() * scale) - halfScale;
        y = (Math.random() * scale) - halfScale;
        z = (Math.random() * scale) - halfScale;
    }
    
    /**
     * Gets the scalar distance to position v.
     * <br>d = sqrt( (x1 - x2)^2 + (y1 - y2)^2 + (z1 - z2)^2 )
     * 
     * @param v - Position Vector
     * @return Scalar distance to V
     */
    public double getDistance(Vector3 v) {
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
    public Vector3 getDistanceVector(Vector3 v) {
        return getDistanceVector(v, new Vector3());
    }
    
    /**
     * Gets the vector distance to position v and places results in <code>target</code>.
     * @param v
     * @param target
     * @return
     */
    public Vector3 getDistanceVector(Vector3 v, Vector3 target) {
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
    public Vector3 getUnitVector() {
        return getUnitVector(new Vector3());
    }
    
    /**
     * Calculates the unit vector and places it in the <code>unitVector</code> object.
     * 
     * @param unitVector
     * @return
     */
    public Vector3 getUnitVector(Vector3 unitVector) {
        double sum = Math.abs(x) + Math.abs(y) + Math.abs(z);
        
        unitVector.x = x / sum;
        unitVector.y = y / sum;
        unitVector.z = z / sum;
        
        return unitVector;
    }
    
    /**
     * 
     * 
     * @param v
     * @return Dot product Vector
     */
    public double getDotProduct(Vector3 v) {
        return (v.x * x) + (v.y * y) + (v.z * z);
    }
    
    /**
     * 
     * 
     * @param v
     * @return 
     */
    public Vector3 getCrossProduct(Vector3 v) {
        double q = 0,
               r = 0,
               s = 0;
        
        q = (y * v.z) - (z * v.y);
        r = (z * v.x) - (x * v.z);
        s = (x * v.y) - (y * v.x);
        
        return new Vector3(q,r,s);
    }

    public void add(Vector3 v) {
        x = x + v.x;
        y = y + v.y;
        z = z + v.z;
    }

    public void sub(Vector3 v) {
        x = x - v.x;
        y = y - v.y;
        z = z - v.z;
    }
    
    public void mul(Vector3 v) {
        x = x * v.x;
        y = y * v.y;
        z = z * v.z;
    }
    
    public void div(Vector3 v) {
        x = x / v.x;
        y = y / v.y;
        z = z / v.z;
    }
    
    public void scale(double val) {
        x = x * val;
        y = y * val;
        z = z * val;
    }

    public Object clone() {
        return new Vector3(this);
    }
    
    public String toString() {
        return "{x"+x+", y"+y+", z"+z+"}";
    }
}
