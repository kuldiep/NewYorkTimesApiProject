package com.android_poc.newyorktimesproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.android_poc.newyorktimesproject.R
import com.android_poc.newyorktimesproject.databinding.FragmentNytArticleListBinding
import com.android_poc.newyorktimesproject.viewmodel.NytArticleDataViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NytArticleListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NytArticleListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var binding:FragmentNytArticleListBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_nyt_article_list, container, false)
        binding = DataBindingUtil.bind(view)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.rvNytArticles?.layoutManager = LinearLayoutManager(activity)
        binding?.rvNytArticles?.itemAnimator = DefaultItemAnimator()
        val nytArticleListRecyclerAdapter = NytArticleListRecyclerAdapter(arrayListOf(),requireContext())
        binding?.rvNytArticles?.adapter = nytArticleListRecyclerAdapter
        val nytArticleDataViewModel by activityViewModels<NytArticleDataViewModel>()
        nytArticleDataViewModel.getNytArticlesFromNW()
        nytArticleDataViewModel.getApiCallFlag().observe(viewLifecycleOwner,{
            if(!it){
                Toast.makeText(activity,"Network call failed please try again", Toast.LENGTH_LONG).show()
            }
        })
        nytArticleDataViewModel.getNytDbStatusFromRepo().observe(viewLifecycleOwner,{
            if(!it){
                Toast.makeText(activity,"Database Transaction failed please try again", Toast.LENGTH_LONG).show()
            }
        })
        nytArticleDataViewModel.getNytArticlesFromRepo().observe(viewLifecycleOwner,{
            if(it.isNotEmpty()){
                nytArticleListRecyclerAdapter.setNytCustomObjectList(it)
            }
        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NytArticleListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                NytArticleListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}