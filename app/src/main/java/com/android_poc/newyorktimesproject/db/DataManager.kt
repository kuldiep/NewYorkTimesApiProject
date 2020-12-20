package com.android_poc.newyorktimesproject.db

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.android_poc.newyorktimesproject.pojos.NytCustomDetailObj
import com.android_poc.newyorktimesproject.utils.AppConstants.Companion.TAG
import com.android_poc.newyorktimesproject.utils.DatabaseTransactionStatusListener
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers


class DataManager(application: Application) {
    private var nytDbHelper: NytDbHelper

    init {
        nytDbHelper = NytDbHelper.getInstance(application)
    }

    fun insertSongAttributesIntoDB(nytCustomDetailObjList: List< NytCustomDetailObj>,
                                   databaseTransactionStatusListener: DatabaseTransactionStatusListener) {

        Completable.fromAction {
            for (nytCustomObj in nytCustomDetailObjList)
                nytDbHelper.getNytCustomObjDao().insertCustomDetailObjIntoTbl(nytCustomObj)
        }.subscribeOn(Schedulers.io()).doOnComplete {
            Log.d(TAG, "insertSongAttributesIntoDB: ")
            databaseTransactionStatusListener.isDataStoredSuccessfull(true)
        }.subscribe()

    }

    fun getNytCustomDetailObjFromTbl() : LiveData<List<NytCustomDetailObj>> {
        return nytDbHelper.getNytCustomObjDao().getAllNytCustomDetailObjFromTbl()
    }

}