package com.example.ui.theme

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BrightnessAuto
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

/**
 * Supported Theme Modes across the Sonia Saxena Executive Portfolio.
 */
enum class ThemeMode(
    val title: String,
    val subtitle: String,
    val icon: ImageVector
) {
    SYSTEM(
        title = "System Default",
        subtitle = "Matches device OS appearance",
        icon = Icons.Outlined.BrightnessAuto
    ),
    LIGHT(
        title = "Light Mode",
        subtitle = "Crisp slate & high contrast",
        icon = Icons.Outlined.LightMode
    ),
    DARK(
        title = "Dark Mode",
        subtitle = "Midnight obsidian palette",
        icon = Icons.Outlined.DarkMode
    )
}

/**
 * Controller responsible for managing global theme state and persistence.
 */
@Stable
class ThemeController(
    initialMode: ThemeMode = ThemeMode.SYSTEM,
    private val preferences: SharedPreferences? = null
) {
    private val _themeMode = mutableStateOf(initialMode)
    val themeMode: ThemeMode get() = _themeMode.value

    fun isDark(systemInDarkTheme: Boolean): Boolean {
        return when (_themeMode.value) {
            ThemeMode.SYSTEM -> systemInDarkTheme
            ThemeMode.LIGHT -> false
            ThemeMode.DARK -> true
        }
    }

    fun setThemeMode(mode: ThemeMode) {
        _themeMode.value = mode
        preferences?.edit()?.putString(KEY_THEME_MODE, mode.name)?.apply()
    }

    fun toggleTheme(systemInDarkTheme: Boolean) {
        val currentIsDark = isDark(systemInDarkTheme)
        setThemeMode(if (currentIsDark) ThemeMode.LIGHT else ThemeMode.DARK)
    }

    companion object {
        private const val PREFS_NAME = "sonia_portfolio_theme_prefs"
        private const val KEY_THEME_MODE = "saved_theme_mode"

        fun create(context: Context): ThemeController {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val savedName = prefs.getString(KEY_THEME_MODE, ThemeMode.SYSTEM.name)
            val initialMode = try {
                ThemeMode.valueOf(savedName ?: ThemeMode.SYSTEM.name)
            } catch (e: Exception) {
                ThemeMode.SYSTEM
            }
            return ThemeController(initialMode, prefs)
        }
    }
}

val LocalThemeController = staticCompositionLocalOf<ThemeController> {
    error("ThemeController not provided. Wrap your content in SoniaPortfolioTheme.")
}

/**
 * Interactive Dialog for selecting theme modes with instant preview.
 */
@Composable
fun ThemeSelectorDialog(
    onDismiss: () -> Unit
) {
    val themeController = LocalThemeController.current
    val currentMode = themeController.themeMode

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("theme_selector_dialog")
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Display Appearance",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Text(
                            text = "Select executive contrast scheme",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    ThemeMode.values().forEach { mode ->
                        val isSelected = currentMode == mode
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            border = BorderStroke(
                                1.dp,
                                if (isSelected) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .clickable {
                                    themeController.setThemeMode(mode)
                                    onDismiss()
                                }
                                .testTag("theme_option_${mode.name.lowercase()}")
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer,
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Icon(
                                                imageVector = mode.icon,
                                                contentDescription = null,
                                                tint = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondaryContainer,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                    }

                                    Column {
                                        Text(
                                            text = mode.title,
                                            style = MaterialTheme.typography.labelLarge.copy(
                                                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Bold,
                                                color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                                            )
                                        )
                                        Text(
                                            text = mode.subtitle,
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                fontSize = 11.sp,
                                                color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f) else MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        )
                                    }
                                }

                                if (isSelected) {
                                    Icon(
                                        Icons.Outlined.Check,
                                        contentDescription = "Selected",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                ) {
                    Text("Done", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                }
            }
        }
    }
}
