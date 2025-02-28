package com.example.clockapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TimeWheel(range: IntRange, selectedValue: Int, onValueChange: (Int) -> Unit){
    Row(
        modifier = Modifier
            .size(80.dp,150.dp)
            .background(Color(0xff191e09), RoundedCornerShape(10.dp))
    ){
        LazyColumn {
            itemsIndexed(range.toList()){_, item ->
                Text(
                    text = item.toString().padStart(2, '0'),
                    fontSize = 32.sp,
                    color = if(item == selectedValue) Color.Cyan else Color.Gray,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { onValueChange(item) }
                )

            }
        }
    }
}

@Composable
fun WheelTimePicker(){
    var selectedHours by remember { mutableStateOf(0) }
    var selectedMinutes by remember { mutableStateOf(0) }
    var selectedSeconds by remember { mutableStateOf(0) }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        TimeWheel(0..23,selectedHours) {selectedHours = it}
        Box{
        Spacer(Modifier.height(16.dp))
        Text(":", fontSize = 40.sp, color = Color.White)}
        TimeWheel(0..59,selectedMinutes) {selectedMinutes = it}
        Box{
            Spacer(Modifier.height(16.dp))
            Text(":", fontSize = 40.sp, color = Color.White)}
        TimeWheel(0..59,selectedSeconds) {selectedSeconds = it}

    }
}



@Composable
fun TimerScreen(navHostController: NavHostController){
    Column (Modifier.fillMaxSize().background(Color(0xFF0E0E0E))){
        Box(
            Modifier.weight(9f).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            WheelTimePicker()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(end = 8.dp, start = 8.dp, bottom = 16.dp).clip(
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
                    onClick = {navHostController.navigate("clock_screen")},
                    Modifier.weight(1f)
                ) {

                    Box(Modifier.clip(CircleShape)) {
                        Icon(
                            painter = painterResource(id = R.drawable.schedule_24px,),
                            "Clock",
                            Modifier.background(Color.White).clip(
                                CircleShape
                            ).padding(8.dp)
                        )}
                }
                IconButton(
                    onClick = {},
                    Modifier.weight(1f)
                ) {
                    Box(Modifier.clip(CircleShape)) {

                    Icon(painter = painterResource(id = R.drawable.timer_24px,),"Clock",Modifier.background(Color.Cyan).clip(
                        CircleShape
                    ).padding(8.dp))
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
@Preview(showBackground = true)
fun TimerScreenPreview(){
    //TimerScreen()
}