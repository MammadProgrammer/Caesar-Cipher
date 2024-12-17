package com.example.caesarcipher;

import static android.app.PendingIntent.getActivity;
import static android.icu.text.DisplayOptions.DisplayLength.LENGTH_SHORT;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Define Our Views
    Button resetBtn;
    Button code;
    Button text;
    EditText outputView;
    EditText inputEdit;
    EditText shiftEdit;
    Button exitBtn;
    Button copy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Connect Our Defined Views To Main Activity XML
        resetBtn = findViewById(R.id.reset);
        code = findViewById(R.id.code);
        text = findViewById(R.id.text);
        outputView = findViewById(R.id.output1);
        inputEdit = findViewById(R.id.input);
        shiftEdit = findViewById(R.id.shift);
        exitBtn = findViewById(R.id.exit);
        copy = findViewById(R.id.copy);

        // String For Get Value And Copy It To CLipboard
        final String[] clip = {""};

        // Reset Btn Click To Reset All Text Fields
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
            @Override
            public void onClick(View v) {
                try {
                    reset();
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, "Reset!!!", Toast.LENGTH_LONG).show();
                }
            }
        });


        // Code Btn For Encrypt All Input Text
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    // Variables For Code Function
                    String alpha = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
                    String input = inputEdit.getText().toString();
                    int shift = 0;

                    // If Shift Value Is Null Set To 13
                    if (shiftEdit.getText().toString().equals("")) {
                        shift = 13;
                        shiftEdit.setText(String.valueOf(13));
                    }  // If It Isn't Null Set Shift
                    else {
                        shift = Integer.parseInt(shiftEdit.getText().toString());
                    }
                    // If Shift Isn't Correct Value Make Toast
                    if (shift > 26 || shift < 1) {
                        Toast.makeText(MainActivity.this, "Enter Correct Shift Value", Toast.LENGTH_LONG).show();
                    } // If Shift Value Is Correct Do Your Work
                    else {
                        outputView.setText(coding(input, alpha, shift));
                        clip[0] = coding(input, alpha, shift);
                    }
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, "Error While Encrypt", Toast.LENGTH_LONG).show();
                }

            }
        });

        // Text Btn For Decrypt All Input Text
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Make Variable For Text Button
                    String alpha = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
                    String input = inputEdit.getText().toString();
                    int shift = 0;

                    // If Shift Value Is Null Set To 13
                    if (shiftEdit.getText().toString().equals("")) {
                        shift = 13;
                        shiftEdit.setText(String.valueOf(13));
                    } // If It Isn't Null Set Shift
                    else {
                        shift = Integer.parseInt(shiftEdit.getText().toString());
                    }
                    // If Shift Isn't Correct Value Make Toast
                    if (shift > 26 || shift < 1) {
                        Toast.makeText(MainActivity.this, "make shift value < 26", Toast.LENGTH_LONG).show();
                    } // If Shift Value Is Correct Do Your Work
                    else {
                        outputView.setText(encode(input, alpha, shift));
                        clip[0] = coding(input, alpha, shift);
                    }
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, "Error While Decrypt", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Exit Btn For Exit From App And Close It
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close App
                finish();
            }
        });

        // Copy Btn For Copy Answer To CLip Board
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cope To Clip Board Function
                clipBoard(clip[0]);
            }
        });

    }

    // Reset All Text's
    public void reset() {
        inputEdit.setText("");
        inputEdit.setHint("Enter Text To Get Code");
        outputView.setText("");
        outputView.setHint("Output Of Your Input");
        shiftEdit.setText("");
        shiftEdit.setHint("Shift");
    }

    // Algorythm For Encrypt
    public String coding(String txt, String alpha, int shift) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == ' ' || (txt.charAt(i) != 'a' && txt.charAt(i) != 'b' && txt.charAt(i) != 'c' && txt.charAt(i) != 'd' && txt.charAt(i) != 'e' && txt.charAt(i) != 'f' && txt.charAt(i) != 'g' && txt.charAt(i) != 'h' && txt.charAt(i) != 'i' && txt.charAt(i) != 'j' && txt.charAt(i) != 'k' && txt.charAt(i) != 'l' && txt.charAt(i) != 'm' && txt.charAt(i) != 'n' && txt.charAt(i) != 'o' && txt.charAt(i) != 'p' && txt.charAt(i) != 'q' && txt.charAt(i) != 'r' && txt.charAt(i) != 's' && txt.charAt(i) != 't' && txt.charAt(i) != 'u' && txt.charAt(i) != 'v' && txt.charAt(i) != 'w' && txt.charAt(i) != 'x' && txt.charAt(i) != 'y' && txt.charAt(i) != 'z')) {
                out.append(txt.charAt(i));
            } else {
                out.append(alpha.charAt(alpha.indexOf(txt.charAt(i)) + shift));
            }
        }
        return out.toString();
    }

    // Algorithm Of Decrypt
    public String encode(String txt, String alpha, int shift) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == ' ' || (txt.charAt(i) != 'a' && txt.charAt(i) != 'b' && txt.charAt(i) != 'c' && txt.charAt(i) != 'd' && txt.charAt(i) != 'e' && txt.charAt(i) != 'f' && txt.charAt(i) != 'g' && txt.charAt(i) != 'h' && txt.charAt(i) != 'i' && txt.charAt(i) != 'j' && txt.charAt(i) != 'k' && txt.charAt(i) != 'l' && txt.charAt(i) != 'm' && txt.charAt(i) != 'n' && txt.charAt(i) != 'o' && txt.charAt(i) != 'p' && txt.charAt(i) != 'q' && txt.charAt(i) != 'r' && txt.charAt(i) != 's' && txt.charAt(i) != 't' && txt.charAt(i) != 'u' && txt.charAt(i) != 'v' && txt.charAt(i) != 'w' && txt.charAt(i) != 'x' && txt.charAt(i) != 'y' && txt.charAt(i) != 'z')) {
                out.append(txt.charAt(i));
            } else {
                out.append(alpha.charAt(alpha.indexOf(txt.charAt(i)) - shift + 26));
            }
        }
        return out.toString();
    }

    // Copy To Clip Board
    public void clipBoard(String txt) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Persian English", txt);
        clipboard.setPrimaryClip(clip);
    }


}