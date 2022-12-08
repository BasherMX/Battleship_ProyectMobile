package mx.basher.battleship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WinnerActivity : AppCompatActivity() {
    private lateinit var winnerTXT: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

        winnerTXT = findViewById(R.id.winnerTXT)

        //recuperar el nombre del jugador
        var nombreJugador= intent.getStringExtra("Nombre")


        winnerTXT.text = nombreJugador.toString()



    }
}