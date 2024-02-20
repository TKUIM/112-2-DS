// Implements a simple rectangle class.
// (c) 1997, 1998, 2001 duane a. bailey
package element;
import java.awt.Rectangle;

/**
 * An implementation of a simple graphics rectangle.
 *
 * @version $Id: Rect.java 8 2006-08-02 19:03:11Z bailey $
 * @author, 2001 duane a. bailey
 */
public class Rect implements Drawable
{
    /**
     * The left side of the rectangle.
     */
    protected int left;
    /**
     * The top side of the rectangle.
     */
    protected int top;
    /**
     * The width of the rectangle.
     */
    protected int width;
    /**
     * The height of the rectangle.
     */
    protected int height;

    /**
     * Constructs a trivial rectangle, about the origin.
     * @post constructs a trivial rectangle at origin
     */
    public Rect()
    {
        this(0,0,0,0);
    }

    /**
     * Constructs the rectangle determined by two points, p1 and p2.
     * 
     * @post constructs a rectangle between p1 and p2
     * @param p1 one corner of the rectangle
     * @param p2 the opposite corner of the rectangle
     */
    public Rect(Pt p1, Pt p2)
    {
        int x1 = p1.x();
        int x2 = p2.x();
        int y1 = p1.y();
        int y2 = p2.y();

        // construct the canonical form of the rectangle
        left = Math.min(x1,x2);
        top = Math.min(y1,y2);
        width = Math.abs(x1-x2);
        height = Math.abs(y1-y2);
    }

    /**
     * Construct a rectangle from the bounding box of another drawable
     * object.
     *
     * @post constructs a rectangle, based on another drawable object
     * @param o an object to be used as the basis for the constructed rect.
     */
    public Rect(Drawable o)
    {
        this.left = o.left();
        this.top = o.top();
        this.width = o.width();
        this.height = o.height();
    }

    /**
     * Construct a square centered about (cx,cy), with radius r.
     *
     * @pre radius >= 0
     * @post constructs a radius r square centered about (cx,cy)
     * @param cx the x coordinate of the center
     * @param cy the y coordinate of the center
     * @param r the radius of the rectangle
     */
    public Rect(int cx, int cy, int r)
    {
        this(cx-r, cy-r, 2*r, 2*r);
    }

    /**
     * Construct a square with radius r, centered about a point p.
     *
     * @pre radius >= 0
     * @post constructs a radius r square centered about (cx,cy)
     * @param p the center of the square
     * @param r the radius of the square
     */
    public Rect(Pt p, int r)
    {
        this(p.x(), p.y(), r);
    }

    /**
     * Construct a rectangle with upper left (x,y) and dimensions (w,h).
     * 
     * @pre  w >= 0, h >= 0
     * @post constructs a rectangle with upper left (x,y),
     *       width w, height h
     * @param x the left of the rectangle
     * @param y the top of the rectangle
     * @param w the width of the rectangle
     * @param h the height of the rectangle
     */
    public Rect(int x, int y, int w, int h)
    {
        left = x;
        top = y;
        width = (w >= 0)? w : 0;
        height = (h >= 0)? h : 0;
    }

    /**
     * Returns true if p is contained within rectangle
     * @pre p is a valid point
     * @post true iff p is within the rectangle
     * @param p the point to be considered
     * @return true iff p is contained within the rectangle
     */
    public boolean contains(Pt p)
    {
        return Line.within(p.x(),left,width) &&
               Line.within(p.y(),top,height);
    }

    /**
     * Return the left coordinate of the rectangle.
     * 
     * @post returns left coordinate of the rectangle
     * @return the left coordinate of the rectangle
     */
    public int left()
    {
        return left;
    }

    /**
     * Sets the left side of the rectangle.  Dimensions remain unchanged.
     * 
     * @post sets left to x; dimensions remain unchanged
     * @param x the desired left side.
     */
    public void left(int x)
    {
        left = x;
    }

    /**
     * Returns the top coordinate of the rectangle
     * 
     * @post returns top coordinate of the rectangle
     * @return the top coordinate of the rectangle
     */
    public int top()
    {
        return top;
    }

    /**
     * Returns right coordinate of the rectangle
     * 
     * @return the right coordinate of the rectangle
     */
    public int right()
    // post: returns right coordinate of the rectangle
    {
        return left+width;
    }

    /**
     * Returns the bottom coordinate of the rectangle
     * 
     * @post returns the bottom coordinate of the rectangle
     * @return the bottom coordinate of the rectangle
     */
    public int bottom()
    {
        return top+height;
    }

