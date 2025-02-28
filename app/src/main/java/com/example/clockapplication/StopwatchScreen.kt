package com.example.clockapplication
import java.time.Duration
import java.time.LocalTime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun StopwatchScreen(navHostController: NavHostController) {
    Column(Modifier.fillMaxSize().background(Color(0xFF0E0E0E))) {
        Box(
            Modifier.weight(9f),
            contentAlignment = Alignment.Center
        ) {
            Stopwatch()
        }
        Box(
            modifier = Modifier.fillMaxSize().weight(1f)
                .padding(end = 8.dp, start = 8.dp, bottom = 16.dp).clip(
                RoundedCornerShape(CornerSize(60.dp))
            ),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { navHostController.navigate("clock_screen") },
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
                    onClick = { navHostController.navigate("timer_screen") },
                    Modifier.weight(1f)
                ) {


                    Box(Modifier.clip(CircleShape)) {
                        Icon(
                            painter = painterResource(id = R.drawable.timer_24px,),
                            "Clock",
                            Modifier.background(Color.White).clip(
                                CircleShape
                            ).padding(8.dp)
                        )
                    }
                }
                IconButton(
                    onClick = {},
                    Modifier.weight(1f)
                ) {
                    Box(Modifier.clip(CircleShape)) {
                        Icon(
                            painter = painterResource(id = R.drawable.time_auto_24px),
                            "Clock", Modifier.background(Color.Cyan).clip(
                                CircleShape
                            ).padding(8.dp)
                        )
                    }
                }

            }
        }
    }
}







data class flagd(var tstrtime:Duration,var tendtime:Duration)
@Composable
fun Stopwatch() {
    Column {
        Spacer(Modifier.height(20.dp))

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Red, fontSize = 55.sp)) {
                    append("S") // Convert char to String
                }
                withStyle(
                    style = SpanStyle(
                        color =
                        MaterialTheme.colorScheme.onBackground,
                        fontSize = 50.sp
                    )
                ) {
                    append("topwatch") // Append the rest of the (topwatch)
                }
            }
        )

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var isPlaying by remember { mutableStateOf(false) }
            var flag by remember { mutableStateOf(false) }
            val distance by remember {
                derivedStateOf { // Use derivedStateOf for dynamic calculation
                    if (!flag) {
                        (300)
                    } else {
                        (50)
                    }
                }
            }
            var timelist by remember { mutableStateOf(listOf<flagd>()) }

            var startTime: LocalTime by remember { mutableStateOf(LocalTime.now()) }
            Spacer(Modifier.height(distance.dp))
            var Runningtime by remember { mutableStateOf(LocalTime.MIDNIGHT) }
            val duration = Duration.between(startTime, Runningtime).abs()

            val hours = duration.toHours().toInt()
            val minutes = (duration.toMinutes() % 60).toInt()
            val seconds = (duration.seconds % 60).toInt()

            val nano = (duration.nano / 10_000_000)%100

            if (isPlaying) {
                Text("$hours:$minutes:$seconds.$nano", fontSize = 32.sp)

                startTime=LocalTime.now()
                LaunchedEffect(isPlaying) {
//                        delay(10)
                    Runningtime= LocalTime.now()
                }
                Spacer(Modifier.height((if(flag){250+300}else{250}).dp))

                Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceAround){
                    Button(onClick = {
                        flag=true
//                            timelist.add(flagd(,Runningtime))
                        if(timelist.size==0){
                            var addtime = flagd(Duration.ZERO,duration)
                            timelist = timelist+addtime
                        }
                        else{
//                                timelist.add(flagd(abs(timelist[timelist.size-1].tendtime.minus(duration)),duration))
                            val lastFlagEndTime : Duration= timelist[timelist.size - 1].tendtime
                            val newFlagDuration: Duration = duration.minus(lastFlagEndTime)
                            var addTime = flagd(lastFlagEndTime, lastFlagEndTime.plus(newFlagDuration))
                            timelist = timelist+addTime
                        }
                    }) {
                        Text("flag")
                    }
                    Button(onClick = {isPlaying=!isPlaying}) {
                        Text("Stop")
                    }
                }

            } else {
                Text("00:00:00.00", fontSize = 32.sp)
                Spacer(Modifier.height((if(flag){250+300}else{250}).dp))
                Button(onClick = {isPlaying=!isPlaying}) {
                    Text("Play")
                }
            }

        }

    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun StopwatchPreview(){
//    Stopwatch()
//}


@Composable
@Preview(showBackground = true)
fun StopwatchScreenPreview(){
   // StopwatchScreen()
}