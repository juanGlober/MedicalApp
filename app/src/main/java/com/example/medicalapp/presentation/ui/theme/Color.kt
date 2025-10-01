package com.example.medicalapp.presentation.ui.theme

import androidx.compose.ui.graphics.Color

// Primary Colors - Medical Blue
val PrimaryBlue = Color(0xFF4B7BFF)
val PrimaryBlueLight = Color(0xFF7FA3FF)
val PrimaryBlueDark = Color(0xFF1E4FCC)
val PrimaryBlueContainer = Color(0xFFE3ECFF)
val OnPrimaryBlueContainer = Color(0xFF001B3E)

// Secondary Colors - Teal/Green for Medical
val SecondaryTeal = Color(0xFF00BFA5)
val SecondaryTealLight = Color(0xFF5DF2D6)
val SecondaryTealDark = Color(0xFF008E76)
val SecondaryTealContainer = Color(0xFFB2F2E1)
val OnSecondaryTealContainer = Color(0xFF00201A)

// Tertiary Colors - Soft Purple
val TertiaryPurple = Color(0xFF7C4DFF)
val TertiaryPurpleLight = Color(0xFFB47CFF)
val TertiaryPurpleDark = Color(0xFF3700B3)
val TertiaryPurpleContainer = Color(0xFFE7DDFF)
val OnTertiaryPurpleContainer = Color(0xFF21005D)

// Error Colors
val ErrorRed = Color(0xFFDC3545)
val ErrorRedDark = Color(0xFFB02A37)
val ErrorContainer = Color(0xFFFFDAD6)
val OnErrorContainer = Color(0xFF410002)

// Success Colors
val SuccessGreen = Color(0xFF28A745)
val SuccessGreenDark = Color(0xFF1E7E34)
val SuccessContainer = Color(0xFFD4EDDA)
val OnSuccessContainer = Color(0xFF0A3622)

// Warning Colors
val WarningYellow = Color(0xFFFFC107)
val WarningYellowDark = Color(0xFFE0A800)
val WarningContainer = Color(0xFFFFF3CD)
val OnWarningContainer = Color(0xFF3B2F00)

// Neutral Colors - Grays
val Gray50 = Color(0xFFFAFAFA)
val Gray100 = Color(0xFFF5F5F5)
val Gray200 = Color(0xFFEEEEEE)
val Gray300 = Color(0xFFE0E0E0)
val Gray400 = Color(0xFFBDBDBD)
val Gray500 = Color(0xFF9E9E9E)
val Gray600 = Color(0xFF757575)
val Gray700 = Color(0xFF616161)
val Gray800 = Color(0xFF424242)
val Gray900 = Color(0xFF212121)

// Background Colors
val BackgroundLight = Color(0xFFFDFDFF)
val BackgroundDark = Color(0xFF1A1C1E)
val SurfaceLight = Color(0xFFFFFFFF)
val SurfaceDark = Color(0xFF1A1C1E)
val SurfaceVariantLight = Color(0xFFF3F4F6)
val SurfaceVariantDark = Color(0xFF2B2D30)

// Light Theme Colors
val md_theme_light_primary = PrimaryBlue
val md_theme_light_onPrimary = Color.White
val md_theme_light_primaryContainer = PrimaryBlueContainer
val md_theme_light_onPrimaryContainer = OnPrimaryBlueContainer
val md_theme_light_secondary = SecondaryTeal
val md_theme_light_onSecondary = Color.White
val md_theme_light_secondaryContainer = SecondaryTealContainer
val md_theme_light_onSecondaryContainer = OnSecondaryTealContainer
val md_theme_light_tertiary = TertiaryPurple
val md_theme_light_onTertiary = Color.White
val md_theme_light_tertiaryContainer = TertiaryPurpleContainer
val md_theme_light_onTertiaryContainer = OnTertiaryPurpleContainer
val md_theme_light_error = ErrorRed
val md_theme_light_errorContainer = ErrorContainer
val md_theme_light_onError = Color.White
val md_theme_light_onErrorContainer = OnErrorContainer
val md_theme_light_background = BackgroundLight
val md_theme_light_onBackground = Gray900
val md_theme_light_surface = SurfaceLight
val md_theme_light_onSurface = Gray900
val md_theme_light_surfaceVariant = SurfaceVariantLight
val md_theme_light_onSurfaceVariant = Gray700
val md_theme_light_outline = Gray400
val md_theme_light_inverseOnSurface = Gray50
val md_theme_light_inverseSurface = Gray900
val md_theme_light_inversePrimary = PrimaryBlueLight
val md_theme_light_surfaceTint = PrimaryBlue
val md_theme_light_outlineVariant = Gray300
val md_theme_light_scrim = Color.Black

// Dark Theme Colors
val md_theme_dark_primary = PrimaryBlueLight
val md_theme_dark_onPrimary = OnPrimaryBlueContainer
val md_theme_dark_primaryContainer = PrimaryBlueDark
val md_theme_dark_onPrimaryContainer = PrimaryBlueContainer
val md_theme_dark_secondary = SecondaryTealLight
val md_theme_dark_onSecondary = OnSecondaryTealContainer
val md_theme_dark_secondaryContainer = SecondaryTealDark
val md_theme_dark_onSecondaryContainer = SecondaryTealContainer
val md_theme_dark_tertiary = TertiaryPurpleLight
val md_theme_dark_onTertiary = OnTertiaryPurpleContainer
val md_theme_dark_tertiaryContainer = TertiaryPurpleDark
val md_theme_dark_onTertiaryContainer = TertiaryPurpleContainer
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = ErrorRedDark
val md_theme_dark_onError = OnErrorContainer
val md_theme_dark_onErrorContainer = ErrorContainer
val md_theme_dark_background = BackgroundDark
val md_theme_dark_onBackground = Gray100
val md_theme_dark_surface = SurfaceDark
val md_theme_dark_onSurface = Gray100
val md_theme_dark_surfaceVariant = SurfaceVariantDark
val md_theme_dark_onSurfaceVariant = Gray300
val md_theme_dark_outline = Gray600
val md_theme_dark_inverseOnSurface = Gray900
val md_theme_dark_inverseSurface = Gray100
val md_theme_dark_inversePrimary = PrimaryBlue
val md_theme_dark_surfaceTint = PrimaryBlueLight
val md_theme_dark_outlineVariant = Gray700
val md_theme_dark_scrim = Color.Black

// Custom Colors for Medical App
object MedicalColors {
    // Status Colors
    val ActiveStatus = SuccessGreen
    val InactiveStatus = Gray400
    val PendingStatus = WarningYellow
    val CriticalStatus = ErrorRed

    // Appointment Types
    val ConsultationColor = PrimaryBlue
    val TreatmentColor = SecondaryTeal
    val FollowUpColor = TertiaryPurple
    val EmergencyColor = ErrorRed

    // Card Backgrounds
    val CardBackgroundLight = Color.White
    val CardBackgroundDark = Color(0xFF2D2D30)

    // Gradient Colors
    val GradientStart = PrimaryBlue
    val GradientEnd = PrimaryBlueLight

    // Text Colors
    val TextPrimary = Gray900
    val TextSecondary = Gray600
    val TextDisabled = Gray400
    val TextPrimaryDark = Gray100
    val TextSecondaryDark = Gray400
    val TextDisabledDark = Gray600
}

