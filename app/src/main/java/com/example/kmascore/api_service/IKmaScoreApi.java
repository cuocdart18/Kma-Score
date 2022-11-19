package com.example.kmascore.api_service;

import com.example.kmascore.models.Result;
import com.example.kmascore.models.SearchResult;
import com.example.kmascore.models.StatisticsResult;
import com.example.kmascore.models.StudentResult;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IKmaScoreApi {

    String BASE_URL = "https://kma.lucasdang.me";

    OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true);

    IKmaScoreApi getInstance = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(builder.build())
            .build()
            .create(IKmaScoreApi.class);

    @GET("/statistics")
    Observable<StatisticsResult> getStatistics();

    @GET("/student/{studentId}")
    Observable<StudentResult> getStudentStatistics(@Path("studentId") String studentId);

    @GET("/subject/{subjectId}")
    Observable<Result> getSubjectStatistics(@Path("subjectId") String subjectId);

    @GET("/subjects")
    Observable<Result> getSubjects();

    @GET("/search")
    Observable<SearchResult> search(@Query("query") String data);
}
