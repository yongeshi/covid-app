package com.example.myapplication.ui.news.api;

import com.example.myapplication.ui.news.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    //News search conditions
    @GET("getArticles")
    Call<News> getLocalSearch(

            @Query("keyword") String[] keyword,
            @Query("keywordLoc") String keywordLoc,
            @Query("lang") String language,
            @Query("resultType") String resultType,
            @Query("country") String country,
            @Query("conceptUri") String concept,
            @Query("articlesSortBy") String sortBy,
            @Query("articlesCount") String articleCount,
            @Query("articleBodyLen") String articleBodyLen,
            @Query("isDuplicateFilter") String isDuplicate,
            @Query("apiKey") String apiKey
    );

    @GET("getArticles")
    Call<News> getLocationSearch(

            @Query("keyword") String[] keyword,
            @Query("keywordLoc") String keywordLoc,
            @Query("lang") String language,
            @Query("resultType") String resultType,
            @Query("articlesSortBy") String sortBy,
            @Query("articlesCount") String articleCount,
            @Query("articleBodyLen") String articleBodyLen,
            @Query("keywordOper") String keywordOper,
            @Query("sourceLocationUri") String location,
            @Query("isDuplicateFilter") String isDuplicate,
            @Query("apiKey") String apiKey
    );
}