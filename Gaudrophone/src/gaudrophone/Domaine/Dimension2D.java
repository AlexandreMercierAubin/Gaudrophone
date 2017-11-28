package gaudrophone.Domaine;

import java.io.Serializable;

public class Dimension2D extends java.awt.geom.Dimension2D implements Serializable
{
    double width;
    double height;
    
    public Dimension2D(double width, double height)
    {
        this.width = width;
        this.height = height;
    }
    
    public double getHeight()
    {
        return height;
    }
    
    public double getWidth()
    {
        return width;
    }
    
    public void setSize(double width, double height)
    {
        this.width = width;
        this.height = height;
    }
}
