#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_warpspeedassessment_utils_AppParameters_getBaseUrl(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "https://api.themoviedb.org/3/");
}

JNIEXPORT jstring JNICALL
Java_com_example_warpspeedassessment_utils_AppParameters_getApiKey(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YWIxZDAwMTEwMmI3Zjg2YmRiNzU4OTdiMTg3OTc5YiIsIm5iZiI6MTcxOTM4MTY5OS4wNzc5MzksInN1YiI6IjYzMWQzY2VjY2I4MDI4MDA3YWI2ZjNkNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-JH5x3lqCh-7ytAoRdtPSf7woKtlIBUG8j4prgzrDgY");
}

JNIEXPORT jstring JNICALL
Java_com_example_warpspeedassessment_utils_AppParameters_getPosterBaseUrl(JNIEnv *env,
                                                                          jobject thiz) {
    return (*env)->NewStringUTF(env, "https://image.tmdb.org/");
}