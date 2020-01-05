package com.payilagam.oli.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import android.widget.Toast;

import com.payilagam.oli.R;

public class SoundProcessor {
    private MediaPlayer mediaPlayer;
    private static String FILE_FORMAT = ".m4a";
    private Context context;
    public SoundProcessor(Context context) {
        mediaPlayer = new MediaPlayer();
        this.context = context;
    }

    public void playPhonetics(String letter, final AlertDialog alertDialog) {
        try {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = new MediaPlayer();
            AssetFileDescriptor descriptor = context.getResources().getAssets().openFd(letter+FILE_FORMAT);
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            mediaPlayer.prepare();
            mediaPlayer.setVolume(1f, 1f);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                    alertDialog.dismiss();
                   // Toast.makeText(context, "completion", Toast.LENGTH_SHORT).show();

                }
            });



            mediaPlayer.start();
        } catch (Exception e) {
            alertDialog.dismiss();
            Toast.makeText(context,
                    "Please select a valid letter from panel",Toast.LENGTH_LONG).show();

        }
    }
}
