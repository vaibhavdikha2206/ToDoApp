package com.example.todoapp.models

data class ToDoItem(
    val itemTitle: String,
    val deleteButtonVisible : Boolean = true
)