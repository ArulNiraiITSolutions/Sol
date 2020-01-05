package com.payilagam.oli.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.payilagam.oli.R;

public class DFragment extends DialogFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.result_message, container,
				false);
		((RatingBar)rootView.findViewById(R.id.ratingBar)).setRating(getArguments().getFloat("rating"));
		getDialog().setTitle("Using Custom Dialog Fragment");
		/*TextView yourScore = ((TextView)rootView.findViewById(R.id.textView5));
		yourScore.setText(getArguments().getString("score"));
		yourScore.setTextColor(getArguments().getInt("score_color"));*/
		return rootView;
	}
}