package com.digihuman.atls_mca.ui;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class SPUtils {
	
	private static Context myContext;
	
	
	public SPUtils() {
    }

    public SPUtils(Context mContext) {
        myContext = mContext;
    }
    
    
	
    /**
     * �������ֻ����SP�ļ���
     */
    public static final String FILE_NAME = "my_sp";

    /**
     * ��������
     */
    public static void put(String key, Object obj) {
        SharedPreferences sp = myContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (obj instanceof Boolean) {
            editor.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float) {
            editor.putFloat(key, (Float) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(key, (Integer) obj);
        } else if (obj instanceof Long) {
            editor.putLong(key, (Long) obj);
        } else {
            editor.putString(key, (String) obj);
        }
        editor.commit();
    }


    /**
     * ��ȡָ������
     */
    public static Object get(String key, Object defaultObj) {
        SharedPreferences sp = myContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if (defaultObj instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObj);
        } else if (defaultObj instanceof Float) {
            return sp.getFloat(key, (Float) defaultObj);
        } else if (defaultObj instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObj);
        } else if (defaultObj instanceof Long) {
            return sp.getLong(key, (Long) defaultObj);
        } else if (defaultObj instanceof String) {
            return sp.getString(key, (String) defaultObj);
        }
        return null;
    }

    /**
     * ɾ��ָ������
     */
    public static void remove(String key) {
        SharedPreferences sp = myContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }


    /**
     * �������м�ֵ��
     */
    public static Map<String, ?> getAll() {
        SharedPreferences sp = myContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        Map<String, ?> map = sp.getAll();
        return map;
    }

    /**
     * ɾ����������
     */
    public static void clear() {
        SharedPreferences sp = myContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * ���key��Ӧ�������Ƿ����
     */
    public static boolean contains(String key) {
        SharedPreferences sp = myContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

}
