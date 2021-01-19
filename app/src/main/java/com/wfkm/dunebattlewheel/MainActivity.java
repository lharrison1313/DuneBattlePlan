package com.wfkm.dunebattlewheel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private String[] leaders = {"Cheap Hero +0", "Thufir Hawat +5","Lady Jessica +5", "Gurney Halleck +4", "Duncan Idaho +2", "Dr. Wellington Yueh +1","Alia +5", "Margot Lady Fenring +5",
    "Mother Ramallo +5", "Princess Irulan +5", "Wanna Yueh +5", "Hasimir Fenring +6", "Captain Aramsham +5", "Caid +3", "Burseg +3", "Bashar +2",
    "Stilgar +7", "Chani +6", "Otheym +5", "Shadout Mapes +3", "Jamis +2", "Feyd-Rautha +6", "Beast Rabban +4", "Piter De Vries +3", "Captain Iakin Nefud +2",
    "Umman Kudu +1", "Dominic Vernius +4", "CTair Pilru +5", "Tessia Vernius +5", "Kailea Vernius +2", "Cammar Pilru +1", "Staban Tuek +5", "Master Bewt +3",
    "Esmar Tuek +3", "Soo-Soo Sook +2", "Guild Rep. +1", "Zoal +x", "Hidar Fen Ajidica +4", "Master Zaaf +3", "Wykk +2", "Blin +1"};
    private String[] weapons = {"n/a", "Lasgun", "Projectile", "Poison", "Artillery Strike", "Basilia Weapon", "Hunter Seeker", "Weirding Way", "Poison Blade", "Poison Tooth", "Chemistry", "Worthless Card"};
    private String[] defence = {"n/a", "Shield", "Snooper","Chemistry", "Worthless Card", "Shield Snooper", "Weirding Way"};
    private ArrayList<String> leadersAL, weaponsAL, defenceAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intentE = new Intent(this, EmperorActivity.class);
        final Intent intentA = new Intent(this, AtreidesActivity.class);
        final Intent intentF = new Intent(this, FremenActivity.class);
        final Intent intentI = new Intent(this, IxiansActivity.class);
        final Intent intentBHST = new Intent(this, BHSTActivity.class);

        defenceAL = new ArrayList(Arrays.asList(defence));
        weaponsAL = new ArrayList(Arrays.asList(weapons));

        AppCompatButton atreides = findViewById(R.id.AtreidesButton);
        atreides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leadersAL = new ArrayList(Arrays.asList(Arrays.copyOfRange(leaders,0,6)));
                leadersAL.add(0,"n/a");
                intentA.putExtra("leaders",leadersAL);
                intentA.putExtra("weapons",weaponsAL);
                intentA.putExtra("defence",defenceAL);
                intentA.putExtra("faction","Atreides");
                startActivity(intentA);
            }
        });

        AppCompatButton benne = findViewById(R.id.BennegesseritButton);
        benne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leadersAL = new ArrayList(Arrays.asList(Arrays.copyOfRange(leaders,6,11)));
                leadersAL.add(0,"Cheap Hero +0");
                leadersAL.add(0,"n/a");
                intentBHST.putExtra("leaders",leadersAL);
                intentBHST.putExtra("weapons",weaponsAL);
                intentBHST.putExtra("defence",defenceAL);
                intentBHST.putExtra("faction","Benne Gesserit");
                startActivity(intentBHST);
            }
        });

        AppCompatButton emperor = findViewById(R.id.EmperorButton);
        emperor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                leadersAL = new ArrayList(Arrays.asList(Arrays.copyOfRange(leaders,11,16)));
                leadersAL.add(0,"Cheap Hero +0");
                leadersAL.add(0,"n/a");
                intentE.putExtra("leaders",leadersAL);
                intentE.putExtra("weapons",weaponsAL);
                intentE.putExtra("defence",defenceAL);
                intentE.putExtra("faction","Emperor");
                startActivity(intentE);
            }
        });

        AppCompatButton fremen = findViewById(R.id.FremenButton);
        fremen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leadersAL = new ArrayList(Arrays.asList(Arrays.copyOfRange(leaders,16,21)));
                leadersAL.add(0,"Cheap Hero +0");
                leadersAL.add(0,"n/a");
                intentF.putExtra("leaders",leadersAL);
                intentF.putExtra("weapons",weaponsAL);
                intentF.putExtra("defence",defenceAL);
                intentF.putExtra("faction","Fremen");
                startActivity(intentF);
            }
        });

        final AppCompatButton harkonen = findViewById(R.id.HarkonnenButton);
        harkonen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> hLeaders = new ArrayList(Arrays.asList(Arrays.copyOfRange(leaders,21,26)));
                ArrayList<String> allLeaders = new ArrayList<>(Arrays.asList(leaders));
                for(String leader: hLeaders){
                    allLeaders.remove(leader);
                }
                allLeaders.addAll(1,hLeaders);
                allLeaders.add(0, "n/a");

                intentBHST.putExtra("leaders",allLeaders);
                intentBHST.putExtra("weapons",weaponsAL);
                intentBHST.putExtra("defence",defenceAL);
                intentBHST.putExtra("faction","Harkonnen");
                startActivity(intentBHST);
            }
        });

        AppCompatButton ixians = findViewById(R.id.IxiansButton);
        ixians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leadersAL = new ArrayList(Arrays.asList(Arrays.copyOfRange(leaders,26,31)));
                leadersAL.add(0,"Cheap Hero +0");
                leadersAL.add(0,"n/a");
                intentI.putExtra("leaders",leadersAL);
                intentI.putExtra("weapons",weaponsAL);
                intentI.putExtra("defence",defenceAL);
                intentI.putExtra("faction","Ixians");
                startActivity(intentI);
            }
        });

        AppCompatButton spacing = findViewById(R.id.SpacingGuildButton);
        spacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leadersAL = new ArrayList(Arrays.asList(Arrays.copyOfRange(leaders,31,36)));
                leadersAL.add(0,"Cheap Hero +0");
                leadersAL.add(0,"n/a");
                intentBHST.putExtra("leaders",leadersAL);
                intentBHST.putExtra("weapons",weaponsAL);
                intentBHST.putExtra("defence",defenceAL);
                intentBHST.putExtra("faction","Spacing Guild");
                startActivity(intentBHST);
            }
        });

        AppCompatButton tl = findViewById(R.id.TleilaxuButton);
        tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> tLeaders = new ArrayList(Arrays.asList(Arrays.copyOfRange(leaders,36,41)));
                ArrayList<String> allLeaders = new ArrayList<>(Arrays.asList(leaders));
                for(String leader: tLeaders){
                    allLeaders.remove(leader);
                }
                allLeaders.addAll(1,tLeaders);
                allLeaders.add(0,"n/a");

                leadersAL = allLeaders;
                intentBHST.putExtra("leaders",leadersAL);
                intentBHST.putExtra("weapons",weaponsAL);
                intentBHST.putExtra("defence",defenceAL);
                intentBHST.putExtra("faction","Tleilaxu");
                startActivity(intentBHST);
            }
        });

    }

}
