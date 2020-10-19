package br.edu.dmos5.projeto_isaias_luiz_dmos5.view;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.LinkedList;
import java.util.List;

import br.edu.dmos5.projeto_isaias_luiz_dmos5.R;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.Character;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.CharacterDetails;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.House;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.exceptions.EmptyDatabaseException;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.repository.CharacterRepository;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.service.PermissionService;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.service.character.CharacterService;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.service.character.details.CharacterDetailsService;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.view.adapter.CharacterItemAdapter;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.view.adapter.RecyclerItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private List<Character> characterList;

    private CharacterItemAdapter characterItemAdapter;

    private PermissionService permissionService;

    private CharacterService characterService;

    private CharacterDetailsService characterDetailsService;

    private CharacterRepository characterRepository;

    private House house;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.character_recycler_view);

        progressBar = findViewById(R.id.character_loading);

        characterList = new LinkedList<>();

        characterItemAdapter = new CharacterItemAdapter(characterList);

        characterItemAdapter.setClickListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getCharacterDetails(characterList.get(position));
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(characterItemAdapter);

        permissionService = new PermissionService(this);

        characterService = new CharacterService();

        characterDetailsService = new CharacterDetailsService();

        characterRepository = new CharacterRepository(this);

        extractData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionService.REQUEST_PERMISSION) {
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equalsIgnoreCase(Manifest.permission.INTERNET) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    getCharacters();
                }

            }
        }
    }

    private void extractData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            Toast.makeText(this, R.string.default_error_msg, Toast.LENGTH_SHORT);
            finish();
        }

        house = (House) bundle.get(House.HOUSE_KEY);
        getCharacters();
    }

    private void getCharacters() {
        if (permissionService.hasPermission()) {
            progressBar.setVisibility(View.VISIBLE);
            Call<List<Character>> call = characterService.get(house);
            call.enqueue(new Callback<List<Character>>() {
                @Override
                public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {
                    if (response.isSuccessful()) {
                        List<Character> list = response.body();
                        characterList.clear();
                        characterList.addAll(list);
                        characterItemAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<List<Character>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        } else {
            permissionService.requestPermission();
        }
    }

    private void getCharacterDetails(final Character character) {

        try {
            CharacterDetails characterDetails = characterRepository.findById(character.getId());
            viewCharacter(characterDetails);
        } catch (EmptyDatabaseException e) {
            if (permissionService.hasPermission()) {
                Call<CharacterDetails> call = characterDetailsService.get(character);
                call.enqueue(new Callback<CharacterDetails>() {
                    @Override
                    public void onResponse(Call<CharacterDetails> call, Response<CharacterDetails> response) {
                        if (response.isSuccessful()) {
                            CharacterDetails characterDetails = response.body();
                            characterRepository.save(characterDetails);
                            viewCharacter(characterDetails);
                        }
                    }

                    @Override
                    public void onFailure(Call<CharacterDetails> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            } else {
                permissionService.requestPermission();
            }
        } catch (Exception e) {
            Toast.makeText(this, R.string.default_error_msg, Toast.LENGTH_SHORT);
        }
    }

    private void viewCharacter(CharacterDetails characterDetails) {
        new MaterialAlertDialogBuilder(this, R.style.Theme_MaterialComponents_Light_Dialog_Alert_Bridge)
                .setTitle(characterDetails.getName())
                .setMessage(characterDetails.toString())
                .setNegativeButton(R.string.close_msg, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

}
