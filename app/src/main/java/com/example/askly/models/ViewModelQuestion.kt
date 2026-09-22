package com.example.askly.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ViewModelQuestion : ViewModel() {
    var questionList by mutableStateOf(
        value = listOf(
            QuestionModel(
                questionId = 1,
                questionText = "Test question?"
            )
        )
    )

    fun addQuestion (questionText: String) {
        val newQuestion = QuestionModel(
            questionId = (questionList.maxOfOrNull { it.questionId } ?: 0) + 1,
            questionText = questionText
        )
        questionList += newQuestion
    }

    fun deleteQuestion (questionId: Int) {
        questionList = questionList.filter {
            it.questionId != questionId
        }
    }

    fun getQuestionById (questionId: Int) : QuestionModel? {
        return questionList.find {
            it.questionId == questionId
        }
    }

    fun updateQuestion (
        questionId: Int,
        questionName: String
    ) {
        questionList = questionList.map {
                questionData ->
            if (questionData.questionId == questionId) {
                questionData.copy (
                    questionId = questionId,
                    questionText = questionName
                )
            } else {
                questionData
            }
        }
    }
}
