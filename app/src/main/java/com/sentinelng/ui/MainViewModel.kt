package com.sentinelng.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.sentinelng.data.SupportedLanguage
import com.sentinelng.utils.LanguageManager

class MainViewModel(application: Application) : AndroidViewModel(application) {

    fun getCurrentLanguage(): SupportedLanguage =
        LanguageManager.getLanguage(getApplication())
}
