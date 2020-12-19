package com.android_poc.newyorktimesproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android_poc.newyorktimesproject.pojos.NytCustomDetailObj
import com.android_poc.newyorktimesproject.utils.ApiCallStatusListener

class NytArticleDataViewModel : BaseViewModel() {

    private var mutableLiveDataApiFlag = MutableLiveData<Boolean>()

    fun getNytArticlesFromNW(){

        val apiCallStatusListener = object : ApiCallStatusListener{
            override fun isApiCallSuccessfull(apiFlag: Boolean) {
                mutableLiveDataApiFlag.value = apiFlag
            }
        }
        getRepositoryInstance().makeNytApiCall(apiCallStatusListener)
    }

    fun getNytArticlesFromRepo():LiveData<List<NytCustomDetailObj>>{
        return getRepositoryInstance().getNytArticlesFromDataSource()
    }

    fun getNytDbStatusFromRepo() : LiveData<Boolean>{
        return getRepositoryInstance().getDbStatusLiveData()
    }

    fun getApiCallFlag():LiveData<Boolean>{
        return mutableLiveDataApiFlag
    }
}