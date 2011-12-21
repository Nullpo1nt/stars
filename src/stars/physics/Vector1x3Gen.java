package stars.physics;

public class Vector1x3Gen<T extends Number> implements Cloneable  {
    public T x, y, z;

    public Vector1x3Gen() {  }

    public Vector1x3Gen(Vector1x3Gen<T> v) {
        set(v);
    }

    public Vector1x3Gen(T x, T y, T z) {
        set(x,y,z);
    }

    // SETs/GETs ...
    public void set(Vector1x3Gen<T> v) { set(v.x, v.y, v.z); }
    public void set(T x, T y, T z) {
        setX(x);
        setY(y);
        setZ(z);
    }
    public void setX(T x) { this.x = x; }
    public void setY(T y) { this.y = y; }
    public void setZ(T z) { this.z = z; }

    public T getX() { return x; }
    public T getY() { return y; }
    public T getZ() { return z; }
    // END SETs / GETs 

    /**
     * Sets each vector component to a random value between the range
     * of [-scale, scale].
     * 
     * @param double d - The scale component
     */
    public void setRandom(T d) {
    	T doubleScale = d * 2;
        
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
    public double getDistance(Vector1x3Gen<T> v) {
        T xx = this.x - v.x,
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
    public Vector1x3Gen<T> getDistanceVector(Vector1x3Gen<T> v) {
        return getDistanceVector(v, new Vector1x3Gen<T>());
    }
    
    /**
     * Gets the vector distance to position v and places results in <code>target</code>.
     * 
     * @param v
     * @param target
     * @return
     */
    public Vector1x3Gen getDistanceVector(Vector1x3Gen<T> v, Vector1x3Gen<T> target) {
        T xx = v.x - x,
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
    public T getMagnitude() {
        return Math.sqrt((x*x) + (y*y) + (z*z));
    }
    
    /**
     * Calculates the unit vector and returns a new Vector3 object.
     * 
     * @return Unit Vector
     */
    public Vector1x3Gen<T> getUnitVector() {
        return getUnitVector(new Vector1x3Gen<T>());
    }
    
    /**
     * Calculates the unit vector and places it in the <code>unitVector</code> object.
     * 
     * @param unitVector
     * @return
     */
    public Vector1x3Gen<T> getUnitVector(Vector1x3Gen<T> unitVector) {
        T sum = Math.abs(x) + Math.abs(y) + Math.abs(z);
        
        unitVector.x = x / sum;
        unitVector.y = y / sum;
        unitVector.z = z / sum;
        
        return unitVector;
    }
    
    /**
     * Calculates the dot product of the current vector and 
     * a target vector.
     * 
     * @param Vector1x3 v - The target vector 
     * @return double - The value of the dot product.
     */
    public T getDotProduct(Vector1x3Gen<T> v) {
        return (v.x * x) + (v.y * y) + (v.z * z);
    }
    
    /**
     * 
     * @param v
     * @param result
     * @return
     */
    public Vector1x3Gen<T> getCrossProduct(Vector1x3Gen<T> v, Vector1x3Gen<T> result) {
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
    public Vector1x3Gen<T> getCrossProduct(Vector1x3Gen<T> v) {
        return getCrossProduct(v, new Vector1x3Gen());
    }

    
    public void add(Vector1x3Gen<T> v) {
        x = x + v.x;
        y = y + v.y;
        z = z + v.z;
    }

    public void sub(Vector1x3Gen<T> v) {
        x = x - v.x;
        y = y - v.y;
        z = z - v.z;
    }
    
    public void mul(Vector1x3Gen<T> v) {
        x = x * v.x;
        y = y * v.y;
        z = z * v.z;
    }
    
    public void div(Vector1x3Gen<T> v) {
        x = x / v.x;
        y = y / v.y;
        z = z / v.z;
    }
        
    public void scale(T val) {
        scale(val, val, val);
    }
    
    public void scale(T xS, T yS, T zS) {
        x = x * xS;
        y = y * yS;
        z = z * zS;
    }

    public Object clone() {
        return new Vector1x3Gen<T>(this);
    }
    
    public String toString() {
        return "{x=" + x + ", y=" + y + ", z=" + z + "}";
    }
}
