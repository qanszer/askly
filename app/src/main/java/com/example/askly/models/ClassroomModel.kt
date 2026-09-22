package com.example.askly.models

data class ClassroomModel (
    val classId: Int,
    val joinCode: String,
    val className: String,
    val profId: Int,
    val isActive: Boolean
)