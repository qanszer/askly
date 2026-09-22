package com.example.askly.models

data class ProfessorModel (
    val profId: Int,
    val profName: String,
    val classroomIds: List<Int>
)