package com.goodfellows.morssenger;

import android.media.MediaPlayer;
import android.os.Vibrator;

import static android.content.Context.VIBRATOR_SERVICE;


public class MorseBothPlayer {

    private String morseCode;
    private int morsePosition;
    private MessagesActivity activity;

    public MorseBothPlayer(String morseCode, MessagesActivity activity) {
        this.morseCode = morseCode;
        this.activity = activity;
    }

    public void both(){
        char variables = morseCode.charAt(morsePosition);
        MediaPlayer vibrationSoundMP = null;

        Vibrator vibrator = (Vibrator) activity.getSystemService(VIBRATOR_SERVICE);

        if (variables == '.') {
            vibrationSoundMP = MediaPlayer.create(activity, R.raw.short_beep);
//            Short beep for a .
            vibrationSoundMP.start();
            vibrator.vibrate(200);
        }

        else if (variables == '-') {
            vibrationSoundMP = MediaPlayer.create(activity, R.raw.long_beep);
//            long  beep for a -
            vibrationSoundMP.start();
            vibrator.vibrate(400);
        }

        else if (variables == ' ') {
//            Make empty sound mp3 for a space
            vibrationSoundMP = MediaPlayer.create(activity, R.raw.silence);
            vibrationSoundMP.start();
            vibrator.vibrate(0);
        }

        else if (variables != '.' || variables != '-' || variables != ' ') {
//            must increment morsePosition for '/'
//            set beepSoundMP to something so as to go into the onCompletinon function and run the next note
            vibrationSoundMP = MediaPlayer.create(activity, R.raw.silence);
            vibrationSoundMP.start();
        }

        if (vibrationSoundMP != null) {
            vibrationSoundMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (variables == '.' || variables == '-' || variables == ' ') {
                        try {
                            Thread.sleep(350);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    else if (variables != '.' || variables != '-' || variables != ' ') {
                        try {
                            Thread.sleep(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (morsePosition < morseCode.length() - 1) {
                        morsePosition++;
                        both();
                    }

                    else {
                        morsePosition = 0;
                    }
                }
            });
        }
    }
}