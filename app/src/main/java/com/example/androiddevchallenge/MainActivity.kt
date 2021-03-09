/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewmodel.TimerViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val viewModel by viewModels<TimerViewModel>()
                val timerValue = viewModel.timerValue.collectAsState().value
                val play = viewModel.play.collectAsState().value
                Surface(color = MaterialTheme.colors.background) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                                .clip(CircleShape)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(220.dp)
                                    .background(Color(0xFF121d33))
                            ) { }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                                .clip(CircleShape)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(210.dp)
                                    .background(Color(0xFF6c1964))
                            ) { }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                                .clip(CircleShape)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(200.dp)
                                    .background(Color(0xFF1a2e52))
                            ) { }
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "${getTimerLabel(timerValue)}",
                                style = MaterialTheme.typography.subtitle2,
                                fontSize = 40.sp,
                                color = Color(0xFF47D9E8),
                                modifier = Modifier.padding(0.dp, 15.dp)
                            )

                            Card(
                                modifier = Modifier
                                    .size(48.dp)
                                    .padding(5.dp)
                                    .clickable(onClick = { if (play) viewModel.pause() else viewModel.start() }),
                                shape = CircleShape,
                                elevation = 2.dp,
                                backgroundColor = Color(0xFF101f3a)
                            ) {
                                Image(
                                    painter = if (play) painterResource(id = R.drawable.ic_pause) else painterResource(
                                        R.drawable.ic_play
                                    ),
                                    contentDescription = null
                                )
                            }


                        }
                    }
                }
            }
        }
    }
}

fun getTimerLabel(value: Int): String {
    return "${padding(value / 60)} : ${padding(value % 60)}"
}

fun padding(value: Int) = if (value < 10) ("0$value") else "" + value
