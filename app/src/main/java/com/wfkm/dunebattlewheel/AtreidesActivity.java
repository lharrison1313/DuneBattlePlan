package com.wfkm.dunebattlewheel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

public class AtreidesActivity extends Activity {
    private NumberPicker soldier, spice;
    private ArrayList<String> leaderArray,weaponArray,defenceArray;
    private Spinner leader;
    private CheckBox kwiz;
    private AppCompatButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atreides);

        TextView title = findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("faction"));

        kwiz = findViewById(R.id.KwisatzCheckBox);
        createNumberPickers();
        createButton();
        createSpinners();
    }

    private void createNumberPickers(){
        soldier = findViewById(R.id.SoldierNumberPicker);
        soldier.setMinValue(0);
        soldier.setMaxValue(20);

        leaderArray = getIntent().getStringArrayListExtra("leaders");
        weaponArray = getIntent().getStringArrayListExtra("weapons");
        defenceArray = getIntent().getStringArrayListExtra("defence");

        spice = findViewById(R.id.SpiceNumberPicker);
        spice.setMinValue(0);
        spice.setMaxValue(20);
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
                    kwiz.setChecked(false);

                    weapon.setEnabled(false);
                    defence.setEnabled(false);
                    kwiz.setEnabled(false);
                }
                else{
                    weapon.setEnabled(true);
                    defence.setEnabled(true);
                    kwiz.setEnabled(true);
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
                if(spice.getValue() > soldier.getValue()){
                    button.setText("Error: Total spice cannot be greater then total troops");
                }
                else{
                    String heroString = leader.getSelectedItem().toString();
                    String heroCharValue = heroString.substring(heroString.length()-1);

                    int heroValue;
                    if(heroCharValue.equals("x")) heroValue = 0;
                    else if(heroCharValue.equals("a")) heroValue = 0;
                    else heroValue = Integer.parseInt(heroCharValue);

                    int kwizValue = 0;
                    if(kwiz.isChecked()) kwizValue = 2;

                    double soldierTotal = (.5*soldier.getValue()) + (.5*spice.getValue());
                    button.setText(String.format("Total Force: %.1f",(soldierTotal+heroValue+kwizValue)));
                }
            }
        });
    }
}
