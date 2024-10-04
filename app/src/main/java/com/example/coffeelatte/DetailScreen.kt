package com.example.coffeelatte

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailScreen : AppCompatActivity() {
    companion object {
        const val EXTRA_COFFEE = "extra_coffee"
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)
        supportActionBar?.title="Detail Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        window.statusBarColor = resources.getColor(R.color.status_bar_color, theme)

        val tvDetailName: TextView = findViewById(R.id.tv_item_name_object)
        val tvDetailDescription: TextView = findViewById(R.id.tv_item_description_object)
        val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        val btnShare: Button = findViewById(R.id.action_share)

        val coffee = when {
            Build.VERSION.SDK_INT > 33 -> {
                intent.getParcelableExtra(MainActivity.EXTRA_COFFEE, Coffee::class.java)
            }
            else -> {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(MainActivity.EXTRA_COFFEE)
            }
        }
        if (coffee != null){
            val tvNameHero = coffee.name
            val tvNameDescription = coffee.description
            tvDetailName.text = tvNameHero
            tvDetailDescription.text = tvNameDescription
            imgPhoto.setImageResource(coffee.photo)
        }

        btnShare.setOnClickListener {
            coffee?.let {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Nama: ${it.name}\nDeskripsi: ${it.description}")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Bagikan detail informasi melalui"))
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}