package com.miw.mymovie.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.miw.mymovie.databinding.ActivityLatestsBinding

class LatestsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLatestsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatestsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}