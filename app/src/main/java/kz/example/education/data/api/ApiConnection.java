package kz.example.education.data.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnection {

    Retrofit retrofit;
    Gson gson;
    OkHttpClient okHttpClient;

    public Retrofit getRetrofit(){
        if(retrofit != null){
            return retrofit;
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .client(getOkHttpClient())
                .build();

        return retrofit;

    }

    public Gson getGson(){
        if(gson != null){
            return gson;
        }

        gson = new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .setDateFormat("yyyy-MM-dd")
                .create();

        return gson;
    }

    public OkHttpClient getOkHttpClient(){
        if(okHttpClient != null){
            return okHttpClient;
        }

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        return okHttpClient;
    }
}
