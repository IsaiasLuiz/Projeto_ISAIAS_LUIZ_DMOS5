package br.edu.dmos5.projeto_isaias_luiz_dmos5.service.character;

import java.util.List;

import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.Character;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.House;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.service.ServiceUtils;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharacterService {

    private Retrofit retrofit;

    public Call<List<Character>> get(House house) {
        retrofit = new Retrofit.Builder()
                .baseUrl(ServiceUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitCharacterService retrofitService = retrofit.create(RetrofitCharacterService.class);

        return retrofitService.get(house.getName(), ServiceUtils.ACCESS_KEY);
    }

}