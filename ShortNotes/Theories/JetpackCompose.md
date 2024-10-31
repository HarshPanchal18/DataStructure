# Jetpack Compose

## Jetpack Compose topics and quick notes to prefer

## **INDEX**

- [State](#what-is-a-state-in-jetpack-compose)

- [rememberSaveable](#what-is-remembersaveable)

- [derievedStateOf](#what-is-derievedstateof)

- [Why we have to declare properties of viewModels like the following?](#why-we-have-to-declare-properties-of-viewmodels-like-the-following)

- [Purpose of View Models](#purpose-of-view-models)

## `What is a state in Jetpack Compose?`

In Jetpack Compose, state represents the data that can change over time and can be used to update the UI. State is typically managed using the `State` class, which provides methods to read and write the state value.

To create a state variable, you can use the `remember` function, which takes a lambda as an argument and returns a `State` object. The lambda passed to `remember` should be a function that returns the initial value of the state variable.

`val count = remember { 0 }`

Once you have created a state variable, you can read its value using the `value` property. You can also update the value of the state variable using the `value = newValue` syntax.

`count.value = count.value + 1`

State variables are automatically invalidated when their dependencies change. This means that if you update a state variable that is used in a composable function, the composable function will be recomposed with the new value of the state variable.

State is an important concept in Jetpack Compose, and it is used to manage the data that can change over time and can be used to update the UI.

[Back on index](#index)

## `What is rememberSaveable?`

`rememberSaveable` is a function in Jetpack Compose that allows you to create a state variable that will be saved and restored when the composable function is recreated. This is useful for storing data that needs to persist across configuration changes, such as the user's preferences or the current scroll position.

To use `rememberSaveable`, you pass a lambda as an argument to the function, and the lambda should return the initial value of the state variable. The state variable will be saved and restored automatically when the composable function is recreated.

val count = rememberSaveable { 0 }

The `rememberSaveable` function takes an optional `Saver` parameter, which allows you to customize how the
`state variable is saved and restored. The`Saver`interface has two methods`: `save` and `restore`. The `save` method is called when the state variable is saved, and the `restore` method is called when the state variable is restored.

Here is an example of a custom `Saver` that saves the state variable to a file:

```kotlin
val saver = object : Saver<Int, File> {
    override fun save(value: Int, output: File) {
        output.writeText(value.toString())
    }

    override fun restore(input: File): Int {
        return input.readText().toInt()
    }
}
```

`val count = rememberSaveable(saver = saver) { 0 }`

`rememberSaveable` is a useful function for storing data that needs to persist across configuration changes. It is important to note that `rememberSaveable` only saves the state variable itself, not any other state that the state variable depends on. If you need to save other state, you can use the `saveable` modifier.

### When to use rememberSaveable

You should use `rememberSaveable` when you need to store data that needs to persist across configuration changes. This includes data such as:

- User preferences
- Current scroll position
- Game state
- Shopping cart contents

### When not to use rememberSaveable

You should not use `rememberSaveable` for data that does not need to persist across configuration changes. This includes data such as:

- Temporary values that are only used within a single composable function
- Data that is already stored in a database or other persistent storage

`rememberSaveable` is a powerful function that allows you to store data that needs to persist across configuration changes. It is important to use `rememberSaveable` correctly, and to only save data that needs to be persisted.

[Back on index](#index)

## `What is derievedStateOf?`

`derivedStateOf` is a function in Jetpack Compose that allows you to create a state variable that is derived from other state variables. This is useful for creating state variables that depend on other state variables, without having to manually update the derived state variable when the other state variables change.

To use `derivedStateOf`, you pass a lambda as an argument to the function, and the lambda should return the derived state value. The derived state value will be automatically updated when any of the state variables that the lambda depends on change.

```kotlin
val count = remember { 0 }

val doubledCount = derivedStateOf { count.value * 2 }
```

In this example, the `doubledCount` state variable will be automatically updated whenever the `count` state variable changes. This is because the lambda passed to `derivedStateOf` depends on the `count` state variable.

`derivedStateOf` is a useful function for creating state variables that are derived from other state variables. It is important to note that `derivedStateOf` only creates a new state variable if the derived value has changed. This means that if the derived value does not change, the same state variable will be returned.

### When to use derivedStateOf

You should use `derivedStateOf` when you need to create a state variable that is derived from other state variables. This is useful for creating state variables that:

- Depend on other state variables
- Are expensive to calculate
- Do not need to be updated every time the state variables that they depend on change

### When not to use derivedStateOf

You should not use `derivedStateOf` for state variables that:

- Do not depend on other state variables
- Are not expensive to calculate
- Need to be updated every time the state variables that they depend on change

`derivedStateOf` is a powerful function that allows you to create state variables that are derived from other state variables. It is important to use `derivedStateOf` correctly, and to only create derived state variables that are needed.

[Back on index](#index)

## Why we have to declare properties of viewModels like the following?

```kotlin
// SettingsViewModel.kt

private var _colorScheme =
    MutableStateFlow<Theme?>(Theme.Dark)
val colorScheme =_colorScheme.asStateFlow()
```

In the context of `MVC (Model-View-Controller) architecture`, a view model serves as an intermediary between the controller (which handles user input and application logic) and the view (which displays data to the user). Let’s break down why we declare properties in view models like the example you provided:

[Back on index](#index)

## Purpose of View Models

A view model represents the data that you want to display on your view or page. It acts as a model specifically designed for a particular view.
Unlike domain models (which represent the underlying data structure of your application), view models are tailored to the needs of the UI.
View models contain only the data (represented by properties) required for rendering in the view.

### Example Scenario

- Imagine you’re building an application where users can customize their color scheme (e.g., light mode or dark mode).
- You have a Theme enum with values like Theme.Light and Theme.Dark.
- Your domain model might include additional properties related to an employee (e.g., ID, first name, last name, date created).
- However, when displaying the color scheme settings, you don’t need all the employee-related properties.
- In this case, you create a view model specifically for the color scheme settings.

### Creating the View Model

In your example, the `SettingsViewModel.kt` file defines a view model for color scheme settings.

The `_colorScheme` property is a `private mutable state flow` that holds the selected theme (either light or dark).
The `colorScheme` property <u>exposes the_colorScheme as an immutable state flow (using asStateFlow()).</u>

By doing this, you encapsulate the color scheme logic within the view model.

### Why Use a View Model?

- Separation of concerns: View models allow you to separate UI-related concerns from your domain model.
- Tailored data: View models provide only the necessary data for a specific view, avoiding unnecessary clutter.
- Flexibility: You can transform or aggregate data from different sources into a format suitable for rendering.
- Testability: View models are easier to test because they focus on specific UI requirements.

In summary, view models help bridge the gap between the controller and the view, ensuring that the UI displays the relevant data without exposing unnecessary details from the domain model.

### Why I shouldn't expose mutableState directly? Why should I write like following

```kotlin
// SettingsViewModel.kt

private var _colorScheme =
    MutableStateFlow<Theme?>(Theme.Dark)
val colorScheme =_colorScheme.asStateFlow()
```

`MutableState:`

- Purpose: MutableState is specifically designed for Jetpack Compose. It allows you to read and write values in a way that Compose can automatically track changes to that value.
- Usage: You use MutableState within composables (UI components) to manage local state. When the value of a MutableState changes, Compose automatically recomposes the relevant parts of the UI that depend on that state.

Example:

```kotlin
val count = mutableStateOf(0)
val currentCount = count.value // Read the value
count.value = currentCount + 1 // Update the value
```

`MutableStateFlow:`

- Purpose: MutableStateFlow is a more general-purpose reactive state holder. It’s meant to be used outside of composables, such as in ViewModels.
- Behavior: Unlike MutableState, MutableStateFlow does not automatically trigger recomposition. Instead, it provides a flow of values that you can observe and react to.

Example:

```kotlin
private val _colorScheme = MutableStateFlow<Theme?>(Theme.Dark)
val colorScheme =_colorScheme.asStateFlow()
// Read the value
val currentColorScheme = colorScheme.value
// Update the value
_colorScheme.value = Theme.Light
AI-generated code. Review and use carefully. More info on FAQ.
```

`When to Use Which:`

- Use `MutableState` when managing UI state within composables. It’s efficient and integrates seamlessly with Compose.

- Use `MutableStateFlow` when working with ViewModel state or other non-UI logic. It provides more flexibility and allows you to perform additional operations (e.g., mapping, filtering) on the state flow.

- **Note:** If you expose a MutableState directly from a ViewModel, it might lead to *`unintended recompositions in your UI`*. By using `MutableStateFlow` and exposing it as a read-only StateFlow, you avoid this issue.

- In summary, choose MutableState for UI-related state within composables, and use MutableStateFlow for broader state management, especially in ViewModels. The latter provides more control and flexibility while avoiding unnecessary recompositions.

### What is the purpose of passing private variable in constructor? i.e. class Demo(private val name: String) {}

Passing a private variable in a constructor, like in your example class Demo(private val name: String) {}, serves the primary purpose of initializing and encapsulating the object's state. Let's break down why this is a common and beneficial practice:

1. Initialization:
The constructor is called when a new instance of the class (Demo in this case) is created.
By passing name as a constructor parameter, you provide the initial value for the name property of the object.
This ensures that every Demo object starts with a defined name, preventing it from being in an undefined or invalid state.

2. Encapsulation:
Declaring name as private restricts its direct access from outside the class.
This enforces encapsulation, a core principle of object-oriented programming, which hides the internal details of an object and protects its integrity.
By controlling access to name, you prevent accidental or unintended modifications from external code, ensuring the object's state remains consistent.

3. Immutability (Optional):
In your example, using val for name makes it a read-only property, further enhancing encapsulation.
Immutability means the name of a Demo object cannot be changed after it's initialized, promoting predictability and reducing the risk of unexpected side effects.
Example:

```kotlin
class Demo(private val name: String) {
    fun greet() {
        println("Hello, my name is $name!")
    }
}

fun main() {
    val demo = Demo("Alice")
    demo.greet() // Output: Hello, my name is Alice!
    // demo.name = "Bob" // This would be an error, as 'name' is private
}
```

In essence, passing private variables in constructors helps you create objects with well-defined initial states, protect their internal data, and optionally enforce immutability, leading to more robust and maintainable code.

#### How does it play role while injecting with constructor? Mostly used while Dependency injection

You're spot on! Passing private variables in constructors plays a crucial role in dependency injection (DI). It enables you to provide dependencies to a class in a clean, testable, and maintainable way.

Here's how it works in the context of DI:

1. Declaring Dependencies:
When a class depends on other objects (dependencies) to function, you declare those dependencies as constructor parameters.
By making these parameters private, you adhere to encapsulation and prevent external modification of the dependencies.
2. Providing Dependencies:
A dependency injection framework (like Hilt or Koin) is responsible for creating and providing the required dependencies.
When the framework creates an instance of your class, it automatically passes the necessary dependencies as arguments to the constructor.
3. Using Dependencies:
Inside your class, you can use the private dependency variables to access the functionality provided by the injected objects.

Example (using Hilt):

```kotlin
class AnalyticsService @Inject constructor(private val analyticsTracker: AnalyticsTracker) {
    fun trackEvent(eventName: String) {
        analyticsTracker.track(eventName)
    }
}
```

Explanation:

`AnalyticsService` depends on an `AnalyticsTracker` to track events.
The `@Inject` annotation tells Hilt to provide an instance of AnalyticsTracker when creating AnalyticsService.
`AnalyticsService` uses the private analyticsTracker variable to call the track() method.

Benefits of Constructor Injection with Private Variables:

- `Loose Coupling`: Classes don't directly create their dependencies, reducing tight coupling and improving modularity.
- `Testability`: You can easily replace dependencies with mocks or fakes during testing, isolating the class under test.
- `Maintainability`: Changes to dependencies don't require modifications within the class itself, as long as the constructor parameters remain the same.
- `Readability`: The constructor clearly shows the dependencies a class requires, making the code easier to understand.

In summary, passing private variables in constructors is a fundamental technique in dependency injection, enabling you to manage dependencies effectively, improve testability, and create more maintainable and modular code.

#### How does Hilt provides dependencies implicitly?

Hilt, being a dependency injection framework built on top of `Dagger`, provides dependencies implicitly through a combination of annotations, code generation, and a component hierarchy. Here's a simplified breakdown of how it works:

1. Annotations:
You use annotations like `@Inject`, `@Module`, `@InstallIn`, and others to mark classes, functions, and entry points for Hilt to process.
`@Inject` on a constructor tells Hilt that *it needs to provide instances of that class*.
`@Module` marks classes that define *how to provide dependencies*.
`@InstallIn` specifies the *scope or component where a module should be installed*.
2. Code Generation:
At compile time, Hilt's annotation processor `(kapt)` generates code based on the annotations you've used.
This generated code includes dependency providers, component interfaces, and the logic to wire everything together.
3. Component Hierarchy:
Hilt establishes a hierarchy of components, each responsible for providing dependencies within a specific scope `(e.g., @SingletonComponent, @ActivityComponent)`.
Components are linked together, allowing dependencies to be shared across different scopes.
4. Implicit Provision:
When you request an instance of a class with `@Inject` on its constructor,

Hilt automatically:

- Searches for a matching provider in the relevant component.
- If no provider is found, it tries to create one based on the constructor parameters.
- Recursively resolves dependencies of the dependencies, ensuring all required objects are available.

Example:

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        // ...
    }
}

class MyRepository @Inject constructor(private val okHttpClient: OkHttpClient) {
    // ...
}
```

- Hilt sees `@Inject` on MyRepository's constructor and needs to provide an OkHttpClient.
- It finds the `provideOkHttpClient()` function in NetworkModule, which is installed in the SingletonComponent.
- Hilt calls this function to get an OkHttpClient instance and passes it to MyRepository's constructor.

Key Points:

- You don't explicitly call the dependency providers; Hilt handles that behind the scenes.
- The component hierarchy and scoping rules ensure that dependencies are provided in the correct context (e.g., a singleton instance across the app, or a new instance per activity).
- Hilt's generated code takes care of the complex wiring and object creation, making your code cleaner and more focused on business logic. By leveraging annotations and code generation, Hilt provides dependencies implicitly, freeing you from manual dependency management and promoting a more structured and maintainable approach to Android development.

[Back on index](#index)

## Kotlin Coroutines and Flows

#### `Coroutines:`

- Coroutines in Kotlin are `lightweight threads` that can be used for `asynchronous programming`.
- They allow for `non-blocking operations` without the overhead of traditional threads.
- Coroutines simplify the handling of asynchronous tasks by providing a way to write asynchronous code sequentially, making it easier to read and maintain compared to callback-based or thread-based code.

#### `Flows:`

- Flows are a `cold asynchronous data stream` provided by Kotlin coroutines.
- They can emit multiple values sequentially, making them ideal for handling sequences of values asynchronously.

- Flows are designed to be composable and can handle backpressure, allowing the producer to slow down when the consumer is unable to keep up with the emitted values. This makes flows a powerful tool for handling streams of data in a reactive and efficient manner.

### What is Channel in Kotlin coroutines?

- In Kotlin coroutines, a `Channel` is a non-blocking primitive for communication between multiple coroutines.

- It can be used to send and receive elements asynchronously. Channels provide a way for coroutines to communicate with each other in a `producer-consumer` fashion.

### What are coroutines in Kotlin and how do they differ from traditional threads?

Coroutines are lightweight threads in Kotlin that allow for asynchronous programming. Unlike traditional threads, coroutines are non-blocking and can be suspended and resumed without blocking the underlying thread, making them more efficient for handling asynchronous tasks.

### How do you create a coroutine in Kotlin?

Coroutines in Kotlin can be created using the `launch` or async builders provided by the CoroutineScope.

For example, you can create a coroutine using launch like this:

```kotlin
coroutineScope.launch {
    // Coroutine code here
}
```

### What is a Flow in Kotlin coroutines and how is it different from a regular collection?

A Flow in Kotlin coroutines is an asynchronous sequence of values that can emit multiple values sequentially.

Unlike regular collections, Flows are cold streams that are evaluated for each collector individually, allowing for asynchronous processing of data and handling backpressure.

### How do you collect values from a Flow in Kotlin?

Values from a Flow can be collected using the collect operator, which is a terminal operator that starts the flow and consumes the emitted values.

For example:

```kotlin
flow.collect { value ->
    // Process the emitted value
}
```

### What is backpressure in the context of Flows in Kotlin?

Backpressure is a mechanism used in Flows to handle cases where the producer is emitting values faster than the consumer can process them.

Flows can handle backpressure by allowing the consumer to request a specific number of items to be emitted, enabling flow control and preventing overflow of data.

### When to use flow?

Flows in Kotlin are ideal for scenarios where you need to handle asynchronous streams of data that may emit multiple values sequentially.

- You can use Flows in the following situations:

`Asynchronous Operations`: When dealing with asynchronous operations that emit multiple values over time, such as fetching data from a network call or reading from a database.

`Reactive Programming`: For implementing reactive programming patterns where you need to react to changes in data streams and handle events asynchronously.

`Handling Streams of Data`: When processing streams of data that require asynchronous processing, such as real-time data processing or event-driven applications.

`Backpressure Handling`: In cases where you need to handle backpressure, which occurs when the producer emits values faster than the consumer can process, Flows provide mechanisms to control the flow of data efficiently.

`Composability and Transformation`: Flows are composable, allowing you to combine, transform, and filter data streams easily, making them suitable for scenarios where you need to manipulate and process data asynchronously.
