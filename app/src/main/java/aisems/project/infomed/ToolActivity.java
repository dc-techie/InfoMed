package aisems.project.infomed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToolActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);


    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        String buttonText = clickedButton.getText().toString().toLowerCase();

        if (buttonText.equalsIgnoreCase("immunizations")) {
            search("immunizations.csv");
        } else if (buttonText.equalsIgnoreCase("medications")) {
            search("medications.csv");
        }
    }

    private void search(String fileName) {
        nameEditText = findViewById(R.id.nameEditText);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView purposeTextView = findViewById(R.id.purposeTextView);
        TextView backgroundTextView = findViewById(R.id.backgroundTextView);
        TextView relevantInfoView1 = findViewById(R.id.relevantInfoView1);
        TextView relevantInfoView2 = findViewById(R.id.relevantInfoView2);
        TextView relevantInfoView3 = findViewById(R.id.relevantInfoView3);
        String name = nameEditText.getText().toString().trim();

        if (!name.isEmpty()) {
            AssetManager manager = this.getAssets();
            Scanner scanner = null;
            try {
                InputStream input = manager.open(fileName);
                scanner = new Scanner(input);

                String line = "";
                List<String[]> dataLines = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    String[] lineSplit = line.trim().split(";");

                    dataLines.add(lineSplit);
                }

                for (int i = 0; i < dataLines.size(); i++) {
                    String[] tempArr = dataLines.get(i);
                    if (tempArr[0].contains(name)) { // Found what we are searching for!
                        nameTextView.setText(tempArr[0]);
                        purposeTextView.setText(tempArr[1]);
                        backgroundTextView.setText(tempArr[2]);
                        relevantInfoView1.setText(tempArr[3]);
                        relevantInfoView2.setText(tempArr[4]);
                        relevantInfoView3.setText(tempArr[5]);
                        break;
                    } else if (i == (dataLines.size() - 1)) {
                        nameTextView.setText("Unable to locate the specified choice. Please try again!");
                    }
                }

            } catch(FileNotFoundException e) {
                Log.d("FileException", "File not found");
            } catch (IOException e) {
                Log.d("Exception", "IO Exception");
            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
        } else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }
    }
}