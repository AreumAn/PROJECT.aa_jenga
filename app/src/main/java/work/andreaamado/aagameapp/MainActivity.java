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

    ArrayList<JSONObject> list = new ArrayList<>();
    String[] questions = new String[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set Category button
        ImageButton btnGen = (ImageButton) findViewById(R.id.btn_cat_gen);
        ImageButton btnAdu = (ImageButton) findViewById(R.id.btn_cat_adu);
        ImageButton btnFam = (ImageButton) findViewById(R.id.btn_cat_fam);
        ImageButton btnSch = (ImageButton) findViewById(R.id.btn_cat_sch);

//        // Set alert message
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you wanna ");

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

            for(int i=0; i<jsonArray.length(); i++) {
                //jsonObject = jsonArray.getJSONObject(i);
                //System.out.println(jsonObject.getString("CODE") + " : " + jsonObject.getString("TYPE") + " : " + jsonObject.getString("QUESTION"));
                //list.add(jsonObject.getString("QUESTION"));
                list.add(jsonArray.getJSONObject(i));
            }

        }catch(JSONException je){
            Log.e("jsonErr", "JSON ERROR", je);
        }catch(Exception e){
            Log.e("execption", "NO FILE", e);
        }

        // Cat: General
        btnGen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String cate = "General";
                makeQueArr(cate);

                builder.setTitle(cate);
                builder.setPositiveButton("Enter specific number", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        intentGameNumber();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Random number", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intentGameRandom();
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }

        });


        // Cat: Adult
        btnAdu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String cate = "Adult";
                makeQueArr(cate);

                builder.setTitle(cate);
                builder.setPositiveButton("Enter specific number", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        intentGameNumber();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Random number", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intentGameRandom();
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // Cat: Family
        btnFam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String cate = "Family";
                makeQueArr(cate);

                builder.setTitle(cate);
                builder.setPositiveButton("Enter specific number", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        intentGameNumber();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Random number", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intentGameRandom();
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // Cat: School
        btnSch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String cate = "School";
                makeQueArr(cate);

                builder.setTitle(cate);
                builder.setPositiveButton("Enter specific number", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        intentGameNumber();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Random number", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intentGameRandom();
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });



    }

    // Create array of questions of specific category
    public void makeQueArr(String cate) {
        int j = 0;
        for(int i=0; i<list.size(); i++) {
            try {
                if(list.get(i).getString("TYPE").equals(cate)) {
                    questions[j] = list.get(i).getString("QUESTION");
                    j++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // Move to GameNumber
    public void intentGameNumber() {
        Intent intent = new Intent(MainActivity.this, GameNumbersActivity.class);
        intent.putExtra("myQuestions", questions);
        startActivity(intent);
    }

    // Move to GameRandom
    public void intentGameRandom() {
        Intent intent = new Intent(MainActivity.this, GameRandomActivity.class);
        intent.putExtra("myQuestions", questions);
        startActivity(intent);
    }
}
