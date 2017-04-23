package com.example.puma.treelog;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.puma.treelog.models.TreeData;
import com.example.puma.treelog.models.TreeSession;
import com.example.puma.treelog.utils.Constants;
import com.example.puma.treelog.utils.FetchAddressIntentService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

public class LocateNewTree extends AppCompatActivity
        implements ConnectionCallbacks, OnConnectionFailedListener {
    public static final String EXTRA_TREE_DATA = "EXTRA_TREE_DATA";
    private EditText mLatitudeText; //editText for Latitude
    private EditText mLongitudeText; //editText for Longitude
    private EditText mAddressText; //editText for Address
    private Button btnLocate; //button to call GPS snapping
    private Button btnNextLocate; //button to go to the next page after Locating New Tree
    private EditText editPropertyType; //edit for property type, ListView will be called here

    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient mGoogleApiClient;

    /**
     * Represents a geographical location.
     */
    protected Location mLastLocation;

    /**
     * Receiver registered with this activity to get the response from FetchAddressIntentService.
     */
    private AddressResultReceiver mResultReceiver;

    /**
     * Visible while the address is being fetched.
     */
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_new_tree);

        mResultReceiver = new AddressResultReceiver(new Handler());
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mLatitudeText = (EditText) findViewById(R.id.edit_latitude);
        mLongitudeText = (EditText) findViewById(R.id.edit_longitude);
        mAddressText = (EditText) findViewById(R.id.edit_address);

        btnLocate = (Button) findViewById(R.id.btn_locate);
        btnNextLocate = (Button) findViewById(R.id.btn_next_locate);
        btnLocate.setOnClickListener(new LocateLstr());
        btnNextLocate.setOnClickListener(new LocationDataLstr());
        editPropertyType = (EditText) findViewById(R.id.edit_property);
        editPropertyType.setInputType(InputType.TYPE_NULL);
        editPropertyType.setOnClickListener(new PropertyTypeLstr());
        //Intent locateNewTree = getIntent();
        //Bundle bundle = locateNewTree.getExtras();
        TreeData treeData = TreeSession.getInstance().getTreeData();
        if (treeData == null){
            treeData = new TreeData();
            TreeSession.getInstance().setTreeData(treeData);

        }
        if (treeData != null) {
            mLatitudeText.setText(treeData.getLatitude());
            mLongitudeText.setText(treeData.getLongitude());
            mAddressText.setText(treeData.getStreetAddress());
            editPropertyType.setText(treeData.getPropertyType());
        }

        updateUIWidgets(true);
        buildGoogleApiClient();
    }

    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // Runs when a GoogleApiClient object successfully connects.
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        mGoogleApiClient.connect();
    }

    private class LocateLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_locate) {
                mLatitudeText.setText(null);
                mLongitudeText.setText(null);
                mAddressText.setText(null);
                // Provides a simple way of getting a device's location and is well suited for
                // applications that do not require a fine-grained location and that do not need location
                // updates. Gets the best and most recent location currently available, which may be null
                // in rare cases when a location is not available.
                if (ActivityCompat.checkSelfPermission(LocateNewTree.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                    if (mLastLocation != null) {
                        mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
                        mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
                        startIntentService();
                        updateUIWidgets(false);
                    } else {
                        showToast(getString(R.string.no_location_detected));
                    }
                } else {
                    ActivityCompat.requestPermissions(LocateNewTree.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                }
            }
        }
    }

    // Checks for user response to permission request
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(LocateNewTree.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                        if (mLastLocation != null) {
                            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
                            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
                            startIntentService();
                            updateUIWidgets(false);
                        } else {
                            showToast(getString(R.string.no_location_detected));
                        }
                    }
                }
            }
        }
    }

    /**
     * Creates an intent, adds location data to it as an extra, and starts the intent service for
     * fetching an address.
     */
    protected void startIntentService() {
        // Create an intent for passing to the intent service responsible for fetching the address.
        Intent intent = new Intent(this, FetchAddressIntentService.class);

        // Pass the result receiver as an extra to the service.
        intent.putExtra(Constants.RECEIVER, mResultReceiver);

        // Pass the location data as an extra to the service.
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);

        // Start the service. If the service isn't already running, it is instantiated and started
        // (creating a process for it if needed); if it is running then it remains running. The
        // service kills itself automatically once all intents are processed.
        startService(intent);
    }

    /**
     * Receiver for data sent from FetchAddressIntentService.
     */
    private class AddressResultReceiver extends ResultReceiver {
        AddressResultReceiver(Handler handler) {
            super(handler);
        }

        /**
         * Receives data sent from FetchAddressIntentService and updates the UI in MainActivity.
         */
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultCode == Constants.SUCCESS_RESULT)
                mAddressText.setText(resultData.getString(Constants.RESULT_DATA_KEY));
            else if (resultCode == Constants.FAILURE_RESULT)
                showToast(resultData.getString(Constants.RESULT_DATA_KEY));

            // Reset. Enable the Fetch Address button and stop showing the progress bar.
            updateUIWidgets(true);
        }
    }

    /**
     * Toggles the visibility of the progress bar. Enables or disables the Fetch Address button.
     */
    private void updateUIWidgets(boolean toggle) {
        btnLocate.setEnabled(toggle);
        if (toggle)
            mProgressBar.setVisibility(ProgressBar.GONE);
        else
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    private class LocationDataLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //TODO: validate all fields for not being empty
            //bean to save the obtained data for the next page
            if (view.getId() == R.id.btn_next_locate) {
                //showToast("Next page clicked");
                TreeData treeData = TreeSession.getInstance().getTreeData();
                treeData.setLatitude(mLatitudeText.getText().toString());
                treeData.setLongitude(mLongitudeText.getText().toString());
                treeData.setStreetAddress(mAddressText.getText().toString());
                treeData.setPropertyType(editPropertyType.getText().toString());
                Intent intent = new Intent(LocateNewTree.this, LeafIdentification.class);
                startActivity(intent);
            }
        }
    }

    private class PropertyTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //use bean to save data from Location Activity for the next page, besides Property Type list
            TreeData treeData = TreeSession.getInstance().getTreeData();
            treeData.setLatitude(mLatitudeText.getText().toString());
            treeData.setLongitude(mLongitudeText.getText().toString());
            treeData.setStreetAddress(mAddressText.getText().toString());
            treeData.setPropertyType(editPropertyType.getText().toString());
            Intent intent = new Intent(LocateNewTree.this, PropertyList.class);
            intent.putExtra("className", LocateNewTree.class);
            startActivity(intent);
        }
    }

    /**
     * Shows a toast with the given text.
     */
    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
