# Travel Companion App 📱

This is an Android application developed using Java in Android Studio for SIT708 Task 2.1.

## 🚀 Features
- Currency Conversion (USD, AUD, EUR, JPY, GBP)
- Fuel & Distance Conversion (mpg, km/L, Liter, Gallon, etc.)
- Temperature Conversion (Celsius, Fahrenheit, Kelvin)
- Input validation (empty input, invalid input, negative values)
- Handles same unit conversion (e.g., USD → USD)

## 🛠 Technologies Used
- Java
- Android Studio
- XML (UI Design)

## 📂 Project Structure
```
2_1/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/a2_1/MainActivity.java
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── gradlew / gradlew.bat

---

## ⚙️ How It Works
1. User selects a **conversion category**
2. App updates available **From** and **To** units
3. User enters a value
4. Clicks **Convert**
5. App validates input and shows result

---

## ⚠️ Validation & Error Handling
- Shows error if input is empty
- Shows error for invalid (non-numeric) input
- Prevents invalid negative values (fuel)
- Handles same-unit conversion properly

---

## 📚 Learning Outcomes
- Understanding Android Activity lifecycle
- Using UI components (Spinner, EditText, Button, TextView)
- Implementing input validation
- Writing conversion logic in Java
- Building simple Android UI

---

## ▶️ How to Run
1. Open project in **Android Studio**
2. Sync Gradle
3. Run on emulator or physical device
4. Use the app to perform conversions

---

## 👨‍💻 Author
**Srikar Boske**  
Deakin University  
SIT708 – Mobile Systems Development
