package stars.physics.nbody.space;

import stars.math.Tuple3;
import stars.math.Vector2;

public class Boundary {
    public Vector2 boundX;
    public Vector2 boundY;
    public Vector2 boundZ;

    public Boundary(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax) {
        boundX = new Vector2(xMin,xMax);
        boundY = new Vector2(yMin,yMax);
        boundZ = new Vector2(zMin,zMax);
    }

    public boolean contains(Tuple3 position) {
        boolean result = false;

        if (position.getX() >= boundX.getX() && position.getX() <= boundX.getY() &&
            position.getY() >= boundY.getX() && position.getY() <= boundY.getY() &&
            position.getZ() >= boundZ.getX() && position.getZ() <= boundZ.getY()) {
            result = true;
        }

        return result;
    }

    public double getWidthX() {
        return boundX.getY() - boundX.getX();
    }

    public double getWidthY() {
        return boundX.getY() - boundX.getX();
    }

    public double getWidthZ() {
        return boundX.getY() - boundX.getX();
    }

    public Boundary divide(int position) {
        double aX = (boundX.getY() - boundX.getX()) / 2;
        double aY = (boundY.getY() - boundY.getX()) / 2;
        double aZ = (boundZ.getY() - boundZ.getX()) / 2;
        switch (position) {
            case 0:
            return new Boundary(boundX.getX() + aX, boundX.getY(), 
                                boundY.getX() + aY, boundY.getY(), 
                                boundZ.getX() + aZ, boundZ.getY());
            case 1:
            return new Boundary(boundX.getX() + aX, boundX.getY(), 
                                boundY.getX(),      boundY.getY() - aY, 
                                boundZ.getX() + aZ, boundZ.getY());
            case 2:
            return new Boundary(boundX.getX(),      boundX.getY() - aX, 
                                boundY.getX(),      boundY.getY() - aY, 
                                boundZ.getX() + aZ, boundZ.getY());
            case 3:
            return new Boundary(boundX.getX(),      boundX.getY() - aX, 
                                boundY.getX() + aY, boundY.getY(), 
                                boundZ.getX() + aZ, boundZ.getY());
            case 4:
            return new Boundary(boundX.getX() + aX, boundX.getY(), 
                                boundY.getX() + aY, boundY.getY(), 
                                boundZ.getX(),      boundZ.getY() - aZ);
            case 5:
            return new Boundary(boundX.getX() + aX, boundX.getY(), 
                                boundY.getX(),      boundY.getY() - aY, 
                                boundZ.getX(),      boundZ.getY() - aZ);
            case 6:
            return new Boundary(boundX.getX(),      boundX.getY() - aX, 
                                boundY.getX(),      boundY.getY() - aY, 
                                boundZ.getX(),      boundZ.getY() - aZ);
            case 7:
            return new Boundary(boundX.getX(),      boundX.getY() - aX, 
                                boundY.getX() + aY, boundY.getY(), 
                                boundZ.getX(),      boundZ.getY() - aZ);
            default: throw new IllegalArgumentException();
        }
    }
}