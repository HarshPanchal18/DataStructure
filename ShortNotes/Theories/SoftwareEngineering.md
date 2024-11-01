# Software Engineering

## Design Principle vs Design Pattern

*In software engineering, design principle and design pattern are not the same.*

* Design Principle

Design principles provide `high level` guidelines to `design better software applications`. They do not provide implementation guidelines and are `not bound` to any programming language. The `SOLID (SRP, OCP, LSP, ISP, DIP)` principles are one of the most popular sets of design principles.

>For example, the Single Responsibility Principle (SRP) suggests that a class should have only one reason to change. This is a high-level statement which we can keep in mind while designing or creating classes for our application. SRP does not provide specific implementation steps but it's up to you how you implement SRP in your application.

* Design Pattern

Design Pattern provides `low-level` solutions related to `implementation`, of commonly occurring object-oriented problems.

In other words, `design pattern suggests a specific implementation for the specific object-oriented programming problem`. For example, if you want to create a class that can only have one object at a time, then you can use the Singleton design pattern which suggests the best way to create a class that can only have one object.

>Design patterns are tested by others and are safe to follow, e.g. Gang of Four patterns: Abstract Factory, Factory, Singleton, Command, etc.

## SOLID

SOLID is an acronym that represents five essential principles of object-oriented design: `Single Responsibility`, `Open-Closed`, `Liskov Substitution`, `Interface Segregation`, and `Dependency Inversion`.

1. **Single Responsibility Principle (SRP):**
The Single Responsibility Principle states that a class should have only `one reason to change`. Each class should focus on `doing one thing and doing it well`.

```java
class WeatherDataManager {
    public WeatherData fetchWeatherData() {
        // Fetch weather data from a web service
    }
}

class WeatherDataParser {
    public Weather parseWeatherData(String rawData) {
        // Parse raw data into Weather object
    }
}

class WeatherUIUpdater {
    public void updateUI(Weather weather) {
        // Update the UI with the weather information
    }
}
```

* In this example, we have divided the responsibilities into separate classes: one for data retrieval, another for data parsing, and a third for updating the UI.

```kotlin
class InvoicePrinter {
    fun print(invoice: Invoice) {
        // Print the invoice
    }
}

class InvoiceSaver {
    fun save(invoice: Invoice) {
        // Save the invoice to the database
    }
}

class Invoice(private val printer: InvoicePrinter, private val saver: InvoiceSaver) {
    fun print() {
        printer.print(this)
    }

    fun save() {
        saver.save(this)
    }
}
```

* Each class has a single responsibility, making it easier to maintain and test. This not only enhances code clarity but also allows for reusability in different parts of the application.

2. **Open-Closed Principle (OCP):**
The Open-Closed Principle suggests that `a class should be open for extension but closed for modification`, means you should be able to `add new features without changing existing code`.

```java
interface NewsAdapter { // Open for extension
    void display();
}

class NewsFeedAdapter implements NewsAdapter {
    @Override
    public void display() {
        // Display news feed
    }
}

class FeaturedNewsAdapter implements NewsAdapter {
    @Override
    public void display() {
        // Display featured news
    }
}
```

* Here, we define an interface NewsAdapter, which is open for extension. You can add new adapter implementations like TrendingNewsAdapter without modifying the existing code, keeping it closed for modification.

```kotlin
abstract class Shape {
    abstract fun area(): Double
}

class Circle(private val radius: Double) : Shape() {
    override fun area(): Double {
        return Math.PI *radius* radius
    }
}

class Rectangle(private val width: Double, private val height: Double) : Shape() {
    override fun area(): Double {
        return width * height
    }
}

fun main() {
    val shapes: List<Shape> = listOf(Circle(5.0), Rectangle(4.0, 6.0))
    for (shape in shapes) {
        println("Area: ${shape.area()}")
    }
}
```

* This approach is critical for maintainability when your app evolves over time with new features and display requirements.

3. **Liskov Substitution Principle (LSP):**
The Liskov Substitution Principle emphasizes that `objects of a derived class should be able to replace objects of the base class without affecting the program’s correctness`.

