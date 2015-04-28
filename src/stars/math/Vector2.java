package stars.math;

public class Vector2 implements Cloneable, Tuple2 {
    private double x;
    private double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector2(Tuple2 v) {
        this(v.getX(), v.getY());
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public Object clone() {
        return new Vector2(this);
    }

    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
