package com.personalvault.ui.theme

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = VaultBluePrimary,
    secondary = VaultBlueSecondary,
    tertiary = VaultTealAccent,
    background = VaultDarkBackground,
    surface = VaultDarkSurface,
    surfaceVariant = VaultDarkSurfaceVariant,
    onPrimary = VaultDarkTextPrimary,
    onBackground = VaultDarkTextPrimary,
    onSurface = VaultDarkTextPrimary,
    onSurfaceVariant = VaultDarkTextSecondary,
    outline = VaultDarkBorder
)

private val LightColorScheme = lightColorScheme(
    primary = VaultLightBluePrimary,
    secondary = VaultBlueSecondary,
    tertiary = VaultTealAccent,
    background = VaultLightBackground,
    surface = VaultLightSurface,
    surfaceVariant = VaultLightSurfaceVariant,
    onPrimary = VaultLightSurface,
    onBackground = VaultLightTextPrimary,
    onSurface = VaultLightTextPrimary,
    onSurfaceVariant = VaultLightTextSecondary,
    outline = VaultLightBorder
)

private fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}

@Composable
fun PersonalVaultTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context.findActivity()
            if (activity != null) {
                try {
                    WindowCompat.getInsetsController(activity.window, view).isAppearanceLightStatusBars = !darkTheme
                } catch (_: Exception) {
                }
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
