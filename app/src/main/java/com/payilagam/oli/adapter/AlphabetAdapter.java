package com.payilagam.oli.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.payilagam.oli.AlphabetViewActivity;
import com.payilagam.oli.R;
import com.payilagam.oli.model.Letter;

import java.util.ArrayList;
import java.util.List;

import static com.payilagam.oli.util.LettersUtil.getLetterGroupName;

public class AlphabetAdapter extends BaseAdapter {

    private  Context mContext;
    private  List<String> alphabets;

    private static List<String> bigFontLetters = new ArrayList<String>();

    static {
        bigFontLetters.add("ஔ");
    }

    public AlphabetAdapter(Context context,List<String> alphabets){
       this.alphabets = alphabets;
       this.mContext = context;
    }

    @Override
    public int getCount() {
        return alphabets.size();
    }

    @Override
    public Object getItem(int position) {
        return alphabets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String alphabet = alphabets.get(position);
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.grid_cell_view, null);
        }
        TextView aTextview = convertView.findViewById(R.id.a_text_value);
        if(bigFontLetters.contains(alphabet)){
            aTextview.setTextSize(50);
        }
        aTextview.setText(alphabet);
        aTextview.setTag(new Letter(alphabet,position,getLetterGroupName(mContext,alphabet)));
        aTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,AlphabetViewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Letter letter = (Letter) v.getTag();
                intent.putExtra("LETTER",letter.getLetter());
                intent.putExtra("LETTER_GROUP_NAME",letter.getLetterGroup());
                intent.putExtra("LETTER_INDEX",letter.getLetterIndex());
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}

