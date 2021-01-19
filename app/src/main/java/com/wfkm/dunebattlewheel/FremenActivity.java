package com.wfkm.dunebattlewheel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

public class FremenActivity extends Activity {
    private NumberPicker soldier, soldierS, spice, spiceS;
    private AppCompatButton button;
    private ArrayList<String> leaderArray,weaponArray,defenceArray;
    private Spinner leader;
    private CheckBox karama1, karama2;
    private RelativeLayout spice1, spice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fremen);

        TextView title = findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("faction"));

        leaderArray = getIntent().getStringArrayListExtra("leaders");
        weaponArray = getIntent().getStringArrayListExtra("weapons");
        defenceArray = getIntent().getStringArrayListExtra("defence");

        createNumberPickers();
        createButton();
        createSpinners();
        createCheckBoxes();
    }

    private void createNumberPickers(){
        soldier = findViewById(R.id.SoldierNumberPicker);
        soldier.setMinValue(0);
        soldier.setMaxValue(17);

        spice = findViewById(R.id.SoldierSpiceNumberPicker);
        spice.setMinValue(0);
        spice.setMaxValue(17);

        soldierS = findViewById(R.id.StarSoldierNumberPicker);
        soldierS.setMinValue(0);
        soldierS.setMaxValue(3);

        spiceS = findViewById(R.id.StarSoldierSpiceNumberPicker);
        spiceS.setMinValue(0);
        spiceS.setMaxValue(3);
    }

    private void createCheckBoxes(){

        spice1 = findViewById(R.id.SoldierSpiceContainer);
        spice2 = findViewById(R.id.StarSoldierSpiceContainer);

        karama1 = findViewById(R.id.Karama1Checkbox);
        karama2 = findViewById(R.id.Karama2Checkbox);

        karama1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    spice1.setVisibility(View.VISIBLE);
                    spice2.setVisibility(View.VISIBLE);
                }
                else{
                    spice1.setVisibility(View.GONE);
                    spice2.setVisibility(View.GONE);
                }
            }
        });
    }

    private void createSpinners(){
        leader = findViewById(R.id.LeaderSpinner);
        ArrayAdapter<String> leaderAdapter= new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, leaderArray);
        leader.setAdapter(leaderAdapter);

        final Spinner weapon = findViewById(R.id.WeaponSpinner);
        ArrayAdapter<String> weaponAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,weaponArray);
        weapon.setAdapter(weaponAdapter);

        final Spinner defence = findViewById(R.id.DefenceSpinner);
        ArrayAdapter<String> defenceAdapter= new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, defenceArray);
        defence.setAdapter(defenceAdapter);

        leader.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    weapon.setSelection(0);
                    defence.setSelection(0);

                    weapon.setEnabled(false);
                    defence.setEnabled(false);
                }
                else{
                    weapon.setEnabled(true);
                    defence.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                weapon.setEnabled(true);
                defence.setEnabled(true);
            }
        });

    }

    private void createButton(){
        button = findViewById(R.id.CalculateButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heroString = leader.getSelectedItem().toString();
                String heroCharValue = heroString.substring(heroString.length()-1);

                int heroValue;
                if(heroCharValue.equals("x")) heroValue = 0;
                else if(heroCharValue.equals("a")) heroValue = 0;
                else heroValue = Integer.parseInt(heroCharValue);

                double soldierTotal = soldier.getValue();
                double starSoldierTotal = 2*soldierS.getValue();

                if(karama1.isChecked()  && karama2.isChecked()){
                    if(spice.getValue() > soldier.getValue()){
                        button.setText("Error: Total spice cannot be greater then total troops");
                    }
                    else if(spiceS.getValue()>soldierS.getValue()){
                        button.setText("Error: Total Fedaykin spice cannot be greater than total Fedaykin");
                    }
                    else{
                        soldierTotal = (.5*soldier.getValue()) + (.5*spice.getValue());
                        starSoldierTotal = (.5*soldierS.getValue())+(.5*spiceS.getValue());
                        button.setText(String.format("Total Force: %.1f",(starSoldierTotal+soldierTotal+heroValue)));
                    }
                }
                else if(karama2.isChecked()) {
                    starSoldierTotal = soldierS.getValue();
                    button.setText(String.format("Total Force: %.1f",(starSoldierTotal+soldierTotal+heroValue)));
                }
                else if(karama1.isChecked()){
                    if(spice.getValue() > soldier.getValue()){
                        button.setText("Error: Total spice cannot be greater then total troops");
                    }
                    else if(spiceS.getValue()>soldierS.getValue()){
                        button.setText("Error: Total Fedaykin spice cannot be greater than total Fedaykin");
                    }
                    else{
                        soldierTotal = (.5 * soldier.getValue()) + (.5 * spice.getValue());
                        starSoldierTotal = soldierS.getValue() + spiceS.getValue();
                        button.setText(String.format("Total Force: %.1f",(starSoldierTotal+soldierTotal+heroValue)));
                    }

                }
                else{
                    button.setText(String.format("Total Force: %.1f",(starSoldierTotal+soldierTotal+heroValue)));
                }



            }
        });
    }
}

