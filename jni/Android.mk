LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES := HelloJNI.c
LOCAL_MODULE    := myjni

#LOCAL_STATIC_LIBRARIES += breakpad_client

include $(BUILD_SHARED_LIBRARY)


#ifneq ($(NDK_MODULE_PATH),)
#  $(call import-module,google_breakpad)
#else
# include ../android/google_breakpad/Android.mk
#endif