```java
class Vehicle {
    public void startEngine() {
        // Start the vehicle's engine
    }
}

class Car extends Vehicle {
    // Car-specific methods and properties
}

class Motorcycle extends Vehicle {
    // Motorcycle-specific methods and properties
}
```

* In this case, the `Car` and `Motorcycle` subclasses can replace the base `Vehicle` class `without affecting the program’s correctness`. You can call `startEngine()` on both car and motorcycle objects seamlessly.

```kotlin
class Bird {
    fun fly() {
        println("Flying")
    }
}

class Sparrow : Bird()

class Ostrich : Bird() {
    override fun fly() {
        throw UnsupportedOperationException("Ostriches can't fly")
    }
}

fun main() {
    val birds: List<Bird> = listOf(Sparrow(), Ostrich())

    for (bird in birds) {
        try {
            bird.fly()
        } catch (e: UnsupportedOperationException) {
            println(e.message)
        }
    }
}
```

* This concept ensures that `subclassing maintains the same contract as the superclass`, making it easier to extend and adapt your software.

4. **Interface Segregation Principle (ISP):**
The Interface Segregation Principle advises that `clients should not be forced to depend on interfaces they do not use`.

```java
interface Authentication {
    void login();
    void logout();
}

interface DataRetrieval {
    void fetchData();
}

interface Analytics {
    void trackEvent(String event);
}

class BackendService implements Authentication, DataRetrieval, Analytics {
    // Implement all three interfaces
}
```

* In this example, we have segregated the large interface into smaller, specialized interfaces, allowing classes to `implement only the interfaces they need, reducing unnecessary dependencies`.

```kotlin
interface Printer {
    fun print()
}

interface Scanner {
    fun scan()
}

class MultiFunctionPrinter : Printer, Scanner {
    override fun print() {
        println("Printing")
    }

    override fun scan() {
        println("Scanning")
    }
}

class SimplePrinter : Printer {
    override fun print() {
        println("Printing")
    }
}

fun main() {
    val printer: Printer = SimplePrinter()
    printer.print()

    val multiFunctionPrinter: MultiFunctionPrinter = MultiFunctionPrinter()
    multiFunctionPrinter.print()
    multiFunctionPrinter.scan()
}
```

* This is especially useful in software development when `dealing with various APIs and services`, as it keeps classes clean and focused on their specific responsibilities.

5. **Dependencies Inversion Principle (DIP):**

The Dependency Inversion Principle `promotes decoupling high-level modules from low-level modules`, making both more maintainable.

```java
class NetworkClient {
    // Network client implementation
}

class AppService {
    private NetworkClient networkClient;

    public AppService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    // Use the network client for API calls
}
```

* Here, we’ve used `dependency injection` by passing the `NetworkClient` as a parameter to the `AppService` constructor. This decouples the high-level module (AppService) from the low-level module (NetworkClient), making the code more maintainable and flexible.

```kotlin
interface Database {
    fun save(data: String)
}

class MySQLDatabase : Database {
    override fun save(data: String) {
        println("Saving data to MySQL Database")
    }
}

class MongoDBDatabase : Database {
    override fun save(data: String) {
        println("Saving data to MongoDB Database")
    }
}

class DataService(private val database: Database) {
    fun saveData(data: String) {
        database.save(data)
    }
}

fun main() {
    val mysqlDatabase = MySQLDatabase()
    val dataService = DataService(mysqlDatabase)
    dataService.saveData("Sample Data")

    val mongoDatabase = MongoDBDatabase()
    val dataServiceMongo = DataService(mongoDatabase)
    dataServiceMongo.saveData("Sample Data")
}
```

* It also enables easier testing by allowing you to `inject mock dependencies` during testing.

* In the dynamic world of development, adhering to `SOLID` principles can significantly contribute to the maintainability and extensibility of your codebase. By implementing `SRP`, `OCP`, `LSP`, `ISP`, and `DIP`, you can write code that is `cleaner`, `easier to debug`, and `more adaptable` to future changes.
* These principles are `not rigid rules but guidelines that can lead to better software design and development`.
* So, keep your code SOLID, and your software will stand the test of time, allowing you to navigate the ever-changing landscape of software development with confidence.
