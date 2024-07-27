package com.space.friendsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class viewfriends extends AppCompatActivity {
    AppCompatButton b1;
    TextView t1;

    String apiUrl = "https://friendsapi-re5a.onrender.com/view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewfriends);
        t1=(TextView)findViewById(R.id.tv);

        JsonArrayRequest i =new JsonArrayRequest(Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
//                        Toast.makeText(getApplicationContext(),response.toString()+"", Toast.LENGTH_LONG).show();
                            StringBuilder j = new StringBuilder();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject frnd = response.getJSONObject(i);
                                String name = frnd.getString("name");
                                String fname = frnd.getString("friendName");
                                String fnicname = frnd.getString("friendNickName");
                                String desc = frnd.getString("DescribeYourFriend");

                                j.append(name).append(" ").append(fname).append(" ").append(fnicname).append(" ").append(desc);
                            }
                            t1.setText(j.toString());
                        }catch (Exception e) {
                            Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_LONG).show();


                        }
                        }
                    },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(),"API unsuccessfull", Toast.LENGTH_LONG).show();

                    }
                }

    );
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(i);

    }
}