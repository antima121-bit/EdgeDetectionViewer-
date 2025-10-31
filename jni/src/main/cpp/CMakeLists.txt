cmake_minimum_required(VERSION 3.22.1)

project("edge_detection_viewer")

# Set the path to your OpenCV Android SDK
# This project will automatically download it if not found.
set(OPENCV_ANDROID_SDK_DIR "" CACHE PATH "Path to OpenCV Android SDK")

if(NOT OPENCV_ANDROID_SDK_DIR)
    # Download OpenCV if not found
    include(FetchContent)
    FetchContent_Declare(
            opencv
            GIT_REPOSITORY https://github.com/opencv/opencv.git
            GIT_TAG 4.x
    )
    FetchContent_MakeAvailable(opencv)
    set(OPENCV_ANDROID_SDK_DIR ${opencv_SOURCE_DIR}/platforms/android)
endif()

find_package(OpenCV REQUIRED COMPONENTS core imgproc)

add_library(
        edge-detection
        SHARED
        edge_detection.cpp)

target_link_libraries(
        edge-detection
        ${OpenCV_LIBS}
        android
        log)