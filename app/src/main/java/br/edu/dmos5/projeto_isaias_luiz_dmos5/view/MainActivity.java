package br.edu.dmos5.projeto_isaias_luiz_dmos5.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import br.edu.dmos5.projeto_isaias_luiz_dmos5.R;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.House;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.exceptions.EmptyDatabaseException;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.repository.HouseRepository;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.service.PermissionService;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.service.house.HouseService;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.view.adapter.HouseItemAdapter;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.view.adapter.RecyclerItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private List<House> housesList;

    private HouseItemAdapter houseItemAdapter;

    private PermissionService permissionService;

    private HouseService houseService;

    private HouseRepository houseRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_recycler_view);

        progressBar = findViewById(R.id.main_loading);

        housesList = new LinkedList<>();

        houseItemAdapter = new HouseItemAdapter(housesList);

        houseItemAdapter.setClickListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                House house = housesList.get(position);
                Intent intent = new Intent(getApplicationContext(), CharacterActivity.class);
                intent.putExtra(House.HOUSE_KEY, house);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(houseItemAdapter);

        permissionService = new PermissionService(this);

        houseService = new HouseService();

        houseRepository = new HouseRepository(this);

        getHouses();

    }

    private void getHouses() {
        try {
            List<House> houses = houseRepository.findAll();
            updateView(houses);
        } catch (EmptyDatabaseException e) {
            if (permissionService.hasPermission()) {
                progressBar.setVisibility(View.VISIBLE);
                Call<List<House>> call = houseService.get();
                call.enqueue(new Callback<List<House>>() {
                    @Override
                    public void onResponse(Call<List<House>> call, Response<List<House>> response) {
                        if (response.isSuccessful()) {
                            List<House> list = response.body();
                            updateView(list);
                            saveHouses(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<House>> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            } else {
                permissionService.requestPermission();
            }
        } catch (Exception e) {
            Toast.makeText(this, R.string.default_error_msg, Toast.LENGTH_SHORT);
        }
    }

    private void saveHouses(List<House> houses) {
        for (House house : houses) {
            houseRepository.save(house);
        }
    }

    public void updateView(List<House> houses) {
        housesList.clear();
        housesList.addAll(houses);
        houseItemAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionService.REQUEST_PERMISSION) {
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equalsIgnoreCase(Manifest.permission.INTERNET) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    getHouses();
                }
            }
        }
    }

}
