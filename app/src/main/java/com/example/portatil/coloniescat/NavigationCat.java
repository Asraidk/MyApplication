package com.example.portatil.coloniescat;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
//implements de tots els fragments que tenim
public class NavigationCat extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        frgAjuntament.OnFragmentInteractionListener,
        frgCasaCaritat.OnFragmentInteractionListener,
        frgEdificiModer.OnFragmentInteractionListener,
        frgMap.OnFragmentInteractionListener,
        frgTeatre.OnFragmentInteractionListener,
        frgCasaConvalecencia.OnFragmentInteractionListener,
        frgIglesia.OnFragmentInteractionListener,
        frgMacba.OnFragmentInteractionListener,
        frgQuestionari.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_cat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Tracament dels fragmens per fer quan carregem l'activity que conte el navigation
        //mostrem algun fragment de base
        boolean FragmentTransaction = false;
        Fragment fragment = null;

            //nostre cas un mapa
            fragment = new frgMap();
            FragmentTransaction = true;

        if(FragmentTransaction){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_navigation_cat, fragment)
                    .commit();
            getSupportActionBar().setTitle("Mapa");
        }
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
        getMenuInflater().inflate(R.menu.navigation_cat, menu);
        return true;
    }

    /**metode que tenim de 3 punts del toolbar farem simplement
     * tancarem la aplicacio i ja esta, per donarli alguna funcionalitat
     **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //em programat un petit sentit al boto perque l'aplicacio es tanqui al clicar
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean FragmentTransaction = false;
        Fragment fragment = null;

        //Tenim variables per fer el tracament dels fragments basicament busque la id que tenim en un menu del navigation
        //i afegim el fragment al layout
        if (id == R.id.casaCaritat) {
            fragment = new frgCasaCaritat();
            FragmentTransaction = true;
        } else if (id == R.id.macba) {
            fragment = new frgMacba();
            FragmentTransaction = true;
        }else if (id == R.id.iglesia) {
            fragment = new frgIglesia();
            FragmentTransaction = true;
        }else if (id == R.id.casaConvalecencia) {
            fragment = new frgCasaConvalecencia();
            FragmentTransaction = true;
        }else if (id == R.id.teatreRaval) {
            fragment = new frgTeatre();
            FragmentTransaction = true;
        }else if (id == R.id.ajuntament) {
            fragment = new frgAjuntament();
            FragmentTransaction = true;
        }else if (id == R.id.edificiModernisme) {
            fragment = new frgEdificiModer();
            FragmentTransaction = true;
        }else if (id == R.id.map) {
            fragment = new frgMap();
            FragmentTransaction = true;
        }
        else if (id == R.id.questionari) {
            fragment = new frgQuestionari();
            FragmentTransaction = true;
        }
// a mes si tenim en try farem que el titol que tenim cambiarem el nom del toolbar perque aparegui el que nosaltres desitgem no del
        //fragment
        if(FragmentTransaction){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_navigation_cat, fragment)
                    .commit();


            //Aquestes dues lineas faran que el titol del lloc tingui el mateix nom que el de la nav bar
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //El necesitem per poder fer servir els fragments al implements
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
