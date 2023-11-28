/**
 * Original InfoMed created by David Christiansen in November 2023
 * for UTSA AIS Smart Design Project.
 *
 * Credits for original app idea belong to David Christiansen, Joyce Njabi, and Matti Pruitt.
 */
package aisems.project.infomed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButtons();
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        String buttonText = clickedButton.getText().toString().toLowerCase();
        if (buttonText.equalsIgnoreCase("search") || buttonText.equalsIgnoreCase("try our tool here!")) {
            Intent toolIntent = new Intent(MainActivity.this, ToolActivity.class);
            startActivity(toolIntent);
        } // else if (buttonText.equalsIgnoreCase("alerts")) { ---> TO BE IMPLEMENTED AT A LATER DATE. <---
            // Intent alertsIntent = new Intent(MainActivity.this, AlertsActivity.class);
            // startActivity(alertsIntent);
        //} else if (buttonText.equalsIgnoreCase("settings")) {  ---> TO BE IMPLEMENTED AT A LATER DATE. <---
           // Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            // startActivity(settingsIntent);
        //}
    }

    private void setupButtons() {
        // two buttons with an id that is an integer
        int[] buttonIDs = {R.id.remindersButton, R.id.toolButton1, R.id.settingsButton, R.id.toolButton2};
        String[] buttonText = {"Alerts", "Search", "Settings", "Try our tool here!"};
        for (int i = 0; i < buttonIDs.length; i++) {
            Button button = findViewById(buttonIDs[i]);
            button.setText(buttonText[i]);
            button.setOnClickListener(this);
        }
    }
}