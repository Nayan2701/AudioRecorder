package com.example.audiorecorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.audiorecorder.playBack.AndroidAudioPlayer
import com.example.audiorecorder.record.AndroidAudioRecorder
import com.example.audiorecorder.ui.theme.AudioRecorderTheme
import java.io.File
import java.util.jar.Manifest

class MainActivity : ComponentActivity() {
    private val recorder by lazy {
        AndroidAudioRecorder(applicationContext)
    }
    private val player by lazy {
        AndroidAudioPlayer(applicationContext)
    }
    private var audiofile: File?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            0
        )
        setContent {
            AudioRecorderTheme {
                // A surface container using the 'background' color from the theme
                Column(modifier = Modifier.fillMaxSize(), 
                    verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = {
                        File(cacheDir,"Audio.Mp3").also {
                            recorder.start(it)
                            audiofile=it
                        }
                    }) {
                        Text(text = "Start Recording")
                        
                    }
                    Button(onClick = {
                       recorder.stop()
                    }) {
                        Text(text = "Stop Recording")

                    }
                    Button(onClick = {
                        player.playFile(audiofile?:return@Button)
                    }) {
                        Text(text = "Start Playing")

                    }
                    Button(onClick = {
                        player.stop()
                    }) {
                        Text(text = "Stop Playing")

                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AudioRecorderTheme {

    }
}