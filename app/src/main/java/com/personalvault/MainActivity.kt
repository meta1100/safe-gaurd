package com.personalvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.personalvault.presentation.navigation.VaultNavGraph
import com.personalvault.ui.theme.PersonalVaultTheme
import com.personalvault.utils.update.UpdateCheckWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Safely schedule update checks on background thread
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                UpdateCheckWorker.schedule(applicationContext)
            } catch (_: Exception) {
                // Non-blocking fallback
            }
        }

        setContent {
            PersonalVaultTheme {
                VaultNavGraph()
            }
        }
    }
}
