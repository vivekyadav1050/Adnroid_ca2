# Bill Splitter App

A simple Android application built using **Kotlin and Jetpack Compose** that helps manage and split a bill between multiple people.

The project follows a basic **MVVM architecture** to keep the UI, business logic, and data separate.

## Features

* Display bill name and amount
* Increase the number of people splitting the bill using the **(+)** button
* Decrease the split count using the **(-)** button
* State management using **StateFlow**
* Simple and responsive Jetpack Compose UI
* Basic MVVM architecture

## Tech Stack

* **Kotlin**
* **Jetpack Compose**
* **StateFlow**
* **ViewModel**
* **Android Studio**

## Project Structure

```text
app/
└── src/
    └── main/
        └── java/
            └── com/example/
                ├── Model.kt
                ├── Repository.kt
                ├── ViewModel.kt
                ├── Ui.kt
                └── MainActivity.kt
```

### Model.kt

Contains the `BillModel` data class.

```kotlin
data class BillModel(
    val name: String,
    val amount: Int,
    val split: Int
)
```

It stores the basic bill information such as the bill name, amount, and number of people splitting the bill.

### Repository.kt

Contains the `BillRepository` class.

The repository handles the logic for:

* Increasing the split count
* Decreasing the split count

This keeps the business logic separate from the UI.

### ViewModel.kt

Contains the `BillViewModel`.

The ViewModel uses **StateFlow** to maintain and update the current bill state.

It communicates with the repository whenever the user increases or decreases the split count.

### Ui.kt

Contains the `BillSplitterScreen` Composable.

It displays:

* Bill name
* Bill amount
* Current split count
* `+` button
* `-` button

The UI observes the state provided by the ViewModel and updates automatically when the state changes.

### MainActivity.kt

`MainActivity` is the entry point of the application.

It sets up the ViewModel and displays the `BillSplitterScreen` using Jetpack Compose.

## How It Works

The application follows a simple MVVM flow:

```text
User
  ↓
Jetpack Compose UI
  ↓
BillViewModel
  ↓
BillRepository
  ↓
BillModel
```

### 1. User Interaction

The user presses the **+** or **-** button to change the number of people splitting the bill.

### 2. ViewModel

The `BillViewModel` receives the action and communicates with the `BillRepository`.

### 3. Repository

The repository handles the logic for increasing or decreasing the split count.

### 4. StateFlow

The ViewModel updates the `StateFlow` with the new bill state.

### 5. UI Update

Jetpack Compose observes the state and automatically recomposes the screen with the updated split count.

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/vivekyadav1050/Adnroid_ca2.git
```

### Open the Project

Open the project in **Android Studio**.

### Run the Application

Connect an Android device or start an Android Emulator and click the **Run** button.

## Purpose

This project demonstrates the basic implementation of:

* MVVM architecture
* Repository pattern
* ViewModel
* StateFlow
* Jetpack Compose
* State-driven UI

## Author

**Vivek Yadav**

GitHub: https://github.com/vivekyadav1050
