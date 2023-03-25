package com.example.atividadebanco1;

import android.content.Intent;
import android.os.Bundle;

import com.example.atividadebanco1.modelo.Registro;
import com.example.atividadebanco1.viewModel.RegistroListAdapter;
import com.example.atividadebanco1.viewModel.RegistroViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.atividadebanco1.databinding.ActivityMainBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RegistroViewModel mRegistroViewModel;
    public RegistroListAdapter adapter;

    public static final int NEW_REG_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_REG_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mRegistroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);

        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.recyclerview);
        adapter = new RegistroListAdapter(mRegistroViewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        mRegistroViewModel.getAll().observe(this, regs -> {
            adapter.submitList(regs);
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivityForResult(intent, NEW_REG_ACTIVITY_REQUEST_CODE);
            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_REG_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Registro reg = new Registro(0, data.getStringExtra("nome"), "", data.getStringExtra("pensamento"));
            mRegistroViewModel.insertAll(reg);
        } else if (requestCode == EDIT_REG_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Registro reg = new Registro(data.getIntExtra("id", 0), data.getStringExtra("nome"), "", data.getStringExtra("pensamento"));
            mRegistroViewModel.update(reg);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}