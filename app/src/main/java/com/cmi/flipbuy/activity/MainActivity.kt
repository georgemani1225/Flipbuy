package com.cmi.flipbuy.activity

import activity.LoginActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.cmi.flipbuy.*
import com.cmi.flipbuy.fragment.*
import com.google.firebase.auth.FirebaseAuth


import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout:DrawerLayout
    lateinit var coordinatorLayout:CoordinatorLayout
    lateinit var ToolBar:Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView:NavigationView
    lateinit var  sharedPreferences: SharedPreferences
    lateinit var menuSignout:MenuItem
    var previousMenuItem:MenuItem?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences=getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        ToolBar = findViewById(R.id.ToolBar)
        navigationView = findViewById(R.id.navigationView)
        frameLayout=findViewById(R.id.frameLayout)

        var titleName=sharedPreferences.getString("Title","Flip buy")
        title=titleName

        openDashboard()
        setUpToolBar()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity, drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {
            if (previousMenuItem!=null){
                previousMenuItem?.isChecked=false
            }
            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it

            when (it.itemId) {
                R.id.menu_dashboard->{
                    openDashboard()
                    drawerLayout.closeDrawers()
                }
                R.id.menu_Offers -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, CartFragment())
                        .commit()
                    supportActionBar?.title = "Offers"
                    drawerLayout.closeDrawers()
                }
                R.id.menu_my_cart -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, CartFragment())
                        .commit()
                    supportActionBar?.title = "Your cart"
                    drawerLayout.closeDrawers()
                }
                R.id.menu_my_wishList -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, WishListFragment()).commit()
                    supportActionBar?.title = "Your WishList"
                    drawerLayout.closeDrawers()
                }
                R.id.menu_my_orders -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, OrdersFragment())
                        .commit()
                    supportActionBar?.title = "Your Orders"
                    drawerLayout.closeDrawers()
                }
                R.id.menu_my_account -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, AccountFragment())
                        .commit()
                    supportActionBar?.title = "Your Account"
                    drawerLayout.closeDrawers()

                }

                R.id.menu_signout->{


                    val intent= Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    sharedPreferences.edit().clear().apply()
                    finish()
                }



            }
            return@setNavigationItemSelectedListener true

        }
    }
    fun setUpToolBar(){
        setSupportActionBar(ToolBar)
        supportActionBar?.title="Flip buy"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id=item.itemId
        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    fun openDashboard(){
        val fragment=DashboardFragment()
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.commit()
        supportActionBar?.title="Flip Buy"
        navigationView.setCheckedItem(R.id.menu_dashboard)
    }

    override fun onBackPressed() {
        val frag=supportFragmentManager.findFragmentById(R.id.frameLayout)
        when(frag){
            !is DashboardFragment->openDashboard()
            else->super.onBackPressed()
        }

    }

}
