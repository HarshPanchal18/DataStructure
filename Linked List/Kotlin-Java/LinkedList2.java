import java.util.LinkedList;
import java.util.Scanner;

public class LinkedList2 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        /* Creating object of class linkedList */

        LinkedList<Integer> Llist = new LinkedList<>();
        System.out.println("Singly Linked List Test\n");

        int choice;

        do {
            /* Perform list operations */
            System.out.println("\nSingly Linked List Operations\n");
            System.out.println("1. insert at end");
            System.out.println("2. insert at begining");
            System.out.println("3. insert at position");
            System.out.println("4. delete at position");
            System.out.println("5. Display link list");
            System.out.println("6. Exit");

            System.out.println("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Enter integer element to insert last");
                    int item = input.nextInt();
                    Llist.addLast(item);
                }
                case 2: {
                    System.out.println("Enter integer element to insert first");
                    int item1 = input.nextInt();
                    Llist.addFirst(item1);
                }
                case 3: {
                    System.out.println("Enter integer element to insert");
                    int num = input.nextInt();
                    System.out.println("Enter position");
                    int pos = input.nextInt();
                    if (pos < 1 || pos > Llist.getLast() + 1)
                        System.out.println("Invalid position\n");
                    else
                        Llist.add(pos, num);
                }
                case 4: {
                    System.out.println("Enter position");
                    int p = input.nextInt();
                    if (p < 1 || p > Llist.getLast() + 1)
                        System.out.println("Invalid position\n");
                    else
                        Llist.remove(p);
                }
                case 5:
                    System.out.println(Llist);
                default:
                    System.out.println("Wrong Entry or You Selected To exit\n ");
                    input.close();
            }
        } while (true);

    }
}
