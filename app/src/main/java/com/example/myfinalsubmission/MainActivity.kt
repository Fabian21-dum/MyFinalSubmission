package com.example.myfinalsubmission

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvSings: RecyclerView
    private val list = ArrayList<Sings>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rvSings = findViewById(R.id.rv_sings)
        rvSings.setHasFixedSize(true)

        list.addAll(getListSings())
        showRecyclerList()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showRecyclerList() {
        rvSings.layoutManager = LinearLayoutManager(this)
        val listSings= ListSings(list)
        rvSings.adapter = listSings

        listSings.setOnItemClickCallback(object: ListSings.OnItemClickCallback{
            override fun onItemClicked(data: Sings){
                val intentToDetail = Intent(this@MainActivity, SingsDescriptionActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })
    }
    private fun getListSings(): ArrayList<Sings> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listSings = ArrayList<Sings>()
        for (i in dataName.indices){
            val sings = Sings(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listSings.add(sings)
        }
        return listSings
    }
    private fun showSelectedSings(sings: Sings){
        Toast.makeText(this, "Kamu memilih " + sings.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_list->{
                rvSings.layoutManager = LinearLayoutManager(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}