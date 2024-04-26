# Android

This list contains many questions with answers that are necessary to know in order to pass an android developer job interview. There are 3 parts to cover all steps of android app development: Java, Kotlin, and Android. Questions were taken from [this post](https://github.com/niharika2810/android-interview-questions).

PART 1 — Java
Interfaces vs Abstract Classes. Why Interfaces if abstract classes are already there.
In Java, both interfaces and abstract classes serve as mechanisms for defining contracts and enforcing certain behaviors in classes that implement or extend them. While they share some similarities, they have distinct differences and serve different purposes.

Abstract Classes:
An abstract class is a class that cannot be instantiated on its own and typically contains one or more abstract methods.
Abstract methods are methods declared without an implementation. Subclasses must provide concrete implementations for these abstract methods.
Abstract classes can also contain concrete methods with implementations.
Abstract classes can have constructors, fields, and other members just like regular classes.
A class can extend only one abstract class.

Interfaces:
An interface in Java is a reference type that defines a set of abstract methods and/or constant fields.
Interfaces cannot contain concrete method implementations, only method declarations.
A class can implement multiple interfaces, providing implementation details for all the methods declared in those interfaces.
Interfaces can’t have constructors or instance fields (prior to Java 8), although they can have constants (static final fields).
Interfaces provide a way for unrelated classes to achieve polymorphism, as multiple classes can implement the same interface.

When to Use Each:
Use abstract classes when you want to provide a common base for a group of related classes that share some behavior or state.
Use interfaces when you want to define a contract that multiple unrelated classes can adhere to, providing a way for polymorphism without the need for inheritance.
If you need to evolve your design over time, favor interfaces, as they allow for more flexibility. Classes can implement multiple interfaces, enabling them to inherit behaviors from different sources.

JAVA 8 new features
Hashmap implementation, ArrayList implementation
HashMap in Java stores the data in (Key, Value) pairs, and you can access them by an index of another type (e.g. an Integer). One object is used as a key (index) to another object (value). If you try to insert the duplicate key in HashMap, it will replace the element of the corresponding key.

Java HashMap is similar to HashTable, but it is unsynchronized. It allows to store the null keys as well, but there should be only one null key object and there can be any number of null values. This class makes no guarantees as to the order of the map. Hierarchy: HasghMap -> AbstractMap -> Map -> Iterable
A HashMap is a data structure that is used to store and retrieve values based on keys. Some of the key characteristics of a hashmap include:
`Fast access time`: HashMaps provide constant time access to elements, which means that retrieval and insertion of elements are very fast, usually O(1) time complexity.
`Uses hashing function`: HashMaps uses a hash function to map keys to indices in an array. This allows for a quick lookup of values based on keys.
`Stores key-value pairs`: Each element in a HashMap consists of a key-value pair. The key is used to look up the associated value.
`Supports null keys and values`: HashMaps allow for null values and keys. This means that a null key can be used to store a value, and a null value can be associated with a key.
`Not ordered`: HashMaps are not ordered, which means that the order in which elements are added to the map is not preserved. However, LinkedHashMap is a variation of HashMap that preserves the insertion order.
`Allows duplicates`: HashMaps allow for duplicate values, but not duplicate keys. If a duplicate key is added, the previous value associated with the key is overwritten.
`Thread-unsafe`: HashMaps are not thread-safe, which means that if multiple threads access the same hashmap simultaneously, it can lead to data inconsistencies. If thread safety is required, ConcurrentHashMap can be used.
`Capacity and load factor`: HashMaps have a capacity, which is the number of elements that it can hold, and a load factor, which is the measure of how full the hashmap can be before it is resized.

Advantages of Java HashMap:
`Fast retrieval`: HashMaps provide constant time access to elements, which means that retrieval and insertion of elements is very fast.
`Efficient storage`: HashMaps use a hashing function to map keys to indices in an array. This allows for quick lookup of values based on keys, and efficient storage of data.
`Flexibility`: HashMaps allow for null keys and values, and can store key-value pairs of any data type.
`Easy to use`: HashMaps have a simple interface and can be easily implemented in Java.
`Suitable for large data sets`: HashMaps can handle large data sets without slowing down.

Complexity:
Insertion (put operation): The time complexity for insertion in a HashMap is O(1) on average. However, in the worst-case scenario where there are many hash collisions, insertion can degrade to O(n), where n is the number of elements in the bucket.
Retrieval (get operation): The time complexity for retrieval in a HashMap is also O(1) on average. Like insertion, retrieval can degrade to O(n) in the worst-case scenario due to hash collisions.
Deletion (remove operation): Similar to insertion and retrieval, the time complexity for deletion in a HashMap is O(1) on average, but it can degrade to O(n) in the worst case.
Search: Searching for a key in a HashMap has a time complexity of O(1) on average, but as with other operations, it can degrade to O(n) in the worst-case scenario.
Iteration: Iterating over the elements of a HashMap using iterators or enhanced for-loops has a time complexity of O(n), where n is the number of elements in the HashMap.

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private List<Entry<K, V>>[] buckets;
    private int size;

    public HashMap() {
        buckets = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Null key is not allowed.");

        int index = getIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        // Check if key already exists in the bucket
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        // Key doesn't exist, add a new entry
        buckets[index].add(new Entry<>(key, value));
        size++;

        // Check if rehashing is needed
        if ((double) size / buckets.length > LOAD_FACTOR) {
            resizeAndRehash();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        if (buckets[index] != null) {
            for (Entry<K, V> entry : buckets[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null; // Key not found
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private void resizeAndRehash() {
        List<Entry<K, V>>[] oldBuckets = buckets;
        buckets = new List[oldBuckets.length * 2];
        size = 0;

        for (List<Entry<K, V>> bucket : oldBuckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Other methods like remove, containsKey, size, etc. can be implemented similarly
}
```

* ArrayList
ArrayList is a Java class implemented using the List interface. Java ArrayList, as the name suggests, provides the functionality of a dynamic array where the size is not fixed as an array. Also, as a part of the Collection framework, it has many features not available with arrays.
Dynamic Sizing: Unlike arrays, ArrayLists automatically resize themselves when elements are added or removed. This means you don’t need to specify the size of an ArrayList when you create it.
Indexed Access: Like arrays, ArrayLists allow you to access elements by their index. You can retrieve and modify elements using methods like get(int index) and set(int index, E element).
Ordered Collection: ArrayList maintains the order of elements, meaning the elements are stored in the order they are added.
Resizable: As mentioned earlier, ArrayLists can grow or shrink dynamically, which makes them suitable for situations where the size of the collection may change.
Accepts Duplicates: ArrayLists can contain duplicate elements, meaning you can add the same element multiple times.
Generics Support: ArrayLists in Java support generics, which means you can specify the type of elements the ArrayList will contain. This helps ensure type safety at compile time.

```java
import java.util.Arrays;

public class ArrayList<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) elements[index];
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        elements[index] = element;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("Initial list: " + list);

        list.remove(1);
        System.out.println("List after removing element at index 1: " + list);

        list.set(0, 5);
        System.out.println("List after setting element at index 0 to 5: " + list);
    }
}
```

How to make a class immutable
Immutable class in java means that once an object is created/initialized, we cannot change its content. In Java, all the wrapper classes (like Integer, Boolean, Byte, Short) and String class is immutable. We can create our own immutable class as well.

Requirements:
Declare the class as final so it can’t be extended.
Make all of the fields private so that direct access is not allowed.
Don’t provide setter methods for variables.
Make all mutable fields final so that a field’s value can be assigned only once.
Initialize all fields using a constructor method performing deep copy.
Perform cloning of objects in the getter methods to return a copy rather than returning the actual object reference.

Usage examples:
Financial Transactions: In a banking or financial application, immutable classes can be used to represent financial transactions. Once a transaction is created, its details such as amount, date, and involved parties should remain unchanged. Immutable transaction objects ensure data integrity and audit trail accuracy.
Employee Records: In an HR management system, immutable classes can represent employee records. Immutable objects can store information such as employee ID, name, department, and hire date. This ensures that once an employee record is created, its details remain consistent throughout its lifecycle.
Order Processing: In an e-commerce platform, immutable classes can be used to represent orders. Immutable order objects can store information such as order ID, customer details, items purchased, and total amount. This prevents accidental modifications to orders and ensures accurate order processing.

```Text
String pools,intern keyword,new() keyword
Linked list vs Array,Array vs ArrayList,Set vs List vs Map,Iterator vs Enumeration.
How to compare two objects in Set?(hint : equals() and hashcode() overriding)
How ClickListener implemented in Java?
Programs based on Inheritance and multithreading. (Most Important)
1.) Add(String string) 2.) Add (Object object), you called Add(null) what will be the output here?
Why main Static?
Static vs Singleton? Which to prefer when?
Can a static method be overloaded/overridden?Can a constructor be made static? Can we override/overload constructor? Which type of error you get-Compile/Runtime.
Can we have final/static/default methods in interface/abstract classes?
Check for every keyword? Which type of error-Compile time or runtime(I remember it after I tried a sample in Android studio).
Stack vs Heap allocation in Java.
Why is String immutable?
Why reflection in Java?Introspection vs Reflection?
Anonymous vs Inner Classes.Static Inner Classes. Can we have Static inner classes on Top?
What is the naming structure for an inner class in java?
Default value for Access specifiers,primitie types.
Protected vs Default. Do static retain value if an application is removed from recent.
How is the Exception class implemented in java?Try/Catch with finally block.
Any situation when finally won’t get executed?
Is Java pass by value/reference?
What will be the result if StringBuffer value compared with String — strbuf.equals(str) /str.equals(strbuf)
Synchronization in Java.
Can one interface extend/implement other/Can one abstract class extend other? Can interface extend abstract classes?
Runtime vs compile time polymorphism with real-world scenarios. How do you define encapsulation?
this vs super() keyword
Implement own Garbage collection in java.
Why runnable interface if we have already had Thread class in Java?
Why thread pool in Java?
Order of call of constructors during Inheritance.
```

* PART 2 — Kotlin
Why should we use Kotlin for Android Development?
Kotlin offers several advantages for Android development. Firstly, it’s a modern language that addresses many pain points of Java, the traditional language for Android. Kotlin reduces boilerplate code, enhancing productivity and readability. Its null safety features help prevent common bugs, which is crucial for stable Android apps. Kotlin’s interoperability with Java allows for a smooth transition, enabling developers to leverage existing Java libraries and frameworks within their Kotlin projects. Moreover, its support by Google as a preferred language for Android development further solidifies its position as a reliable choice for building robust and efficient Android applications.

Kotlin operators — apply, run, let?
These Kotlin operators, or rather functions, are powerful tools that help streamline code and manage object scopes efficiently. These functions, along with the safe call operator, contribute significantly to Kotlin’s concise and safe coding practices, making code more readable and less prone to errors.

Apply: This function is used to initialize or configure an object. It operates on the object itself and returns the same object after applying the given block of code. It’s handy when you need to perform operations on an object immediately after its creation.

```kotlin
val person = Person().apply {
    name = "John"
    age = 30
}
```

Run: The run function is similar to apply, but it doesn't operate directly on the object. Instead, it executes the provided block of code within the context of the object and returns the result of that block.

```kotlin
val greeting = person.run {
    "Hello, $name! You are $age years old."
}
```

Let: let is used to execute code only if an object isn’t null. It's particularly helpful for avoiding null pointer exceptions by performing actions on nullable objects safely.

```kotlin
val length = str?.let {
    // Execute this block only if 'str' is not null
    it.length
} ?: 0 // Default value if 'str' is null
```

Safe call operator ?: This isn’t exactly an operator or function like the others mentioned, but it’s an essential Kotlin feature. The ?. operator is used to safely call methods or access properties on nullable objects without causing null pointer exceptions.
```kotlin val length = str?.length // Safe access to 'length' property of 'str'```

How Kotlin is null-safe?
Kotlin addresses the infamous null pointer exceptions prevalent in languages like Java by integrating null safety features into its core design. Here are some key aspects that make Kotlin null-safe. By enforcing strict null safety rules, providing clear syntax for handling nullability, and offering features like safe access operators and smart casts, Kotlin significantly reduces the chances of encountering null pointer exceptions, making code more robust and reliable.

Nullable Types: In Kotlin, types are non-nullable by default. If you declare a variable without specifying it as nullable, the compiler ensures that it cannot hold a null value.
val name: String = "John" // Non-nullable type
// name = null // This will result in a compilation error
Nullable Types with Safe Access: To allow null values, you must explicitly declare a type as nullable using the ? operator.
val name: String? = null // Nullable type
val length = name?.length // Safe access using the safe call operator
Safe Call Operator (?.): This operator allows chaining method calls or property access on potentially nullable objects without causing a null pointer exception. If the object is null, the call is skipped, and the expression evaluates to null.
val length = name?.length // Safe access to 'length' property of 'name'
Elvis Operator (?:): This operator provides a default value when dealing with nullable types. If the expression before ?: is null, the value after ?: is used.
val length = name?.length ?: 0 // Use 0 as the default length if 'name' is null
Smart Casts: Kotlin’s compiler tracks null checks, allowing automatic smart casts for nullable types after null checks within conditional statements, reducing the need for explicit type casting.

```kotlin
if (name != null) {
    // 'name' is automatically cast to non-nullable String within this block
    println(name.length)
}
```

What is the difference between var and val?
In Kotlin, var and val are used to declare variables, but they differ in terms of mutability:

val (Immutable):

val is short for "value" and declares an immutable reference. Once assigned, its value cannot be changed.
It’s similar to declaring a constant in other languages.
val pi = 3.14
// pi = 3.14159 // This will result in a compilation error, as 'val' is immutable
var (Mutable):

var stands for "variable" and declares a mutable reference. It allows the value to be reassigned after its initial assignment.
Use var when you need the variable to be mutable, meaning its value can change during the execution of your program.
var counter = 0
counter += 1 // This works fine, as 'var' allows reassignment
The choice between val and var depends on whether you anticipate the value of the variable to change after its initial assignment. It's generally a good practice to prefer val over var when possible because immutable values can help prevent bugs related to unexpected changes and make code more predictable and easier to reason about. However, there are cases where mutability is necessary, and in those situations, using var is appropriate.

How const is different from val?
In Kotlin, const and val are both used for declaring constants, but they have significant differences:

const:
const is used to declare compile-time constants at the top level or inside an object declaration or a companion object.
It can’t be used for local variables or properties of classes.
The value assigned to a const property must be known at compile time and should be a primitive type or a String.
const properties are implicitly public and are treated as static final fields in Java.
const val PI = 3.14
val (Immutable):

val declares an immutable reference, but it is not a compile-time constant.
It’s used for variables whose values are known at runtime and can be assigned through a function call or a non-primitive type.
val can be declared at the top level, within classes, or in local scopes.
val radius = 5 // Value known at runtime

Key Differences:
const properties are compile-time constants and have strict requirements on initialization (known at compile time), while val is initialized at runtime.
const properties are limited to certain types and places where they can be declared, whereas val can be used more flexibly.
const properties are implicitly public, whereas val can have different visibility modifiers.
const properties are not allowed to have custom getters/setters, while val can have custom logic associated with its access.
In essence, const is specifically for compile-time constants with stricter rules, whereas val is for declaring immutable variables whose values are known at runtime.

What is the difference between ?. and !!
In Kotlin, ?. and !! are operators used to deal with potentially nullable values, but they behave quite differently:

The ?. operator is called the safe call operator

It allows you to safely access properties or call methods on nullable types without causing a null pointer exception.
If the receiver (the object or variable before ?.) is not null, the operation is performed; otherwise, it returns null.
val length = str?.length // Safe access to 'length' property of 'str'
Not-null Assertion Operator !!

The !! operator is the not-null assertion operator.
It’s used to assert that a nullable value is not null. It tells the compiler that you’re sure the value isn’t null and to proceed even if the type is nullable.
If the value is actually null at runtime, it will throw a NullPointerException.
val length = str!!.length // Assumes 'str' is not null, can throw NPE if 'str' is null
Usage Considerations:

Use ?. when you're unsure if a variable might be null and want to safely access its properties or methods without causing a null pointer exception. It's a safer way to work with nullable types.
Use !! when you, as the developer, are confident that a nullable variable isn’t null at a particular point in your code and you want to proceed, explicitly indicating to the compiler that you acknowledge the risk of a potential null pointer exception. However, use it sparingly as it bypasses null safety checks and can lead to runtime crashes if misused.
In general, it’s recommended to prefer the safe call operator ?. and utilize null safety features in Kotlin to avoid null pointer exceptions rather than resorting to the not-null assertion operator !!, which can introduce risks into your code if used without due caution.

How to define static functions in Kotlin?
In Kotlin, you can create functions that behave like static functions in other languages by defining them as top-level functions or within companion objects. Here are two ways to define static functions:

1. Top-Level Functions:
Simply declare functions outside of any class or object. These functions can be accessed from anywhere within the same file or module.

```kotlin
// Top-level function
fun myStaticFunction() {
    // Function logic
}
```

2. Companion Object Functions:
Define functions within a companion object inside a class. These functions can be accessed using the class name as a qualifier.

```kotlin
class MyClass {
    companion object {
        // Static function inside companion object
        fun myStaticFunction() {
            // Function logic
        }
    }
}
```

To call these static functions:
For top-level functions, simply call them by their name from any part of your code.
myStaticFunction()
For functions within companion objects, use the class name as a qualifier.
MyClass.myStaticFunction()
Both approaches allow you to create functions that behave similarly to static functions in other languages, providing reusable logic that can be accessed without the need for an instance of a class.

What are Data Classes in Kotlin?
In Kotlin, data classes are a concise way to declare classes that are primarily used to hold data. They’re designed to automatically provide several functionalities, reducing boilerplate code typically required for classes that are meant to only hold data.

To declare a data class, you use the data keyword before the class declaration:

data class Person(val name: String, val age: Int)
Here’s what data classes provide automatically:

1. Automatic Generation of equals(), hashCode(), and toString():
Data classes automatically generate equals(), hashCode(), and toString() methods based on the properties declared in the primary constructor.

2. Component Functions:
Data classes also have automatically generated component functions (component1(), component2(), etc.). These functions allow you to destructure objects conveniently, especially useful in scenarios like pattern matching or decomposition.
val person = Person("Alice", 30)
val (name, age) = person // Destructuring declaration

3. Copy Function:
They include a copy() function that lets you create a copy of an object with modified properties. It's helpful for creating a new object with some updated values while keeping the rest intact.
val person = Person("Bob", 25)
val olderPerson = person.copy(age = 26) // Creating a copy with modified age
Data classes are especially handy when you need to create simple container classes to hold data. They help in reducing boilerplate code by automatically providing standard functionalities, making code more concise and readable.

What do you mean by Sealed classes in Kotlin?
In Kotlin, a sealed class is a class that restricts the inheritance hierarchy of classes that can extend it. It’s used to represent restricted class hierarchies wherein a class can only be subclassed within its own file. This restriction allows the compiler to determine all possible subclasses of the sealed class at compile time, which can be useful in scenarios like when you want to define a closed set of types.

Key points about sealed classes:

Limited Hierarchy: Sealed classes allow you to define a fixed set of classes that can extend the sealed class. All direct subclasses of a sealed class must be declared within the same file where the sealed class is declared.
Usage with When Expressions: Sealed classes are frequently used with when expressions in Kotlin. When used in conjunction, the compiler can ensure that all subclasses are accounted for, making it unnecessary to add an else clause.
sealed class Result
class Success(val data: String) : Result()
class Error(val message: String) : Result()

fun handleResult(result: Result) {
    when (result) {
        is Success -> println("Success: ${result.data}")
        is Error -> println("Error: ${result.message}")
    }
}
Constructor Visibility: By default, the constructors of subclasses must be declared in the same file as the sealed class, but their visibility can be modified (e.g., private) if needed.

Sealed classes are handy when you want to define a restricted hierarchy of classes, ensuring exhaustive checks in code while allowing a closed set of subclasses.

Collections in Kotlin.
Kotlin provides a robust set of collection types that are part of its standard library, making it easy to work with collections of data. These collections offer various functionalities, immutability options, and are compatible with Java collections.

Immutable Collections:

1. List: An ordered collection that allows duplicate elements. It has a read-only interface.
```val immutableList: List<String> = listOf("apple", "banana", "orange")```
2. Set: A collection that contains unique elements. There’s no defined order in the elements.
```val immutableSet: Set<Int> = setOf(1, 2, 3, 4, 5)```
3. Map: A collection of key-value pairs. Each key is unique, and it maps to a corresponding value.
```val immutableMap: Map<String, Int> = mapOf("one" to 1, "two" to 2, "three" to 3)```

Mutable Collections:
Mutable collections allow modification of their elements after creation.

1. MutableList
Ordered Collection: MutableList maintains the order of elements based on their insertion sequence. It allows duplicate elements.
Access by Index: Elements in a MutableList can be accessed by their index, modified, and removed based on their position.

```val mutableList: MutableList<Int> = mutableListOf(1, 2, 3)
mutableList.add(4)
```

2. MutableSet
Unordered Collection: MutableSet does not maintain the order of elements and does not allow duplicates.
Fast Lookup: It provides fast lookup times for elements but does not have the concept of positional access or indices.

```val mutableSet: MutableSet<Double> = mutableSetOf(1.2, 3.4, 5.6)
mutableSet.add(7.8)
```

3. MutableMap

```val mutableMap: MutableMap<String, String> = mutableMapOf("key1" to "value1", "key2" to "value2")
mutableMap["key3"] = "value3"
```

Operations and Functionalities:
Kotlin’s collections offer a wide range of functions for manipulation and traversal, often in a functional programming style:

forEach: Iterates over elements.
map: Transforms elements.
filter: Selects elements based on a condition.
reduce / fold: Combines elements into a single value.
sort / sortBy / sortedBy: Arranges elements.
groupBy: Groups elements based on a key.

Extension Functions and Operators:
Kotlin’s collections also benefit from extension functions and overloaded operators (+, -, etc.) for various operations, making code concise and readable.

For instance:

```kotlin
val numbers = listOf(1, 2, 3, 4, 5)
val doubled = numbers.map { it * 2 } // [2, 4, 6, 8, 10]
val sum = numbers.reduce { acc, next -> acc + next } // 15
```

Nullability Handling:
Kotlin also provides nullable and non-nullable versions of its collection types (List?, List) to handle nullability more explicitly.

Collections in Kotlin are versatile, offering both immutable and mutable options along with a rich set of functions, making them convenient and efficient for various use cases.

```Text
Difference between lateinit and lazy.
What is Kotlin synthetic binding?
Difference between Kotlin Synthetics, View binding and Butterknife?
What are extension functions in Kotlin?
What are lambda expressions?
What are Higher-Order functions in Kotlin?
Difference between let, run, with, also, apply in Kotlin.
What is the best way for performing tasks on a background thread in kotlin?
What are Coroutines in Kotlin?
What are the different Coroutines Scope?
Why use suspend function in Kotlin Coroutines?
Explain Coroutine LifecycleScope.
What is Kotlin Flow?
Kotlin Flow vs RxJava.
Room vs Realm? Why not Realm?
```

PART 3 — Android

```Text
Activity/Fragment/Broadcast/Application/Service Lifecycle (Explain with one line description)
Which lifecycle method gets called when A goes to B/come back to A? Activity Launch Modes with examples
Let’s say 4 activities are there A->B->C->D. How to launch A from D finishing in between activities?
4 Activities are there A->B->C->D.How to finish application from D if none of the activities opened earlier finished till now?
How CLEAR_TOP flag intent works in android?
Service vs IntentService?When to prefer which type of service?
Explain ways for Activity to activity communication/Activity to fragment communication/Fragment to fragment communication.
How to make a service persist even after Application killed?
How to achieve Interprocess communication in android?
How Async task internally works? Async task implementation?
Cons of using Async task? How to overcome this limitation?
Why Job Scheduler?
Notification related changes in Oreo?
How to handle Background service and execution limits with new Oreo Updates?
Have you seen Google I/O 2018? What were the updates for Android there?
Why do we use Headless fragments?
Memory Leakage, Causes, and preventions in Android? I have written a blog out here.You can refer this too
Ways to optimize an Android Application.
Proguard-Obfuscation/ Minification, Build Variants, Flavours.
How to achieve MultiThreading in Android?
Suppose you have 10 images to fetch from Server. What is the best way to fetch images? (Hint: Read about ThreadPool and Executor)
Builder vs Factory Pattern with an example
Why MVP not MVC? They will ask you to make a wireframe kind of thing on paper to demonstrate MVP pattern in android.
Why RXJAVA so popular nowadays?
Problems in RXJAVA? They can provide you with some scenarios to get to know if you can find out the problems with it.
Map vs Flat map? (Most commonly asked in RXJAVA)
Should we provide a context in presenter/Model? Answering yes/no is not enough. You have to provide a solution for it.
Why Schedulers and How Rxjava internally works with them? SusbcribeOn vs ObserveOn, Schedulers on which both of these work? .subscribe(new Subscriber…) vs .subscribe(new Observer…)
IOScheduler/new Thread Scheduler in RXJava
How dagger internally worksDagger vs Dagger2? Explain the purpose of every annotation taken in Dagger2.
How do you decide scope in Dagger? Why Qualifier annotation?
Why do we use Handlers? Do you know the Loopers? What’s the role of Message queue? With this, they will go for Main Thread working. They will also ask you about the exceptions we get as Looper.prepare() exceptions
Cause of Memory leak during the Async Task?
Explain Singleton pattern. Problems with it and Handling. (hint: Double checked locking/synchronization)
Why do you prefer Retrofit over Volley? Why Picasso? Now they will ask all the third party libraries you have used. Be honest with your answer. They may go deep in any of those.
Why ConstraintLayout?Barriers vs Guidelines, Why Group/Chains?
Why do we prefer Framelayout for Fragments?
Why ANR?How to prevent it?
Do async.execute make threads in parallel or serial?
How to make a custom view in android? Explain lifecycle methods. Difference between onLayout/onMeasure.
Have you ever faced any issue with SingleInstance launch mode or example if you have used it? Providing real-life examples are preferred more.
When 1 fragment added over another-Explain lifecycle methods. Another question would be if you replace instead of adding it. The 3rd question will be if you have back stack there.
Why fragment newInstance() is there (Best practice for instantiating a new Android Fragment)
Suppose you have to update location every minute even if you don’t get permission. But the tricky thing here is how will you notify the server-Through the same service you were sending updates using FusedLocationProvider/Will you use some other service or broadcast. Just design the whole thing.
Observable.just/Observable.from/Observable.create in RXJAVA?
Why Instant Apps? How do you achieve this?
How to load Large Bitmaps? Handle Memory leaks and Sample sizing
How parcelable internally works? Design it.
What happens if your serializable class contains a member which is not serializable? How to handle it?
Why do we use WeakReferences?
How to retain data on orientation change? Explain lifecycle during orientation changes.
How to make endless circular recyclerView?
Suppose you have given an array of array of custom Objects. Which data structure will you use to show these on UI?
How do you handle opening activity using notifications?
How to achieve Push notifications in android? Explain behavior during App in front/Background-How to handle both scenarios.
How to download files in parallel?
Why Realm?Realm Methods, Realm vs Sqlite.
How to handle different screen sizes/density/languages/orientation in android?
What the Fragment’s method setRetainInstance(boolean) does?
Keep yourself up to date with the latest library versions if using.
Why is there a need for JobIntentService?(hint:: Oreo update)
Basics for Testing in Android-They will ask some questions about Espresso,mockito,roboelectric(not in that deep).Stub vs mock. Also if you are following MVP pattern. Be prepared for the questions now.
How the OS handles process flow. When is the app killed or removed from recent memory? How OS decides this.
Which method gets called when home button pressed, back pressed etc.(Most commonly asked)
Manifest related attributes -allowBackup,intent-filters,process,taskAffinity,permission vs uses-permission etc.
Draw a simple basic design you follow to make an android application(folder structure, methods, variables)
Why services preferred over Broadcast receiver for background applications
Broadcast-related changes in Oreo and Handling them.
Accessibility services in android. How do you handle system apps(I mentioned this work too, so asked)
Thread.start() vs run method,submit vs Execute ,callable vs runnable
View Holder pattern.ListView vs RecyclerView.
Remote Views and How to use them?
Inner Classes vs Static Classes?
Volatile vs Transient
Internal Implementation of Picasso, Glide
Internal implementation of Recyclerview
Design Patterns and Practical Usage
Custom View and Lifecycle
Singleton vs Static and why we use them?
Deep vs Dynamic Linking
Livedata postValue vs set
Livedata vs ObservableField
ViewModelFactory
HashCode and Equals Implementation
Dagger — Component vs SubComponenets
TDD and frameworks — if using , tell the challenges faced
GSON vs Jackson vs Moshi
MultiModule Benfeits
Android Bundle — and Dynamic Feature usage
Proguard vs R8
What is Scoped Storage and how to implement?
Observable vs Completable vs Single
Rx vs Coroutines. Which one you prefer and when?
Difference between MVP and MVVM?
Explain SOLID principles?
Difference between const and val
When is onPause() called without onStop() being called?
Dagger vs Koin?
Any 5 RxJava operatrs. How subscribeOn and observeOn works?
Headless Fragments.
Workmanager, type of requests, methods, minimum interval before a job repeat itself.
Workmanager vs JobScheduler.
Why ConstraintLayout? Tell some properties which I find worth.
How SharedPreferences work internally?
```

PART 4 — System Design

* Design Whatsapp/Chat application/Facebook messenger.
* Implement search functionality with debounce operator (If user keeps on typing, then cancel the last network call and hit for the present one, How will you achieve that?)
* Design a Location Tracking application
* Design Uber/Ola.
* Design Twitter.
* Design Bookmyshow.
