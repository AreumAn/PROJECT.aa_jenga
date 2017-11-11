package work.andreaamado.aagameapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        TextView toolbar_title = (TextView) findViewById(R.id.action_bar_title);
        toolbar_title.setText(getResources().getString(R.string.title_activity_categories));

        // Locate the button
        Button btnRandom = (Button) findViewById(R.id.btn_random);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, GameRandomActivity.class);
                startActivity(intent);
            }
        });

        // Locate the button
        Button btnNumber = (Button) findViewById(R.id.btn_numbers);
        btnNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, GameNumbersActivity.class);
                startActivity(intent);
            }
        });

        // Locate the image button
        ImageButton btnAnima = (ImageButton) findViewById(R.id.btn_anima);
        btnAnima.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Set alert message
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Type of Game");
                builder.setMessage("Do you wanna ");
                builder.setPositiveButton("Enter specific number", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        Intent intent = new Intent(MainActivity.this, GameNumbersActivity.class);
                        startActivity(intent);

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Random number", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, GameRandomActivity.class);
                        startActivity(intent);
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

//                Intent intent = new Intent(MainActivity.this, SplashScreenActivity.class);
//                startActivity(intent);
            }
        });


        //----- Get data from JSON file -----//

        // Get assets
        AssetManager assetManager = getResources().getAssets();

        try{
            // Open Json
            AssetManager.AssetInputStream jfile = (AssetManager.AssetInputStream)assetManager.open("questions.json");

            // Read Stream
            BufferedReader br = new BufferedReader(new InputStreamReader(jfile));
            StringBuilder sb = new StringBuilder();

            // Error : if file size is too big(over 4096 byte)
            int bufferSize = 1024 * 1024;
            char readBuf [] = new char[bufferSize];
            int resultSize = 0;

            // Read all file
            while((resultSize = br.read(readBuf))  != -1){
                if(resultSize == bufferSize){
                    sb.append(readBuf);
                }else{
                    for(int i = 0; i < resultSize; i++){
                        sb.append(readBuf[i]);
                    }
                }
            }

            String jString = sb.toString();

            // Get JSONObject
            JSONObject jsonObject =  new JSONObject(jString);

            JSONArray jsonArray = jsonObject.getJSONArray("questions");
            //ArrayList<String> list = new ArrayList<String>();

            for(int i=0; i<jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                System.out.println(jsonObject.getString("CODE") + " : " + jsonObject.getString("TYPE") + " : " + jsonObject.getString("QUESTION"));
               // list.add(jsonObject.getString("CODE") +" "+ jsonObject.getString("QUESTION"));
            }

        }catch(JSONException je){
            Log.e("jsonErr", "JSON ERROR", je);
        }catch(Exception e){
            Log.e("execption", "NO FILE", e);
        }
    }

}
