package com.android_poc.newyorktimesproject.ui

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.android_poc.newyorktimesproject.R
import com.android_poc.newyorktimesproject.databinding.ActivityNytArticleBinding
import com.android_poc.newyorktimesproject.viewmodel.NytArticleDataViewModel

class NytArticleActivity : AppCompatActivity() {
    private var binding: ActivityNytArticleBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_nyt_article)
        binding?.rvNytArticles?.layoutManager = LinearLayoutManager(this)
        binding?.rvNytArticles?.itemAnimator = DefaultItemAnimator()
        val nytArticleListRecyclerAdapter = NytArticleListRecyclerAdapter(arrayListOf(),this)
        binding?.rvNytArticles?.adapter = nytArticleListRecyclerAdapter
        val nytArticleDataViewModel by viewModels<NytArticleDataViewModel>()
        nytArticleDataViewModel.getNytArticlesFromNW()
        nytArticleDataViewModel.getApiCallFlag().observe(this,{
            if(!it){
                Toast.makeText(this,"Network call failed please try again",Toast.LENGTH_LONG).show()
            }
        })
        nytArticleDataViewModel.getNytDbStatusFromRepo().observe(this,{
            if(!it){
                Toast.makeText(this,"Database Transaction failed please try again",Toast.LENGTH_LONG).show()
            }
        })
        nytArticleDataViewModel.getNytArticlesFromRepo().observe(this,{
            if(it.isNotEmpty()){
                nytArticleListRecyclerAdapter.setNytCustomObjectList(it)
            }
        })
    }
}