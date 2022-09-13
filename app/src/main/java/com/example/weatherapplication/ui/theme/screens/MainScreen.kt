package com.example.weatherapplication.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapplication.R
import com.example.weatherapplication.ui.theme.application.MainViewModel
import com.example.weatherapplication.ui.theme.di.DataOrException
import com.example.weatherapplication.ui.theme.models.Weather
import com.example.weatherapplication.ui.theme.navigation.Screens
import com.example.weatherapplication.ui.theme.utils.formatDateTime


@Composable
fun mainScreen(navController: NavController, mainViewModel: MainViewModel, city: String?) {

    val currentcity  : String = if(city!!.isBlank())"pune" else city

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = mainViewModel.getWeatherData(currentcity)
    }.value

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = Color.Transparent


        ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                if (weatherData.loading == true) {
                    CircularProgressIndicator(modifier = Modifier.size(100.dp))
                }else if (weatherData.data != null) {
                    design(navController = navController, weatherData = weatherData)
                    lowerdesign(mainViewModel,weatherData)
                }


            }

        }
    }


@Composable
fun design(navController: NavController, weatherData: DataOrException<Weather, Boolean, Exception>) {

       Box(modifier = Modifier
           .fillMaxWidth()
           .height(600.dp)
           .clip(RoundedCornerShape(corner = CornerSize(10.dp)))) {

//
               Image(
                   painter = painterResource(id =
                       R.drawable.cloudy),
                   contentDescription = "cloudy",
                   contentScale = ContentScale.FillBounds
               )
           Row(
               modifier = Modifier
                   .padding(20.dp)
                   .fillMaxWidth(),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.End
           ) {
               IconButton(onClick = {
                    navController.popBackStack()
                   navController.navigate(Screens.SearchScreen.name)
               }) {
                   Icon(
                       imageVector = Icons.Default.Search,
                       contentDescription = "search",
                       modifier = Modifier.size(25.dp),
                       tint = Color.White
                   )
               }
           }
           Column(
               modifier = Modifier
                   .padding(5.dp)
                   .fillMaxSize()
                   .padding(top = 80.dp),
              verticalArrangement = Arrangement.Top,
               horizontalAlignment = Alignment.CenterHorizontally
           ) {

               Text(
                   text = weatherData.data!!.weather[0].main,
                   style = MaterialTheme.typography.h4,
                   color = Color.White,
                   fontWeight = FontWeight.ExtraBold
               )

               Text(
                   text = weatherData.data!!.name,
                   style = MaterialTheme.typography.h2,
                   modifier = Modifier.padding(top = 7.dp),
                   color = Color.White,
                   fontWeight = FontWeight.ExtraBold
               )

               val temp  = ( weatherData.data!!.main.temp - 273.15).toInt()

               Text(
                   text =" ${temp}°",
                   style = MaterialTheme.typography.h4,
                   modifier = Modifier.padding(top = 20.dp),
                   color = Color.White,
                   fontWeight = FontWeight.ExtraBold,
                   fontSize = 130.sp
               )

               Spacer(modifier = Modifier.height(17.dp))

//               Row(
//                   modifier = Modifier
//                       .fillMaxWidth()
//                       .padding(horizontal = 20.dp),
//                   verticalAlignment = Alignment.CenterVertically,
//                   horizontalArrangement = Arrangement.SpaceBetween
//               ) {
//                   val tempamax  = (weatherData.data!!.main.temp_max - 273.15-1).toInt()
//                   val tempamin  = (weatherData.data!!.main.temp_min - 273.15+1).toInt()
//
//                   tempmaxmin(text ="min", value = tempamin.toString(), units = "°" )
//                   tempmaxmin(text ="max", value = tempamax.toString(), units = "°" )
//
//               }
           }
       }


}

@Composable
fun lowerdesign(
    mainViewModel: MainViewModel,
    weatherData: DataOrException<Weather, Boolean, Exception>
) {


    Card(modifier = Modifier
        .padding(top = 10.dp)
        .fillMaxSize(), shape = RoundedCornerShape(CornerSize(12.dp)), elevation = 10.dp, backgroundColor = Color(
        0xF80F0346
    )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            attribute(
                image = painterResource(id = R.drawable.wi_humidity),
                text = "humidity",
                value = weatherData.data!!.main.humidity.toString(),
                units = "%"
            )
            attribute(
                image = painterResource(id = R.drawable.wi_sunrise),
                text = "sunrise",
                value = formatDateTime(weatherData.data!!.sys.sunrise),
                units = ""
            )
            attribute(
                image = painterResource(id = R.drawable.wi_sunset),
                text = "sunset",
                value =  formatDateTime(weatherData.data!!.sys.sunset),
                units = ""
            )
            attribute(
                image = painterResource(id = R.drawable.wi_windy),
                text = "wind",
                value =  weatherData.data!!.wind.speed.toString(),
                units = "mph"
            )

        }
    }
}

@Composable
fun attribute(image : Painter, text : String, value : String, units : String){
    Column(
        modifier = Modifier.padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = value + units,
            style = MaterialTheme.typography.subtitle1,
            color = Color.White,
            fontWeight = FontWeight.Light
        )
        Image(
            painter = image, contentDescription = "image", modifier = Modifier
                .padding(top = 15.dp)
                .size(50.dp), colorFilter = ColorFilter.tint(color = Color.White)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(top = 5.dp),
            color = Color.White,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun tempmaxmin(text : String, value : String, units : String){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = value + units,
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp,
            textAlign = TextAlign.Right

        )

        Text(
            text = text,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(top = 2.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Left
        )
    }
}