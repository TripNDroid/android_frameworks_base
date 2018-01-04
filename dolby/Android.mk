LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE:= tripndroid.dolby.audio

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-subdir-java-files)

include $(BUILD_JAVA_LIBRARY)

#################################################

include $(CLEAR_VARS)
LOCAL_MODULE        := dax-default.xml
LOCAL_MODULE_TAGS   := optional
LOCAL_MODULE_CLASS  := ETC
LOCAL_SRC_FILES     := configs/dax-default.xml
LOCAL_MODULE_PATH   := $(TARGET_OUT_ETC)/dolby
include $(BUILD_PREBUILT)

include $(CLEAR_VARS)
LOCAL_MODULE        := media_codecs_dolby.xml
LOCAL_MODULE_TAGS   := optional
LOCAL_MODULE_CLASS  := ETC
LOCAL_SRC_FILES     := configs/media_codecs_dolby.xml
LOCAL_MODULE_PATH   := $(TARGET_OUT_VENDOR)/etc
include $(BUILD_PREBUILT)

