package me.wcy.music.api;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * Created by hzwangchenyan on 2016/11/12.
 */
public class Key {
    public static final String FIR_KEY = "FIR_KEY";

    public static String get(Context context, String keyName) {
        String pkgName = context.getPackageName();
        String className = pkgName + ".api.KeyStore";
        try {
            Class apiKey = Class.forName(className);
            Field field = apiKey.getField(keyName);
            field.setAccessible(true);
            return (String) field.get(null);
        } catch (Exception ignored) {
        }
        return "";
    }
}
