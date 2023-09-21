package com.seallook.androidx.share

@JvmInline
value class TypeOption(val value: UserType)

@JvmInline
value class GenderOption(val value: Gender)

enum class UserType {
    CLIENT, COUNSELOR, OFFICE,
}

enum class Gender {
    MALE, FEMALE,
}
