# ROOM Database

1. What is Android Room, and why is it used in Android app development?
Android Room is a part of the Android Jetpack library and is used as a SQLite object mapping library that makes it easier to work with databases in Android. It provides a higher-level abstraction over raw SQLite and offers compile-time verification of SQL queries. Room simplifies database operations and helps manage data persistence efficiently in Android apps.

2. What are the core components of Android Room?
The core components of Android Room are:
`Entity`: Represents a table in the database.
`DAO (Data Access Object)`: Provides methods to interact with the database.
`RoomDatabase`: Acts as the database holder and serves as the main entry point for accessing the database.

3. How do you define an Entity in Android Room?
An Entity in Android Room is defined as a regular Kotlin data class with the @Entity annotation. Each property in the class represents a column in the database table, and you can use additional annotations like `@PrimaryKey` and `@ColumnInfo` to customize the entity's behavior and schema.

4. What is a DAO in Android Room, and what is its role?
A DAO (Data Access Object) in Room is an interface that defines methods for performing database operations such as insertion, retrieval, updating, and deletion. It acts as a bridge between the Entity classes and the database, allowing you to execute SQL queries or methods to interact with the data.

5. Explain the difference between LiveData and Flow in Room.
LiveData is a data holder class that is lifecycle-aware, meaning it automatically updates the UI when the data changes and respects the Android app’s lifecycle.
Flow is a reactive stream provided by Kotlin, which can be used with Room to handle asynchronous data updates.
While LiveData is designed specifically for Android, Flow is a more general-purpose reactive stream.

6. How do you perform basic CRUD operations with Room?
To perform CRUD operations in Room:
Use @Insert to insert data.
Use @Query to read data.
Use @Update to update data.
Use @Delete to delete data.
Define corresponding methods in the DAO interface.

7. What is a Room Database Migration, and why might you need it?
A Room Database Migration is the process of modifying the database schema as the app evolves to handle changes such as adding new tables, columns, or modifying existing ones. Migrations ensure data integrity during database schema changes and prevent data loss.

8. How does Room handle relationships between entities?
Room supports relationships between entities using annotations like @Relation, @ForeignKey, and @Embedded. These annotations allow you to define one-to-one, one-to-many, and many-to-many relationships between entities.
[Click here for more details](https://www.geeksforgeeks.org/android-entity-relationship-in-room/)

9. What is the purpose of the `@Query` annotation in Room, and how is it used?
The @Query annotation is used to define custom SQL queries in Room. You can annotate DAO methods with @Query to execute complex database operations and retrieve data based on your specific requirements.

10. Discuss Room’s support for TypeConverters. When and how would you use them?
Room TypeConverters allow you to specify how to convert custom data types, which are not directly supported by Room, into types that can be stored in the database. You can use them by annotating methods in your database class with @TypeConverters and providing the converter class.

11. What is the role of the RoomDatabase class in Room?
The RoomDatabase class serves as the database holder and is responsible for managing database connections and providing access to DAOs. It is typically a singleton class in your application, and you can define database configurations within it.

12. How can you ensure thread safety when working with Room?
You can ensure thread safety when working with Room by using appropriate concurrency mechanisms, such as running database operations on background threads using coroutines or LiveData, or by using transactions for atomic operations.

13. What are some best practices for optimizing database performance with Room?
Best practices for optimizing Room database performance include using appropriate indices, designing efficient queries, using pagination for large data sets, and considering data caching strategies to reduce unnecessary database accesses.

14. What is different between query and transaction in Room database?
Query:
A query in Room is used to retrieve data from the database. It involves selecting, filtering, and reading data from one or more database tables.
Queries are typically read-only operations and do not modify the database’s structure or content.
Room provides various ways to create queries, such as using the @Query annotation to define custom SQL queries or using built-in query methods provided by the DAO (Data Access Object) interface.
Example of a query in Room:

```kotlin
@Query("SELECT * FROM users WHERE age > :minAge")
fun getUsersOlderThan(minAge: Int): List<User>
```

Transaction:
A transaction in Room is used to group multiple database operations (insertions, updates, deletions, etc.) into a single unit of work that is executed atomically.
Transactions ensure the consistency and integrity of the database by ensuring that a series of operations either all succeed or all fail. If any part of the transaction fails, the entire transaction is rolled back, and the database remains unchanged.
Room provides built-in support for transactions, and you can use the @Transaction annotation to mark methods in the DAO interface that should be executed within a transaction.
Example of a transaction in Room:

```kotlin
@Transaction
suspend fun insertUserWithAddress(user: User, address: Address) {
    insertUser(user)
    insertAddress(address)
}
```

Transactions are important for maintaining data consistency and integrity, especially when multiple database operations need to be executed as a single, indivisible unit.
