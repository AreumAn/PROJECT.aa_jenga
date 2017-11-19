package work.andreaamado.aagameapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends BaseActivity {

    ArrayList<JSONObject> list = new ArrayList<>();
    // For real
    //String[] questions = new String[100];
    //String[] questionsMix = new String[400];
    // For test
    String[] questions = new String[5];
    String[] questionsMix = new String[20];
    TextView txtTitle;
    Button btnRandom;
    Button btnNumber;
    String cate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set Category button
        ImageButton btnGen = (ImageButton) findViewById(R.id.btn_cat_gen);
        ImageButton btnAdu = (ImageButton) findViewById(R.id.btn_cat_adu);
        ImageButton btnFam = (ImageButton) findViewById(R.id.btn_cat_fam);
        ImageButton btnSch = (ImageButton) findViewById(R.id.btn_cat_sch);
        ImageButton btnMix = (ImageButton) findViewById(R.id.btn_cat_mix);

//        // Set alert message
//        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setMessage("Do you wanna ");

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        //----- Get data from JSON file -----//

        // Get assets
        AssetManager assetManager = getResources().getAssets();

        try{
            // Open Json : real
            //AssetManager.AssetInputStream jfile = (AssetManager.AssetInputStream)assetManager.open("questions.json");
            // Open Json : test data
            AssetManager.AssetInputStream jfile = (AssetManager.AssetInputStream)assetManager.open("test.json");

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
                cate = "General";
                makeQueArr(cate);
                showDialog();
            }
        });

        // Cat: Adult
        btnAdu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                cate = "Adult";
                makeQueArr(cate);
                showDialog();
            }
        });

        // Cat: Family
        btnFam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                cate = "Family";
                makeQueArr(cate);
                showDialog();
            }
        });

        // Cat: School
        btnSch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                cate = "School";
                makeQueArr(cate);
                showDialog();
            }
        });

        // Cat: Mix
        btnMix.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                cate = "Mix";
                for(int i=0; i<list.size(); i++) {
                    try {
                        questionsMix[i] = list.get(i).getString("QUESTION");
                           // System.out.println(questions[i]);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                showDialog();
            }
        });

    }

    // Show the custom dialog from layout_custom_dialog.xml file
    public void showDialog(){
        final Dialog dialog = new Dialog(MainActivity.this);
        //create dialog without title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set the custom dialog's layout to the dialog
        dialog.setContentView(R.layout.layout_custom_dialog);
        //set the background of dialog box as transparent
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //display the dialog box
        dialog.show();

        //initializing views of custom dialog
        txtTitle = (TextView)dialog.findViewById(R.id.txtTitle);
        btnRandom = (Button)dialog.findViewById(R.id.btnRandom);
        btnNumber = (Button)dialog.findViewById(R.id.btnNumber);

        txtTitle.setText(cate);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cate.equals("Mix")) {
                    Intent intent = new Intent(MainActivity.this, GameRandomActivity.class);
                    intent.putExtra("myQuestions", questionsMix);
                    intent.putExtra("cate", cate);
                    startActivity(intent);
                    dialog.dismiss();
                } else {
                    intentGameRandom(cate);
                    dialog.dismiss();
                }
            }
        });

        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cate.equals("Mix")) {
                    Intent intent = new Intent(MainActivity.this, GameNumbersActivity.class);
                    intent.putExtra("myQuestions", questionsMix);
                    intent.putExtra("cate", cate);
                    startActivity(intent);
                    dialog.dismiss();
                } else {
                    intentGameNumber(cate);
                    dialog.dismiss();
                }
            }
        });

//        //Typeface class specifies style of a font.
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Aller_Lt.ttf");
//        //setting the font of some textviews and buttons
//        txtLogin.setTypeface(typeface);
//        txtEmail.setTypeface(typeface);
//        txtPassword.setTypeface(typeface);
//        btnLogin.setTypeface(typeface);
//        btnPress.setTypeface(typeface);

    }

    // Create array of questions of specific category
    public void makeQueArr(String cate) {
        int j = 0;
        for(int i=0; i<list.size(); i++) {
            try {
                if(list.get(i).getString("TYPE").equals(cate)) {
                    questions[j] = list.get(i).getString("QUESTION");
                    System.out.println(questions[j]);
                    j++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // Move to GameNumber
    public void intentGameNumber(String cate) {
        Intent intent = new Intent(MainActivity.this, GameNumbersActivity.class);
        intent.putExtra("myQuestions", questions);
        intent.putExtra("cate", cate);
        startActivity(intent);
    }

    // Move to GameRandom
    public void intentGameRandom(String cate) {
        Intent intent = new Intent(MainActivity.this, GameRandomActivity.class);
        intent.putExtra("myQuestions", questions);
        intent.putExtra("cate", cate);
        startActivity(intent);
    }

}
