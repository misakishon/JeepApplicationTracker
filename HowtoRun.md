# Passenger & Driver App — Java Android Architecture

This is a complete starter architecture for the application shown in the supplied UI reference.

Architecture:

- Java
- Android Studio
- MVVM
- Repository pattern
- Separate Passenger and Driver feature modules/packages
- Material-style UI
- Bottom navigation
- Mock/local data ready to replace with an API/database

## Passenger screens represented

- Home
- Profile
- Map
- Favorites

## Driver screens represented

- Home
- Trips
- Trip History
- Profile

## Suggested backend-ready layers

data -> domain -> presentation

No real authentication, GPS, routing, or server API is included yet. The project is organized so those services can be added without rewriting the UI layer.

## check emulator

& "$env:LOCALAPPDATA\Android\Sdk\emulator\emulator.exe" -list-avds

## run emulator

& "$env:LOCALAPPDATA\Android\Sdk\emulator\emulator.exe" -avd Pixel6 -gpu host

## install debug application

$env:Path = ";C:\tools\gradle-8.9\bin;$env:Path"
cd "C:\JeepApplicationTracker"
gradle installDebug

## launch the apk

& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" -s emulator-5554 shell am start -n com.example.passengerdriverapp/.presentation.MainActivity

## launch debug

$env:Path = "C:\tools\gradle-8.9\bin;$env:Path"; gradle :app:assembleDebug
