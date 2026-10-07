package com.personalvault.utils.update

/**
 * Official GitHub repository configuration for PersonalVault releases and update checking.
 */
object UpdateConfig {
    const val GITHUB_OWNER = "meta1100"
    const val GITHUB_REPO = "safe-gaurd"

    val RELEASES_API_URL: String
        get() = "https://api.github.com/repos/$GITHUB_OWNER/$GITHUB_REPO/releases/latest"

    const val CHECK_INTERVAL_HOURS = 24L
    const val NOTIFICATION_CHANNEL_ID = "personal_vault_updates"
    const val NOTIFICATION_ID = 1001
}
