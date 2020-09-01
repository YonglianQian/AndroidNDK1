#include <jni.h>
#include <string>
#include <stdio.h>
#include <android/log.h>

#include "../google_breakpad/src/client/linux/handler/exception_handler.h"
#include "../google_breakpad/src/client/linux/handler/minidump_descriptor.h"



void Crash(){
    volatile int* a = reinterpret_cast<volatile int*>(NULL);
    *a = 1;
}
bool dumpCallback(const google_breakpad::MinidumpDescriptor &descriptor,
                  void *context,
                  bool succeeded) {

    // Allow system to log the native stack trace.
    __android_log_print(ANDROID_LOG_INFO, "YourLogTag",
                        "Wrote breakpad minidump at %s succeeded=%d\n", descriptor.path(),
                        succeeded);
    return false;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_androidndk1_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++, version 4";

    Crash();
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_androidndk1_MainActivity_setupNativeCrashesListener(JNIEnv *env, jobject thiz,
                                                                     jstring path) {
    const char *dumpPath = (char *) env->GetStringUTFChars(path, NULL);
    google_breakpad::MinidumpDescriptor descriptor(dumpPath);
    new google_breakpad::ExceptionHandler(descriptor, NULL, dumpCallback, NULL, true, -1);
    env->ReleaseStringUTFChars(path, dumpPath);
}

