package com.example.configureddialog_ex09221;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * The type Credits activity.
 *
 * @author      Tal Weintraub <tv0823@bs.amalnet.k12.il>
 * @version	    1
 * @since		27/1/2025
 * short description:
 *      Creates 4 results for 4 buttons and changes background color by users choice and makes users text appear on screen..
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The Abd.
     */
    AlertDialog.Builder abd;
    /**
     * The Layout.
     */
    LinearLayout layout;

    /**
     * The Colors.
     */
    final String[] colors = {"RED", "GREEN", "BLUE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.LinearLayout);
    }

    /**
     * One color btn, let the user choose one background color.
     *
     * @param view the view
     */
    public void oneColorBtn(View view) {
        abd = new AlertDialog.Builder(this);
        abd.setCancelable(false);

        int[] color = new int[] {0, 0, 0};

        abd.setTitle("Change one color");
        abd.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                color[which] = 255;
                layout.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
            }
        });
        abd.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });

        AlertDialog ad = abd.create();
        ad.show();
    }

    /**
     * Change multiple colors btn, let the user choose multiple background colors.
     *
     * @param view the view
     */
    public void ChangeMultColorsBtn(View view) {
        abd = new AlertDialog.Builder(this);
        abd.setCancelable(false);

        int[] color = new int[] {0, 0, 0};

        abd.setTitle("Change multiple colors");
        abd.setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked) {
                    color[which] = 255;
                }
                else if (color[which] == 255) {
                    color[which] = 0;
                }
            }
        });

        abd.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });

        abd.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                layout.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
            }
        });

        AlertDialog ad = abd.create();
        ad.show();
    }

    /**
     * Reset btn, resets the background color to white..
     *
     * @param view the view
     */
    public void resetBtn(View view) {
        layout.setBackgroundColor(Color.WHITE);
    }

    /**
     * Put text toast btn, gets users text and makes toast on screen with the text..
     *
     * @param view the view
     */
    public void putTextToastBtn(View view) {
        abd = new AlertDialog.Builder(this);
        abd.setCancelable(false);

        abd.setTitle("Write text on screen");

        final EditText abdEt = new EditText( this);
        abdEt.setHint("Type text");
        abd.setView(abdEt);

        abd.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        abd.setNegativeButton("finish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = abdEt.getText().toString();
                if(!text.equals("")) {
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog ad = abd.create();
        ad.show();
    }

    /**
     * Creates the options menu on screen
     *
     * @param menu the menu
     * @return ture
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Checks if the clicked menuItem is R.id.menuCred
     *
     * @param item a menuItem
     * @return ture
     */
    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menuCred) {
            Intent si = new Intent(this, CreditsActivity.class);
            startActivity(si);
        }

        return true;
    }
}