package com.nicathaciyev.crazycovid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.nicathaciyev.crazycovid.fragments.HomeFragment
import com.nicathaciyev.crazycovid.fragments.InfoFragment
import com.nicathaciyev.crazycovid.fragments.ListFragment
import com.nicathaciyev.crazycovid.fragments.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener{
    lateinit var   listFragment : ListFragment
    lateinit var  infoFragment: InfoFragment
    lateinit var  newsFragment: NewsFragment
    lateinit var  homeFragment: HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolBar)
        val actionBar = supportActionBar
        actionBar?.title = "Navigation Drawer"
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,drawerLayout,toolBar,(R.string.open),(R.string.close)
        ){

        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

      naw_view.setNavigationItemSelectedListener (this)
        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()



    }


    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
when(menuItem.itemId){
    R.id.home ->{
        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

    }
    R.id.list ->{
        listFragment = ListFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,listFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

    }
    R.id.news ->{
        newsFragment = NewsFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,newsFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

    }
    R.id.info ->{
        infoFragment = InfoFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,infoFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

    }
}
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else{
            super.onBackPressed()

        }
    }
}
