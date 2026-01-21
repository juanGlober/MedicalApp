package com.example.medicalapp.presentation.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.medicalapp.R
import com.example.medicalapp.presentation.ui.splash.components.MedicalLogo
import com.example.medicalapp.presentation.ui.theme.PrimaryBlue

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }
    val isReady by viewModel.isReady.collectAsState()

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = SPLASH_ANIMATION_DURATION_MS,
                delayMillis = SPLASH_ANIMATION_DELAY_MS
            )
        )
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = SPLASH_ANIMATION_DURATION_MS,
                delayMillis = SPLASH_ANIMATION_DELAY_MS
            )
        )
    }

    LaunchedEffect(isReady) {
        if (isReady) {
            onNavigateToLogin()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBlue),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .scale(scale.value)
                .alpha(alpha.value)
        ) {
            MedicalLogo(
                modifier = Modifier.size(LOGO_SIZE_DP.dp)
            )

            Spacer(modifier = Modifier.height(SPACING_LARGE_DP.dp))

            Text(
                text = stringResource(R.string.app_brand_name),
                fontSize = APP_NAME_FONT_SIZE_SP.sp,
                fontWeight = FontWeight.Light,
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = APP_NAME_LINE_HEIGHT_SP.sp
            )

            Spacer(modifier = Modifier.height(SPACING_MEDIUM_DP.dp))

            Text(
                text = stringResource(R.string.app_brand_tagline),
                fontSize = TAGLINE_FONT_SIZE_SP.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center
            )
        }
    }
}

private const val SPLASH_ANIMATION_DURATION_MS = 800
private const val SPLASH_ANIMATION_DELAY_MS = 100
private const val LOGO_SIZE_DP = 120
private const val SPACING_LARGE_DP = 24
private const val SPACING_MEDIUM_DP = 16
private const val APP_NAME_FONT_SIZE_SP = 48
private const val APP_NAME_LINE_HEIGHT_SP = 52
private const val TAGLINE_FONT_SIZE_SP = 16
