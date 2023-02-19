package com.particlesdevs.photoncamera.pro;

import android.os.Build;

public class Devices {
    public static boolean isA50() {
        return Build.DEVICE.equalsIgnoreCase("a50");
    }
}
