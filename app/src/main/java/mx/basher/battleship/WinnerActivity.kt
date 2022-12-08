package mx.basher.battleship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WinnerActivity : AppCompatActivity() {
    lateinit var volverjugar_btn: Button
    lateinit var fin_btn: Button
    private lateinit var winnerTXT: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

        winnerTXT = findViewById(R.id.winnerTXT)

        volverjugar_btn = findViewById(R.id.volverjugar_btn)
        fin_btn = findViewById(R.id.fin_btn)

        //recuperar el nombre del jugador
        var nombreJugador= intent.getStringExtra("Nombre")


        winnerTXT.text = nombreJugador.toString()

        volverjugar_btn.setOnClickListener(){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        fin_btn.setOnClickListener(){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

    }
}