package br.edu.dmos5.projeto_isaias_luiz_dmos5.service.character.details;


import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.Character;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.CharacterDetails;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.service.ServiceUtils;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharacterDetailsService {

    private Retrofit retrofit;

    public Call<CharacterDetails> get(Character character) {
        retrofit = new Retrofit.Builder()
                .baseUrl(ServiceUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitCharacterDetailsService retrofitService = retrofit.create(RetrofitCharacterDetailsService.class);

        return retrofitService.get(character.getId(), ServiceUtils.ACCESS_KEY);
    }


}
