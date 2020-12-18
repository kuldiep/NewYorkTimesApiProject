package com.android_poc.newyorktimesproject.networking

import com.android_poc.newyorktimesproject.tos.NytRootRespTO
import com.android_poc.newyorktimesproject.utils.AppConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NytApiCallService {

    @GET(AppConstants.END_POINT)
    fun getArticlesOfNyt(@Query("api-key") apiKey:String) : Call<List<NytRootRespTO>>
}