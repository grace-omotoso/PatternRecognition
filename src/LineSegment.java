import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class LineSegment {
    private final Point p;   // one endpoint of this line segment
    private final Point q;   // the other endpoint of this line segment

    /**
     * Initializes a new line segment.
     *
     * @param  p one endpoint
     * @param  q the other endpoint
     * @throws NullPointerException if either <tt>p</tt> or <tt>q</tt>
     *         is <tt>null</tt>
     */
    public LineSegment(Point p, Point q) {
        if (p == null || q == null) {
            throw new NullPointerException("argument is null");
        }
        this.p = p;
        this.q = q;
    }


    /**
     * Draws this line segment to standard draw.
     */
    public void draw() {
        p.drawTo(q);
    }

    /**
     * Returns a string representation of this line segment
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this line segment
     */
    public String toString() {
        return p + " -> " + q;
    }

    /**
     * Throws an exception if called. The hashCode() method is not supported because
     * hashing has not yet been introduced in this course. Moreover, hashing does not
     * typically lead to good *worst-case* performance guarantees, as required on this
     * assignment.
     *
     * @throws UnsupportedOperationException if called
     */
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        Point p = new Point(0,1000);
        Point q = new Point(0,1000);
        Point r = new Point(0,2000);
        Point s = new Point(0,2000);
        LineSegment[] segments = new LineSegment[2];
        ArrayList<LineSegment>  arr = new ArrayList<>();
        LineSegment lineSegment1 = new LineSegment(p,s);
        LineSegment lineSegment2 = new LineSegment(q,r);
        LineSegment lineSegment3 = new LineSegment(new Point(0,1000),new Point(0,200));
        arr.add(lineSegment1);
        arr.add(lineSegment2);
        //arr.add(lineSegment3);
        System.out.println(arr.contains(new LineSegment(new Point(0,1000),new Point(0,2000))));




    }

}