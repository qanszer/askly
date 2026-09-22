package com.example.askly.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.askly.models.QuestionModel
import com.example.askly.models.ViewModelQuestion


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentClassScreen (
    navController: NavController,
    questionModel: ViewModelQuestion
) {
    val context = LocalContext.current;

    var questionText by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp),
                        text = "Ask Anonymously",
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {
        paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                border = BorderStroke(width = 1.dp, color = Color.Gray),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Type your question here",
                        textAlign = TextAlign.Left,
                        color = Color.Gray
                    )
                    TextField(
                        value = questionText,
                        onValueChange = { questionText = it },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Gray,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.DarkGray
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                onClick = {
                    if (questionText.isNotBlank()) {
                        questionModel.addQuestion(questionText)
                        questionText = ""
                    }
                }
            ) {
                Text(
                    text = "Submit Question"
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Your questions",
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .width(300.dp),
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(questionModel.questionList) {
                            questionList ->

                        var isEditing by remember(questionList.questionId) {
                            mutableStateOf(false)
                        }
                        var editedText by remember(questionList.questionId) {
                            mutableStateOf(questionList.questionText)
                        }

                        Card (
                            colors = CardDefaults.cardColors (
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            border = BorderStroke(width = 1.dp, color = Color.Gray),
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                if (isEditing) {
                                    TextField(
                                        value = editedText,
                                        onValueChange = { editedText = it },
                                        colors = TextFieldDefaults.colors(
                                            focusedContainerColor = Color.White,
                                            unfocusedContainerColor = Color.White,
                                            focusedTextColor = Color.Black,
                                            unfocusedTextColor = Color.Gray,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            cursorColor = Color.DarkGray
                                        ),
                                        modifier = Modifier.fillMaxWidth()
                                    )

                                    Column (
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .width(IntrinsicSize.Max),
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Button(
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.Transparent,
                                                contentColor = Color.DarkGray
                                            ),
                                            onClick = {
                                                if (editedText.isNotBlank()) {
                                                    questionModel.updateQuestion(
                                                        questionList.questionId,
                                                        editedText
                                                    )
                                                    isEditing = false
                                                }
                                            }
                                        ) {
                                            Text(
                                                text = "Save"
                                            )
                                        }
                                        Button(
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.Transparent,
                                                contentColor = Color.Red
                                            ),
                                            onClick = {
                                                editedText = questionList.questionText
                                                isEditing = false
                                            }
                                        ) {
                                            Text(
                                                text = "Cancel"
                                            )
                                        }
                                    }
                                } else {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = questionList.questionText
                                        )
                                        Column (
                                            modifier = Modifier.width(IntrinsicSize.Max),
                                            verticalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Button(
                                                modifier = Modifier.fillMaxWidth(),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color.Transparent,
                                                    contentColor = Color.DarkGray
                                                ),
                                                onClick = {
                                                    isEditing = true
                                                }
                                            ) {
                                                Text(
                                                    text = "Edit"
                                                )
                                            }
                                            Button(
                                                modifier = Modifier.fillMaxWidth(),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color.Transparent,
                                                    contentColor = Color.Red
                                                ),
                                                onClick = {
                                                    questionModel.deleteQuestion(questionList.questionId)
                                                }
                                            ) {
                                                Text(
                                                    text = "Delete"
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

