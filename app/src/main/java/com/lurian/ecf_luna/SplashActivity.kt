package com.lurian.ecf_luna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.bumptech.glide.Glide
import androidx.core.os.HandlerCompat
import com.lurian.ec3_luna.R
import android.os.Handler
import com.lurian.ec3_luna.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        HandlerCompat.postDelayed(Handler(),{val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()},null,4500)

        Glide.with(baseContext).load(R.drawable.ima).into(binding.image)
    }
}