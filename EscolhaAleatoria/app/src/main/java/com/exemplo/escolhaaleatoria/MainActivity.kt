package com.exemplo.escolhaaleatoria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TelaEscolha()
                }
            }
        }
    }
}

@Composable
fun TelaEscolha() {
    var opcao1 by remember { mutableStateOf("") }
    var opcao2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF94b3db))
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxWidth()
        ) {
            Canvas(modifier = Modifier
                    .padding(bottom = 25.dp)
                    .size(width = 180.dp, height = 200.dp)) {
                scale(scaleX = 7f, scaleY = 4.2f) {
                    drawCircle(color = Color(0xFF5e81af), radius = 22.dp.toPx())
                }
            }

            Text(
                text = "Pergunte a Estrela!",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 34.sp,
                    color = Color(0xFF071B3D)
                ),
                modifier = Modifier.padding(bottom = 0.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.estrela),
                contentDescription = "estrela",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(450.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .padding(bottom = 20.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = opcao1,
            onValueChange = { opcao1 = it },
            label = { Text("Opção 1") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = opcao2,
            onValueChange = { opcao2 = it },
            label = { Text("Opção 2") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                resultado = if (opcao1.isNotBlank() && opcao2.isNotBlank()) {
                    if (Random.nextBoolean()) opcao1 else opcao2
                } else {
                    null
                }
            },
            enabled = opcao1.isNotBlank() && opcao2.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Perguntar", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(40.dp))

        AnimatedVisibility(visible = resultado != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "A Estrela escolheu:",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = resultado ?: "",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

