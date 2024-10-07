package com.first.testbiuildhmsapp

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.first.testbiuildhmsapp.intent.ClientIntent
import com.first.testbiuildhmsapp.viewmodel.ClientViewModel

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: ClientViewModel // ViewModel
    private lateinit var locationManager: LocationManager // Добавляем объявление LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Получаем ViewModel через ViewModelProvider
        viewModel = ViewModelProvider(this)[ClientViewModel::class.java]

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val fineLocationGranted = permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] ?: false
            val coarseLocationGranted = permissions[android.Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
            if (fineLocationGranted || coarseLocationGranted) {
                requestLocationUpdates() // Если разрешения получены
            }
        }

        // Запрашиваем разрешения
        requestPermissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        setContent {
            ClientScreen(viewModel = viewModel) // Передаем ViewModel в Composable
        }
    }

    private fun requestLocationUpdates() {
        try {
            // Используем GPS и NETWORK провайдеры
            val gpsProvider = LocationManager.GPS_PROVIDER
            val networkProvider = LocationManager.NETWORK_PROVIDER

            if (locationManager.isProviderEnabled(gpsProvider)) {
                locationManager.requestLocationUpdates(
                    gpsProvider,
                    5000L,
                    5f,
                    locationListener,
                    Looper.getMainLooper()
                )
            }

            if (locationManager.isProviderEnabled(networkProvider)) {
                locationManager.requestLocationUpdates(
                    networkProvider,
                    5000L,
                    5f,
                    locationListener,
                    Looper.getMainLooper()
                )
            }

        } catch (e: SecurityException) {
            // Логируем ошибку
        }
    }

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            // Обновляем координаты через ViewModel
            viewModel.updateLocation(location.latitude, location.longitude)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
}

@Composable
fun ClientScreen(viewModel: ClientViewModel) {
    val state by viewModel.state.collectAsState()
    val latitude by viewModel.latitude.collectAsState()
    val longitude by viewModel.longitude.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(ClientIntent.LoadClientData)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = state.clientName,
                color = Color(android.graphics.Color.parseColor("#${state.textColor.toString(16).padStart(6, '0')}")),
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Latitude: $latitude\nLongitude: $longitude",
                color = Color(android.graphics.Color.parseColor("#${state.textColor.toString(16).padStart(6, '0')}")),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}