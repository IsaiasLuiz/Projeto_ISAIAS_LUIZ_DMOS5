package br.edu.dmos5.projeto_isaias_luiz_dmos5.service.house;

import java.util.List;

import br.edu.dmos5.projeto_isaias_luiz_dmos5.model.House;
import br.edu.dmos5.projeto_isaias_luiz_dmos5.service.ServiceUtils;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HouseService {

    private Retrofit retrofit;

    public Call<List<House>> get() {


        retrofit = new Retrofit.Builder()
                .baseUrl(ServiceUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitHouseService retrofitService = retrofit.create(RetrofitHouseService.class);

        return retrofitService.get(ServiceUtils.ACCESS_KEY);
    }

}
