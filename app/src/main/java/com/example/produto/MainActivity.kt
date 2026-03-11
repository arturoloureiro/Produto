package com.example.produto

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.BlurTarget // Importação do Target

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var dotIndictor: LinearLayout

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

        viewPager = findViewById(R.id.viewPager)
        dotIndictor = findViewById(R.id.dotIndictor)


        val items = listOf(
            (R.drawable.panela1),
            (R.drawable.panelas2),
            (R.drawable.panelas3)
        )

        val adapter = MyPagerAdapter(items)

        viewPager.adapter = adapter

        createDotIndictor(items.size)

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                updateIndicator(position)
            }
        })

    }
    private fun createDotIndictor(count: Int) {

        for (i in 0 until count) {
            val dot = ImageView(this)
            dot.setImageResource(R.drawable.dot_selector)
            dotIndictor.addView(dot)
        }
    }

        private fun updateIndicator(position: Int){
            for(i in 0 until dotIndictor.childCount){
                val dot = dotIndictor.getChildAt(i) as ImageView
                dot.isSelected = i == position
            }
        }

    }

