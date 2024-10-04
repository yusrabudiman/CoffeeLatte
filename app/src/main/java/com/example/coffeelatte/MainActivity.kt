package com.example.coffeelatte
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_COFFEE = "extra_coffee"
    }

    private lateinit var rvCoffee: RecyclerView
    private val list = ArrayList<Coffee>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = resources.getColor(R.color.status_bar_color, theme)

        rvCoffee = findViewById(R.id.rv_coffee)
        rvCoffee.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveAboutScreen = Intent(this@MainActivity, AboutScreen::class.java)
                startActivity(moveAboutScreen)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("Recycle")
    private fun getListHeroes(): ArrayList<Coffee> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listCoffees = ArrayList<Coffee>()
        for (i in dataName.indices) {
            val coffee = Coffee(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listCoffees.add(coffee)
        }
        return listCoffees
    }

    private fun showRecyclerList() {
        rvCoffee.layoutManager = LinearLayoutManager(this)
        val listCoffeeAdapter = ListCoffeeAdapter(list)
        rvCoffee.adapter = listCoffeeAdapter

        listCoffeeAdapter.setOnItemClickCallback(object : ListCoffeeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Coffee) {
                showSelectedHero(data)
            }
        })
    }
    private fun showSelectedHero(coffee: Coffee) {
    //  Toast.makeText(this, "Kamu memilih " + coffee.name, Toast.LENGTH_SHORT).show()
        val moveDetailScreen = Intent(this@MainActivity, DetailScreen::class.java)
        moveDetailScreen.putExtra(DetailScreen.EXTRA_COFFEE, coffee)
        startActivity(moveDetailScreen)
    }
}