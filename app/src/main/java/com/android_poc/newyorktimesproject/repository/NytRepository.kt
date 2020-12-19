package com.android_poc.newyorktimesproject.repository

import android.util.Log
import com.android_poc.newyorktimesproject.NytApplication
import com.android_poc.newyorktimesproject.db.DataManager
import com.android_poc.newyorktimesproject.networking.NytApiCallService
import com.android_poc.newyorktimesproject.networking.NytRetrofitApiClient
import com.android_poc.newyorktimesproject.pojos.NytCustomDetailObj
import com.android_poc.newyorktimesproject.tos.NytRootRespTO
import com.android_poc.newyorktimesproject.utils.AppConstants.Companion.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NytRepository private constructor(){
    private var dataManager: DataManager

    init {
        dataManager = DataManager(NytApplication.getApplicationInstance())
    }

    private object HOLDER {
        val INSTANCE = NytRepository()
    }

    companion object {
        fun getNytRepositoryInstance(): NytRepository {
            val instance: NytRepository by lazy { HOLDER.INSTANCE }
            return instance
        }
    }

    fun makeNytApiCall() {
        val nytApiCallService = NytRetrofitApiClient.buildService(NytApiCallService::class.java)
        nytApiCallService.getArticlesOfNyt().enqueue(object : Callback<NytRootRespTO> {
            override fun onResponse(call: Call<NytRootRespTO>, response: Response<NytRootRespTO>) {
                if (response.isSuccessful) {
                    insertResponseIntoDB(response.body())
                }
            }

            override fun onFailure(call: Call<NytRootRespTO>, t: Throwable) {
                Log.d(TAG, "onFailure: exception ..", t.cause)
            }

        })
    }

    fun insertResponseIntoDB(nytRootRespTO: NytRootRespTO?) {
        try {
            Log.d(TAG, "insertResponseIntoDB: nytRootRespToResult list is = "+nytRootRespTO?.results)
            val nytCustomDetailObjList = arrayListOf<NytCustomDetailObj>()
            if (nytRootRespTO?.results != null && nytRootRespTO.results.isNotEmpty()) {
                for (results in nytRootRespTO.results) {
                    val nytCustomDetailObj = NytCustomDetailObj()
                    nytCustomDetailObj.title = results?.title
                  //  Log.d(TAG, "insertResponseIntoDB: ")
                    nytCustomDetailObj.description = results?.jsonMemberAbstract
                    nytCustomDetailObj.publishDate = results?.publishedDate
                        if(results?.media!=null && results?.media?.isNotEmpty()) {
                        Log.d(TAG, "insertResponseIntoDB: url is = " + results?.media?.get(0)?.mediaMetadata?.get(0)?.url)
                        nytCustomDetailObj.imgUrl = results?.media?.get(0)?.mediaMetadata?.get(0)?.url
                    }
                    nytCustomDetailObjList.add(nytCustomDetailObj)
                }
            }
            dataManager.insertSongAttributesIntoDB(nytCustomDetailObjList)
        } catch (ex: Exception) {
            Log.d(TAG, "insertResponseIntoDB: exception..", ex)
        }
    }
}