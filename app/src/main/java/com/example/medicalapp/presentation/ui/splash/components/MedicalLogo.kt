package com.example.medicalapp.presentation.ui.splash.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill

@Composable
fun MedicalLogo(
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {
    Canvas(modifier = modifier) {
        val crossWidth = size.width * 0.3f
        val crossHeight = size.height * 0.8f
        val cornerRadius = size.width * 0.1f

        // Draw medical cross
        drawMedicalCross(
            color = color,
            crossWidth = crossWidth,
            crossHeight = crossHeight,
            cornerRadius = cornerRadius
        )

        // Draw decorative leaves
        drawLeaves(
            color = color,
            centerX = size.width / 2,
            centerY = size.height / 2
        )
    }
}

private fun DrawScope.drawMedicalCross(
    color: Color,
    crossWidth: Float,
    crossHeight: Float,
    cornerRadius: Float
) {
    val horizontalLeft = (size.width - crossHeight) / 2
    val horizontalTop = (size.height - crossWidth) / 2
    val verticalLeft = (size.width - crossWidth) / 2
    val verticalTop = (size.height - crossHeight) / 2

    // Horizontal part of cross
    drawRoundRect(
        color = color,
        topLeft = Offset(horizontalLeft, horizontalTop),
        size = Size(crossHeight, crossWidth),
        cornerRadius = CornerRadius(cornerRadius, cornerRadius)
    )

    // Vertical part of cross
    drawRoundRect(
        color = color,
        topLeft = Offset(verticalLeft, verticalTop),
        size = Size(crossWidth, crossHeight),
        cornerRadius = CornerRadius(cornerRadius, cornerRadius)
    )
}

private fun DrawScope.drawLeaves(
    color: Color,
    centerX: Float,
    centerY: Float
) {
    val leafPath = Path().apply {
        // Top-left leaf
        moveTo(centerX - size.width * 0.25f, centerY - size.height * 0.15f)
        quadraticBezierTo(
            centerX - size.width * 0.35f, centerY - size.height * 0.25f,
            centerX - size.width * 0.25f, centerY - size.height * 0.35f
        )
        quadraticBezierTo(
            centerX - size.width * 0.15f, centerY - size.height * 0.25f,
            centerX - size.width * 0.25f, centerY - size.height * 0.15f
        )

        // Top-right leaf
        moveTo(centerX + size.width * 0.15f, centerY - size.height * 0.25f)
        quadraticBezierTo(
            centerX + size.width * 0.25f, centerY - size.height * 0.35f,
            centerX + size.width * 0.35f, centerY - size.height * 0.25f
        )
        quadraticBezierTo(
            centerX + size.width * 0.25f, centerY - size.height * 0.15f,
            centerX + size.width * 0.15f, centerY - size.height * 0.25f
        )
    }

    drawPath(
        path = leafPath,
        color = color,
        style = Fill
    )
}