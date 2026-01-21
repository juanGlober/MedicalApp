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
        val crossWidth = size.width * CROSS_WIDTH_RATIO
        val crossHeight = size.height * CROSS_HEIGHT_RATIO
        val cornerRadius = size.width * CORNER_RADIUS_RATIO

        drawMedicalCross(
            color = color,
            crossWidth = crossWidth,
            crossHeight = crossHeight,
            cornerRadius = cornerRadius
        )

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

    drawRoundRect(
        color = color,
        topLeft = Offset(horizontalLeft, horizontalTop),
        size = Size(crossHeight, crossWidth),
        cornerRadius = CornerRadius(cornerRadius, cornerRadius)
    )

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
        moveTo(
            centerX - size.width * LEAF_OUTER_WIDTH_RATIO,
            centerY - size.height * LEAF_TOP_OFFSET_RATIO
        )
        quadraticBezierTo(
            centerX - size.width * LEAF_OUTER_CONTROL_X_RATIO,
            centerY - size.height * LEAF_OUTER_CONTROL_Y_RATIO,
            centerX - size.width * LEAF_OUTER_WIDTH_RATIO,
            centerY - size.height * LEAF_OUTER_HEIGHT_RATIO
        )
        quadraticBezierTo(
            centerX - size.width * LEAF_INNER_WIDTH_RATIO,
            centerY - size.height * LEAF_OUTER_CONTROL_Y_RATIO,
            centerX - size.width * LEAF_OUTER_WIDTH_RATIO,
            centerY - size.height * LEAF_TOP_OFFSET_RATIO
        )

        moveTo(
            centerX + size.width * LEAF_INNER_WIDTH_RATIO,
            centerY - size.height * LEAF_OUTER_CONTROL_Y_RATIO
        )
        quadraticBezierTo(
            centerX + size.width * LEAF_OUTER_WIDTH_RATIO,
            centerY - size.height * LEAF_OUTER_HEIGHT_RATIO,
            centerX + size.width * LEAF_OUTER_CONTROL_X_RATIO,
            centerY - size.height * LEAF_OUTER_CONTROL_Y_RATIO
        )
        quadraticBezierTo(
            centerX + size.width * LEAF_OUTER_WIDTH_RATIO,
            centerY - size.height * LEAF_TOP_OFFSET_RATIO,
            centerX + size.width * LEAF_INNER_WIDTH_RATIO,
            centerY - size.height * LEAF_OUTER_CONTROL_Y_RATIO
        )
    }

    drawPath(
        path = leafPath,
        color = color,
        style = Fill
    )
}

private const val CROSS_WIDTH_RATIO = 0.3f
private const val CROSS_HEIGHT_RATIO = 0.8f
private const val CORNER_RADIUS_RATIO = 0.1f
private const val LEAF_OUTER_WIDTH_RATIO = 0.25f
private const val LEAF_OUTER_CONTROL_X_RATIO = 0.35f
private const val LEAF_OUTER_CONTROL_Y_RATIO = 0.25f
private const val LEAF_OUTER_HEIGHT_RATIO = 0.35f
private const val LEAF_INNER_WIDTH_RATIO = 0.15f
private const val LEAF_TOP_OFFSET_RATIO = 0.15f
