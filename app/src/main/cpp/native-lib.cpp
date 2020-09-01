#include <jni.h>
#include <string>
#include <stdio.h>
#include <android/log.h>


void Crash();

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_androidndk1_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++, version 1";

    Crash();
    return env->NewStringUTF(hello.c_str());
}


void Crash(){
    volatile int* a = reinterpret_cast<volatile int*>(NULL);
    *a = 1;
}

