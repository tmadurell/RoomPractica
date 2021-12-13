package com.company.room;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.annotation.*;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.*;
import androidx.navigation.ui.*;
import androidx.navigation.fragment.*;

import com.company.room.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //Importante que no explica
    ActivityMainBinding binding;
    private ElementosViewModel elementosViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Importante que no explica
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        //Importante que no explica
        elementosViewModel = new ViewModelProvider(this).get(ElementosViewModel.class);

        //Navegación
        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);

        //NavGraph Main amb navegació de SearchView,NavHost i BottomNavigationView
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.nuevoElementoFragment
                        || destination.getId() == R.id.mostrarElementoFragment) {
                    binding.bottomNavView.setVisibility(View.GONE);
                } else {
                    binding.bottomNavView.setVisibility(View.VISIBLE);
                }

                if (destination.getId() == R.id.recyclerBuscarFragment){
                    binding.searchView.setVisibility(View.VISIBLE);
                    binding.searchView.setIconified(false);
                    binding.searchView.requestFocusFromTouch();
                } else {
                    binding.searchView.setVisibility(View.GONE);
                }
            }
        });

        //View
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                elementosViewModel.establecerTerminoBusqueda(newText);
                return false;
            }
        });

    }
}