package com.payilagam.oli.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.payilagam.oli.AlphabetViewActivity;
import com.payilagam.oli.R;
import com.payilagam.oli.util.LettersUtil;
import com.payilagam.oli.util.SoundProcessor;

public class WordAdapter extends BaseAdapter {
    private Context mContext;
    private String[] words;
    private String[] engWord;
    public WordAdapter(Context context, String[] words,String[] engWord){
        this.mContext = context;
        this.words = words;
        this.engWord =  engWord;
    }

    @Override
    public int getCount() {
        return words.length;
    }

    @Override
    public Object getItem(int position) {
        return words[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.words_view, null);
        }

        TextView textView =  convertView.findViewById(R.id.words);
        textView.setText(words[position]);

        //English words
        TextView englishWord =  convertView.findViewById(R.id.selected_text_info);
        englishWord.setText(engWord[position]);



        ImageButton pronounceWord = convertView.findViewById(R.id.pronounce_word);
        pronounceWord.setImageResource(R.drawable.ic_speaking_black);
        pronounceWord.setTag(words[position]);
        pronounceWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - LettersUtil.mLastClickTime < 1000) {
                    return;
                }
                LettersUtil.mLastClickTime = SystemClock.elapsedRealtime();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setCancelable(false);
               // alertDialogBuilder.setMessage("Playing....");
                LayoutInflater inflater = LayoutInflater.from(mContext.getApplicationContext());
                View dialoglayout = inflater.inflate(R.layout.custom_dialog, null);
                alertDialogBuilder.setView(dialoglayout);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.getWindow().setLayout(100, 400);
                alertDialog.show();
                new SoundProcessor(mContext).playPhonetics("audio/words/"+(String)v.getTag(),alertDialog);
             //   Toast.makeText(mContext,"Pronouncing the word " ,Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }
}
