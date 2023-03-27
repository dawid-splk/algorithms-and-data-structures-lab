package lab11.Zad2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class Graham {

    private class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(Point p){
            x = p.x;
            y = p.y;
        }
        public String toString(){
            return "(" + x + "," + y + ")";
        }
    }

    private ArrayList<Point> points;

    public Graham(){
        points = new ArrayList<>();
    }

    public void addPoint(int x, int y){
        points.add(new Point(x, y));
    }

    public void convexHull(){

        Point p = getStart();
        ArrayList<Point> pointsCopy = polarSort();
        DynamicSizeStack<Point> stack = new DynamicSizeStack<>(3);

        stack.push(p);
        stack.push(pointsCopy.get(0));
        stack.push(pointsCopy.get(1));

        for (int i = 2; i < pointsCopy.size(); i++) {
            Point next = pointsCopy.get(i);
            Point q = stack.nextToTop();
            Point r = stack.top();
            int det = next.x * q.y + next.y * r.x + q.x * r.y - q.y * r.x - next.x * r.y - next.y * q.x;
            while(det < 0){       //dopoki wektor q->r jest po prawej stronie wektora p->q
                stack.pop();
                q = stack.nextToTop();
                r = stack.top();
                det = next.x * q.y + next.y * r.x + q.x * r.y - q.y * r.x - next.x * r.y - next.y * q.x;
            }
            stack.push(pointsCopy.get(i));
        }

        ArrayList<Point> print = new ArrayList<>();

        System.out.println("\n\nOur convex hull: ");
        int j = stack.getLastIndex();
        for (int i = 0; i < j; i++) {
            print.add(stack.pop());
        }
        for (int i = print.size() - 1; i > 0; i--) {
            System.out.print(print.get(i) + ", ");
        }
        System.out.print(print.get(0));
    }

    private ArrayList<Point> polarSort(){
        Point p = getStart();
        ArrayList<Point> pointsCopy = new ArrayList<>();
        ArrayList<Point> toRemove = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            if(points.get(i) != p) {
                pointsCopy.add(new Point(points.get(i)));
            }
        }

        Comparator<Point> comp = new Comparator<>() {
            @Override
            public int compare(Point q, Point r) {
                int det = p.x * q.y + p.y * r.x + q.x * r.y - q.y * r.x - p.x * r.y - p.y * q.x;
                if (det > 0) {
                    return -1;
                }
                if (det < 0) {
                    return 1;
                }
                if(Math.pow(p.x - q.x, 2) + Math.pow(p.y - q.y, 2) > Math.pow(p.x - r.x, 2) + Math.pow(p.y - r.y, 2)){
                    toRemove.add(r);        //przypadek z usuwaniem punktow z takim samym katem
                } else {
                    toRemove.add(q);       //przypadek z usuwaniem punktow z takim samym katem
                }
                return 0;
            }
        };
        pointsCopy.sort(comp);
        for (int i = 0; i < toRemove.size(); i++) {
            pointsCopy.remove(toRemove.get(i));
        }
        return pointsCopy;
    }

    public Point getStart(){
        ArrayList<Point> lowest = new ArrayList<>();

        int min = points.get(0).y;
        lowest.add(points.get(0));

        for (int i = 1; i < points.size(); i++) {
            if(points.get(i).y < min){
                lowest.clear();
                lowest.add(points.get(i));
                min = points.get(i).y;
            }
            if(points.get(i).y == min){
                lowest.add(points.get(i));
            }
        }

        Point p = lowest.get(0);
        if(lowest.size() > 1){
            min = lowest.get(0).x;
            for (int i = 1; i < lowest.size(); i++) {
                if(lowest.get(i).x < min){
                    p = lowest.get(i);
                    min = lowest.get(i).x;
                }
            }
        }
        return p;
    }

    public void printPoints(){
        System.out.println("All points: ");
        for (int i = 0; i < points.size() - 1; i++) {
            System.out.print(points.get(i) + ", ");
        }
        System.out.print(points.get(points.size() - 1));
    }
}
