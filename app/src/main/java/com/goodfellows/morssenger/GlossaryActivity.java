package com.goodfellows.morssenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GlossaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossary);

        Utils.greenStatusBar(this, R.color.colorMorseGreen);
        setTitle("Glossary");
    }

//   "A = .-\nB = -...\nC = .. .\nD = -..\nE = .\nF = .-.\nG = --\nH = ....\nI = ..\nJ = -.-.\nK = -.-\nL = -\nM = --\nN = -.\nO = . .\nP = .....\nQ = ..-.\nR = . ..\nS = ...\nT = -\nU = ..-\nV = ...-\nW = .--\nX = .-..\nY = .. ..\nZ = ... .\n"

//   "A = .-     B = -...\nC = .. .     D = -..\nE = .     F = .-.\nG = --     H = ....\nI = ..     J = -.-.\nK = -.-     L = -\nM = --     N = -.\nO = . .     P = .....\nQ = ..-.     R = . ..\nS = ...     T = -\nU = ..-     V = ...-\nW = .--     X = .-..\nY = .. ..     Z = ... .\n"

}
