package com.company.room;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecyclerBusquedaFragment extends RecyclerElementosFragment{
    @Override
    LiveData<List<Elemento>> obtenerElementos() {
        return elementosViewModel.buscar();
    }
}
