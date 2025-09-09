package com.nikola.doghealth.ui.random

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.nikola.doghealth.R
import com.nikola.doghealth.databinding.ActivityRandomDogBinding
import com.nikola.doghealth.vm.DogViewModel

class RandomDogActivity : AppCompatActivity() {

    private lateinit var b: ActivityRandomDogBinding
    private val vm: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityRandomDogBinding.inflate(layoutInflater)
        setContentView(b.root)
        title = "Random dog"

        // slika
        vm.images.observe(this) { imgs ->
            val url = imgs.firstOrNull()?.url
            if (url.isNullOrBlank()) {
                b.ivRandom.setImageDrawable(null)
            } else {
                b.ivRandom.load(url)
            }
        }

        // loading
        vm.loading.observe(this) { loading ->
            b.progress.visibility = if (loading) android.view.View.VISIBLE else android.view.View.GONE
            b.btnRefresh.isEnabled = !loading
        }

        // error
        vm.error.observe(this) { err ->
            err?.let { Toast.makeText(this, getString(R.string.error_loading), Toast.LENGTH_SHORT).show() }
        }

        b.btnRefresh.setOnClickListener { vm.loadRandomDog() }

        // pokretanje prvog učitavanja
        vm.loadRandomDog()
    }
}
