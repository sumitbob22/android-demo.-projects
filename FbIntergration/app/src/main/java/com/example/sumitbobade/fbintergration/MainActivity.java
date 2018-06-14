package com.example.sumitbobade.fbintergration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{

    TextView status;
    LoginButton loginB;

    AccessToken accessToken=null;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        //getting correct keyhash
        try {
            @SuppressLint("PackageManagerGetSignatures") PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.sumitbobade.fbintergration",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        initialisedControls();
         boolean isToken= AccessToken.getCurrentAccessToken()!=null;
        if (isToken)
        {
            loginB.setVisibility(View.GONE);
            accessToken=AccessToken.getCurrentAccessToken();
            accessUserdata();
            Log.i("isnside not null","token ");
        }
        else{
            accessToken=null;
            loginB.setVisibility(View.VISIBLE);
            LoginWithFb();
        }

        //LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList("email"));
        //LoginManager.getInstance().logInWithPublishPermissions(this,Arrays.asList("publish_actions"));
    }

    public void initialisedControls() {
        callbackManager = CallbackManager.Factory.create();
        status = findViewById(R.id.status);
        loginB = findViewById(R.id.login_button);


        loginB.setReadPermissions(Arrays.asList("user_status"));
        loginB.setReadPermissions(Arrays.asList("public_profile"));
    }

    private void LoginWithFb()
    {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                status.setText("Login Successful" + loginResult.getAccessToken());
                accessToken = loginResult.getAccessToken();

            }

            @Override
            public void onCancel() {
                status.setText("Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                status.setText("Login Error" + error.getMessage());
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        accessUserdata();
    }


    public void accessUserdata()
    {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback()
        {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response)
                    {
                        // Application code
                        final JSONObject jobj = response.getJSONObject();
                       // System.out.println( jobj.toString());

                        Intent intent=new Intent(getApplicationContext(),ProfileData.class);

                        try {
                            String id=jobj.getString("id");
                            String name=jobj.getString("name");
                            String link=jobj.getString("link");

                            intent.putExtra("id",id);
                            intent.putExtra("name",name);
                            intent.putExtra("link",link);
                            startActivity(intent);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();

    }

}