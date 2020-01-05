package com.payilagam.oli;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.payilagam.oli.adapter.WordAdapter;
import com.payilagam.oli.fragments.DFragment;
import com.payilagam.oli.model.Letter;
import com.payilagam.oli.util.LettersUtil;
import com.payilagam.oli.util.SoundProcessor;

import java.util.ArrayList;
/*
Activity to display Letter Description
 */

public class AlphabetViewActivity extends AppCompatActivity {

    private TextToSpeech textToSpeech;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private String letter;
    private int letterIndex;
    private String letterGroupName;
    private ImageButton speakButton;
    private ImageButton playButton;
    private String letterFormation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_view);

        Intent intent = getIntent();
        letter = intent.getStringExtra("LETTER");
        letterGroupName = intent.getStringExtra("LETTER_GROUP_NAME");
        letterIndex = intent.getIntExtra("LETTER_INDEX",0);
        letterFormation = intent.getStringExtra("LETTER_FORMATION");

        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(LettersUtil.getTamilTitle(letterGroupName,getApplicationContext()));

        speakButton = findViewById(R.id.speak_button);
        speakButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    promptSpeechInput();
                }
            });
            playButton = findViewById(R.id.play_button);

        playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SystemClock.elapsedRealtime() - LettersUtil.mLastClickTime < 1000) {
                        return;
                    }
                    LettersUtil.mLastClickTime = SystemClock.elapsedRealtime();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AlphabetViewActivity.this);
                    alertDialogBuilder.setCancelable(false);
                    //alertDialogBuilder.setMessage("Playing......");
                    LayoutInflater inflater = getLayoutInflater();
                    View dialoglayout = inflater.inflate(R.layout.custom_dialog, null);
                    alertDialogBuilder.setView(dialoglayout);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.getWindow().setLayout(100, 400);
                    alertDialog.show();
                    new SoundProcessor(getApplicationContext()).playPhonetics("audio/letters/"+letter,alertDialog);
                }

            });
        // ((ImageView)findViewById(R.id.pronounce_diagram)).setImageResource(R.drawable.dummy_data_1);
         ((TextView)findViewById(R.id.selected_text)).setText(letter);
         ((TextView)findViewById(R.id.selected_text_info)).setText(LettersUtil.getLetter(letterIndex,letterGroupName));
        ((ListView)findViewById(R.id.words_list)).setAdapter(new WordAdapter(AlphabetViewActivity.this,
                LettersUtil.getWordsList(letterIndex,letterGroupName), LettersUtil.getEnglishWordsList(letterIndex,letterGroupName)));

    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "ta-IN");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "ta-IN");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ta-IN");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "உங்களின் உச்சரிப்பு");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Speech Not Supported ",
                    Toast.LENGTH_SHORT).show();
        }
    }

   /*
    * Receiving speech input
    * */
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String resultValue = result.get(0);


                  /*  Bundle bundle = new Bundle();
                    bundle.putString("score","Good");
                    if(resultValue.equals(letter)){
                        bundle.putString("score","3");
                        bundle.putInt("score_color", Color.GREEN);
                    }else if(resultValue.contains(letter)){
                        bundle.putString("score","2");
                        bundle.putInt("score_color",Color.GREEN);
                    }else{
                        bundle.putString("score","1");
                        bundle.putInt("score_color",Color.GREEN);
                    }
                    DFragment fragment = new DFragment();
                    fragment.setArguments(bundle);
                    fragment.show(getSupportFragmentManager(),getString(R.string.pronounce));*/
                    //Toast.makeText(getApplicationContext(),resultValue,Toast.LENGTH_LONG).show();
                   float rating = 1f;
                    if(resultValue.contains(letter))
                        rating = 3f;
                    if(resultValue.equals(letter))
                        rating = 5f;
                        Bundle bundle = new Bundle();
                        bundle.putFloat("rating",rating);
                    DFragment fragment = new DFragment();
                    fragment.setArguments(bundle);
                    fragment.show(getSupportFragmentManager(),getString(R.string.pronounce));
                   /* if(rating == 0f){
                        Snackbar snackbar = Snackbar
                                .make(findViewById(R.iopld.),
                                        "Please read one of letters to pronounce better", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }*/
                }
                break;
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


    public Spanned getFormatedTxt(String givenString){
        Spanned sp = Html.fromHtml(givenString);
        return sp;
    }



}
