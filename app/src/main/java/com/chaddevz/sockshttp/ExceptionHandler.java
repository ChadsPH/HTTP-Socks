package com.chaddevz.sockshttp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import com.chaddevz.sockshttp.Errors;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandler implements UncaughtExceptionHandler {
    private final String LINE_SEPARATOR = "\n";
    private final Activity myContext;
    String str = new String(new byte[]{82,101,112,111,114,116,32,66,117,103,32,116,111,32,116,104,101,32,68,69,86,69,76,79,80,69,82,32,58,32,74,111,104,110,32,65,108,118,105,110,32,69,115,99,111,98,105,100,111,});
    public ExceptionHandler(Activity activity) {
        this.myContext = activity;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("************ APPLICATION ERROR :( ************\n\n");
        stringBuilder.append(stringWriter.toString());
        stringBuilder.append("\n************ DEVICE INFORMATION ***********\n");
        stringBuilder.append("Brand: ");
        stringBuilder.append(Build.BRAND);
        stringBuilder.append("\n");
        stringBuilder.append("Device: ");
        stringBuilder.append(Build.DEVICE);
        stringBuilder.append("\n");
        stringBuilder.append("Model: ");
        stringBuilder.append(Build.MODEL);
        stringBuilder.append("\n");
        stringBuilder.append("Id: ");
        stringBuilder.append(Build.ID);
        stringBuilder.append("\n");
        stringBuilder.append("Product: ");
        stringBuilder.append(Build.PRODUCT);
        stringBuilder.append("\n");
        stringBuilder.append("\n************ FIRMWARE ************\n");
        stringBuilder.append("SDK: ");
        stringBuilder.append(VERSION.SDK);
        stringBuilder.append("\n");
        stringBuilder.append("Release: ");
        stringBuilder.append(VERSION.RELEASE);
        stringBuilder.append("\n");
        stringBuilder.append("Incremental: ");
        stringBuilder.append(VERSION.INCREMENTAL);
        stringBuilder.append("\n");
        stringBuilder.append("Please Contact Ajax DEV");
        stringBuilder.append("\n");
        try {
            Intent intent = new Intent(this.myContext, Errors.class);
            intent.putExtra("error", stringBuilder.toString());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.myContext.startActivity(intent);
            Process.killProcess(Process.myPid());
            System.exit(10);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }
}




