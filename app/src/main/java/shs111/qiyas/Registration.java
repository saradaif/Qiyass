package shs111.qiyas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class Registration extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfPassword;
    private EditText editTextEmail;
    private EditText editTextConfEmail;

    private Button buttonRegister;

    private static final String REGISTER_URL = "http://group chatting for qiyas mobile application/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = (EditText) findViewById(R.id.nameEditText);
        editTextPassword = (EditText) findViewById(R.id.passwordEditText2);
        editTextConfPassword = (EditText) findViewById(R.id.confpasswordEditText1);
        editTextEmail = (EditText) findViewById(R.id.EmaileditText4);
        editTextConfEmail = (EditText) findViewById(R.id.confEmailEditText5);

        buttonRegister = (Button) findViewById(R.id.okButton);

        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister) {
            registerUser();
        }
        if(v == buttonRegister) {
            startActivity(new Intent(this, GroupsSetting.class));
        }
    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString().trim().toLowerCase();
        String confpassword = editTextConfPassword.getText().toString().trim().toLowerCase();
        String email = editTextEmail.getText().toString().trim().toLowerCase();
        String confemail = editTextConfEmail.getText().toString().trim().toLowerCase();

        register(username,password,confpassword ,email,confemail);
    }

    private void register(String username, String password, String confpassword, String email, String confemail) {
        class RegisterUser extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Registration.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("username",params[0]);
                data.put("password",params[1]);
                data.put("confpassword",params[2]);
                data.put("email",params[3]);
                data.put("confemail",params[3]);

                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(username,password,confpassword,email, confemail);
    }
}