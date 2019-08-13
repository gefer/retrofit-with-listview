package br.com.examplerest.service;

import java.util.List;

import br.com.examplerest.entity.Photo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoService {

    @GET("photos")
    Call<List<Photo>> findAll();
}
