# Food Delivery Android App
This is a sample app that uses Android Architecture Components and JetPack to build a demo Grocery App.

**NOTE:** It is a relatively more complex and complete example so if you are not familiar with Architecture Components, you are highly recommended to check other examples in this repository first.

## Introduction
### Functionality
The app is composed of 3 main screens.

#### MainActivity(Product List)
Allows you to check category wise product list with prices. Each product is kept in the database in products table which is quried from productDao file.

Each time a new product is added, the same product code record in the Database is updated with the updated product count in cart_items table.


#### CartActivity
This Activity displays the details of a cart, add/remove items from cart and finally places order successfully.


#### OrderActivity
This Activity displays the details past orders of the user with date.

#### ProfileActivity
This Screen displays a user and their profile informations.

#### SignupActivity
From this Screen a user can signup by filling forms.

#### LoginActivity
A user with email and password can Login into app from this screen.


### Building
You can open the project in Android studio and press run.

## Libraries
- Android Support Library
- Android Architecture Components
- Android Data Binding
- Hilt for dependency injection
- Retrofit for REST api communication
- Room for Accessing app's SQLite database with in-app objects and compile-time checks.
- Lifecycles - Create a UI that automatically responds to lifecycle events.
- LiveData - Build data objects that notify views when the underlying database changes.
- ViewModel - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.
- WorkManager - Manage your Android background jobs.
- Glide for image loading
- Kotlin Coroutines for managing background threads with simplified code and reducing needs for callbacks
