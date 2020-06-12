package com.nicathaciyev.crazycovid

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicathaciyev.crazycovid.data.ApiService
import com.nicathaciyev.crazycovid.data.responses.AzerbijanItem
import com.nicathaciyev.crazycovid.data.responses.CountryItem
import com.nicathaciyev.crazycovid.data.responses.GlobalItem
import kotlinx.android.synthetic.main.fragment_home.*
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
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val baseUrl: String = "https://covid-az.herokuapp.com"
    val globalUrl: String = "https://coronavirus-19-api.herokuapp.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
        fetchLocalData()
        fetchGlobalData()
    }
    fun fetchLocalData() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(globalUrl)
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val myCall: Call<AzerbijanItem> = apiService.getAzerbaijanData()
        myCall.enqueue(object : Callback<AzerbijanItem> {
            override fun onResponse(call: Call<AzerbijanItem>, response: Response<AzerbijanItem>) {
                tv_country.text = response.body()?.country
                tv_cases.text = response.body()?.totalCases
                tv_new_cases.text = response.body()?.todayCases
                tv_deaths.text = response.body()?.totalDeaths
                tv_new_deaths.text = response.body()?.todayDeaths
                tv_recovered.text = response.body()?.totalRecovered
                tv_critical.text = response.body()?.critical


            }

            override fun onFailure(call: Call<AzerbijanItem>, t: Throwable) {
                Log.d("ERROR", t.message.toString())
            }


        })

    }

    fun fetchGlobalData() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(globalUrl)
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val myCall: Call<GlobalItem> = apiService.getGlobalData()
        myCall.enqueue(object : Callback<GlobalItem> {

            override fun onResponse(call: Call<GlobalItem>, response: Response<GlobalItem>) {
                tv_global_cases.text = response.body()?.globalCases
                tv_global_death.text = response.body()?.globalDeath
                tv_global_recovered.text = response.body()?.globalRecovered
            }

            override fun onFailure(call: Call<GlobalItem>, t: Throwable) {
                Log.d("ERROR", t.message.toString())
            }


        })

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
