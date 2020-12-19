package com.android_poc.newyorktimesproject.viewmodel

import androidx.lifecycle.ViewModel
import com.android_poc.newyorktimesproject.repository.NytRepository

open class BaseViewModel : ViewModel(){
    fun getRepositoryInstance():NytRepository{
        return NytRepository.getNytRepositoryInstance()
    }
}