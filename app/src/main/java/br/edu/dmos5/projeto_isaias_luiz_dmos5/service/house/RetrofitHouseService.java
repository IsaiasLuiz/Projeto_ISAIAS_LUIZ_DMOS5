package br.edu.dmos5.projeto_isaias_luiz_dmos5.service.house;

import java.util.List;

import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.House;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitHouseService {

    @GET("houses")
    Call<List<House>> get(@Query("key") String key);

}
