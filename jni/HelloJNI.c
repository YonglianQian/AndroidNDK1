#include <jni.h>
#include "include/HelloJNI.h"
#include <jni.h>

JNIEXPORT
jstring
Java_com_example_androidndk1_MainActivity_getMessage(JNIEnv
           * env,
           jobject thiz
) {
    return (*env)->NewStringUTF(env, "Version1, Hello World from native code!");
}



