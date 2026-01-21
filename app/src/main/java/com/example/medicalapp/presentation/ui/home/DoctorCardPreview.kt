package com.example.medicalapp.presentation.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.medicalapp.R
import com.example.medicalapp.domain.model.Doctor
import com.example.medicalapp.domain.model.Appointment
import com.example.medicalapp.domain.model.AppointmentStatus
import com.example.medicalapp.domain.model.AppointmentType
import com.example.medicalapp.domain.model.User
import com.example.medicalapp.presentation.ui.theme.MedicalAppTheme // Replace with your actual theme package

@Preview(showBackground = true, name = "Doctor Card")
@Composable
fun DoctorCardPreview() {
    MedicalAppTheme {
        DoctorCard(
            doctor = MockData.doctors[0],
            isFavorite = false,
            onFavoriteToggle = {},
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Welcome Section")
@Composable
fun WelcomeSectionPreview() {
    MedicalAppTheme {
        WelcomeSection()
    }
}

@Preview(showBackground = true, name = "Appointment Card")
@Composable
fun AppointmentCardPreview() {
    MedicalAppTheme {
        AppointmentCard(appointment = MockData.upcomingAppointment)
    }
}

@Preview(showBackground = true, name = "Quick Actions")
@Composable
fun QuickActionsPreview() {
    MedicalAppTheme {
        QuickActionsSection(onDoctorsClick = {})
    }
}


@Preview(showSystemUi = true, name = "Full Home Screen")
@Composable
fun HomeScreenPreview() {
    MedicalAppTheme {
        Scaffold(
            topBar = {
                HomeTopBar(
                    user = MockData.mockUser,
                    onNotificationClick = {},
                    onSettingsClick = {}
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item { WelcomeSection() }
                item { QuickActionsSection(onDoctorsClick = {}) }
                item { AppointmentCard(appointment = MockData.upcomingAppointment) }
                item { SectionTitle(title = stringResource(R.string.available_doctors), onSeeAllClick = {}) }
                items(MockData.doctors) { doctor ->
                    DoctorCard(
                        doctor = doctor,
                        isFavorite = false,
                        onFavoriteToggle = {},
                        onClick = {}
                    )
                }
            }
        }
    }
}

object MockData {
    val doctors = listOf(
        Doctor(
            id = "1",
            name = "Dr. Sarah Johnson",
            specialty = "Cardiologist",
            rating = 4.8f,
            reviewCount = 124,
            photoUrl = ""
        ),
        Doctor(
            id = "2",
            name = "Dr. Michael Chen",
            specialty = "Dermatologist",
            rating = 4.9f,
            reviewCount = 89,
            photoUrl = ""
        ),
        Doctor(
            id = "3",
            name = "Dr. Elena Rodriguez",
            specialty = "Pediatrician",
            rating = 4.7f,
            reviewCount = 210,
            photoUrl = ""
        )
    )

    val upcomingAppointment = Appointment(
        id = "1",
        doctorName = "Dr. Michael Chen",
        specialty = "Dermatologist",
        time = "10:30 AM",
        date = "2024-05-20",
        doctorId = "2",
        type = AppointmentType.EMERGENCY,
        status = AppointmentStatus.SCHEDULED,
    )

    val mockUser = User(
        id = "1",
        name = "Juan Benavides",
        email = "juan@example.com",
        photoUrl = "",
        displayName = "Juan Benavides",
        isEmailVerified = true
    )
}
