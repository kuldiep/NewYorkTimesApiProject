package com.android_poc.newyorktimesproject.repository

import com.android_poc.newyorktimesproject.NytApplication
import com.android_poc.newyorktimesproject.db.DataManager

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

    fun makeNytApiCall(){

    }
}