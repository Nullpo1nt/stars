package stars.ui;

import java.awt.Graphics2D;

import stars.math.Tuple3;

public class GraphicsWrapper {
    double     scale;

    int        width;
    int        height;

    int        halfWidth;
    int        halfHeight;

    Graphics2D graphics;

    public GraphicsWrapper(Graphics2D graphics, int width, int height,
            double scale) {
        setGraphics(graphics, width, height, scale);
    }

    public void setGraphics(Graphics2D graphics, int width, int height,
            double scale) {
        this.graphics = graphics;

        this.width = width;
        this.height = height;
        this.halfWidth = width / 2;
        this.halfHeight = height / 2;

        this.scale = scale;
    }

    public int scale(double value) {
        return (int) Math.round(value * scale);
    }

    public int halfWidth() {
        return halfWidth;
    }

    public int halfHeight() {
        return halfHeight;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public Graphics2D g() {
        return graphics;
    }

    // public void drawRectScaled(double x, double y, double width, double height) {
    //     graphics.drawRect((int) (x * scale), (int) (y * scale),
    //             (int) (width * scale), (int) (height * scale));
    // }

    public void drawRect(double x, double y, double width, double height) {
        graphics.drawRect(scale(x) + halfWidth, scale(y) + halfHeight, 
                          scale(width), scale(height));
    }

    // public void drawString(String s, int x, int y) {
    //     char[] c = s.toCharArray();
    //     graphics.drawChars(c, 0, c.length, x, y);
    // }

    // public void drawRect(int x, int y, int width, int height) {
    //     graphics.drawRect(x, y, width, height);
    // }

    // public void drawLine(int x, int y, int xx, int yy) {
    //     graphics.drawLine(x,y,xx,yy);
    // }
    public void drawLine(double x, double y, double xx, double yy) {
        graphics.drawLine(scale(x) + halfWidth, scale(y) + halfHeight, 
                          scale(xx) + halfWidth, scale(yy) + halfHeight);
    }

    public void drawVector(Tuple3 origin, Tuple3 vector) {
        drawVector(origin, vector, 1.0d);
    }

    public void drawVector(Tuple3 origin, Tuple3 vector, double scale) {
        int x = scale(origin.getX()) + halfWidth;
        int y = scale(origin.getY()) + halfHeight;

        graphics.drawLine(x, y, 
            x + scale(vector.getX() * scale), 
            y + scale(vector.getY() * scale));
    }

    public void drawCircle(double x, double y, double radius) {
        int diameter = scale(radius * 2d);
        graphics.drawOval(scale(x) - scale(radius) + halfWidth, scale(y) - scale(radius) + halfHeight, diameter, diameter);
    }

    public void fillCircle(double x, double y, double radius) {
        int diameter = scale(radius * 2d);
        graphics.fillOval(scale(x) - scale(radius) + halfWidth, scale(y) - scale(radius) + halfHeight, diameter, diameter);
    }

    public void drawCross(double x, double y, int size) {
        int ix = scale(x);
        int iy = scale(y);
        graphics.drawLine(ix + halfWidth, iy - size + halfHeight, 
                          ix + halfWidth, iy + size + halfHeight);
        graphics.drawLine(ix + halfWidth - size, iy + halfHeight, 
                          ix + halfWidth + size, iy + halfHeight);
    }

}
