package com.zms.jniLearning;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class SoUtils {
    public static void copySo(Context context) throws IOException {
        File file = new File("/sdcard/Android/data/com.zms.jniLearning/libtestJNI.so");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) (file.length()));
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        FileOutputStream fileOutputStream = context.openFileOutput("libtestJNI.so", Context.MODE_PRIVATE);
        FileChannel fileChannelOutput = fileOutputStream.getChannel();
        fileChannelOutput.write(byteBuffer);
        fileOutputStream.close();
        fileInputStream.close();
    }
}
