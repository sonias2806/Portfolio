package com.example.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = GeoPrimaryDark,
    onPrimary = GeoOnPrimaryDark,
    primaryContainer = GeoPrimaryContainerDark,
    onPrimaryContainer = GeoOnPrimaryContainerDark,
    secondary = GeoSecondaryDark,
    onSecondary = GeoOnSecondaryDark,
    secondaryContainer = GeoSecondaryContainerDark,
    onSecondaryContainer = GeoOnSecondaryContainerDark,
    tertiary = GeoTertiaryDark,
    onTertiary = GeoOnTertiaryDark,
    tertiaryContainer = GeoTertiaryContainerDark,
    onTertiaryContainer = GeoOnTertiaryContainerDark,
    background = GeoBackgroundDark,
    onBackground = GeoOnSurfaceDark,
    surface = GeoSurfaceDark,
    onSurface = GeoOnSurfaceDark,
    surfaceVariant = GeoSurfaceVariantDark,
    onSurfaceVariant = GeoOnSurfaceVariantDark,
    outline = GeoOutlineDark,
    outlineVariant = GeoOutlineVariantDark,
  )

private val LightColorScheme =
  lightColorScheme(
    primary = GeoPrimaryLight,
    onPrimary = GeoOnPrimaryLight,
    primaryContainer = GeoPrimaryContainerLight,
    onPrimaryContainer = GeoOnPrimaryContainerLight,
    secondary = GeoSecondaryLight,
    onSecondary = GeoOnSecondaryLight,
    secondaryContainer = GeoSecondaryContainerLight,
    onSecondaryContainer = GeoOnSecondaryContainerLight,
    tertiary = GeoTertiaryLight,
    onTertiary = GeoOnTertiaryLight,
    tertiaryContainer = GeoTertiaryContainerLight,
    onTertiaryContainer = GeoOnTertiaryContainerLight,
    background = GeoBackgroundLight,
    onBackground = GeoOnSurfaceLight,
    surface = GeoSurfaceLight,
    onSurface = GeoOnSurfaceLight,
    surfaceVariant = GeoSurfaceVariantLight,
    onSurfaceVariant = GeoOnSurfaceVariantLight,
    outline = GeoOutlineLight,
    outlineVariant = GeoOutlineVariantLight,
  )

/**
 * SoniaPortfolioTheme sets up the M3 Color Scheme, Typography, and Global LocalThemeController.
 */
@Composable
fun SoniaPortfolioTheme(
  themeController: ThemeController? = null,
  content: @Composable () -> Unit,
) {
  val context = LocalContext.current
  val controller = themeController ?: remember { ThemeController.create(context) }
  val systemDark = isSystemInDarkTheme()
  val darkTheme = controller.isDark(systemDark)

  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  CompositionLocalProvider(
    LocalThemeController provides controller
  ) {
    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
  }
}
