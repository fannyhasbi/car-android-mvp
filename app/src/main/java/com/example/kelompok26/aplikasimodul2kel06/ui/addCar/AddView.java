package com.example.kelompok26.aplikasimodul2kel06.ui.addCar;


public interface AddView {

    String getName();

    String getMerk();

    String getModel();

    String getYear();

    void successAddCar();

    void failedAddCar();
}