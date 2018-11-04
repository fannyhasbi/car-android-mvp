package com.example.kelompok26.aplikasimodul2kel06.ui.detail;

import android.util.Log;

import com.example.kelompok26.aplikasimodul2kel06.data.model.DataCar;
import com.example.kelompok26.aplikasimodul2kel06.data.network.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {
    private DetailInterface dInter;

    public Presenter(DetailInterface inter){
        this.dInter = inter;
    }

    public void getCarById(DataCar car) {
        RetrofitClient.getInstance()
                .getApi()
                .getCarById(car.getId())
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            JsonObject body = response.body();
                            JsonObject data = body.get("result").getAsJsonArray().get(0).getAsJsonObject();
                            DataCar carResponse = new Gson().fromJson(data, DataCar.class);

                            dInter.success(carResponse);
                        } else {
                            Log.d("DATA", "ERROR");
                            dInter.error("Maaf, sedang terjadi error");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.d("DATA", t.getMessage());
                        dInter.error(t.getMessage());
                    }
                });
    }

    public void deleteCar(DataCar car){
        RetrofitClient.getInstance()
                .getApi()
                .deleteCar(car.getId())
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            JsonObject body = response.body();
                            String data = body.get("result").getAsString();

                            dInter.deleteSuccess(data);
                        } else {
                            Log.d("DATA", "ERROR");
                            dInter.error("Maaf, sedang terjadi error");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.d("DATA", t.getMessage());
                        dInter.error(t.getMessage());
                    }
                });
    }
}
