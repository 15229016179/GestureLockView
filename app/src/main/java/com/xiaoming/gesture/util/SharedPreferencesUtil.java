package com.xiaoming.gesture.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {
	private Editor editor;
	private SharedPreferences sharedPreferences;

	public Editor getEditor() {
		return editor;
	}

	public SharedPreferencesUtil(Context context, String fileName) {
		sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}

	public String getString(String string) {
		return sharedPreferences.getString(string, null);
	}

	public boolean getBoolean(String key) {
		return sharedPreferences.getBoolean(key, false);
	}

	public int getInt(String key) {
		return sharedPreferences.getInt(key, 0);
	}

	/**
	 * 作用：清除数据
	 * 
	 * @param eception
	 *            不想清除的数据
	 */
	public void clear(List<String> eception) {
		Map<String, ?> map = sharedPreferences.getAll();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			if (eception != null && eception.contains(key)) {
				continue;
			}
			editor.remove(key);
		}
		editor.commit();
	}
}
