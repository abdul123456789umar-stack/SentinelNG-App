package com.sentinelng.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sentinelng.data.ModelType
import com.sentinelng.databinding.ActivityMainBinding
import com.sentinelng.utils.LanguageManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    override fun onResume() {
        super.onResume()
        updateLanguageDisplay()
    }

    private fun setupUI() {
        val lang = LanguageManager.getLanguage(this)
        binding.tvGreeting.text = LanguageManager.getGreeting(lang)

        // Quick-action buttons
        binding.btnCropDoctor.setOnClickListener { openCamera(ModelType.CROP_DOCTOR) }
        binding.btnHealthScan.setOnClickListener { openCamera(ModelType.HEALTH_SCAN) }
        
        // Chat feature removed for this build
        binding.btnChat.setOnClickListener { 
            // Feature disabled
        }
        
        binding.btnSecurity.setOnClickListener { startActivity(Intent(this, SecurityActivity::class.java)) }
        binding.btnSettings.setOnClickListener { startActivity(Intent(this, SettingsActivity::class.java)) }

        // NLU input removed for this build
        // Hide the NLU search bar container
        binding.etNluInput.isEnabled = false
        binding.btnNluSend.isEnabled = false
    }

    private fun openCamera(modelType: ModelType) {
        val intent = Intent(this, CameraActivity::class.java).apply {
            putExtra(CameraActivity.EXTRA_MODEL_TYPE, modelType.name)
        }
        startActivity(intent)
    }

    private fun updateLanguageDisplay() {
        val lang = LanguageManager.getLanguage(this)
        binding.tvGreeting.text = LanguageManager.getGreeting(lang)
        binding.tvCurrentLanguage.text = lang.displayName
    }
}
