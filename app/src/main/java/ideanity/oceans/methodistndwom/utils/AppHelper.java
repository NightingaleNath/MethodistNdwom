package ideanity.oceans.methodistndwom.utils;

import android.app.Activity;
import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AppHelper {
    private Activity activity;

    public AppHelper(Activity activity) {
        this.activity = activity;
    }

    public String gfile(String fileName) {
        InputStream inputStream;
        StringBuilder sb = new StringBuilder();

        try{
            inputStream = activity.getAssets().open(fileName);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int read = inputStream.read(); read != -1; read = inputStream.read())
            {
                byteArrayOutputStream.write(read);
            }
            inputStream.close();
            sb.append(byteArrayOutputStream.toString());
            sb.append("\n");

        } catch (Exception e) {
            sb.append("");
            e.printStackTrace();
        }
        return sb.toString();
    }

}
