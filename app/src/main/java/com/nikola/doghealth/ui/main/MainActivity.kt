package com.nikola.doghealth.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView
import com.nikola.doghealth.databinding.ActivityMainBinding
import com.nikola.doghealth.ui.detail.BreedDetailActivity
import com.nikola.doghealth.ui.random.RandomDogActivity
import com.nikola.doghealth.vm.DogViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding
    private val vm: DogViewModel by viewModels()
    private lateinit var adapter: BreedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        // Recycler + adapter
        adapter = BreedAdapter(emptyList()) { breed ->
            val i = Intent(this, BreedDetailActivity::class.java).apply {
                putExtra("breedId", breed.id)
                putExtra("breedName", breed.name)
                putExtra("temperament", breed.temperament ?: "")
                putExtra("lifeSpan", breed.life_span ?: "")
                putExtra("weight", breed.weight?.metric ?: "")
                putExtra("height", breed.height?.metric ?: "")
            }
            startActivity(i)
        }
        b.rvBreeds.layoutManager = LinearLayoutManager(this)
        b.rvBreeds.adapter = adapter

        // Random dog ekran
        b.btnRandom.setOnClickListener {
            startActivity(Intent(this, RandomDogActivity::class.java))
        }

        // Search (pretraga rasa)
        b.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                val q = newText.orEmpty()
                if (q.isBlank()) vm.loadBreeds() else vm.searchBreeds(q)
                return true
            }
        })

        // OBSERVE: lista, loading, error
        vm.breeds.observe(this) { breeds ->
            adapter.submit(breeds)
        }

        vm.loading.observe(this) { loading ->
            setLoadingUi(loading)
        }

        vm.error.observe(this) { err ->
            err?.let {
                Toast.makeText(this, "Greška: $it", Toast.LENGTH_SHORT).show()
            }
        }

        // inicijalno učitavanje
        vm.loadBreeds()
    }

    private fun setLoadingUi(isLoading: Boolean) {
        b.btnRandom.isEnabled = !isLoading
        b.searchView.isEnabled = !isLoading
        b.rvBreeds.alpha = if (isLoading) 0.6f else 1f
        b.searchView.alpha = if (isLoading) 0.6f else 1f
    }
}
