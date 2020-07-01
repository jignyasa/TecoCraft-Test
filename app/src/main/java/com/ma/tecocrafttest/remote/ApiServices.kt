package com.ma.tecocrafttest.remote

import com.google.gson.JsonObject
import com.ma.tecocrafttest.model.CategoryItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServices {
    @GET
    fun getCategoryData(@Url url:String):Observable<ArrayList<CategoryItem>>
}