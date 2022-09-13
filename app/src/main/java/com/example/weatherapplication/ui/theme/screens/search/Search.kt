package com.example.weatherapplication.ui.theme.screens

import android.util.Log
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapplication.ui.theme.di.DataOrException
import com.example.weatherapplication.ui.theme.modelAutoComplete.Search
import com.example.weatherapplication.ui.theme.navigation.Screens
import com.example.weatherapplication.ui.theme.screens.search.SearchViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun searchScreen(navController: NavController, searchViewModel: SearchViewModel,){
    //val searchViewModel = hiltViewModel<SearchViewModel>()
    val searchstate = remember {
        mutableStateOf("")
    }

    val auto = remember {
        mutableStateOf(false)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            backgroundColor = Color(0xF8B6EBE4),
            elevation = 2.dp,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back arrow",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }, backgroundColor = Color(0xF8B6EBE4)) {

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

            outlinedtextfield(searchViewModel,searchstate,navController ,auto)

        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun outlinedtextfield(
    searchViewModel: SearchViewModel,
    searchstate: MutableState<String>,
    navController: NavController,
    auto: MutableState<Boolean>,
) {


    val keyboardController = LocalSoftwareKeyboardController.current
    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            Modifier
                .padding(vertical = 30.dp, horizontal = 5.dp)
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            color = Color.Transparent
        ) {


            OutlinedTextField(
                value = searchstate.value,
                onValueChange = {
                    searchstate.value = it


                }, modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(CornerSize(5.dp))),
                enabled = true,
                label = {
                    Text(text = "Enter City", color = Color.Black)
                },
                keyboardActions = KeyboardActions {
                    val city = searchstate.value
                    keyboardController?.hide()
                    navController.popBackStack()
                    navController.navigate(Screens.MainScreen.name + "/$city")
                },
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Go
                ), singleLine = true, colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black
                )


            )


        }
    }
}

//
//@Composable
//fun Recommend(searchViewModel: SearchViewModel, text: MutableState<String>){
//    val citydata = produceState<DataOrException<Search, Boolean, Exception>>(
//        initialValue = DataOrException(loading = true)
//    ) {
//        value = searchViewModel.getCityName(city = text.value)
//    }.value
//
//
//    Log.d("TAGAPI",citydata.data.toString())
//
//
//
//
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(15.dp)
//            .height(350.dp),
//        shape = RoundedCornerShape(corner = CornerSize(8.dp))
//    , color = Color.LightGray) {
//        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
//            if (citydata.loading == true) {
//                CircularProgressIndicator(modifier = Modifier.size(50.dp))
//            }else if (citydata.data != null) {
//
//                LazyColumn(modifier = Modifier
//                    .height(300.dp)
//                    .fillMaxWidth()){
//                    items(citydata.data!!.Result){
//                        card(cityName = it,text)
//                    }
//                }
//            }
//
//
//        }
//    }
//}
//
//
//@Composable
//fun card(cityName: String, text: MutableState<String>) {
//    Card(modifier = Modifier
//        .fillMaxWidth()
//        .padding(5.dp)
//        .height(50.dp)
//        .clickable {
//            text.value = cityName.split(",")[0].split(" ")[0]
//        }
//        , backgroundColor = Color.Transparent, border = BorderStroke(1.dp,Color.Transparent), elevation = 0.dp
//
//    ) {
//
//        Text(
//            text = cityName ,
//            style = MaterialTheme.typography.h6,
//            color = Color.Black,
//            modifier = Modifier.padding(10.dp)
//        )
//    }
//}