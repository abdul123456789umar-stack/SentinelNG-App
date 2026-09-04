# SentinelNG Android App

SentinelNG is an Android application prototype combining community/security alerts with on-device AI-assisted crop and health scanning. The application uses traditional Android Activities and XML view binding. It includes camera capture, TensorFlow Lite inference, a security-alert data source, multilingual UI helpers, and bundled model assets.

## User-facing areas

| Area | Implementation |
|---|---|
| Main dashboard | `ui/MainActivity.kt` and `MainViewModel.kt`. |
| Camera scanning | `ui/CameraActivity.kt`, CameraX, and `CameraViewModel.kt`. |
| Results | `ui/ResultActivity.kt`. |
| Security alerts | `ui/SecurityActivity.kt`, `SecurityViewModel.kt`, and alert adapters. |
| Settings/language | `ui/SettingsActivity.kt` and `utils/LanguageManager.kt`. |
| Local inference | `ml/TFLiteHelper.kt` and assets such as `crop_doctor.tflite` and `health_scan.tflite`. |

The model layer defines inference results, supported languages, alert categories, severity levels, and chat messages in `data/Models.kt`. Security data can be supplied by the real data source or the mock repository, which makes the prototype usable without a live backend.

## Build

```bash
./gradlew assembleDebug
```

The project targets SDK 34, supports API 24 and above, and uses Java 17. Open the repository root in Android Studio to run the debug application on a camera-capable emulator or device. Camera permission is required for scanning.

## On-device models

The application packages multiple model assets under `app/src/main/assets/`, including TensorFlow Lite models for crop and health scans and other language/model files. These binaries increase APK size and should be versioned deliberately. Confirm model licenses and verify the expected input shape before replacing an asset.

## Architecture and safety

Activities own screen navigation, ViewModels coordinate screen state, `TFLiteHelper` owns interpreter lifecycle, and data-source classes abstract alert retrieval. Scan output is an aid, not a medical or agricultural guarantee; the UI and release documentation should preserve appropriate disclaimers. Before production release, add instrumentation tests, model accuracy evaluation, secure network configuration, and a documented privacy policy.
