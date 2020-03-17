package com.adrian.trabajofinalandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.adrian.trabajofinalandroid.Adaptadores.AdaptadorFragments;
import com.adrian.trabajofinalandroid.Fragments.FragmentAgregar;
import com.adrian.trabajofinalandroid.Fragments.FragmentBuscar;
import com.adrian.trabajofinalandroid.Fragments.FragmentMostrar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainAplicacion extends AppCompatActivity {
    // Elementos
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> listaFG;
    private AdaptadorFragments adaptadorFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_aplicacion);
        instancias();
        iniciarPager();
        acciones();
    }
    // Instancias
    private void instancias() {
        viewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tablayout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("LIBRERÍA DataBase");
        tabLayout.setupWithViewPager(viewPager);
    }
    // Iniciar pager
    private void iniciarPager() {
        listaFG = new ArrayList();
        listaFG.add(new FragmentAgregar());
        listaFG.add(new FragmentMostrar());
        listaFG.add(new FragmentBuscar());
        adaptadorFragments = new AdaptadorFragments(getSupportFragmentManager(),0,listaFG);
        viewPager.setAdapter(adaptadorFragments);
    }
    // Acciones cotnrol pager
    private void acciones() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                Log.v("scroll",String.valueOf(position));
                Fragment fragment = adaptadorFragments.getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.getCurrentItem();
    }
}
