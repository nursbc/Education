package kz.example.education.data.repository;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

import kz.example.education.data.api.Api;
import kz.example.education.data.api.ApiConnection;
import kz.example.education.domain.interfaces.LoadedDataInterface;
import kz.example.education.domain.models.Category;
import kz.example.education.domain.repository.GetCategoryDomainRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCategoryRepository implements GetCategoryDomainRepository {

    Api api;
    LoadedDataInterface loadedDataInterface;

    public GetCategoryRepository(LoadedDataInterface loadedDataInterface){
        ApiConnection apiConnection = new ApiConnection();
        this.api = apiConnection.getRetrofit().create(Api.class);
        this.loadedDataInterface = loadedDataInterface;
    }

    @Override
    public void getCategories() {
        api.getCategories().enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                loadedDataInterface.data(response.body());
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
    }
}
