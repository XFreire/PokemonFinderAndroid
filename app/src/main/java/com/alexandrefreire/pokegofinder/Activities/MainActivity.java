package com.alexandrefreire.pokegofinder.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.alexandrefreire.pokegofinder.Models.Pokemon;
import com.alexandrefreire.pokegofinder.Models.Post;
import com.alexandrefreire.pokegofinder.Models.User;
import com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.SignUpScreen.SignUpActivity;
import com.alexandrefreire.pokegofinder.Modules.Main.MainFragment;
import com.alexandrefreire.pokegofinder.R;
import com.alexandrefreire.pokegofinder.Utils.Settings;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawer;
    private NavigationView mNavigation;
    private Fragment mFragment;
    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Asignamos la Toolbar
       /* mToolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);
        //Botón de menú
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        //Declaramos el DrawerLayout y el NavigationView
        mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        mNavigation = (NavigationView)findViewById(R.id.navview);
        //Asignamos el Listener para capturar el evento de seleccionar una opción
        mNavigation.setNavigationItemSelectedListener(this);
        //Primera pantalla de la app
        mFragment = new MainFragment();
        doTransactionFragment();

        //Añadimos algunos tabs
       /* TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));*/


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mFragment != null){
            mFragment.onActivityResult(requestCode,resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void openDrawer(){
        mDrawer.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
            // Resto de botones de la Toolbar
            // ...
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean fragmentTransaction = false;

        switch (item .getItemId()) {
            case R.id.section1:
                Log.i("NavigationView", "Sección 1");
                mFragment = null;
                fragmentTransaction = true;
                break;
            case R.id.section2:
                Log.i("NavigationView", "Sección 2");
                mFragment = null;
                fragmentTransaction = true;
                break;
            case R.id.section3:
                Log.i("NavigationView", "Sección 3");
                mFragment = null;
                fragmentTransaction = true;
                break;

            case R.id.logout:{
                clearDatabase();
                navigateToSignUp();
                break;
            }
        }

        if(fragmentTransaction) {
            doTransactionFragment();

            item.setChecked(true);
            mToolbar.setTitle(item.getTitle());
        }

        mDrawer.closeDrawers();

        return true;
    }

    private void clearDatabase() {
        User.deleteAll(User.class);
        Pokemon.deleteAll(Pokemon.class);
        Post.deleteAll(Post.class);

        getApplicationContext().getSharedPreferences(Settings.PREFS_NAME, MODE_PRIVATE).edit().clear().commit();
        /*Tracker mixpanelInteractor = new Tracker(getApplicationContext());
        mixpanelInteractor.reset();*/
    }

    public void navigateToSignUp(){
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void doTransactionFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, mFragment)
                .commit();
    }


}
