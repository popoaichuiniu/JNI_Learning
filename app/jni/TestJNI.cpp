//
// Created by jacy on 7/8/20.
//

#include <jni.h>
#include <string>

JNIEXPORT jstring JNICALL
native_getString(JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
/**
 * 对应java类的全路径名，.用/代替
 */
const char *classPathName = "com/zms/jniLearning/MainActivity";

/**
 * JNINativeMethod 结构体的数组
 * 结构体参数1：对应java类总的native方法
 * 结构体参数2：对应java类总的native方法的描述信息，用javap -s xxxx.class 查看
 * 结构体参数3：c/c++ 种对应的方法名
 */
JNINativeMethod method[] = {{"getString", "()Ljava/lang/String;", (void *) native_getString}};

/**
 * 该函数定义在jni.h头文件中，System.loadLibrary()时会调用JNI_OnLoad()函数
 */
JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    //定义 JNIEnv 指针
    JNIEnv *env = NULL;
    //获取 JNIEnv
    vm->GetEnv((void **) &env, JNI_VERSION_1_6);
    //获取对应的java类
    jclass clazz = env->FindClass(classPathName);
    //注册native方法
    env->RegisterNatives(clazz, method, 1);
    //返回Jni 的版本
    return JNI_VERSION_1_6;
}
