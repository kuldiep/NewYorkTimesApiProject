package com.android_poc.newyorktimesproject.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android_poc.newyorktimesproject.BR
import com.android_poc.newyorktimesproject.R
import com.android_poc.newyorktimesproject.databinding.NytArticleBinding
import com.android_poc.newyorktimesproject.pojos.NytCustomDetailObj
import com.bumptech.glide.Glide

class NytArticleListRecyclerAdapter(var nytCustomDetailObjList: List<NytCustomDetailObj>, val context: Context) :
        RecyclerView.Adapter<NytArticleListRecyclerAdapter.NytArticleRecyclerViewHolder>() {

    class NytArticleRecyclerViewHolder(val nytArticleBinding: NytArticleBinding, view: View,val context: Context) :
            RecyclerView.ViewHolder(view) {

        fun bind(nytCustomDetailObj: NytCustomDetailObj) {
            nytArticleBinding.setVariable(BR.NytCustomObject, nytCustomDetailObj)
            nytArticleBinding.executePendingBindings()
            if(nytCustomDetailObj.imgUrl!=null) {
                Glide.with(context).load(nytCustomDetailObj.imgUrl).into(nytArticleBinding.ivArticleBgImg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NytArticleRecyclerViewHolder {
        val nytArticleBinding = DataBindingUtil.inflate<NytArticleBinding>(LayoutInflater.from(parent.getContext()),
                R.layout.nyt_article, parent, false)
        return NytArticleRecyclerViewHolder(nytArticleBinding, nytArticleBinding.root,context)

    }


    override fun onBindViewHolder(holder: NytArticleRecyclerViewHolder, position: Int) {
        holder.bind(nytCustomDetailObjList.get(position))
    }

    override fun getItemCount(): Int {
        return nytCustomDetailObjList.size
    }

    fun setNytCustomObjectList(nytCustomDetailObjList: List<NytCustomDetailObj>){
        this.nytCustomDetailObjList = nytCustomDetailObjList
        notifyDataSetChanged()
    }
}