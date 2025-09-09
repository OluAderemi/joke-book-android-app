# JokeBook App

A simple Android app built with **Jetpack Compose** that lets users explore jokes by category. Browse, read, and swipe through jokes with an intuitive interface.

---

## Features

* Browse jokes by category: `General`, `Knock-Knock`, `Programming`.
* View full joke details with setup and punchline.
* Swipe or use arrows to navigate between jokes.
* Toggle between Light and Dark themes from the home screen.
* Theme preference is remembered even if the app is closed and reopened.
* Clean, responsive UI built with **Material3**.

---

## Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/OluAderemi/joke-book-android-app
cd jokebook
```

### 2. Open in Android Studio

* Open **Android Studio.**
* Select **Open an existing project** and navigate to the cloned repository.

### 3. Sync Gradle

* Android Studio will prompt you to **sync Gradle**.
* Make sure you are connected to the internet so all dependencies can be downloaded.

### 4. Check Dependencies

The project uses:

* **Kotlin 1.9+**
* **Jetpack Compose (Material3)**
* **Navigation Compose**
* **Coroutines**
* **DataStore** for saving theme preference

These are included in `build.gradle` and will be downloaded automatically.

### 5. Run on Device or Emulator

* Connect an Android device or start an emulator.
* Press **Run** (Shift + F10) in Android Studio.

### 6. Internet Access

* The app fetches jokes from `https://official-joke-api.appspot.com`.
* Ensure the device/emulator has an internet connection.

---

## Usage

1. Open the app.
2. Toggle **Light/Dark mode** from the top-right switch on the home screen. The selected theme will apply throughout the app.
3. Tap **Enter** to go to the categories screen.
4. Select a joke category from the top filter chips.
5. Tap on a joke to view the details.
6. Swipe left or right (or use the arrows) to navigate between jokes.
7. Tap **Back to List** to return to the category list.
8. Tap the **Refresh** button to load new jokes for the selected category.

---

## Dependencies

* **Jetpack Compose**
* **Material3**
* **Navigation Compose**
* **Kotlin Coroutines**
* **AndroidX DataStore**

---