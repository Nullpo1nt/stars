package stars.physics;

public abstract class SimpleVector implements Cloneable {
    public double x = 0,
                  y = 0,
                  z = 0;


        public SimpleVector() {  }
        
        public SimpleVector(SimpleVector v) {
            set(v);
        }
        
        public SimpleVector(double x, double y, double z) {
            set(x,y,z);
        }
        
        
        // SETs/GETs ...
        public void set(SimpleVector v) { set(v.x, v.y, v.z); }
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
         * 
         * @param v - Position Vector
         * @return Scalar distance to V
         */
        public abstract double getDistance(SimpleVector v);
        
        /**
         * Gets the vector distance to position v.
         * 
         * @param v
         * @return Distance Vector
         */
        public abstract SimpleVector getDistanceVector(SimpleVector v);
        
        /**
         * Gets the vector distance to position v and places results in <code>target</code>.
         * @param v
         * @param target
         * @return
         */
        public abstract SimpleVector getDistanceVector(SimpleVector v, SimpleVector target);
        
        /**
         * Returns the scalar distance to the coordinates (0,0,0).
         * 
         * @return Distance to (0,0,0)
         */
        public abstract double getMagnitude();
        
        /**
         * Calculates the unit vector and returns a new Vector3 object.
         * 
         * @return Unit Vector
         */
        public abstract SimpleVector getUnitVector();
        
        /**
         * Calculates the unit vector and places it in the <code>unitVector</code> object.
         * 
         * @param unitVector
         * @return
         */
        public abstract SimpleVector getUnitVector(SimpleVector unitVector);
        
        /**
         * 
         * 
         * @param v
         * @return Dot product Vector
         */
        public abstract double getDotProduct(SimpleVector v);
        
        /**
         * 
         * 
         * @param v
         * @return 
         */
        public abstract SimpleVector getCrossProduct(SimpleVector v);

        public abstract void add(SimpleVector v);

        public abstract void sub(SimpleVector v);
        
        public abstract void mul(SimpleVector v);
        
        public abstract void div(SimpleVector v);
        
        public abstract void scale(double val);

        public abstract Object clone();
        
        public String toString() {
            return "{x"+x+", y"+y+", z"+z+"}";
        }
}
