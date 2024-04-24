public class Tour {
    private class Node
    {
        private Point p;
        private Node next;
        Node(Point p)
        {
            this.p = p;
            this.next=null;
        }
        Node(Point p, Node next)
        {
            this.next=next;
            this.p=p;
        }
    }
    Node first;

    public Tour(Point p1, Point p2, Point p3, Point p4) {
        Node n1 = new Node(p1);
        Node n2 = new Node(p2,n1);
        Node n3 = new Node(p3,n2);
        Node n4 = new Node(p4,n3);
        n1.next=n4;
        first = n1;
    }
    int size()
    {
        Node cur = first.next;
        int counter = 1;
        while(cur!=first)
        {
            counter ++;
            cur = cur.next;
        }
        return counter;
    }
    double length()
    {
        double length =0.0;
        Node cur = first.next;
        length = length + first.p.distanceTo(first.next.p);
        while(cur!=first)
        {
            length = length + cur.p.distanceTo(cur.next.p);
            cur=cur.next;
        }
        return length;
    }
    void draw()
    {
        Node cur = first.next;
        first.p.drawTo(first.next.p);
        while (cur!=first)
        {
            cur.p.drawTo(cur.next.p);
            cur=cur.next;
        }
    }
    public void insertNearest(Point p)
    {
        double smallest = first.p.distanceTo(p);
        Node nearestNode = first;
        double distance;
        Node cur = first.next;
        while (cur!=first)
        {
            distance = cur.p.distanceTo(p);
            if(smallest>distance)
            {
                nearestNode = cur;
                smallest = distance;
            }
            cur = cur.next;
        }
        Node newNode = new Node(p,nearestNode.next);
        nearestNode.next = newNode;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node cur = first.next;
        s.append(first.p.toString());
        while (cur!=first)
        {
            s.append(cur.p.toString());
            cur=cur.next;
        }
        return s.toString();
    }
    public static void main(String args[])
    {
        Point p1 = new Point(100.0,100.0);
        Point p2 = new Point(500.0,100.0);
        Point p3 = new Point(500.0,500.0);
        Point p4 = new Point(100.0,500.0);
        Tour tour = new Tour(p1,p2,p3,p4);
        StdOut.println("size  = "+tour.size()+" length = "+tour.length());
        StdOut.println(tour.toString());
        StdDraw.setXscale(0,600);
        StdDraw.setYscale(0,600);
        tour.insertNearest(new Point(350,350));
        StdOut.println("size = "+tour.size()+" length = "+tour.length());
        tour.draw();

    }
}
