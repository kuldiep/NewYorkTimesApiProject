package com.android_poc.newyorktimesproject.utils

interface ApiCallStatusListener {
    fun isApiCallSuccessfull(apiFlag:Boolean)
}

interface DatabaseTransactionStatusListener{
    fun isDataStoredSuccessfull(dbFlag: Boolean = false)
}