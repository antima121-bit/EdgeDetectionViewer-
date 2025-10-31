#include <jni.h>
#include <opencv2/opencv.hpp>

extern "C" {
    JNIEXPORT jbyteArray JNICALL
    Java_com_example_edgedetectionviewer_MainActivity_processFrame(
            JNIEnv *env,
            jobject /* this */,
            jbyteArray inputArray,
            jint width,
            jint height) {
        
        // Get input byte array
        jbyte *inputBuffer = env->GetByteArrayElements(inputArray, nullptr);
        
        // Convert to OpenCV Mat
        cv::Mat inputMat(height, width, CV_8UC1, (unsigned char *)inputBuffer);
        
        // Create output Mat for edge detection
        cv::Mat edges;
        
        // Apply Canny edge detection
        cv::Canny(inputMat, edges, 50, 150);
        
        // Create output byte array
        jbyteArray outputArray = env->NewByteArray(width * height);
        env->SetByteArrayRegion(outputArray, 0, width * height, (jbyte *)edges.data);
        
        // Release resources
        env->ReleaseByteArrayElements(inputArray, inputBuffer, 0);
        
        return outputArray;
    }
}