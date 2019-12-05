package com.goodfellows.morssenger;

import android.media.MediaPlayer;

public class MorseMediaPlayer {


    private String morseCode;
    private int morsePosition;
    private MessagesActivity activity;

    public MorseMediaPlayer(String morseCode, MessagesActivity activity) {
        this.morseCode = morseCode;
        this.activity = activity;
    }

    public void note(){
        char variables = morseCode.charAt(morsePosition);
        MediaPlayer beepSoundMP = null;

        if (variables == '.') {
            beepSoundMP = MediaPlayer.create(activity, R.raw.short_beep);
            // Short beep for a .
            beepSoundMP.start();
        }

        else if (variables == '-') {
            beepSoundMP = MediaPlayer.create(activity, R.raw.long_beep);
            // Long  beep for a -
            beepSoundMP.start();
        }

        else if (variables == ' ') {
            // Make empty sound mp3 for a space
            beepSoundMP = MediaPlayer.create(activity, R.raw.silence);
            beepSoundMP.start();
        }

        else if (variables != '.' || variables != '-' || variables != ' ') {
            // Must increment morsePosition for '/'
            // Set beepSoundMP to something so as to go into the onCompletion function and run the next note
            beepSoundMP = MediaPlayer.create(activity, R.raw.silence);
            beepSoundMP.start();
        }

        if (beepSoundMP != null) {
            beepSoundMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (variables == '.' || variables == '-' || variables == ' ') {
                        try {
                            Thread.sleep(100);
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

                    if (morsePosition != morseCode.length() - 1) {
                        morsePosition++;
                        note();
                    }

                    else {
                        morsePosition = 0;
                    }
                }
            });
        }
    }
}
