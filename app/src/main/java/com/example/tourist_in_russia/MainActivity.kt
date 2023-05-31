package com.example.tourist_in_russia

import android.Manifest
import android.content.ContentProviderClient
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.location.LocationServices
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener (this)

        var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        textCity=findViewById(R.id.textCity)

        getCurrentLocation()
    }

    private lateinit var frusedlocationProviderClient: ContentProviderClient
    private lateinit var textCity: TextView
    private lateinit var tvLongitude: TextView

    fun getCurrentLocation()
    {
        if(checkPermission())
        {
            if(isLocationEnable())
            {

            }
            else
            {
                Toast.makeText(this, "Turn on location",Toast.LENGTH_SHORT).show()
                val intent=Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)

            }
        }
        else
        {
            requestPermissions()
        }


    }

    private fun isLocationEnable():Boolean{
        val locationManager:LocationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )

    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    companion object{
        private const val PERMISSION_REQUEST_ACCESS_LOCATION=100
    }

    private fun checkPermission(): Boolean
    {
        if(ActivityCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION)
            ==PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
            android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode== PERMISSION_REQUEST_ACCESS_LOCATION)
        {
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(applicationContext,"Granted",Toast.LENGTH_SHORT ).show()
                getCurrentLocation()
            }
            else{
                Toast.makeText(applicationContext,"Denied",Toast.LENGTH_SHORT ).show()

            }
        }
    }


    fun onClickGPS(view: View){
        val textCity = findViewById<TextView>(R.id.textCity)
        textCity.text = "Moscow"
    }

    fun onClickSave(view: View){

        var saveCity = findViewById(R.id.saveCity) as Button
        saveCity.setOnClickListener {
            val intent = Intent(this, MainEntertainments::class.java)
            startActivity(intent);
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.id_region -> {
                Toast.makeText(this,"Id region",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            R.id.id_application -> {
                val intent = Intent(this, MainApplication::class.java)
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