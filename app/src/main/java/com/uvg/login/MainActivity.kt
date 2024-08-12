package com.uvg.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Download
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {MiCumpleApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiCumpleApp() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Download, contentDescription = "Descargar")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Actualización disponible")
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                            )
                            context.startActivity(intent)
                        }) {Text("Descargar")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(
                            text = "martes",
                            style = MaterialTheme.typography.displayLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "9 de abril",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = {
                        Toast.makeText(context, "Jornada terminada", Toast.LENGTH_SHORT).show()
                    }) {
                        Text("Terminar Jornada")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                RestauranteInfo(
                    nombre = "Los Cebollines",
                    direccion = "Ciudad de Guatemala 01010",
                    horario = "6:00 - 24:00"
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {
                        Toast.makeText(context, "Samuel Mejia", Toast.LENGTH_SHORT).show()
                    }) {
                        Text("Iniciar")
                    }
                    OutlinedButton(onClick = {
                        Toast.makeText(
                            context,
                            "Comida: Mexicana\nPrecio: A partir de Q100",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Text("Detalles")
                    }
                }
            }
        }
    }
}@Composable
fun RestauranteInfo(nombre: String, direccion: String, horario: String) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = nombre,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OpenGoogleMapButton(query = "Los Cebollines, Ciudad de Guatemala 01010")
        }
        Text(text = direccion)
        Text(text = "Horario: $horario")
    }
}

@Composable
fun OpenGoogleMapButton(query: String) {
    val context = LocalContext.current
    IconButton(onClick = {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("geo:0,0?q=$query")
        )
        context.startActivity(intent)
    }) {
        Icon(Icons.Filled.Directions, contentDescription = "Direcciones")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MiCumpleApp()
}