package com.example.kelompok26.aplikasimodul2kel06.ui.home;

import com.example.kelompok26.aplikasimodul2kel06.data.model.DataCar;

import java.util.List;

public interface HomeView {
    void successShowCar(List<DataCar> dataCars);
    void failedShowCar(String message);
}
