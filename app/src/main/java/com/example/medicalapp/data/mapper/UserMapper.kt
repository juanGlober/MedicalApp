package com.example.medicalapp.data.mapper

import com.example.medicalapp.data.local.entities.UserEntity
import com.example.medicalapp.domain.model.User

fun UserEntity.toUser(): User {
    return User(
        id = id,
        name = name,
        email = email,
        displayName = displayName,
        photoUrl = photoUrl,
        isEmailVerified = isEmailVerified
    )
}

fun User.toUserEntity(password: String): UserEntity {
    return UserEntity(
        id = id,
        email = email,
        password = password,
        name = name,
        displayName = displayName,
        photoUrl = photoUrl,
        isEmailVerified = isEmailVerified
    )
}