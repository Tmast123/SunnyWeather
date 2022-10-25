package com.example.sunnyweather.ui.place

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener


import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnyweather.R
import com.example.sunnyweather.databinding.FragmentPlaceBinding


class PlaceFragment: Fragment() {
    private val viewModel by lazy{ ViewModelProvider(this).get(PlaceViewModel::class.java)}
    private lateinit var adapter: PlaceAdapter


    private lateinit var search:EditText
    private lateinit var recyc: RecyclerView
    private lateinit var bgImage:ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View=inflater.inflate(R.layout.fragment_place,container,false)
        search=view.findViewById<EditText>(R.id.searchPlaceEdit)
        recyc=view.findViewById(R.id.recyclerView)
        bgImage=view.findViewById(R.id.bgImageView)
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            val layoutManager=LinearLayoutManager(activity)

            recyc.layoutManager=layoutManager

            adapter=PlaceAdapter(this,viewModel.placeList)

            recyc.adapter=adapter



            search.addTextChangedListener{  editable->
                val content=editable.toString()
                if (content.isNotEmpty()){
                    viewModel.searchPlaces(content)

                }else{
                    recyc.visibility=View.GONE
                    bgImage.visibility=View.VISIBLE
                    viewModel.placeList.clear()
                    adapter.notifyDataSetChanged()
                }
            }

        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer{ result->
            val places=result.getOrNull()
            if(places!=null){

                recyc.visibility=View.VISIBLE
                bgImage.visibility=View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"未查询到任何地点",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }

        })


    }

}