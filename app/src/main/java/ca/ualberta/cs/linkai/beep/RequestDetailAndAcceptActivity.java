package ca.ualberta.cs.linkai.beep;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.util.List;

public class RequestDetailAndAcceptActivity extends Activity {

    private Bundle bundle;
    public static Request request;

    private TextView riderName;
    private TextView start;
    private TextView end;
    private TextView totalPrice;
    private Button acceptRequest;

    private List<Address> from;
    private List<Address> to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail_and_accept);
    }

    @Override
    public void onStart(){
        super.onStart();

        bundle = getIntent().getExtras();

        if (bundle != null){
            int i = bundle.getInt("request_Detail");
            if(DriverMainActivity.searchType == DriverMainActivity.SEARCH_BY_PRICE ) {
                request = SearchByPriceActivity.resultRequests.get(i);
            } else if(DriverMainActivity.searchType == DriverMainActivity.SEARCH_BY_KEYWORD) {
                request = SearchByKeywordActivity.requestsList.get(i);
            }else if(DriverMainActivity.searchType == DriverMainActivity.SEARCH_BY_ADDRESS){
                request = SearchByAddressActivity.requestList.get(i);
            }
        }

        riderName  = (TextView) findViewById(R.id.textView9);
        start      = (TextView) findViewById(R.id.textView11);
        end        = (TextView) findViewById(R.id.textView13);
        totalPrice = (TextView) findViewById(R.id.textView15);
        acceptRequest = (Button) findViewById(R.id.AcceptButton) ;

        riderName.setText(request.getInitiator().getUsername());
        riderName.setBackgroundResource(R.color.orange);
        riderName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestDetailAndAcceptActivity.this, ViewProfileActivity.class);
                startActivity(intent);
            }
        });
        acceptRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RequestDetailAndAcceptActivity.this, "Congratulation! Your acceptance has added to waiting list.", Toast.LENGTH_SHORT).show();
                request.addAcceptance(RuntimeAccount.getInstance().myAccount);
                ElasticsearchRequestController.AddRequestTask addRequestTask = new ElasticsearchRequestController.AddRequestTask();
                addRequestTask.execute(request);
            }
        });


        Geocoder geocoder = new Geocoder(RequestDetailAndAcceptActivity.this);
        try {
            from = geocoder.getFromLocation(request.getStartLatLng().latitude, request.getStartLatLng().longitude, 1);
            to = geocoder.getFromLocation(request.getEndLatLng().latitude, request.getEndLatLng().longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        start.setText(from.get(0).getLocality());
        end.setText(to.get(0).getLocality());

        totalPrice.setText(request.getFare().toString());


    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

        finish();
    }

}
