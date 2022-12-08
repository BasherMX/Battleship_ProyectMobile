package mx.basher.battleship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class TableroJuego : AppCompatActivity() {
    private lateinit var jugadorName: TextView
    private lateinit var tableroJugador: TresEnRaya
    private lateinit var tableroComputadora: TresEnRaya
    private lateinit var puntosComputadora : TextView
    private lateinit var puntosJugador : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablero_juego)

        jugadorName = findViewById(R.id.txtJugadorName)
        tableroJugador = findViewById(R.id.tableroJugador)
        tableroComputadora = findViewById(R.id.tableroComputadora)
        puntosComputadora = findViewById(R.id.puntosComputadora)
        puntosJugador = findViewById(R.id.puntosJugador)


        //recuperar el nombre del jugador
        var nombreJugador= intent.getStringExtra("Nombre")
        var modoJuego= intent.getStringExtra("Modo")






        if(modoJuego == "uno"){
            var T_Jugador = intent.extras?.getSerializable("TableroJugador") as Array<Array<Int>>
            var AT_Auto = intent.extras?.getSerializable("TableroAuto") as Array<Array<Int>>

            jugadorName.text = nombreJugador;

            //Agrega los tableros a cada jugador
            tableroJugador.setTablero(T_Jugador);
            tableroComputadora.setTablero(AT_Auto);


            //cuando el tablero de la computadora es tocado
            tableroComputadora.setOnCasillaSeleccionadaListener { contador, exist ->

                if(exist != 1){
                    var salir = false
                    do {
                        //obtiene una casilla aleatoria en ese tablero
                        var x = (0..9).random()
                        var y = (0..9).random()
                        var casilla = tableroJugador.getCasilla(x,y)

                        if(casilla != 1 && casilla != 3){
                            salir = true
                            if(casilla == 0){//vacio
                                tableroJugador.setCasilla(x,y,1); //pone agua
                            }else if(casilla == 2){
                                tableroJugador.setCasilla(x,y,3); //pone choque
                            }
                        }

                    }while(!salir)
                }

                RevisarGanador(nombreJugador)

            }



        }else{
            //recuperar datos de la bd

        }









    }

    private fun RevisarGanador(name: String?) {
        var jugadorUno = tableroJugador.getContador()
        var jugadorDos = tableroComputadora.getContador()

        puntosComputadora.text = jugadorDos.toString()
        puntosJugador.text = jugadorUno.toString()

        if(jugadorDos == 16){
            Toast.makeText(this, "gana jugador", Toast.LENGTH_SHORT).show()

            val i = Intent(this, WinnerActivity::class.java)
            i.putExtra("Nombre",name)
            i.putExtra("tipo", "ganador")
            startActivity(i)
        }

        if(jugadorUno == 16){
            Toast.makeText(this, "gana computadora", Toast.LENGTH_SHORT).show()
            val i = Intent(this, WinnerActivity::class.java)
            i.putExtra("Nombre","Computadora")
            i.putExtra("tipo", "ganador")
            startActivity(i)
        }

        if(jugadorUno == 12 && jugadorDos == 12){
            Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show()
            val i = Intent(this, WinnerActivity::class.java)
            i.putExtra("Nombre","")
            i.putExtra("tipo", "empate")
            startActivity(i)

            //Llevar a pantalla empate
        }
    }
}