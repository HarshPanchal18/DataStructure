import java.util.Scanner;

class Node {
    protected int data;
    protected Node link;

    public Node() {
        link = null;
        data = 0;
    }

    public Node(int d, Node n) {
        data = d;
        link = n;
    }

    public void setLink(Node n) {
        link = n;
    }

    public void setData(int d) {
        data = d;
    }

    public int getData() {
        return data;
    }

    public Node getLink() {
        return link;
    }
}

class linkedList {
    protected Node start;
    protected Node end;
    public int size;

    linkedList() {
        start = null;
        end = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void insertAtStart(int d) {
        Node nptr = new Node(d, null);
        size++;

        if (start == null) {
            start = nptr;
            end = start;
        } else {
            nptr.setLink(start);
            start = nptr;
        }
    }

    public void insertAtEnd(int d) {
        Node nptr = new Node(d, null);
        size++;

        if (start == null) {
            start = nptr;
            end = start;
        } else {
            end.setLink(nptr);
            end = nptr;
        }
    }

    public void insertAtPos(int d, int pos) {
        Node nptr = new Node(d, null);
        Node ptr = start;
        pos--;

        for (int i = 1; i < size; i++) {
            if (i == pos) {
                Node tmp = ptr.getLink();
                ptr.setLink(nptr);
                nptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size++;
    }

    public void deleteAtPos(int pos) {
        if (pos == 1) {
            start = start.getLink();
            size--;
            return;
        }

        if (pos == size) {
            Node s = start;
            Node t = start;

            while (s != end) {
                t = s;
                s = s.getLink();
            }
            end = t;
            end.setLink(null);
            size--;
            return;
        }
        Node ptr = start;
        pos--;

        for (int i = 1; i < size - 1; i++) { // traverse the list till the entered position
            if (pos == i) {
                Node tmp = ptr.getLink();
                tmp = tmp.getLink();
                ptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size--;
    }

    public void display() {
        System.out.println("\nSingly Linked List: ");
        if (size == 0) {
            System.out.println("Empty List\n");
            return;
        }
        if (start.getLink() == null) { // If list has only one element
            System.out.println(start.getData());
            return;
        }

        Node ptr = start;
        System.out.print(ptr.getData() + "->");
        ptr = start.getLink();
        while (ptr.getLink() != null) {
            System.out.print(ptr.getData() + "->");
            ptr = ptr.getLink();
        }
        System.out.print(ptr.getData());
    }
}

public class LinkedList {
    public static void main(String a[]) {
        Scanner scan = new Scanner(System.in);
        linkedList list = new linkedList();

        char ch;
        do {
            System.out.println("1. Insert at Beginning");
            System.out.println("2. Insert at Last");
            System.out.println("3. Insert at Position");
            System.out.println("4. Delete from Position");
            System.out.println("5. Display list");
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the element to insert at Beginning: ");
                    list.insertAtStart(scan.nextInt());
                    break;

                case 2:
                    System.out.print("Enter the element to insert at Ending: ");
                    list.insertAtEnd(scan.nextInt());
                    break;

                case 3:
                    System.out.print("Enter the element to insert: ");
                    int num = scan.nextInt();

                    System.out.println("Enter the Position: ");
                    int pos = scan.nextInt();

                    if (pos <= 1 || pos > list.getSize())
                        System.out.println("Invalid Position");
                    else
                        list.insertAtPos(num, pos);
                    break;

                case 4:
                    System.out.println("Enter the element position to delete from: ");
                    int p = scan.nextInt();

                    if (p <= 1 || p > list.getSize())
                        System.out.println("Invalid Position");
                    else
                        list.deleteAtPos(p);
                    break;

                case 5:
                    list.display();
                    break;

                default:
                    System.out.println("Invalid choice...");
                    break;
            }
            System.out.println("\nDo you want to continue?");
            ch = scan.next().charAt(0);
        } while (ch == 'y' || ch == 'Y');

        scan.close();
    }
}
