package com.example.shoppinglist.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.R
import kotlinx.coroutines.delay

@Composable
fun ProfileScreen() {
    val colorScheme = MaterialTheme.colorScheme

    // 🖼️ Daftar foto (tambahkan lebih banyak sesuai kebutuhan)
    val imageList = listOf(
        R.drawable.poto,
        R.drawable.poto2,
    )

    var currentImageIndex by remember { mutableStateOf(0) }

    // 🔁 Auto ganti gambar tiap 1.5 detik
    LaunchedEffect(Unit) {
        while (true) {
            delay(1500)
            currentImageIndex = (currentImageIndex + 1) % imageList.size
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Foto profil berganti otomatis
        Image(
            painter = painterResource(id = imageList[currentImageIndex]),
            contentDescription = "Foto Diri",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorScheme.primaryContainer,
                contentColor = colorScheme.onPrimaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Nama: Muhammad Diaz Ananda Syukri", fontWeight = FontWeight.Bold)
                Text("NIM: 2311521015")
                Text("Hobi: Bermain Game, Membaca")
                Text("Tempat/Tanggal Lahir: Palangka Raya, 11 September 2005")
                Text("Peminatan: Web Programming")
            }
        }
    }
}
