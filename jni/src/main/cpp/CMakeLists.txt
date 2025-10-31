cmake_minimum_required(VERSION 3.10)

project(edge-detection)

set(OpenCV_STATIC ON)
set(OpenCV_DIR ${CMAKE_SOURCE_DIR}/../../../opencv/native/jni)
find_package(OpenCV REQUIRED)

add_library(edge-detection SHARED
            edge_detection.cpp)

target_link_libraries(edge-detection
                     ${OpenCV_LIBS}
                     android
                     log)