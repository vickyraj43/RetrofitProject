package com.app.androidx_project;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

   // POST
///   https://jsonplaceholder.typicode.com/comments


    // GET
// https://jsonplaceholder.typicode.com/posts/1


    @GET("posts/1")
    Call<Posts> getPosts(@Query("id") Integer id);

    @POST("comments")
    Observable<Comments> getCommentById();

    @POST("comments")
    Call<List<Comments>> getComments();

}
