package com.sim.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String url = "http://192.168.137.1:5000";
    private String url_img = "http://192.168.137.1/OptimisedShapeDetection/testImages/test5.jpg";
    private ImageView mAvatarImage;
    public static List<TypeClasse> items;
    public static List<Personality> personalities;
    public static int count_y_1 = 0;
    public static int count_y_2 = 0;
    public static int count_y_3 = 0;
    public static int count_y_4 = 0;
    public Button fab ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAvatarImage = findViewById(R.id.mAvatarImage);
        mAvatarImage.setImageResource(R.drawable.test5);
        fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items = new ArrayList<TypeClasse>();
                personalities = new ArrayList<Personality>();
                saveProfileAccount(view);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void saveProfileAccount(final View v) {
        // loading or check internet connection or something...
        // ... then

        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                try {
                    JSONArray results = new JSONArray(resultResponse);

                    int index = results.length();
                    System.out.println("\nnumber of objects returned  " + index);
                    count_y_1 = 0;
                    count_y_2 = 0;
                    for (int i = 0; i < index; i++) {
                        JSONObject crt = results.getJSONObject(i);
                        Double score = crt.getDouble("score");
                        String classs = crt.getString("class");
                        JSONObject box = crt.getJSONObject("box");
                        Double xmax = box.getDouble("xmax");
                        Double ymax = box.getDouble("ymax");
                        Double xmin = box.getDouble("xmin");
                        Double ymin = box.getDouble("ymin");
                        countPersonality(classs);
                        Box box_obj = new Box(xmax, xmin, ymax, ymin);
                        TypeClasse tupe_obj = new TypeClasse(score, classs, box_obj);
                        items.add(tupe_obj);
                        // regrouper les classes
                        // calculer la pourcentage de chaque personality


//                        Snackbar.make(v, "class   " + classs + " son index = " + i, Snackbar.LENGTH_LONG)
//                                .setAction("Ok", null).show();
                        // tell everybody I have succeed upload image and post strings and I have
                    }
                    System.out.println("\nthis is the count of y_2 in this image : " + count_y_2);
                    System.out.println("\nthis is the count of y_1 in this image : " + count_y_1);
                    System.out.println("\nthis is the count of all detected classes : " + index);
                    Personality pery_2 = calculatePercentage(count_y_2, index, "2.0");
                    Personality pery_1 = calculatePercentage(count_y_1, index, "1.0");
                    personalities.add(pery_1);
                    personalities.add(pery_2);
                 /*   Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {*/
                    goTolist();
                      /*  }
                    } , 5000);*/
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("result " + resultResponse);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                String errorMessage = "Unknown error";
                if (networkResponse == null) {
                    if (error.getClass().equals(TimeoutError.class)) {
                        errorMessage = "Request timeout";
                    } else if (error.getClass().equals(NoConnectionError.class)) {
                        errorMessage = "Failed to connect server";
                    }
                } else {
                    String result = new String(networkResponse.data);
                    try {
                        JSONObject response = new JSONObject(result);
                        System.out.println(response.toString());
                        Double status = response.getDouble("score");
                        String score = response.getString("class");
                        String message = "hello this is eror";
                        System.out.println("class   " + status);
                        System.out.println("score  " + status);
                        System.out.println("the response baby :" + result);

                        if (networkResponse.statusCode == 404) {
                            errorMessage = "Resource not found";
                        } else if (networkResponse.statusCode == 401) {
                            errorMessage = message + " Please login again";
                        } else if (networkResponse.statusCode == 400) {
                            errorMessage = message + " Check your inputs";
                        } else if (networkResponse.statusCode == 500) {
                            errorMessage = message + " Something is getting wrong";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
                Log.i("Error", errorMessage);
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("image", url_img);

                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                params.put("image", new DataPart("test5.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mAvatarImage.getDrawable()), "image/jpeg"));
//                params.put("cover", new DataPart("file_cover.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mCoverImage.getDrawable()), "image/jpeg"));

                return params;
            }
        };

        VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void countPersonality(String classs) {

        if (classs.equals("1.0")) {
            count_y_1 += 1;
        }
        if (classs.equals("2.0")) {
            count_y_2 += 1;
        }
        if (classs.equals("3.0")) {
            count_y_3 += 1;
        }
        if (classs.equals("4.0")) {
            count_y_4 += 1;
        }


    }

    public void goTolist() {
        Intent i = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(i);
    }

    public Personality calculatePercentage(int i, int j, String classs) {

        try {
            float percentage = Float.parseFloat(String.valueOf(i));
            float dec = percentage * 100;

            float per = dec / Float.parseFloat(String.valueOf(j));
            System.out.println(per);
            System.out.println("\nthis is the percentage of class  " + classs + " : " + per + "%");
            Personality p = createPersonalityTrait(classs, per);
            return p;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return createPersonalityTrait(classs, 0);
        }
    }

    public Personality createPersonalityTrait(String classs, float percentage) {

        Personality p = new Personality();
        p.setClass_type(classs);
        p.setPourcentage(percentage);
        if (p.getClass_type().equals("1.0")) {
            System.out.println("personality trait type y_1");
            p.setPersonaly_text("Loner / Loner with lack of drive");

        }
        if (p.getClass_type().equals("2.0")) {
            System.out.println("personality trait type y_2");
            p.setPersonaly_text("Physical frustration ");

        }
        if (p.getClass_type().equals("3.0")) {
            System.out.println("personality trait type y_3");
            p.setPersonaly_text("Clannish / anti_social(lack of trust )");

        }
        if (p.getClass_type().equals("4.0")) {
            System.out.println("personality trait type y_4");
            p.setPersonaly_text("healthy physical drives / exaggerated physical drives");

        }
        System.out.println(p.toString());
        Toast.makeText(this, p.toString(), Toast.LENGTH_SHORT).show();

        return p;
    }


}


















