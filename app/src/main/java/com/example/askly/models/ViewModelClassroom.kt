package com.example.askly.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ViewModelClassroom  : ViewModel() {
    var classroomList by mutableStateOf(
        value = listOf(
            ClassroomModel(
                classId = 1,
                joinCode = "ASDF",
                className = "Mobile Development",
                profId = 1,
                isActive = true
            ),
            ClassroomModel(
                classId = 2,
                joinCode = "QWER",
                className = "Nihongo",
                profId = 2,
                isActive = true
            )
        )
    )

}