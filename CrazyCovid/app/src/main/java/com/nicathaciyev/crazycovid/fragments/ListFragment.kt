package com.nicathaciyev.crazycovid.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicathaciyev.crazycovid.MainActivity
import com.nicathaciyev.crazycovid.R
import com.nicathaciyev.crazycovid.adapters.MyAdapter
import com.nicathaciyev.crazycovid.data.ApiService
import com.nicathaciyev.crazycovid.data.responses.CountryItem
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val globalUrl: String = "https://coronavirus-19-api.herokuapp.com"
    val displayList = ArrayList<CountryItem>()
    val arrayList = ArrayList<CountryItem>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }

        fetchCountriesData()
        setHasOptionsMenu(true)
    }



    private fun fetchCountriesData() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(globalUrl)
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val myCall: Call<List<CountryItem>> = apiService.getCountriesData()
        myCall.enqueue(object : Callback<List<CountryItem>> {
            override fun onResponse(
                call: Call<List<CountryItem>>, response: Response<List<CountryItem>>
            ) {
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<CountryItem>>, t: Throwable) {
                Log.d("ERROR", t.message.toString())

            }

        })
    }

    private fun showData(countries: List<CountryItem>) {
        arrayList.addAll(countries)
        displayList.addAll(countries)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MyAdapter(
                displayList,
                context
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
        val menuItem = menu.findItem(R.id.search)
        if (menuItem != null) {
            val searchView = SearchView((context as MainActivity).supportActionBar?.themedContext?:context)
            //menuItem.actionView as SearchView
            menu.findItem(R.id.search).apply {
                setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or  MenuItem.SHOW_AS_ACTION_IF_ROOM)
                actionView = searchView
            }
            val editText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            editText.hint = "Axtarır..."

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {


                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                         displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        Log.d("Nem","search edir")

                        arrayList.forEach { if( it.country.toLowerCase().contains(search)){
                            displayList.add(it)
                            Log.d("Nem","$it")
                        }



                        }
                        recyclerView.adapter!!.notifyDataSetChanged()

                    }
                    else{
                        displayList.clear()
                        displayList.addAll(arrayList)
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }

            })
        }


        super.onCreateOptionsMenu(menu, inflater)



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Ölkələr"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
