package com.example.testalef

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide


class ShowPhotoActivity : AppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_photo)

        val actionbar = supportActionBar
        actionbar!!.title =  ""
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        val photoUrl = intent.getStringExtra("photoUrl")
        val photo_iv = findViewById<ImageView>(R.id.photo_iv)

        Glide.with(this)
            .load(photoUrl)
            .placeholder(R.drawable.gray_place)
            .error(R.drawable.ic_close)
            .centerCrop()
            .into(photo_iv)
    }
}