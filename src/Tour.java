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

    public Tour(Point p1, Point p2, Point p3, Point p4) { //debug code
        Node n1 = new Node(p1);
        Node n2 = new Node(p2,n1);
        Node n3 = new Node(p3,n2);
        Node n4 = new Node(p4,n3);
        n1.next=n4;
        first = n1;
    }
    public Tour()
    {
        first=null; //empty tour
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
        Node cur = first.next; //current node
        first.p.drawTo(first.next.p);
        while (cur!=first) //completes the circle
        {
            cur.p.drawTo(cur.next.p);
            cur=cur.next;
        }
    }
    //inserts a node to the circular linked list to the nearest node
    public void insertNearest(Point p)
    {
        if(first==null) //if adding first node
        {
            Node new_node = new Node(p);
            first = new_node;
            return;
        }
        if(first.next==null) //adding second node
        {
            first.next=new Node(p);
            first.next.next=first; //create a circular linked list
            return;
        }
        double smallest = first.p.distanceTo(p); //nearest distance
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
    //inserts a node into the linked such that the total length is smalest
    public void insertSmallest(Point p)
    {
        if(first==null) //adding first node
        {
            Node new_node = new Node(p);
            first = new_node;
            return;
        }
        if(first.next==null) //adding second node
        {
            first.next=new Node(p);
            first.next.next=first; //make it circular
            return;
        }
        Node newNode =new Node(p); //node to be inserted
        Node cur = first.next; //point to curent node
        Node next;
        Node insertion=first;
        first.next = newNode;
        newNode.next=cur;
        double smallest = this.length(); //record for smallest length
        double len;
        first.next=cur;
        while (cur!=first)
        {
            next=cur.next;
            cur.next=newNode;
            newNode.next=next;
            len = this.length();
            if(len<smallest)
            {
                insertion=cur;
                smallest=len;
            }
            cur.next=next;
            cur = cur.next;
        }
        next = insertion.next;
        insertion.next=newNode;
        newNode.next=next;
    }
    //returns a string of all points in tour
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
        Point p1 = new Point(100.0,100.0); //debug code
        Point p2 = new Point(500.0,100.0);
        Point p3 = new Point(500.0,500.0);
        Point p4 = new Point(100.0,500.0);
        Tour tour = new Tour(p1,p2,p3,p4);
        StdOut.println("size  = "+tour.size()+" length = "+tour.length());
        StdOut.println(tour.toString());
        StdDraw.setXscale(0,600);
        StdDraw.setYscale(0,600);
        tour.insertNearest(new Point(350,350));
        tour.insertNearest(new Point(50,50));
        tour.insertNearest(new Point(450,400));
        StdOut.println("size = "+tour.size()+" length = "+tour.length());
        StdOut.println(tour.toString());
        tour.insertSmallest(new Point(100,250));
        tour.insertSmallest(new Point(450,550));
        StdOut.println("size = "+tour.size()+" length = "+tour.length());
        StdOut.println(tour.toString());
        tour.draw();

    }
}
