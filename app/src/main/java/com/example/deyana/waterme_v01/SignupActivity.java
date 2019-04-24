package com.example.deyana.waterme_v01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

public class SignupActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText usernameField;
    private EditText passwordField;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainUserActivity.class));
        }

        emailField = findViewById(R.id.emailFieldSignup);
        usernameField = findViewById(R.id.usernameFieldSignup);
        passwordField = findViewById(R.id.passwordFieldSignup);

    }

    public void attemptSignup(View view){
        emailField.setError(null);
        passwordField.setError(null);

        String email = emailField.getText().toString();
        final String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (password.equals("")){
            passwordField.setError(getString(R.string.error_field_required));
            focusView = passwordField;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            passwordField.setError(getString(R.string.error_invalid_password));
            focusView = passwordField;
            cancel = true;
        }

        if (username.equals("")){
            usernameField.setError(getString(R.string.error_field_required));
            focusView = usernameField;
            cancel = true;
        }

        if (email.equals("")) {
            emailField.setError(getString(R.string.error_field_required));
            focusView = emailField;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailField.setError(getString(R.string.error_invalid_email));
            focusView = emailField;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(getString(R.string.username), username);
                        editor.commit();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainUserActivity.class));
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //the error messages are good for displaying - they don't make the app less secure (than it already is)
                    Toast.makeText(SignupActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //TODO send verification email

    private boolean isEmailValid(String email) {
        boolean isEmailValid = false;
        try {
            isEmailValid = new ValidateEmail().execute(email).get();
            Log.e("result", String.valueOf(isEmailValid));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return isEmailValid;
    }

    private boolean isPasswordValid(String password) {
        //TODO use pattern matching to ensure strong password
        return password.length() > 4;
    }

    private class ValidateEmail extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... parameters) {
            String email = parameters[0];
            URL url = null;
            HttpURLConnection connection = null;
            InputStream inputStream = null;
            String jsonResponse = "";
            boolean isEmailValid = false;

            try {
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("apiKey", "at_MB99EDNpSsaHdIw5hQVdLdSlodhM3")
                        .appendQueryParameter("emailAddress", email)
                        .appendQueryParameter("outputFormat", "JSON")
                        .appendQueryParameter("validateDns", "1")
                        .appendQueryParameter("validateSMTP", "0")
                        .appendQueryParameter("checkCatchAll", "0")
                        .appendQueryParameter("checkFree", "0")
                        .appendQueryParameter("checkDisposable", "0")
                        .appendQueryParameter("_hardRefresh", "1");
                String url_string = "https://emailverification.whoisxmlapi.com/api/v1?" + builder.build().getEncodedQuery();
                url = new URL(url_string);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                inputStream = connection.getInputStream();
                jsonResponse = readFromStream(inputStream);
                isEmailValid = turnStringToJsonAndCheckIfEmailIsValid(jsonResponse);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null){
                    connection.disconnect();
                }
                if (inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return isEmailValid;
        }

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if(inputStream != null){
                InputStreamReader inputStreamReader =
                        new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null){
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        private boolean turnStringToJsonAndCheckIfEmailIsValid(String jsonResponse) throws JSONException {
            JSONObject jo = new JSONObject(jsonResponse);
            String formatCheck = jo.getString("formatCheck");
            String dnsCheck = jo.getString("dnsCheck");
            return (formatCheck.equals("true") && dnsCheck.equals("true"));
        }

    }
}
