package ca.ualberta.cs.linkai.beep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.Exchanger;

import static ca.ualberta.cs.linkai.beep.R.styleable.View;

/**
 * Request list to store and show current user requests
 */

public class RequestsListActivity extends Activity {

    private RequestsAdapter myAdapter;
    private ListView myRequestsList;

    // status variable
    private final static int CREATED = 0;
    private final static int OPEN_REQUEST = 1;
    private final static int CONFIRMED = 2;
    private final static int PAID = 3;
    private final static int CANCELLED = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_list);

        myRequestsList = (ListView) findViewById(R.id.requestsListView);

        myAdapter = new RequestsAdapter(this, RuntimeRequestList.getInstance().myRequestList);
        myRequestsList.setAdapter(myAdapter);

        myRequestsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (RuntimeRequestList.getInstance().myRequestList.get(position).getStatus() == OPEN_REQUEST) {
                    Intent intent = new Intent(RequestsListActivity.this, RequestDetailActivity_OPEN.class);
                    intent.putExtra("sendPosition", position);
                    startActivity(intent);
                }else if (RuntimeRequestList.getInstance().myRequestList.get(position).getStatus() == PAID) {
                    Intent intent = new Intent(RequestsListActivity.this, RequestDetailActivity_PAID.class);
                    intent.putExtra("sendPosition", position);
                    startActivity(intent);
                }else if (RuntimeRequestList.getInstance().myRequestList.get(position).getStatus() == CANCELLED) {
                    Intent intent = new Intent(RequestsListActivity.this, RequestDetailActivity_CANCLE.class);
                    intent.putExtra("sendPosition", position);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(RequestsListActivity.this, RequestDetailActivity.class);
                    intent.putExtra("sendPosition", position);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        //Toast.makeText(RequestsListActivity.this, String.valueOf(RuntimeRequestList.getInstance().myRequestList.size()), Toast.LENGTH_SHORT).show();

        //myRequests.clear();
        //myAdapter.clear();
        //myAdapter.addAll(RuntimeRequestList.getInstance().myRequestList);
        myAdapter.notifyDataSetChanged();


        // Search Requests by initiator
        /*
        ElasticsearchRequestController.GetRequestByInitiatorTask getRequestByInitiatorTask =
                new ElasticsearchRequestController.GetRequestByInitiatorTask();
        getRequestByInitiatorTask.execute(RuntimeAccount.getInstance().myAccount);

        try {
            RuntimeRequestList.getInstance().myRequestList = getRequestByInitiatorTask.get();
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the Requests out of the async object.");
            Toast.makeText(RequestsListActivity.this, "Unable to find Requests by elastic search", Toast.LENGTH_SHORT).show();
        }
        */

        /*
        if(RuntimeAccount.getInstance().myAccount.equals(WelcomeActivity.logInAccount)){
            Toast.makeText(RequestsListActivity.this, "successful!", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(RequestsListActivity.this, String.valueOf(myRequests.size()), Toast.LENGTH_SHORT).show();
        */
    }



}
