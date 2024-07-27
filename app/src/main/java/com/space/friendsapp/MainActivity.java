package com.space.friendsapp;

import static com.space.friendsapp.R.id.nam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    AppCompatButton b1,b2;
    EditText e1,e2,e3,e4;
    String apiUrl = "https://friendsapi-re5a.onrender.com/adddata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        b1=(AppCompatButton) findViewById(R.id.done);
        b2=(AppCompatButton) findViewById(R.id.view);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.nicknam);
        e3=(EditText)findViewById(R.id.desc);
        e4=(EditText)findViewById(R.id.nam);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = e1.getText().toString();
                String nickName = e2.getText().toString();
                String description = e3.getText().toString();
                String frnam = e4.getText().toString();
                //creating JSON object.

                JSONObject friend=new JSONObject();
                try {
                    friend.put("name",name);
                    friend.put("friendName",frnam);
                    friend.put("friendNickName",nickName);
                    friend.put("DescribeYourFriend",description);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //JSON object request creation

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        apiUrl,
                        friend,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Toast.makeText(getApplicationContext(), "API successfull", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(getApplicationContext(),"API unsuccessfull", Toast.LENGTH_LONG).show();

                            }
                        }
                );
                //Request queue

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), viewfriends.class);
                startActivity(i);
            }
        });
    }
}