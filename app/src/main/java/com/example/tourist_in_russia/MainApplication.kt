package com.example.tourist_in_russia

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class MainApplication : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
        var adapter: MyAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_application)
        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener (this)

        var list = ArrayList<ListItem>()


        list.addAll(fillArras(resources.getStringArray(R.array.application),
            resources.getStringArray(R.array.application_content),getImageId(R.array.application_image_array)))


        val rcView = findViewById<RecyclerView>(R.id.rcView)
        rcView.adapter = MyAdapter(list,this)
        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this);

        adapter = MyAdapter(list, this)
        rcView.adapter = adapter
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
    fun fillArras(titleArray: Array<String>,contentArray: Array<String>,imageArray: IntArray):List<ListItem>
    {
        var listItemArray = ArrayList<ListItem>()
        for (n in 0..titleArray.size - 1)
        {
            var listItem = ListItem(imageArray[n],titleArray[n],contentArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }
    fun getImageId(imageArrayId: Int):IntArray
    {
        var tArray: TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for(i in ids.indices)
        {
            ids[i] = tArray.getResourceId(i,0)
        }
        tArray.recycle()
        return ids
    }



}