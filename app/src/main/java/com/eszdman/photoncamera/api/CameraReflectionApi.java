package com.eszdman.photoncamera.api;

import android.Manifest;
import android.app.Application;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureResult;
import android.util.Log;

import com.eszdman.photoncamera.ui.CameraFragment;

import org.chickenhook.restrictionbypass.RestrictionBypass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CameraReflectionApi {
    private static String TAG = "CameraReflectionApi";

    public static <T> void set(CameraCharacteristics.Key<T> key, T value) {
        try {
            //RestrictionBypass.getDeclaredField(CameraCharacteristics.class, "mProperties");
            Field CameraMetadataNativeField = RestrictionBypass.getDeclaredField(CameraCharacteristics.class, "mProperties");//= CameraCharacteristics.class.getDeclaredField("mProperties");
            CameraMetadataNativeField.setAccessible(true);
            Object CameraMetadataNative = CameraMetadataNativeField.get(CameraFragment.mCameraCharacteristics);//Ur camera Characteristics
            Method set = RestrictionBypass.getDeclaredMethod(CameraMetadataNative.getClass(),"set",CameraCharacteristics.Key.class, Object.class);//CameraMetadataNative.getClass().getDeclaredMethod("set", CameraCharacteristics.Key.class, Object.class);
            set.setAccessible(true);
            set.invoke(CameraMetadataNative, key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void set(CaptureResult.Key<T> key, T value) {
        try {
            //Field CameraMetadataNativeField = CaptureResult.class.getDeclaredField("mResults");
            Field CameraMetadataNativeField = RestrictionBypass.getDeclaredField(CaptureResult.class,"mResults");
            CameraMetadataNativeField.setAccessible(true);
            Object CameraMetadataNative = CameraMetadataNativeField.get(CameraFragment.mCaptureResult);//Ur camera CaptureResult
            Method set = RestrictionBypass.getDeclaredMethod(CameraMetadataNative.getClass(),"set", CaptureResult.Key.class, Object.class);//CameraMetadataNative.getClass().getDeclaredMethod("set", CaptureResult.Key.class, Object.class);
            set.setAccessible(true);
            set.invoke(CameraMetadataNative, key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void setAuxOn(){
        Log.d(TAG,"Setting Aux ON");
        try {
            /*Class actThread = Class.forName("android.app.ActivityThread");
            Method getCur = RestrictionBypass.getDeclaredMethod(actThread,"currentActivityThread");
            getCur.setAccessible(true);
            Object curThread = getCur.invoke(null);
            Field isSystem = RestrictionBypass.getDeclaredField(curThread.getClass(),"mSystemThread");
            isSystem.set(curThread,true);*/
            Class SystemProperties = Class.forName("android.os.SystemProperties");
            Method set = RestrictionBypass.getDeclaredMethod(SystemProperties,"native_set",String.class,String.class);
            set.setAccessible(true);
            //set.invoke(null,"camera.aux.packagelist", "com.eszdman.photoncamera");//ActivityThread.currentPackageName()
        } catch (Exception e){
            e.printStackTrace();
        }
        Log.d(TAG,"Setting Aux done!");
    }

    public static void setVERBOSE(boolean in) {
        Object capres = CameraFragment.mCaptureResult;//Ur camera CaptureResult
        Field verbose = null;
        try {
            verbose =  CaptureResult.class.getDeclaredField("VERBOSE");
            verbose.setAccessible(true);
            verbose.set(capres, in);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
