package com.nikola.doghealth.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.nikola.doghealth.R
import com.nikola.doghealth.databinding.ActivityBreedDetailBinding
import com.nikola.doghealth.vm.DogViewModel

class BreedDetailActivity : AppCompatActivity() {

    private lateinit var b: ActivityBreedDetailBinding
    private val vm: DogViewModel by viewModels()

    private var breedId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityBreedDetailBinding.inflate(layoutInflater)
        setContentView(b.root)

        breedId  = intent.getIntExtra("breedId", -1)
        val name = intent.getStringExtra("breedName") ?: ""
        val life = intent.getStringExtra("lifeSpan") ?: ""
        val w    = intent.getStringExtra("weight") ?: ""
        val h    = intent.getStringExtra("height") ?: ""
        val temp = intent.getStringExtra("temperament") ?: "—"

        title = name
        b.tvTitle.text = name
        b.tvMeta.text = getString(R.string.breed_meta, life, w, h)
        b.tvTemperament.text = getString(R.string.breed_temperament, temp)
        b.tvTips.text = getString(R.string.health_tips)

        vm.images.observe(this) { imgs ->
            imgs.firstOrNull()?.url?.let { b.ivCover.load(it) }
        }
        if (breedId != -1) vm.loadBreedImages(breedId)
    }
}
