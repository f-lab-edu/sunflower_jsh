package com.example.sunflower.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

@Composable
fun SunflowerTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content:
    @Composable () -> Unit,
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
    )
}

private val LightColors = lightColorScheme(
    primary = MD_THEME_LIGHT_PRIMARY,
    onPrimary = MD_THEME_LIGHT_ON_PRIMARY,
    primaryContainer = MD_THEME_LIGHT_PRIMARY_CONTAINER,
    onPrimaryContainer = MD_THEME_LIGHT_ON_PRIMARY_CONTAINER,
    secondary = MD_THEME_LIGHT_SECONDARY,
    onSecondary = MD_THEME_LIGHT_ON_SECONDARY,
    secondaryContainer = MD_THEME_LIGHT_SECONDARY_CONTAINER,
    onSecondaryContainer = MD_THEME_LIGHT_ON_SECONDARY_CONTAINER,
    tertiary = MD_THEME_LIGHT_TERTIARY,
    onTertiary = MD_THEME_LIGHT_ON_TERTIARY,
    tertiaryContainer = MD_THEME_LIGHT_TERTIARY_CONTAINER,
    onTertiaryContainer = MD_THEME_LIGHT_ON_TERTIARY_CONTAINER,
    error = MD_THEME_LIGHT_ERROR,
    onError = MD_THEME_LIGHT_ON_ERROR,
    errorContainer = MD_THEME_LIGHT_ERROR_CONTAINER,
    onErrorContainer = MD_THEME_LIGHT_ON_ERROR_CONTAINER,
    outline = MD_THEME_LIGHT_OUTLINE,
    background = MD_THEME_LIGHT_BACKGROUND,
    onBackground = MD_THEME_LIGHT_ON_BACKGROUND,
    surface = MD_THEME_LIGHT_SURFACE,
    onSurface = MD_THEME_LIGHT_ON_SURFACE,
    surfaceVariant = MD_THEME_LIGHT_SURFACE_VARIANT,
    onSurfaceVariant = MD_THEME_LIGHT_ON_SURFACE_VARIANT,
    inverseSurface = MD_THEME_LIGHT_INVERSE_SURFACE,
    inverseOnSurface = MD_THEME_LIGHT_INVERSE_ON_SURFACE,
    inversePrimary = MD_THEME_LIGHT_INVERSE_PRIMARY,
    surfaceTint = MD_THEME_LIGHT_SURFACE_TINT,
    outlineVariant = MD_THEME_LIGHT_OUTLINE_VARIANT,
    scrim = MD_THEME_LIGHT_SCRIM,
)

private val DarkColors = darkColorScheme(
    primary = MD_THEME_DARK_PRIMARY,
    onPrimary = MD_THEME_DARK_ON_PRIMARY,
    primaryContainer = MD_THEME_DARK_PRIMARY_CONTAINER,
    onPrimaryContainer = MD_THEME_DARK_ON_PRIMARY_CONTAINER,
    secondary = MD_THEME_DARK_SECONDARY,
    onSecondary = MD_THEME_DARK_ON_SECONDARY,
    secondaryContainer = MD_THEME_DARK_SECONDARY_CONTAINER,
    onSecondaryContainer = MD_THEME_DARK_ON_SECONDARY_CONTAINER,
    tertiary = MD_THEME_DARK_TERTIARY,
    onTertiary = MD_THEME_DARK_ON_TERTIARY,
    tertiaryContainer = MD_THEME_DARK_TERTIARY_CONTAINER,
    onTertiaryContainer = MD_THEME_DARK_ON_TERTIARY_CONTAINER,
    error = MD_THEME_DARK_ERROR,
    onError = MD_THEME_DARK_ON_ERROR,
    errorContainer = MD_THEME_DARK_ERROR_CONTAINER,
    onErrorContainer = MD_THEME_DARK_ON_ERROR_CONTAINER,
    outline = MD_THEME_DARK_OUTLINE,
    background = MD_THEME_DARK_BACKGROUND,
    onBackground = MD_THEME_DARK_ON_BACKGROUND,
    surface = MD_THEME_DARK_SURFACE,
    onSurface = MD_THEME_DARK_ON_SURFACE,
    surfaceVariant = MD_THEME_DARK_SURFACE_VARIANT,
    onSurfaceVariant = MD_THEME_DARK_ON_SURFACE_VARIANT,
    inverseSurface = MD_THEME_DARK_INVERSE_SURFACE,
    inverseOnSurface = MD_THEME_DARK_INVERSE_ON_SURFACE,
    inversePrimary = MD_THEME_DARK_INVERSE_PRIMARY,
    surfaceTint = MD_THEME_DARK_SURFACE_TINT,
    outlineVariant = MD_THEME_DARK_OUTLINE_VARIANT,
    scrim = MD_THEME_DARK_SCRIM,
)
