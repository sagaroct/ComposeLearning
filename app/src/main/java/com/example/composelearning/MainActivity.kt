package com.example.composelearning

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composelearning.House.Builder
import com.example.composelearning.ui.MainUi
import com.example.composelearning.ui.models.Message
import com.example.composelearning.ui.models.SampleData
import com.example.composelearning.ui.theme.ComposeLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    MainUi().Greeting("Android")
//                    MainUi().MessageCard(Message("Sagar", "Going for dandiya today"))
                    MainUi().Conversation(messages = SampleData.conversationSample)
                }
            }
        }
    }

}
