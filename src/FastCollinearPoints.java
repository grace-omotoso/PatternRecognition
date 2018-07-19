import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {


    private int numberOfSegments;
    private ArrayList<Double> slopeOrders = new ArrayList<>();
    private ArrayList<Point> pointsList = new ArrayList<>();
    private ArrayList<LineSegment> segmentLists = new ArrayList<>();
    private ArrayList<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {

        checkNullity(points);
        numberOfSegments = 0;
        double previousSlope = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            slopeOrders.clear();
            Point p = points[i];
            Arrays.sort(points, p.slopeOrder());
            for (int j = 1; j < points.length; j++) {
                Point q = points[j];
                double newSlope = p.slopeTo(q);
                if (previousSlope == newSlope) {
                    slopeOrders.add(newSlope);
                    pointsList.add(points[j]);
                } else {
                    if (slopeOrders.size() >= 3) {
                        pointsList.add(p);
                        Collections.sort(pointsList);
                        LineSegment currentSegment = new LineSegment(pointsList.get(0), pointsList.get(pointsList.size() - 1));
                        if (isSegmentNew(segmentLists, currentSegment)) {
                            numberOfSegments++;
                            segmentLists.add(currentSegment);
                        }
                        slopeOrders.clear();
                        pointsList.clear();
                        previousSlope = newSlope;
                        slopeOrders.add(previousSlope);
                        pointsList.add(points[j]);
                        break;
                    } else {
                        slopeOrders.clear();
                        pointsList.clear();
                        slopeOrders.add(newSlope);
                        pointsList.add(points[j]);
                    }
                }
                previousSlope = newSlope;
            }
        }
    }// finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return numberOfSegments;
    }      // the number of line segments

    public LineSegment[] segments() {

        return segmentLists.toArray(new LineSegment[segments.size()]);
    }


    private void checkNullity(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        ArrayList<Point> pointlist = new ArrayList(Arrays.asList(points));
        if (pointlist.contains(null))
            throw new IllegalArgumentException();
        Arrays.sort(points);
        Point previousPoint = points[0];
        for (int i = 1; i < points.length; i++) {
            Point point = points[i];
            if (point.compareTo(previousPoint) == 0)
                throw new IllegalArgumentException();
            previousPoint = point;
        }
    }

    private boolean isSegmentNew(ArrayList<LineSegment> segmentOld, LineSegment segmentNew) {

        for (int i = 0; i < segmentOld.size(); i++) {
            String pnew = segmentNew.toString().split("->")[0];
            String qnew = segmentNew.toString().split("->")[1];
            LineSegment currSegment = segmentOld.get(i);
            String pold = currSegment.toString().split("->")[0];
            String qold = currSegment.toString().split("->")[1];
            if (pnew.equals(pold) && qnew.equals(qold))
                return false;
        }
        return true;
    }


    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}