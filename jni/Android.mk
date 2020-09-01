LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE    := native-lib
LOCAL_SRC_FILES := native-lib.cpp


LOCAL_STATIC_LIBRARIES += breakpad_client

include $(BUILD_SHARED_LIBRARY)

# pay attention to the second path

ifneq ($(NDK_MODULE_PATH),)
  $(call import-module,google_breakpad)
else
 include ../google_breakpad/Android.mk
endif