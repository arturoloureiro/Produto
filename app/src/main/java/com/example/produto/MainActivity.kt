package com.example.produto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.BlurTarget // Importação do Target

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 1. Encontra a View do Blur e o Target no XML
        val blurView = findViewById<BlurView>(R.id.header)
        val targetView = findViewById<BlurTarget>(R.id.meuAlvo)

        val radius = 5f // Conforme o exemplo da imagem

        // 2. Opcional sugerido pela imagem: pega o fundo da janela
        val decorView = window.decorView
        val windowBackground = decorView.background

        // 3. Aplica o setup usando a nova arquitetura (passando o target)
        blurView.setupWith(targetView)
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)

        // Verificação do EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main) ?: decorView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}