package com.android_poc.newyorktimesproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android_poc.newyorktimesproject.daos.NytCustomObjDao
import com.android_poc.newyorktimesproject.pojos.NytCustomDetailObj

@Database(entities = arrayOf(NytCustomDetailObj::class),version = 1,exportSchema = false)
abstract class NytDbHelper : RoomDatabase(){

    abstract fun getNytCustomObjDao(): NytCustomObjDao

    companion object {
        private var mInstance: NytDbHelper? = null

        fun getInstance(context: Context): NytDbHelper {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(
                        context.applicationContext, NytDbHelper::class.java, "NytDbHelper"
                ).build()
            }
            return mInstance as NytDbHelper
        }
    }
}