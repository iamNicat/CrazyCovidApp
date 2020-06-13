package com.nicathaciyev.crazycovid.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.nicathaciyev.crazycovid.R
import com.nicathaciyev.crazycovid.adapters.MyInfoAdapter
import com.nicathaciyev.crazycovid.data.ApiService
import com.nicathaciyev.crazycovid.data.responses.CountryItem
import com.nicathaciyev.crazycovid.data.responses.InfoItem
import kotlinx.android.synthetic.main.fragment_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val baseUrl: String = "https://covid-az.herokuapp.com"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


        fetchInfoData()
    }

    private fun fetchInfoData() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val myCall: Call<List<InfoItem>> = apiService.getLocalInfo()

        myCall.enqueue(object : Callback<List<InfoItem>>{
            override fun onResponse(call: Call<List<InfoItem>>, response: Response<List<InfoItem>>) {
              showData(response.body()!!)
                Log.d("Melumatlar","${response.body()}")
            }

            override fun onFailure(call: Call<List<InfoItem>>, t: Throwable) {
                Log.d("ERROR", t.message.toString())

            }

        })
    }

    private fun showData(infoItems : List<InfoItem>) {
        recyclerView.apply {
            adapter = MyInfoAdapter(infoItems)
        }
        recyclerView.setHasFixedSize(true)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Məlumatlar"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
