package com.ma.tecocrafttest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ma.tecocrafttest.adapter.CustomExpandableListAdapter
import com.ma.tecocrafttest.model.CategoryItem
import com.ma.tecocrafttest.remote.ApiServices
import com.ma.tecocrafttest.remote.Retrofit
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapterListView: CustomExpandableListAdapter
    private lateinit var altitle: ArrayList<String>
    private lateinit var hmDetails: HashMap<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapterListView = CustomExpandableListAdapter(this)
        expandableListView.setAdapter(adapterListView)
        getData()
    }

    private fun getData() {
        Retrofit.getRetrofit().create(ApiServices::class.java).getCategoryData("test_category.json")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<ArrayList<CategoryItem>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(response: ArrayList<CategoryItem>) {
                    Log.e("data", response.toString())
                    if (response != null && response.size > 0) {
                        altitle = ArrayList()
                        hmDetails = HashMap()
                        val alListTemp = ArrayList<Int>()
                        for (item: CategoryItem in response) {
                            if (item.parentId == 0)
                                altitle.add(item.name)
                            else {
                                if (alListTemp.contains(item.parentId).not())
                                    alListTemp.add(item.parentId)
                            }
                        }
                        setSubCategory(response, alListTemp)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e("error", e.message)
                }

            })
    }

    private fun setSubCategory(response: ArrayList<CategoryItem>, alTemp: ArrayList<Int>) {
        for (i in 0 until alTemp.size) {
            val alList = ArrayList<String>()
            var str = ""
            for (itemSubCategory in response) {
                if (alTemp[i] == itemSubCategory.id)
                    str = itemSubCategory.name
                if (alTemp[i] == itemSubCategory.parentId) {
                    alList.add(itemSubCategory.name)
                }
            }
            hmDetails.put(str, alList)
        }
        for (i in 0 until altitle.size) {
            if (hmDetails.containsKey(altitle[i]).not())
                hmDetails.put(altitle[i], ArrayList())
        }
        adapterListView.addData(altitle, hmDetails)
    }
}