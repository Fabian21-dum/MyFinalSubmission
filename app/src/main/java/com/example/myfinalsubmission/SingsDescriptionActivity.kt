package com.example.myfinalsubmission

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.Person
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SingsDescriptionActivity : AppCompatActivity() {
    companion object{
        const val key_sing= "key_value"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sings_description)

        val dataSings = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Sings>(key_sing, Sings::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Sings>(key_sing)
        }
        val data = intent.getParcelableExtra<Sings>("DATA")
        Log.d("DATA_INTENT", "$dataSings")

        val tvDetailName:TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription:TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto:ImageView = findViewById(R.id.iv_detail_photo)

        tvDetailName.text = dataSings?.name ?: ""
        tvDetailDescription.text = dataSings?.description ?:""
        ivDetailPhoto.setImageResource(dataSings?.photo ?: 0)




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}