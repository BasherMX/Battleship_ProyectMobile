package mx.basher.battleship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var jugar_btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1400)
        setTheme(R.style.Theme_Battleship)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jugar_btn = findViewById(R.id.jugar_btn)

        jugar_btn.setOnClickListener(){
            val i = Intent(this, ModoJuego::class.java)
            startActivity(i)
        }

    }
}