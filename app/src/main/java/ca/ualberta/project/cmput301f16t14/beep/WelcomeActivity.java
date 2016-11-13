package ca.ualberta.project.cmput301f16t14.beep;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class WelcomeActivity extends Activity {

    //private Button riderSignInButton;
    //private Button driverSignInButton;
    private EditText usernameEditText;
    private String username;
    private ArrayList<Account> resultAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //riderSignInButton = (Button) findViewById(R.id.button);
        //driverSignInButton = (Button) findViewById(R.id.button2);
        usernameEditText = (EditText) findViewById(R.id.editText);
    }

    /* Call when the user click on the SignUp button */
    public void signUp(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    /* Call when the user click on the SignIn As Rider button */
    public void signInRider(View view){
        Intent intent = new Intent(this, RiderMainAcitivity.class);
        startActivity(intent);
    }

    public void signInDriver (View view) {
        username = usernameEditText.getText().toString();

        ElasticsearchAccountController.GetAccountTask getAccountTask = new ElasticsearchAccountController.GetAccountTask();
        getAccountTask.execute(username.toLowerCase());

        try {
            resultAccounts = getAccountTask.get();
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the Accounts out of the async object.");
            Toast.makeText(WelcomeActivity.this, "Unable to find the Account on elastic search", Toast.LENGTH_SHORT).show();
        }

        if(resultAccounts.isEmpty()){
            Toast.makeText(WelcomeActivity.this, "Username not found", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(WelcomeActivity.this, resultAccounts.get(0).getEmail(), Toast.LENGTH_SHORT).show();
        }



        Intent intent = new Intent(this, DriverMainActivity.class);
        startActivity(intent);
    }
}
