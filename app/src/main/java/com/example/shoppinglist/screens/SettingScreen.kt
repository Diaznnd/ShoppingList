package com.example.shoppinglist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SettingScreen() {
    val colorScheme = MaterialTheme.colorScheme
    var isDarkMode by remember { mutableStateOf(false) }
    var isNotificationEnabled by remember { mutableStateOf(true) }

    var showAboutDialog by remember { mutableStateOf(false) }
    var showLanguageDialog by remember { mutableStateOf(false) }

    if (showAboutDialog) {
        AlertDialog(
            onDismissRequest = { showAboutDialog = false },
            title = { Text("Tentang Aplikasi") },
            text = { Text("Versi 1.0.0 - Aplikasi Shopping List buatan Muhammad Diaz Ananda Syukri") },
            confirmButton = {
                TextButton(onClick = { showAboutDialog = false }) {
                    Text("Tutup")
                }
            }
        )
    }

    if (showLanguageDialog) {
        AlertDialog(
            onDismissRequest = { showLanguageDialog = false },
            title = { Text("Pilih Bahasa") },
            text = {
                Column {
                    Text("• Indonesia")
                    Text("• English")
                }
            },
            confirmButton = {
                TextButton(onClick = { showLanguageDialog = false }) {
                    Text("Selesai")
                }
            }
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Pengaturan",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.onBackground
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Atur preferensi aplikasi sesuai kebutuhanmu.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                )
            }

            item {
                SettingSection(title = "Tampilan") {
                    SettingItemSwitch(
                        icon = Icons.Default.Brightness4,
                        title = "Mode Gelap",
                        subtitle = "Aktifkan tema gelap untuk tampilan malam hari",
                        checked = isDarkMode,
                        onCheckedChange = { isDarkMode = it }
                    )
                }
            }

            item {
                SettingSection(title = "Notifikasi") {
                    SettingItemSwitch(
                        icon = Icons.Filled.Notifications,
                        title = "Aktifkan Notifikasi",
                        subtitle = "Terima pemberitahuan dari aplikasi",
                        checked = isNotificationEnabled,
                        onCheckedChange = { isNotificationEnabled = it }
                    )
                }
            }

            item {
                SettingSection(title = "Umum") {
                    SettingItemClickable(
                        icon = Icons.Default.Language,
                        title = "Bahasa",
                        subtitle = "Indonesia",
                        onClick = { showLanguageDialog = true }
                    )

                    SettingItemClickable(
                        icon = Icons.Filled.Info,
                        title = "Tentang Aplikasi",
                        subtitle = "Versi 1.0.0",
                        onClick = { showAboutDialog = true }
                    )
                }
            }
        }
    }
}


@Composable
fun SettingSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                color = colorScheme.primary,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
        content()
    }
}

@Composable
fun SettingItemSwitch(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = colorScheme.onBackground
                    )
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                    )
                }
            }
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = colorScheme.primary,
                checkedTrackColor = colorScheme.primaryContainer,
                uncheckedThumbColor = colorScheme.onSurfaceVariant,
                uncheckedTrackColor = colorScheme.surfaceVariant
            )
        )
    }

    HorizontalDivider(
        modifier = Modifier.padding(top = 8.dp),
        thickness = 0.5.dp,
        color = colorScheme.outline.copy(alpha = 0.3f)
    )
}

@Composable
fun SettingItemClickable(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = colorScheme.primary
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(title, fontWeight = FontWeight.SemiBold)
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                )
            }
        }
    }
}
