package mx.basher.battleship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TableroJuego : AppCompatActivity() {
    private lateinit var jugadorName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablero_juego)

        jugadorName = findViewById(R.id.jugadorName)

        //recuperar el nombre del jugador
        var nombreJugador= intent.getStringExtra("Nombre")
        var modoJuego= intent.getStringExtra("Modo")

        jugadorName.text = nombreJugador;





    }
}