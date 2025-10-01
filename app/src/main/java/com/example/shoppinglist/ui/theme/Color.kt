package com.example.shoppinglist.ui.theme
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val BrownLight = Color(0xFF795548)          // Brown 500
val BrownDark = Color(0xFF5D4037)           // Brown 700
val BrownOn = Color(0xFFFFFFFF)
val BrownBackground = Color(0xFFF5F5F5)
val BrownOnBackground = Color(0xFF1C1B1F)
val BrownSurface = Color(0xFFFFFFFF)
val BrownOnSurface = Color(0xFF1C1B1F)

val BrownSurfaceDark = Color(0xFF2B2930)
val BrownOnSurfaceDark = Color(0xFFE6E1E5)

val BrownSecondary = Color(0xFFA1887F)      // Brown-ish Secondary
val BrownSecondaryContainer = Color(0xFFD7CCC8)
val BrownOnSecondaryContainer = Color(0xFF4E342E)

val BrownPrimaryContainer = Color(0xFFD7CCC8)
val BrownOnPrimaryContainer = Color(0xFF3E2723)

// Warna untuk dark mode
val BrownDarkBackground = Color(0xFF121212)
val BrownDarkOnBackground = Color(0xFFE6E1E5)
val BrownDarkPrimaryContainer = Color(0xFF3E2723)
val BrownDarkOnPrimaryContainer = Color(0xFFD7CCC8)
val BrownDarkSecondaryContainer = Color(0xFF4E342E)
val BrownDarkOnSecondaryContainer = Color(0xFFD7CCC8)

// Gradient Coklat → Cream
val BrownGradient = androidx.compose.ui.graphics.Brush.linearGradient(
    colors = listOf(
        Color(0xFF795548), // Brown 500
        Color(0xFFD7CCC8)  // Brown 100 (cream)
    )
)
