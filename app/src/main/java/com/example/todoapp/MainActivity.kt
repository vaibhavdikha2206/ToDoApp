package com.example.todoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.models.ToDoItem
import com.example.todoapp.ui.theme.ToDoAppTheme

class MainActivity : ComponentActivity() {

    lateinit var vm : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                vm = viewModel<MainActivityViewModel>(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return MainActivityViewModel(
                                "test"
                            ) as T
                            //return super.create(modelClass)
                        }
                    }
                )
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //CenteredButtonInRow()
                    TopRow()
                    //Greeting("Android")
                    //CenteredButtonInRowInColumn()
                }
            }
        }
    }


    @SuppressLint("UnrememberedMutableState")
    @Preview(showBackground = true)
    @Composable
    fun TopRow(){


        /*var itemsList:ArrayList<ToDoItem> = arrayListOf<ToDoItem>(
            ToDoItem("Title 1"),
            ToDoItem("Title 2"),
            ToDoItem("Title 3")
        )*/

        // VM
        /*var itemsList = remember {
            vm.listOfItems
        }*/

        /*var itemsList =  mutableStateListOf<ToDoItem>(
            ToDoItem("Title 1"),
            ToDoItem("Title 2"),
            ToDoItem("Title 3")
        )*/

        /*var itemsList =  remember {
            mutableStateListOf<ToDoItem>(
                ToDoItem("Title 1"),
                ToDoItem("Title 2"),
                ToDoItem("Title 3")
            )
        }*/

        var itemsList = vm.listOfItems

        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .fillMaxSize(),
                //verticalArrangement = Arrangement.Top
            ) {


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),) {
                    InputSection{ itemTitle ->
                        // VM
                        itemsList.add(ToDoItem(itemTitle))
                        //vm.addToListOfItems(ToDoItem(itemTitle))
                    }

                }

                // write a dynamic population logic based on data that is input
                LazyColumn{
                    items(itemsList) { item ->
                        inputCard(item){
                                itemTitle ->
                            // VM
                            //itemsList.remove(ToDoItem(itemTitle))
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun inputCard(toDoItem: ToDoItem,removeFromList : (String) -> Unit){
        Row(
            modifier = Modifier.fillMaxWidth(), // for its own position wrt to its parent
            horizontalArrangement = Arrangement.SpaceBetween, // for bache
            verticalAlignment = Alignment.CenterVertically // for bache
        ) {

            var textValue by remember {
                mutableStateOf("")
            }
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(3f),
                textAlign = TextAlign.Center,
                text = toDoItem.itemTitle)

            if(toDoItem.deleteButtonVisible) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 10.dp, end = 10.dp),
                    onClick = {
                        // VM
                        //removeFromList.invoke(toDoItem.itemTitle)
                    }) {
                    Text(
                        fontSize = 10.sp,  // Reduce the text size here
                        text = "Delete"
                    )
                }
            }

        }

    }

    @Composable
    fun InputSection(addToList: (String) -> Unit) {

        var fieldValue by remember {
            mutableStateOf("")
        }


        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(3f),
                value = fieldValue,
                onValueChange = { newText ->
                    fieldValue = newText
                }
            )


            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 10.dp, end = 10.dp),
                onClick = {
                    // VM
                    //addToList.invoke(fieldValue)
                    vm.addToListOfItems(ToDoItem(fieldValue))
                    fieldValue =""
                })
            {
                Text(
                    fontSize = 10.sp,  // Reduce the text size here
                    text = "Submit"
                )
            }
        }
    }


}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun GreetingPreview() {
    ToDoAppTheme {
        Greeting("Android")
    }
}

