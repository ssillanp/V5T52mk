package com.example.v5t52;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    Context context = null;
    EditText myText;
    EditText fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        myText = (EditText) findViewById(R.id.myText);
        fileName = (EditText) findViewById(R.id.fileName);

        System.out.println(context.getFilesDir());
    }

    public void loadText(View v) {
        try {
            InputStream inStream = context.openFileInput(fileName.getText().toString());

            BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
            String myStr = "";
            StringBuilder outStr = new StringBuilder();
            while ((myStr = br.readLine()) != null) {
                outStr.append(myStr);
                outStr.append("\n");
            }
            System.out.println(outStr);
            myText.setText(outStr.toString());
            inStream.close();
        } catch (IOException e) {
            Log.e("LoadFileIOException", "Virhe striimissä");
        } finally {
            System.out.println(context.getFilesDir());
            System.out.println(fileName.getText());
            System.out.println("READ");
        }
    }

    public void saveText(View v) {
        try {
            OutputStreamWriter outStream = new OutputStreamWriter(context.openFileOutput(fileName.getText().toString(),
                    Context.MODE_PRIVATE));

            outStream.write(myText.getText().toString());
            outStream.close();
        } catch (IOException e) {
            Log.e("LoadFileIOException", "Virhe striimissä");
        } finally {
            System.out.println("WRITTEN");
        }
    }
}
