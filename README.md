# Edge Detection Viewer 📱🔍

A real-time edge detection Android application built for a technical assessment. This project uses the device's camera to capture a video feed, processes each frame in real-time using native C++ and OpenCV to detect edges, and displays the result on the screen using OpenGL ES. It also includes a simple companion web viewer built with TypeScript.

## ✅ Implemented Features

### Core Android App
- **Real-time Camera Feed**: Utilizes **CameraX** for efficient and modern camera operations.
- **Native C++ Processing**: A **JNI bridge** passes camera frames to a native C++ layer for high-performance processing.
- **OpenCV for Edge Detection**: Implements **Canny edge detection** using the OpenCV library in C++.
- **OpenGL ES 2.0 Rendering**: A custom renderer displays the processed video feed on a `GLSurfaceView`, with GLSL shaders managing the final output.
- **(Bonus) Toggle Functionality**: A `FloatingActionButton` allows the user to instantly switch between the raw camera feed and the edge-detected view.

### Web Viewer
- **TypeScript & Webpack**: A simple web interface built with TypeScript and bundled using Webpack.
- **Sample Image Display**: Demonstrates how a processed frame could be displayed on a web client, including a button to load a sample image.

## 📷 Screenshots & Demo

*It is highly recommended to add a GIF or screenshots here to demonstrate the final application.*

**Example:**

| Raw Camera View | Edge Detection View |
| :---: | :---: |
| *[Insert Screenshot of Raw View]* | *[Insert Screenshot of Edge-Detected View]* |

## ⚙️ Setup and Build Instructions

### Prerequisites
- Android Studio (latest stable version recommended)
- Android NDK and CMake (installable via SDK Manager in Android Studio)
- Node.js and npm (for the web viewer)

### Building and Running the Project

1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/antima121-bit/EdgeDetectionViewer-.git
    cd EdgeDetectionViewer-
    ```

2.  **Configure OpenCV (if needed)**:
    This project is configured to automatically download and link a pre-built OpenCV library via the `c:\Users\Kuldeep Mishra\Flam\EdgeDetectionViewer\jni\src\main\cpp\CMakeLists.txt` file. No manual OpenCV setup is required.

3.  **Build and Run the Android App**:
    - Open the `EdgeDetectionViewer` project in Android Studio.
    - Let Gradle sync and download all dependencies.
    - Connect an Android device or start an emulator.
    - Click the "Run" button (▶️) in Android Studio.

4.  **Build and Run the Web Viewer**:
    ```bash
    cd web
    npm install
    npm run build
    ```
    After building, open the `web/dist/index.html` file in a web browser to see the viewer.

## 🧠 Architecture Overview

The application follows a clean, modular architecture that separates concerns between the UI, camera handling, native processing, and rendering.

### Data Flow
1.  **Camera Capture**: `MainActivity.kt` uses the **CameraX API**. An `ImageAnalysis` use case provides a stream of frames.
2.  **JNI Bridge**: For each frame, the `ImageAnalysis.Analyzer` calls the native function `processFrame` defined in `edge_detection.cpp`, passing the image data.
3.  **Native Processing**: The C++ code converts the incoming byte array into an `cv::Mat`, performs a grayscale conversion, and applies the `cv::Canny` algorithm.
4.  **Return to Kotlin**: The processed `cv::Mat` (as a byte array) is returned to the Kotlin layer.
5.  **OpenGL Rendering**: The `GLSurfaceView.kt` receives the processed data. Its `EdgeDetectionRenderer` uploads this data into a GL texture and renders it onto a simple quad that fills the view.

### Component Interaction
```
[CameraX ImageAnalysis] → [MainActivity.kt] → [JNI: edge_detection.cpp] → [OpenCV Canny] ↘
                                                                                     [GLSurfaceView.kt] ← [MainActivity.kt]
```

## 🔄 Development Workflow & Git Strategy

The project was developed with a clear, feature-based commit history as required.

- **Commit Message Format**: Conventional Commits standard was used (e.g., `feat:`, `fix:`, `docs:`).
- **Branching**: All work was done on the `master` branch.
- **History**: The commit history was carefully managed to ensure it is clean, atomic, and correctly attributed.

## 📝 License

This project is unlicensed and free to use.

## 🤝 Acknowledgment

This project was completed with the assistance of GitHub Copilot to accelerate development and explore best practices in Android, NDK, and OpenGL integration.