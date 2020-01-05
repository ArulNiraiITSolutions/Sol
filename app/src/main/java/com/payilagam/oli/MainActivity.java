package com.payilagam.oli;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.payilagam.oli.adapter.AlphabetAdapter;
import com.payilagam.oli.model.Letter;
import com.payilagam.oli.util.LettersUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GridView gridView;
    private Spinner spinner1;
    private Spinner spinner2;
    private Map<String,List<String>> alphabetsSet = new HashMap<String,List<String>>();
    private static final String VOWELS = "VOWELS";
    private static final String VOWELS_CONSONANTS = "VOWELS_CONSONANTS";
    private static final String CONSONANTS = "CONSONANTS";
    private int selectedMenuId;
    private static final String seletedMenu = "selectedMENU";
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

             gridView = (GridView)findViewById(R.id.v_gridview);
             spinner1 = findViewById(R.id.letter1);
             spinner2 = findViewById(R.id.letter2);
             /** Event Listeners toolbar
                ((TextView)findViewById(R.id.vc_letter)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),AlphabetViewActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String letter = ((TextView)v).getText().toString();
                        intent.putExtra("LETTER",letter);
                        intent.putExtra("LETTER_FORMATION",
                                letter +" = "+((TextView)findViewById(R.id.vc_letter_info))
                                .getText().toString());
                        getApplicationContext().startActivity(intent);
                    }
                });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView)findViewById(R.id.vc_letter)).setText(LettersUtil.
                        getVowelsConsonantsLetter(spinner1.getSelectedItemPosition(),position));
                ((TextView)findViewById(R.id.vc_letter_info)).setText(spinner1.getSelectedItem().toString()+" + "+
                        spinner2.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                  @Override
                  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                      ((TextView)findViewById(R.id.vc_letter)).setText(LettersUtil.
                              getVowelsConsonantsLetter(spinner1.getSelectedItemPosition(),position));
                      ((TextView)findViewById(R.id.vc_letter_info)).setText(spinner1.getSelectedItem().toString()+" + "+
                              spinner2.getSelectedItem().toString());
                  }
                  @Override
                  public void onNothingSelected(AdapterView<?> parent) {

                  }
              });

        /** Side navigation bar **/
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
             navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

        /** Initialize Alphabets List Values  **/
        alphabetsSet.put(VOWELS,LettersUtil.getVowelsList(getApplicationContext()));
        alphabetsSet.put(CONSONANTS,LettersUtil.getConsonantsList(getApplicationContext()));
        alphabetsSet.put(VOWELS_CONSONANTS,LettersUtil.getVowelsConsonantsLetter(getApplicationContext()));

       /** init the grid view **/
       initGridView();
       /** Spinner Options Hide On Load **/
       switchViewOnCondition(true);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       /* //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        selectedMenuId = item.getItemId();

        // Update the grid view with selected menu item id "selectedMenuId"
        updateGridView();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initGridView(){
        navigationView.getMenu().getItem(0).setChecked(true);
        gridView.setAdapter(new AlphabetAdapter(this,  alphabetsSet.get(VOWELS)));
    }


    private void updateGridView(){

        if (selectedMenuId == R.id.nav_vowels) {
            gridView.setAdapter(new AlphabetAdapter(this,  alphabetsSet.get(VOWELS)));
            switchViewOnCondition(true);
        } else if (selectedMenuId == R.id.nav_consonants) {
            gridView.setAdapter(new AlphabetAdapter(this,  alphabetsSet.get(CONSONANTS)));
            switchViewOnCondition(true);
        } else if (selectedMenuId == R.id.nav_vowels_nav_consonants) {
           //switchViewOnCondition(false);
            gridView.setAdapter(new AlphabetAdapter(this,  alphabetsSet.get(VOWELS_CONSONANTS)));
            switchViewOnCondition(true);
         }else if(selectedMenuId == R.id.nav_ayutha_ezhuthu){
            Intent intent = new Intent(getApplicationContext(),AlphabetViewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("LETTER",getString(R.string.special_character));
            intent.putExtra("LETTER_GROUP_NAME",getString(R.string.special_character));
            intent.putExtra("LETTER_FORMATION","AK");
            getApplicationContext().startActivity(intent);
        }
    }


    private void switchViewOnCondition(boolean condition){

        if(condition){

            /** Hide the Options **/
            findViewById(R.id.vc_letter_options).setVisibility(LinearLayout.GONE);
            findViewById(R.id.vc_letter_card).setVisibility(LinearLayout.GONE);
            findViewById(R.id.v_gridview).setVisibility(LinearLayout.VISIBLE);
        }else {
            /** Show the Options **/
            findViewById(R.id.vc_letter_options).setVisibility(LinearLayout.VISIBLE);
            findViewById(R.id.vc_letter_card).setVisibility(LinearLayout.VISIBLE);
            findViewById(R.id.v_gridview).setVisibility(LinearLayout.GONE);
            generateOptions();
        }
    }


    public void generateOptions(){
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_layout, alphabetsSet.get(VOWELS));
        adapter.setDropDownViewResource(R.layout.spinner_layout);


        ArrayAdapter<String> adapter2 =
                new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_layout, alphabetsSet.get(CONSONANTS));
        adapter2.setDropDownViewResource( R.layout.spinner_layout);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        updateGridView();
    }

}
