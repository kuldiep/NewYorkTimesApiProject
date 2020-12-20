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

    }
}