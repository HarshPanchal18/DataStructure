# Jetpack Compose

## Jetpack Compose topics and quick notes to prefer

## **INDEX**

- [State](#what-is-a-state-in-jetpack-compose)

- [rememberSaveable](#what-is-remembersaveable)

- [derievedStateOf](#what-is-derievedstateof)

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

The `rememberSaveable` function takes an optional `Saver` parameter, which allows you to customize how the state variable is saved and restored. The `Saver` interface has two methods: `save` and `restore`. The `save` method is called when the state variable is saved, and the `restore` method is called when the state variable is restored.

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

[Back on index](#index)
