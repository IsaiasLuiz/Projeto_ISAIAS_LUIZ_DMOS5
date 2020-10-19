package br.edu.dmos5.projeto_isaias_luiz_dmos5.service.character.details;

import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.CharacterDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitCharacterDetailsService {

    @GET("characters/{id}")
    Call<CharacterDetails> get(@Path("id") String id, @Query("key") String key);

}
