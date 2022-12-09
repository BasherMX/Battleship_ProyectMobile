package mx.basher.battleship

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class TableroJuego : AppCompatActivity() {
    private lateinit var jugadorName: TextView
    private lateinit var txtJugador2Name:TextView
    private lateinit var tableroJugador: TresEnRaya
    private lateinit var tableroComputadora: TresEnRaya
    private lateinit var puntosComputadora : TextView
    private lateinit var puntosJugador : TextView
    private lateinit var progressBar: ProgressBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablero_juego)

        //find view
        jugadorName = findViewById(R.id.txtJugadorName)
        txtJugador2Name = findViewById(R.id.txtJugador2Name)
        tableroJugador = findViewById(R.id.tableroJugador)
        tableroComputadora = findViewById(R.id.tableroComputadora)
        puntosComputadora = findViewById(R.id.puntosComputadora)
        puntosJugador = findViewById(R.id.puntosJugador)
        progressBar = findViewById(R.id.progressBar)

        //reiniciar tableros
        tableroJugador.limpiarTodo();
        tableroJugador.setModoJuego(2);
        tableroComputadora.limpiarTodo();
        tableroComputadora.setModoJuego(2);



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
            tableroComputadora.setOnClickListener(){
                esperar(300)
                progressBar.visibility = View.GONE
            }


            tableroComputadora.setOnCasillaSeleccionadaListener { contador, exist ->

                if(exist != 1){
                    progressBar.visibility = View.VISIBLE


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

                RevisarGanador(nombreJugador, "COMPUTADORA")
            }



        }else{
            var T_Jugador1 = intent.extras?.getSerializable("TableroJugadorUno") as Array<Array<Int>>
            var T_Jugador2 = intent.extras?.getSerializable("TableroJugadorDos") as Array<Array<Int>>

            jugadorName.text = nombreJugador;
            txtJugador2Name.text = "JUGADOR 2"

            //Agrega los tableros a cada jugador
            tableroJugador.setTablero(T_Jugador1);
            tableroComputadora.setTablero(T_Jugador2);

            tableroComputadora.setOnCasillaSeleccionadaListener { contador, exist ->
                RevisarGanador(nombreJugador, "JUGADOR 2")
            }

            tableroJugador.setOnCasillaSeleccionadaListener { contador, exist ->
                RevisarGanador(nombreJugador, "JUGADOR 2")
            }

        }
    }

    private fun RevisarGanador(name: String?, name2: String?) {
        var jugadorUno = tableroJugador.getContador()
        var jugadorDos = tableroComputadora.getContador()

        puntosComputadora.text = jugadorDos.toString()
        puntosJugador.text = jugadorUno.toString()

        if(jugadorDos == 16){

            val i = Intent(this, WinnerActivity::class.java)
            i.putExtra("Nombre",name)
            i.putExtra("tipo", "ganador")
            startActivity(i)
            finish();
        }

        if(jugadorUno == 16){
            val i = Intent(this, WinnerActivity::class.java)
            i.putExtra("Nombre",name2)
            i.putExtra("tipo", "ganador")
            startActivity(i)
            finish();
        }

        if(jugadorUno == 12 && jugadorDos == 12){
            Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show()
            val i = Intent(this, WinnerActivity::class.java)
            i.putExtra("Nombre","")
            i.putExtra("tipo", "empate")
            startActivity(i)
            finish();
        }
    }


    fun esperar(n:Int) = runBlocking {
        launch { // lanza una nueva corrutina y avanza
            delay(n.toLong()) // agrega delay por 1 segundo sin bloquear
        }
    }
}