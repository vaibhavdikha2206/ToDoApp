package com.example.todoapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.todoapp.models.ToDoItem

// outlives lifecycle of a screen
class MainActivityViewModel(val str: String) : ViewModel() {

    fun test(){
        println("Coming From VM "+str)
    }

    var listOfItems = mutableStateListOf<ToDoItem>(

    )
        private set


    fun addToListOfItems(toDoItem: ToDoItem){
        listOfItems.add(toDoItem)
    }
}