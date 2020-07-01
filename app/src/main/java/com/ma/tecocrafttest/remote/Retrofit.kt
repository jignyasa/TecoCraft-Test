package com.ma.tecocrafttest.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    companion object{
        fun getRetrofit():Retrofit{
            return Retrofit.Builder().baseUrl("https://www.tecocraft.com/eatenglish/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}