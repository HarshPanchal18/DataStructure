# Java

1. Interface vs. Abstraction:

* Interfaces in Java provide a way to define a contract for classes that implement them, allowing for multiple classes to adhere to the same behavior without inheritance constraints.
* Abstract classes, on the other hand, can have both abstract and concrete methods and serve as a base for related classes.

2. How to increment counter using static variable:

You can increment a counter using a static variable by accessing the static variable and incrementing it within a method or function. For example:

```java
public class Counter {
    private static int count = 0;

    public static void increment() {
        count++;
    }
}
```

3. Polymorphism:

* Polymorphism in Java allows objects of different classes to be treated as objects of a common superclass through method overriding.
* This enables flexibility in the way methods are called and executed based on the actual object type at runtime.

4. ArrayList vs. LinkedList:

* ArrayList in Java is implemented as a `dynamic array`, providing fast random access but slower insertion and deletion of elements.
* LinkedList, on the other hand, is implemented as a doubly linked list, offering fast insertion and deletion but slower random access.

5. LinkedList vs. Circular LinkedList:

* A LinkedList is a linear collection of elements where each element points to the next one. In a
* Circular LinkedList, the last element points to the first element, creating a circular structure. This allows for efficient traversal from the last element to the first without the need to loop back.

6. Internal working of HashMap:

* HashMap in Java uses a hashing mechanism to store key-value pairs.
* It internally uses an array of buckets, where each bucket can hold multiple entries.
* The keys are hashed to determine the index in the array, and collisions are resolved using `linked list or tree` structures.

7. Design patterns: Singleton | Abstract | Factory:

* Singleton pattern ensures that `a class has only one instance and provides a global point of access to it`.
* Abstract Factory pattern provides `an interface for creating families of related or dependent objects without specifying their concrete classes`.
* Factory pattern defines `an interface for creating objects, allowing subclasses to alter the type of objects that will be created`.

8. Monolithic vs. Microservices:

Monolithic architecture is a `single-tiered` software application where `different components are combined into a single program`.
Microservices architecture `breaks down the application into smaller, independent services that can be developed, deployed, and scaled individually`.

### TreeMap in Java

The TreeMap in Java is a concrete implementation of the `java.util.SortedMap` interface. It provides an ordered collection of `key-value` pairs, where the `keys are ordered based on their natural order or a custom Comparator` passed to the constructor.

A TreeMap is implemented using a `Red-Black` tree, which is a type of self-balancing binary search tree. This provides efficient performance for common operations such as adding, removing, and retrieving elements, with an average time complexity of O(log n).

```java
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> treeMap = new TreeMap<>();

        treeMap.put("A", 1);
        treeMap.put("C", 3);
        treeMap.put("B", 2);

        int valueA = treeMap.get("A");
        System.out.println("Value of A: " + valueA);

        treeMap.remove("B");

        for (String key : treeMap.keySet())
            System.out.println("Key: " + key + ", Value: " + treeMap.get(key));

    }
}
```
