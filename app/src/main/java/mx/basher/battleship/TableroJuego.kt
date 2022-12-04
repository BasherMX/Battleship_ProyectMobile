package mx.basher.battleship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TableroJuego : AppCompatActivity() {
    private lateinit var jugadorName: TextView
    private lateinit var tableroJugador: TresEnRaya
    private lateinit var tableroComputadora: TresEnRaya

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablero_juego)

        jugadorName = findViewById(R.id.jugadorName)
        tableroJugador = findViewById(R.id.tableroJugador)
        tableroComputadora = findViewById(R.id.tableroComputadora)


        //recuperar el nombre del jugador
        var nombreJugador= intent.getStringExtra("Nombre")
        var modoJuego= intent.getStringExtra("Modo")
        var T_Jugador = intent.extras?.getSerializable("TableroJugador") as Array<Array<Int>>
        var AT_Auto = intent.extras?.getSerializable("TableroAuto") as Array<Array<Int>>




        jugadorName.text = nombreJugador;


        tableroJugador.setTablero(T_Jugador);
        tableroComputadora.setTablero(AT_Auto);





    }
}