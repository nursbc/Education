package kz.example.education.data.api;

import retrofit2.Call;
import retrofit2.http.GET;
import kz.example.education.domain.models.Category;

public interface Api {

    @GET(ApiConstants.GET_CATEGORIES)
    public Call<Category> getCategories();
}
