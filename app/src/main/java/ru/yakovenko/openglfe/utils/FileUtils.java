package ru.yakovenko.openglfe.utils;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    /**
     * Возвращает содержимое raw-ресурса в виде строки
     *
     * @param context    контекст
     * @param resourceId id raw-ресурса
     * @return содержимое файла в виде строки
     */
    public static String readTextFromRaw(Context context, int resourceId) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = null;
            try {
                InputStream inputStream = context.getResources().openRawResource(resourceId);
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\r\n");
                }
            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Resources.NotFoundException ex) {
            ex.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
