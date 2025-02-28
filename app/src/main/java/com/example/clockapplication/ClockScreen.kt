package com.example.clockapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import java.time.LocalTime

@Composable
fun ClockScreen(navHostController: NavHostController){
    Column (Modifier.fillMaxSize().background(Color(0xFF0E0E0E))){
        Box(
            Modifier.weight(9f).fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Clock()
        }
        Box(
            modifier = Modifier.fillMaxSize().weight(1f).padding(end = 8.dp, start = 8.dp, bottom = 16.dp).clip(
                RoundedCornerShape(CornerSize(60.dp))
            ),
            contentAlignment = Alignment.BottomCenter
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ){
                IconButton(
                    onClick = {},
                    Modifier.weight(1f)
                ) {
                    Box(Modifier.clip(CircleShape)) {
                        Icon(
                            painter = painterResource(id = R.drawable.schedule_24px,),
                            "Clock",
                            Modifier.background(Color.Cyan).clip(
                                CircleShape
                            ).padding(8.dp)
                        )
                    }
                }

                //this is the comment to show the paragraph between two sections
                IconButton(
                    onClick = {
                        navHostController.navigate("timer_screen")
                    },
                    Modifier.weight(1f)
                ) {

                    Box(Modifier.clip(CircleShape)) {
                        Icon(
                            painter = painterResource(id = R.drawable.schedule_24px,),
                            "Clock",
                            Modifier.background(Color.White).clip(
                                CircleShape
                            ).padding(8.dp)
                        )
                    }
                }
                IconButton(
                    onClick = {navHostController.navigate("stopwatch_screen")},
                    Modifier.weight(1f)
                ) {
                    Box(Modifier.clip(CircleShape)) {
                        Icon(
                            painter = painterResource(id = R.drawable.time_auto_24px,),
                            "Clock",
                            Modifier.background(Color.White).clip(
                                CircleShape
                            ).padding(8.dp)
                        )
                    }
                }

            }
        }
    }
}


@Composable
fun Clock() {
    var clockHour by remember { mutableStateOf(LocalTime.now().hour) }
    var clockMinute by remember { mutableStateOf(LocalTime.now().minute) }
    var clockSecond by remember { mutableStateOf(LocalTime.now().second) }


    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            val currentTime = LocalTime.now()
            clockHour = currentTime.hour
            clockMinute = currentTime.minute
            clockSecond = currentTime.second
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Text(
            text = clockHour.toString().padStart(2, '0'),
            color = Color.Cyan,
            fontSize =40.sp,
            fontWeight = FontWeight.Bold
        )
        Text(" : ",color = Color.White,
            fontSize = 40.sp,fontWeight = FontWeight.Bold)
        Text(
            text = clockMinute.toString().padStart(2, '0'),
            color = Color.White,
            fontSize = 40.sp,fontWeight = FontWeight.Bold
        )
        Text(" : ",color = Color.White,
            fontSize = 40.sp,fontWeight = FontWeight.Bold)
        Text(
            text = clockSecond.toString().padStart(2, '0'),
            color = Color.White,
            fontSize = 40.sp,fontWeight = FontWeight.Bold
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewFunction(){
    Clock()
}