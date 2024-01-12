package com.seallook.androidx.share

@JvmInline
value class TypeOption(val value: UserTypeOption)

@JvmInline
value class GenderOption(val value: Gender)

enum class UserTypeOption {
    CLIENT, COUNSELOR, OFFICE,
}

enum class Gender {
    MALE, FEMALE, NONE,
}

enum class DetailTextType {
    DATE, TIME, OFFICE, PRICE,
}
