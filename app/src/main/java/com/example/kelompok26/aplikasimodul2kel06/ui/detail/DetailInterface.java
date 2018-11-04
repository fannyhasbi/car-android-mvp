package com.example.kelompok26.aplikasimodul2kel06.ui.detail;
import com.example.kelompok26.aplikasimodul2kel06.data.model.DataCar;

public interface DetailInterface {
    void error(String pesan);
    void success(DataCar car);
    void deleteSuccess(String pesan);
}
