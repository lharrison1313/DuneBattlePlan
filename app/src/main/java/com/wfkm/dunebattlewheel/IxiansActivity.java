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

public class IxiansActivity extends Activity {
    private NumberPicker soldier, soldierS, spiceS;
    private AppCompatButton button;
    private ArrayList<String> leaderArray,weaponArray,defenceArray;
    private Spinner leader;
    private CheckBox karama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ixian);

        TextView title = findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("faction"));

        leaderArray = getIntent().getStringArrayListExtra("leaders");
        weaponArray = getIntent().getStringArrayListExtra("weapons");
        defenceArray = getIntent().getStringArrayListExtra("defence");

        karama = findViewById(R.id.Karama1Checkbox);

        createNumberPickers();
        createButton();
        createSpinners();
    }

    private void createNumberPickers(){
        soldier = findViewById(R.id.SoldierNumberPicker);
        soldier.setMinValue(0);
        soldier.setMaxValue(13);

        soldierS = findViewById(R.id.StarSoldierNumberPicker);
        soldierS.setMinValue(0);
        soldierS.setMaxValue(7);

        spiceS = findViewById(R.id.StarSpiceNumberPicker);
        spiceS.setMinValue(0);
        spiceS.setMaxValue(7);
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

                if(spiceS.getValue()>soldierS.getValue()){
                    button.setText("Error: Total Cyborg spice cannot be greater than total Cyborgs");
                }
                else{
                    String heroString = leader.getSelectedItem().toString();
                    String heroCharValue = heroString.substring(heroString.length()-1);

                    int heroValue;
                    if(heroCharValue.equals("x")) heroValue = 0;
                    else if(heroCharValue.equals("a")) heroValue = 0;
                    else heroValue = Integer.parseInt(heroCharValue);

                    double soldierTotal = (.5*soldier.getValue());
                    double starSoldierTotal = soldierS.getValue() + spiceS.getValue();
                    if(karama.isChecked())
                        starSoldierTotal = (.5*soldierS.getValue()) + (.5*spiceS.getValue());
                    button.setText(String.format("Total Force: %.1f",(starSoldierTotal+soldierTotal+heroValue)));
                }


            }
        });
    }
}
