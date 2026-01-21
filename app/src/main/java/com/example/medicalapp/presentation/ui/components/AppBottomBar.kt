package com.example.medicalapp.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalHospital
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.medicalapp.R

enum class AppBottomDestination {
    HOME,
    DOCTORS,
    PROFILE,
    CALENDAR
}

@Composable
fun AppBottomBar(
    selectedDestination: AppBottomDestination,
    onHomeClick: () -> Unit,
    onDoctorsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onCalendarClick: () -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        NavigationBarItem(
            selected = selectedDestination == AppBottomDestination.HOME,
            onClick = onHomeClick,
            icon = {
                Icon(
                    imageVector = if (selectedDestination == AppBottomDestination.HOME) {
                        Icons.Filled.Home
                    } else {
                        Icons.Outlined.Home
                    },
                    contentDescription = stringResource(R.string.home)
                )
            },
            label = { Text(stringResource(R.string.home)) }
        )

        NavigationBarItem(
            selected = selectedDestination == AppBottomDestination.DOCTORS,
            onClick = onDoctorsClick,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.LocalHospital,
                    contentDescription = stringResource(R.string.doctors)
                )
            },
            label = { Text(stringResource(R.string.doctors)) }
        )

        NavigationBarItem(
            selected = selectedDestination == AppBottomDestination.PROFILE,
            onClick = onProfileClick,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = stringResource(R.string.profile)
                )
            },
            label = { Text(stringResource(R.string.profile)) }
        )

        NavigationBarItem(
            selected = selectedDestination == AppBottomDestination.CALENDAR,
            onClick = onCalendarClick,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = stringResource(R.string.calendar)
                )
            },
            label = { Text(stringResource(R.string.calendar)) }
        )
    }
}
