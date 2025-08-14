# GenerateExecutable – Windows Build Guide

This project uses **JetBrains Compose Multiplatform** to build a **Windows desktop application** packaged as an `.msi` or `.exe` installer.  
Once packaged, the installer includes a **bundled Java runtime**, so the end user does **not** need to install Java separately.

---

## 1. Prerequisites

Before building for Windows, make sure you have:

1. **Full JDK 17 or later** installed  
   - Recommended: [Eclipse Temurin JDK](https://adoptium.net/) or [Oracle JDK](https://www.oracle.com/java/technologies/downloads/).
   - The JDK **must include `jpackage.exe`** (bundled in standard JDKs 14+).

2. **Gradle JDK setting** in Android Studio pointing to your full JDK:  
   - Go to `File → Settings → Build, Execution, Deployment → Build Tools → Gradle`.
   - Change **Gradle JDK** to your installed JDK (e.g., `Eclipse Temurin 21`).
   - **Do not** use Android Studio's bundled JBR — it does not contain `jpackage`.

3. **Windows machine** or VM  
   - Windows packaging must be done on Windows because `jpackage` generates Windows-specific installers.

---

## 2. Packaging the App

### Build MSI installer
```powershell
.\gradlew :composeApp:packageMsi
````

The `.msi` file will be created at:

```
composeApp/build/compose/binaries/main/msi/
```

### Build EXE installer (optional)

In `build.gradle.kts`:

```kotlin
targetFormats(TargetFormat.Exe, TargetFormat.Msi)
```

Then run:

```powershell
.\gradlew :composeApp:createDistributable
```

The `.exe` will be in:

```
composeApp/build/compose/binaries/main/exe/
```

---

## 3. Installing the App

* Run the `.msi` or `.exe` file.
* By default, the app is installed to:

  ```
  C:\Program Files\<packageName>\
  ```
* To find it:

  * Search for the app name in the Start Menu.
  * Or open `Control Panel → Programs and Features` (or Settings → Apps in Win11).

---

## 4. Adding Shortcuts and Start Menu Entries

In your `build.gradle.kts`, inside `nativeDistributions`:

```kotlin
windows {
    menu = true
    menuGroup = "Generate Executable Apps"
    shortcut = true
}
```

This will:

* Add a Start Menu entry.
* Optionally create a desktop shortcut.

---

## 5. Notes

* **End users do not need Java installed** — the installer bundles the required runtime.
* File sizes are larger because of the embedded JRE.
* If you see `jpackage.exe is missing`:

  * You are still using Android Studio's JBR.
  * Fix by installing a full JDK and switching Gradle to use it.

---

**Author:** Ralph Maron Eda
