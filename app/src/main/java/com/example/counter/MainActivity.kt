package com.example.counter

import android.content.Context.CAMERA_SERVICE
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.text.style.BackgroundColorSpan

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.counter.ui.theme.CounterTheme
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        var cameraM = getSystemService(CAMERA_SERVICE) as CameraManager
        super.onCreate(savedInstanceState)
        setContent {
            CounterTheme {
               Myapp(cameraM = cameraM)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun Myapp(cameraM: CameraManager){

    val state = remember{
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = Color(0xff2F4F4F)

    ){
        Column(modifier = Modifier.background(color = Color(0xff0a7e8c)),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = { state.value=!state.value
            if (state.value) {
                //Get rear-facing camera ID: 0 by default
                val rearCamera = cameraM.cameraIdList[0]

                cameraM.setTorchMode(rearCamera, true)
            } else {
                val rearCamera = cameraM.cameraIdList[0]

                cameraM.setTorchMode(rearCamera, false)
            }

        }, modifier = Modifier.size(250.dp),colors= ButtonDefaults.buttonColors(backgroundColor = Color.Cyan), shape = CircleShape,) {
            if(!state.value){
                Text(text = "ON", color = Color.Black
                    , fontSize = 25.sp, fontWeight = FontWeight.Bold )
            }else{
                Text(text = "OFF", color = Color.Black
                    , fontSize = 25.sp, fontWeight = FontWeight.Bold )
            }

        }

//        Box(contentAlignment = Alignment.Center) {
//            if(!state.value){
//                Text(text = "ON", color = Color.Black
//                    , fontSize = 25.sp, fontWeight = FontWeight.Bold )
//            }else{
//                Text(text = "OFF", color = Color.Black
//                    , fontSize = 25.sp, fontWeight = FontWeight.Bold )
//            }
//
//         }
        }
        }
    }








