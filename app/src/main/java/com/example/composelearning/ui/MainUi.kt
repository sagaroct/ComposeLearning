package com.example.composelearning.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelearning.R
import com.example.composelearning.ui.models.Message
import com.example.composelearning.ui.models.SampleData
import com.example.composelearning.ui.theme.ComposeLearningTheme

/**
 * Created by Sagar Pujari on 02/10/22.
 */
class MainUi {

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Composable
    fun MessageCard(message: Message, checkedState: MutableState<Boolean>) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.sagar),
                "Profile pic",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            var isExpanded by rememberSaveable { mutableStateOf(false) }
//            val isImportant by rememberSaveable { mutableStateOf(message.important) }
            // surfaceColor will be updated gradually from one color to the other
            val surfaceColor by animateColorAsState(
                if (checkedState.value) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            )
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(text = message.author)
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    // surfaceColor color will be changing gradually from primary to surface
                    color = surfaceColor,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(
                        text = message.body,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1
                    )
                }
            }

        }
    }

    @Composable
    fun Conversation(messages: List<Message>){
        Column(Modifier.fillMaxSize()) {
            val checkedState = remember { mutableStateOf(false) }
            SelectAllCheckBox(checkedState, onCheckedChanged = { checkedState.value = it })
        LazyColumn{
            items(messages){ message ->
                MessageCard(message, checkedState)
            }
        }
        }
    }

    @Composable
    fun SelectAllCheckBox(checkedState: MutableState<Boolean>, onCheckedChanged:(Boolean) -> Unit){
        Row(modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Select All")
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { onCheckedChanged.invoke(it) },
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ComposeLearningTheme {
            Greeting("Android")
        }
    }

    @Preview
    @Composable
    fun PreviewConversation(){
        ComposeLearningTheme {
            Conversation(SampleData.conversationSample)
        }
    }

}