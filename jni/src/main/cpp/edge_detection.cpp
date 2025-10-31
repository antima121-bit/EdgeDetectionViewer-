#include <jni.h>
#include <opencv2/core.hpp>
#include <opencv2/imgproc.hpp>
#include <android/log.h>

#define LOG_TAG "EdgeDetection"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

extern "C" JNIEXPORT void JNICALL
Java_com_example_edgedetectionviewer_MainActivity_processFrame(
        JNIEnv *env,
        jobject /* this */,
        jint width,
        jint height,
        jobject yBuffer,
        jobject uBuffer,
        jobject vBuffer,
        jint yRowStride,
        jint uvPixelStride,
        jint uvRowStride,
        jobject output) {

    auto *y = static_cast<uint8_t *>(env->GetDirectBufferAddress(yBuffer));
    auto *u = static_cast<uint8_t *>(env->GetDirectBufferAddress(uBuffer));
    auto *v = static_cast<uint8_t *>(env->GetDirectBufferAddress(vBuffer));

    cv::Mat yuv(height + height / 2, width, CV_8UC1);
    memcpy(yuv.data, y, width * height);

    int uv_offset = width * height;
    for (int i = 0; i < height / 2; ++i) {
        for (int j = 0; j < width / 2; ++j) {
            yuv.data[uv_offset++] = v[i * uvRowStride + j * uvPixelStride];
            yuv.data[uv_offset++] = u[i * uvRowStride + j * uvPixelStride];
        }
    }

    cv::Mat rgba(height, width, CV_8UC4);
    cv::cvtColor(yuv, rgba, cv::COLOR_YUV2RGBA_NV21);

    cv::Mat gray;
    cv::cvtColor(rgba, gray, cv::COLOR_RGBA2GRAY);

    cv::Mat edges;
    cv::Canny(gray, edges, 100, 200);

    cv::Mat edge_rgba;
    cv::cvtColor(edges, edge_rgba, cv::COLOR_GRAY2RGBA);

    auto *out = static_cast<uint8_t *>(env->GetDirectBufferAddress(output));
    memcpy(out, edge_rgba.data, edge_rgba.total() * edge_rgba.elemSize());
}