    /**
     * Returns the width of the rectangle.
     * 
     * @post returns the width of the rectangle
     * @return the width of the rectangle
     */
    public int width()
    {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     * 
     * @post returns the height of the rectangle
     * @return the height of the rectangle
     */
    public int height()
    {
        return height;
    }

    /**
     * Sets the width of the rectangle.  Center and height remain unchanged.
     * 
     * @post sets width of rectangle, center and height unchanged
     * @param w the desired width.
     */
    public void width(int w)
    {
        w = Math.max(w,0);
        left += (width-w)/2;
        width = w;
    }

    /**
     * Sets the height of the rectangle.  Center and width remain unchanged.
     * 
     * @post sets height of the Rect; center and width unchanged
     * @param h the desired height.
     */
    public void height(int h)
    {
        h = Math.max(h,0);
        top += (height-h)/2;
        height = h;
    }

    /**
     * Sets the top side of the rectangle.  Dimensions remain unchanged.
     * 
     * @post sets top to y; dimensions remain unchanged
     * @param y the new top coordinate.
     */
    public void top(int y)
    {
        top = y;
    }

    /**
     * Sets the bottom side of rectangle.  Dimensions remain unchanged.
     * 
     * @post sets bottom to y; dimensions remain unchanged
     * @param y the new bottom coordinate.
     */
    public void bottom(int y)
    {
        top = y-height;
    }

    /**
     * Sets the right coordinate of rectangle.  Dimensions remain unchanged.
     * 
     * @post sets the left coordinate; dimensions unchanged
     * @param x the new right coordinate.
     */
    public void right(int x)
    {
        left = x-width;
    }

    /**
     * Returns the center point of the rectangle.
     * 
     * @post returns center point of rectangle
     * @return the center point of the rectangle
     */
    public Pt center()
    {
        return new Pt(left+width/2,top+height/2);
    }

    /**
     * Sets the center point of the rectangle.  The dimensions remain
     * unchanged.
     * @post sets center of rect to p; dimensions remain unchanged
     * @param p the new center point of the rectangle
     */
    public void center(Pt p)
    {
        left(p.x()-width/2);
        top(p.y()-height/2);
    }

    /**
     * Moves the rectangle in the direction (dx,dy).
     * 
     * @post moves rectangle to left by dx and down by dy
     * @param dx the horizontal change in position
     * @param dy the vertical change in position
     */
    public void move(int dx, int dy)
    {
        left += dx;
        top += dy;
    }

    /**
     * Moves the top-left coordinate of the rectangle to (left,top).
     * The dimensions remain unchanged.
     *
     * @post moves left top of rectangle to (left,top);
     *       dimensions are unchanged
     * @param left the new left coordinate 
     * @param top the new top coordinate
     */
    public void moveTo(int left, int top)
    {
        this.left = left;
        this.top = top;
    }

    /**
     * Moves the top-left coordinate to point p
     * 
     * @post moves left top of rectangle to p
     * @param p the new top left position
     */
    public void moveTo(Pt p)
    {
        moveTo(p.x(), p.y());
    }

    /**
     * Moves the sides of the rectangle outward by dx and dy.
     * 
     * @post moves sides of rectangle outward by dx and dy
     * @param dx the change in horizontal coordinates - positive is outward
     * @param dy the change in vertical coordinates - positive is outward
     */
    public void extend(int dx, int dy)
    {
        if ((-2*dx) > width) {
            left += width/2;
            width = 0;
        } else {
            left -= dx;
            width += 2*dx;
        }

        if ((-2*dy) > height) {
            top += height/2;
            height = 0;
        } else {
            top -= dy;
            height += 2*dy;
        }
    }


    /**
     * Draw a filled rectangle (in the current mode) on drawing window d.
     * @pre d is a valid drawing window
     * @post the rectangle is filled on the drawing window d
     * @param d the target drawing window.
     * @see element.DrawingTarget#fill
     * @see element.DrawingTarget#paintMode
     * @see element.DrawingTarget#invertMode
     */
    public void fillOn(DrawingTarget d)
    {
        d.fillRect(left,top,width,height);
    }

    /**
     * Erase rectangle from the drawing window d.
     *
     * @pre d is a valid drawing window
     * @post the rectangle is erased from the drawing window
     * @param d the target drawing window
     * @see element.DrawingTarget#clear
     */
    public void clearOn(DrawingTarget d)
    {
        d.clearRect(left,top,width,height);
    }

    /**
     * Draw (in the current mode) the rectangle on the drawing window
     *
     * @pre d is a valid drawing window
     * @post the rectangle is drawn on the drawing window
     * @param d the target drawing window
     * @see element.DrawingTarget#paintMode
     * @see element.DrawingTarget#invertMode
     * @see element.DrawingTarget#draw
     */
    public void drawOn(DrawingTarget d)
    {
        d.drawRect(left,top,width,height);
    }

    /**
     * Return an integer for use as a hash code
     * 
     * @return a hash code
     */
    public int hashCode()
    // post: return a hash code
    {
        return left+top+width+height;
    }

    /**
     * Return true iff this rectangle equals the other
     * 
     * @post returns true iff two rectangles are equal
     * @param other another valid rectangle
     * @return true if the two are equal valued, false otherwise
     */
    public boolean equals(Object other)
    {
        Rect that = (Rect)other;
        return (this.left == that.left) &&
               (this.top == that.top) &&
               (this.width == that.width) &&
               (this.height == that.height);
    }

    /**
     * return a distinct copy of this rectangle
     * 
     * @post returns a distinct copy of the rectangle
     * @return a distinct copy of this rectangle
     */
    public Object clone()
    {
        return new Rect(this);
    }

    /**
     * Construct an AWT rectangle from this rect.
     * @post return the equivalent AWT Rectangle
     * @return an AWT rectangle
     * @see java.awt.Rectangle
     */
    public Rectangle Rectangle()
    {
        return new Rectangle(left,top,width,height);
    }

    /**
     * Construct a string representation of this rectangle
     * 
     * @post returns a string representation of this rectangle
     * @return a string representation of this rectangle
     */
    public String toString()
    {
        return "<Rectangle: left="+left+" top="+top+" width="+width+" height="+height+">";
    }
}
