# Edge Detection Viewer

This project is a real-time edge detection application for Android, which streams the processed video feed to a web viewer over WebSockets.

## Features

*   **Real-time Edge Detection**: Uses the device's camera to capture video, processes each frame with OpenCV's Canny edge detector in native C++ code.
*   **OpenGL ES Rendering**: Renders the processed frames with edge detection in a `GLSurfaceView`.
*   **Web Viewer**: A simple TypeScript-based web application that connects to the Android app via a WebSocket and displays the live processed feed.
*   **View Toggle**: A Floating Action Button to switch between the live camera preview and the processed edge detection view.

## Project Structure

The project is divided into several modules:

*   `/app`: The main Android application module, containing the `MainActivity`, CameraX setup, WebSocket server, and UI layouts.
  
*   `/jni`: The native C++ module.
    *   `src/main/cpp/edge_detection.cpp`: Contains the JNI bridge and the OpenCV implementation for edge detection.
    *   `src/main/cpp/CMakeLists.txt`: The build script for the native library, which also handles downloading and linking OpenCV.
      
*   `/gl`: A module for the OpenGL ES rendering logic.
    *   `src/main/java/com/example/edgedetectionviewer/GLSurfaceView.kt`: The `GLSurfaceView` and `GLRenderer` implementation.
    *   `src/main/res/raw`: Contains the GLSL vertex and fragment shaders.
      
*   `/web`: The web viewer application.
    *   `src/index.ts`: The main TypeScript file that handles the WebSocket connection and renders the video frames on a canvas.
    *   `webpack.config.js`: Webpack configuration for building the web app.
      
## Preview
<img width="1024" height="1024" alt="flam11-Photoroom" src="https://github.com/user-attachments/assets/71d336c5-0388-4e66-af6a-f35eb7cf7b43" />
<img width="1024" height="1024" alt="flam2-Photoroom" src="https://github.com/user-attachments/assets/3114ddd8-4bcc-4524-a75f-2f3f69570381" />
<img width="1024" height="1024" alt="flam3-Photoroom" src="https://github.com/user-attachments/assets/1913206b-ac85-4f6e-ac5b-ed17c743f0df" />
<img width="1024" height="1024" alt="flam4-Photoroom" src="https://github.com/user-attachments/assets/96b6acae-06f8-46d9-9777-302d6132c92b" />

## How to Build and Run

### Android App

1.  Open the project in Android Studio.
2.  The project is configured to automatically download the required OpenCV for Android SDK via CMake if it's not found.
3.  Build and run the `app` module on an Android device.
4.  The app will request camera permissions. Once granted, it will start the camera and the WebSocket server on port `8080`.

### Web Viewer

1.  Navigate to the `web` directory in your terminal.
2.  Install the dependencies: `npm install`
3.  Start the development server: `npm start`
4.  Open a web browser and go to `http://localhost:9000`.
5.  Ensure the Android device and the computer running the web viewer are on the same network. The web viewer will attempt to connect to the WebSocket server running on the Android device. You may need to find the IP address of your Android device and update the WebSocket URL in `web/src/index.ts`.

## Commit History

The Git commit history for this project has been structured to reflect the development process, with each major feature implemented in a separate, descriptive commit.
