package br.edu.dmos5.projeto_isaias_luiz_dmos5.service.character;

import java.util.List;

import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.Character;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.service.ServiceUtils;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitCharacterService {

    @GET("characters")
    Call<List<Character>> get(@Query("house") String house, @Query("key") String key);

}
