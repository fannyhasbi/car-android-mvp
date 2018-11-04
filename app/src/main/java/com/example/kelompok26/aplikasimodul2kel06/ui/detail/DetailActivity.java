package com.example.kelompok26.aplikasimodul2kel06.ui.detail;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kelompok26.aplikasimodul2kel06.R;
import com.example.kelompok26.aplikasimodul2kel06.data.model.DataCar;
import com.example.kelompok26.aplikasimodul2kel06.ui.home.HomeActivity;
import com.example.kelompok26.aplikasimodul2kel06.utility.Constant;

public class DetailActivity extends AppCompatActivity implements DetailInterface {

    private DataCar car;
    private Presenter dPresenter;
    private TextView carName, carMerk, carModel, carYear;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initIntentData();
        initPresenter();
        initData();
        delete();
    }

    private void initView(){
        carName  = (TextView) findViewById(R.id.carName);
        carMerk  = (TextView) findViewById(R.id.carMerk);
        carModel = (TextView) findViewById(R.id.carModel);
        carYear  = (TextView) findViewById(R.id.carYear);
        btnDelete= findViewById(R.id.btnDelete);
    }

    private void initData(){
        dPresenter.getCarById(car);
    }

    private void initPresenter(){
        dPresenter = new Presenter(this);
    }

    private void initIntentData(){
        car = getIntent().getParcelableExtra(Constant.Extra.DATA);
        if(car == null){
            finish();
        }
    }

    @Override
    public void error(String pesan){
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(DataCar car) {
        carName.setText(car.getName());
        carMerk.setText(car.getMerk());
        carModel.setText(car.getModel());
        carYear.setText(car.getYear());
    }

    @Override
    public void deleteSuccess(String pesan){
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
    }

    private void deleteClick(){
        Context context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Yakin ingin menghapus?")
                .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dPresenter.deleteCar(car);
                        Intent utama = new Intent(context, HomeActivity.class);
                        startActivity(utama);
                        finish();
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("Not bad");
                    }
                });
        builder.create().show();
    }

    private void delete(){
        btnDelete.setOnClickListener(V -> {
            deleteClick();
        });
    }

    @Override
    public void onBackPressed() {
        Intent utama = new Intent(this, HomeActivity.class);
        startActivity(utama);
        finish();
    }
}
