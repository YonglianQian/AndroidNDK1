#include <jni.h>
#include "include/HelloJNI.h"
#include <jni.h>
#include <stdbool.h>

//#include "../google_breakpad/src/client/linux/handler/exception_handler.h"
//#include "../google_breakpad/src/client/linux/handler/minidump_descriptor.h"

JNIEXPORT
jstring
Java_com_example_androidndk1_MainActivity_getMessage(JNIEnv
           * env,
           jobject thiz
) {

    return (*env)->NewStringUTF(env, "Version3, Hello World from native code!");
}
//void Crash() {
//    volatile int* a = reinterpret_cast<volatile int*>(NULL);
//    *a = 1;
//}
//
//bool dumpCallback(const google_breakpad::MinidumpDescriptor& descriptor,
//void* context,
//bool succeeded) {
//printf("Dump path: %s\n", descriptor.path());
//return succeeded;
//}
//
//void Java_com_example_androidndk1_MainActivity_setupNativeCrashesListener(
//        JNIEnv *env, jobject, jstring path) {
//    const char *dumpPath = (char *) env->GetStringUTFChars(path, NULL);
//    google_breakpad::MinidumpDescriptor descriptor(dumpPath);
//    new google_breakpad::ExceptionHandler(descriptor, NULL, dumpCallback, NULL, true, -1);
//    env->ReleaseStringUTFChars(path, dumpPath);
//    Crash();
//}

