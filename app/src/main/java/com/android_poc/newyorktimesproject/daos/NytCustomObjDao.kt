package com.android_poc.newyorktimesproject.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android_poc.newyorktimesproject.pojos.NytCustomDetailObj

@Dao
interface NytCustomObjDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomDetailObjIntoTbl(nytCustomDetailObj: NytCustomDetailObj)

    @Query("SELECT * FROM NytCustomDetailObj")
    fun getAllNytCustomDetailObjFromTbl() : LiveData<List<NytCustomDetailObj>>
}