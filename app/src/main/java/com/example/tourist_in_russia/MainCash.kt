package com.example.tourist_in_russia

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainCash : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_cash)
        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener (this)






    }
    fun main(args: Array<String>){
        val Budget = findViewById<TextView>(R.id.Budget)
        val Expenses = findViewById<TextView>(R.id.Expenses)
        val History = findViewById<TextView>(R.id.History)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.id_region -> {
                Toast.makeText(this,"Id region", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            R.id.id_entertainments -> {
                val intent = Intent(this, MainEntertainments::class.java)
                startActivity(intent)
            }

            R.id.id_cash -> {
                val intent = Intent(this, MainCash::class.java)
                startActivity(intent)
            }


        }
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
}
private fun TextView.setText(toDouble: Double) {
}
