package com.ma.tecocrafttest.model

import com.google.gson.annotations.SerializedName

data class CategoryItem(
    val id:Int,val name:String,@SerializedName("parent") val parentId:Int
)