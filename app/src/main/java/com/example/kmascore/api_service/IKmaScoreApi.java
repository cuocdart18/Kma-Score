package com.example.kmascore.api_service;

import com.example.kmascore.models.Result;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IKmaScoreApi {

    String BASE_URL = "https://kma.lucasdang.me";

    OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true);

    IKmaScoreApi getInstance = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()
            .create(IKmaScoreApi.class);

    @GET("/statistics")
    Observable<Result> getStatistics();

    @GET("/student/{studentId}")
    Observable<Result> getStudentStatistics(@Path("studentId") String studentId);

    @GET("/subject/{subjectId}")
    Observable<Result> getSubjectStatistics(@Path("subjectId") String subjectId);

    @GET("/subjects")
    Observable<Result> getSubjects();

    @GET("/search?query={data}")
    Observable<Result> search(@Path("data") String data);
}
