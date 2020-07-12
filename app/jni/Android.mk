LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := testJNI # name your module here.
LOCAL_SRC_FILES := TestJNI.cpp
include $(BUILD_SHARED_LIBRARY)