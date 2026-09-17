# System Flow and Folder Structure

## 1. App Overview

This project is an Android Java application for a public transportation tracking system, designed for both passengers and drivers.

Main flow:

1. `MainActivity` runs first.
2. It verifies the Mapbox token from `.env`.
3. If valid, it launches `LoginActivity`.
4. User logs in as either a passenger or driver.
5. The app redirects to the correct dashboard:
   - Passenger: Home, Map, Favorites, Profile
   - Driver: Home, Trips, History, Profile

---

## 2. Core System Flow

```text
MainActivity
   └── checks MapBox token
          ├── missing token -> app stops with error
          └── valid token -> LoginActivity

LoginActivity
   ├── Passenger login -> UserHomePageActivity / Passenger Home
   └── Driver login -> DriverMainActivity

Passenger flow
   ├── Home
   ├── Map
   ├── Favorites
   └── Profile

Driver flow
   ├── Home
   ├── Trips
   ├── History
   └── Profile
```

---

## 3. Folder Structure

```text
Public Transportation Analytics/
├── .github/
├── .gradle/
├── .vscode/
├── app/
│   ├── build.gradle
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/
│       │   │   └── com/example/passengerdriverapp/
│       │   │       ├── data/
│       │   │       │   ├── api/
│       │   │       │   │   ├── JeepneyData.java
│       │   │       │   │   ├── JeepneyRoute.java
│       │   │       │   │   ├── Login.java
│       │   │       │   │   ├── MapBox.java
│       │   │       │   │   └── Register.java
│       │   │       │   ├── model/
│       │   │       │   ├── repository/
│       │   │       │   │   └── AppRepository.java
│       │   │       │   └── components/
│       │   │       ├── domain/
│       │   │       │   └── usecase/
│       │   │       └── presentation/
│       │   │           ├── MainActivity.java
│       │   │           ├── auth/
│       │   │           │   ├── LoginActivity.java
│       │   │           │   └── RegisterActivity.java
│       │   │           ├── passenger/
│       │   │           │   ├── UserHomePageActivity.java
│       │   │           │   ├── components/
│       │   │           │   ├── favorites/
│       │   │           │   ├── home/
│       │   │           │   ├── map/
│       │   │           │   └── profile/
│       │   │           └── driver/
│       │   │               ├── DriverMainActivity.java
│       │   │               ├── home/
│       │   │               ├── trips/
│       │   │               ├── history/
│       │   │               └── profile/
│       │   └── res/
│       │       ├── anim/
│       │       ├── drawable/
│       │       ├── font/
│       │       ├── layout/
│       │       └── values/
├── build.gradle
├── gradle.properties
├── settings.gradle
├── README.md
├── ARCHITECTURE.txt
├── FlowSystem.md
├── SystemFlowEtc/
└── build/
```

---

## 4. Folder Content Description

### `app/src/main/java/com/example/passengerdriverapp/data`

This layer handles raw app data and external/in-memory sources.

- `api/`
  - `MapBox.java` — config and access token handling for map services.
  - `JeepneyData.java` — sample jeepney route and location data.
  - `JeepneyRoute.java` — route model object.
  - `Login.java` — login credential model and validation logic.
  - `Register.java` — registration model.
- `repository/`
  - `AppRepository.java` — central source for mock data used by screens.
- `components/`
  - reusable model/view helper data objects.

### `app/src/main/java/com/example/passengerdriverapp/domain`

This layer is intended for app business rules and use cases.

- `usecase/` contains logic for operations like authentication, route selection, trip management, and profile handling.

### `app/src/main/java/com/example/passengerdriverapp/presentation`

This layer contains all screens and UI flows.

- `MainActivity.java`
  - app startup screen
  - verifies Mapbox token
  - opens the app login flow
- `auth/`
  - `LoginActivity.java` — login UI and role-based navigation
  - `RegisterActivity.java` — account registration screen
- `passenger/`
  - `UserHomePageActivity.java` — main passenger landing page
  - `home/` — passenger home views and controls
  - `map/` — map-related screens and map UI logic
  - `favorites/` — saved routes and favorite transport entries
  - `profile/` — passenger account/profile settings
- `driver/`
  - `DriverMainActivity.java` — driver dashboard
  - `home/` — driver home screen
  - `trips/` — active trip management screens
  - `history/` — trip history and completed routes
  - `profile/` — driver account/profile information

---

## 5. UI and Resource Structure

### `app/src/main/res`

This contains all Android UI resources.

- `layout/` — XML layouts for activities and fragments.
- `drawable/` — custom backgrounds, icons, shapes, and vector assets.
- `values/` — colors, theme, and styling resources.
- `font/` — typography resources such as Roboto and custom font assets.
- `anim/` — animations for screen transitions.

---

## 6. Functional Flow Summary

```text
App Launch
  ↓
MainActivity
  ↓
Check MapBox Access Token
  ↓
LoginActivity
  ├── Passenger login
  │   └── Passenger Home / Map / Profile / Favorites
  └── Driver login
      └── Driver Home / Trips / History / Profile
```

This project is structured to support a mock transportation app where data is currently local or sample-based, but the architecture is ready to be extended with a real backend, API services, and database integration later.

---

## 7. Design Goal of the System

The app is organized into clean layers so it can evolve without rewriting the interface:

- `data` handles source data and sample repositories
- `domain` handles business logic and use cases
- `presentation` handles screens and user interaction
- `res` handles all UI visuals and styling

This separation makes it easier to replace mock data with real database or API services in the future.
