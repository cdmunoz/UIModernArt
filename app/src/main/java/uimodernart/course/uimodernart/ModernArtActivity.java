package uimodernart.course.uimodernart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class ModernArtActivity extends ActionBarActivity {

    public static final String MOMA_URL = "http://www.moma.org";

    RelativeLayout gridV;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art);

        gridV = (RelativeLayout) findViewById(R.id.gridV);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressBar = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                progressBar = progress;
                View child;
                int originalColor = 0;
                int originalR = 0;
                int originalG = 0;
                int originalB = 0;

                //gets the views within relative layout and their color, then changes their background color
                for (int i = 0; i < gridV.getChildCount(); i++) {
                    child = (View) gridV.getChildAt(i);
                    originalColor = Color.parseColor((String.valueOf(child.getTag())));

                    if (getResources().getColor(R.color.light_gray) != originalColor &&
                            getResources().getColor(R.color.white) != originalColor) {

                        originalR = Color.red(originalColor);
                        originalG = Color.green(originalColor);
                        originalB = Color.blue(originalColor);

                        child.setBackgroundColor(Color.rgb(
                                originalR - progress,
                                originalG - progress,
                                originalB - progress
                        ));
                    }

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modern_art, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                openDialogOption();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Creates and Open a Dialog with two buttons
     */
    private void openDialogOption() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ModernArtActivity.this, AlertDialog.THEME_HOLO_DARK);
        builder.setMessage(getResources().getString(R.string.dialog_msg));
        builder.setTitle(getResources().getString(R.string.dialog_title));
        /*TextView txtTitle = new TextView(ModernArtActivity.this);
        txtTitle.setText(getResources().getString(R.string.dialog_title));
        builder.setCustomTitle(txtTitle);*/
        //Change action between positive and negative due to appearing order in alert dialog
        builder.setPositiveButton(getResources().getString(R.string.dialog_lbl_btn_no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Do nothing
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.dialog_lbl_btn_visit), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                openWebPage();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Open MOMA's the web paga
     */
    private void openWebPage(){
        Uri webpage = Uri.parse(MOMA_URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
