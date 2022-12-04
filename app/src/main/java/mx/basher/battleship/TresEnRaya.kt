package mx.basher.battleship

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TresEnRaya : View {
    companion object{
        const val VACIO = 0
        const val AGUA = 1
        const val BARCO = 2
        const val CHOQUE = 3
        var filR = 0
        var colR = 0
        val Partes = 10
        var btnNombre = "Guardar Barcos"
        var ModoJuego = 1 //1 - elegir barcos //2 - Pelear
    }

    private val tablero = Array(10){Array(10){ VACIO }}
    private val tableroGame = Array(10){Array(10){ VACIO }}
    var contadorBarcos: Int = 0;

    private val pBorde = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.GRAY
        strokeWidth = 10f
    }

    private val pAgua = Paint().apply {
        style = Paint.Style.FILL
        color = Color.rgb(96, 176, 255)
        strokeWidth = 10f
    }

    private val pChoque = Paint().apply {
        style = Paint.Style.FILL
        color = Color.RED
    }

    private val pBarco = Paint().apply {
        style = Paint.Style.FILL
        color = Color.rgb(48, 79, 182)
    }

    private val pVacio = Paint().apply {
        style = Paint.Style.FILL
        color = Color.rgb(240, 240, 240)
    }

    var listener : OnCasillaSeleccionadaListener? = null

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {

    }

    constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var ancho = calcularAncho(widthMeasureSpec)
        var alto = calcularAlto(heightMeasureSpec)

        if(ancho < alto)
            alto = ancho
        else
            ancho = alto

        setMeasuredDimension(ancho, alto);
    }

    private fun calcularAlto(limitesSpec: Int): Int {
        var res = 100 //Alto por defecto

        val modo = MeasureSpec.getMode(limitesSpec)
        val limite = MeasureSpec.getSize(limitesSpec)

        if (modo == MeasureSpec.AT_MOST) {
            res = limite
        } else if (modo == MeasureSpec.EXACTLY) {
            res = limite
        }

        return res
    }

    private fun calcularAncho(limitesSpec: Int): Int {
        var res = 100 //Ancho por defecto

        val modo = MeasureSpec.getMode(limitesSpec)
        val limite = MeasureSpec.getSize(limitesSpec)

        if (modo == MeasureSpec.AT_MOST) {
            res = limite
        } else if (modo == MeasureSpec.EXACTLY) {
            res = limite
        }

        return res
    }

    fun limpiar() {
        for(i in 0..(Partes-1)){
            for(j in 0..(Partes-1)){
                tablero[i][j] = 0
            }
        }
    }

    fun setTablero(Rec: Array<Array<Int>>) {
        for(i in 0..(Partes-1)){
            for(j in 0..(Partes-1)){
                setCasilla(i,j,Rec[i][j])
            }
        }
    }

    fun setCasilla(fil: Int, col: Int, valor: Int) {
        tablero[fil][col] = valor
    }

    fun getCasilla(fil: Int, col: Int) : Int {
        return tablero[fil][col]
    }

    fun getBtnNombre(): String{
        return btnNombre
    }

    fun GuardaryJugar() : Array<Array<Int>>{
        if(contadorBarcos == 16){
            ModoJuego = 2
            return tablero;
        }else{
            return Array(0){Array(0){ 0}}
        }

    }

    override fun onDraw(canvas: Canvas?) {
        //Obtenemos las dimensiones del control
        val alto = measuredHeight
        val ancho = measuredWidth

        //Lineas Verticales
        for (j in 1..Partes){
            //Lineas Verticales
            canvas!!.drawLine((j * ancho / Partes).toFloat(), 0f, (j * ancho / Partes).toFloat(), alto.toFloat(), pBorde)
            //Lineas Horizontales
            canvas!!.drawLine(0f, (j * alto / Partes).toFloat(), ancho.toFloat(), (j * alto / Partes).toFloat(), pBorde)
        }

        //Borde de las casillas
        canvas!!.drawRect(0f, 0f, ancho.toFloat(), alto.toFloat(), pBorde)


        //Casillas Seleccionadas
        for (fil in 0..(Partes-1)) {
            for (col in 0..(Partes-1)) {
                canvas.drawRect(
                    (col) * (ancho/ Partes) + ancho / Partes * 0.05f,
                    (fil) * (ancho/ Partes) + ancho / Partes * 0.05f,
                    (col + 1) * (ancho/ Partes) - ancho / Partes * 0.05f,
                    (fil + 1) * (ancho/ Partes) - ancho / Partes * 0.05f,
                    pVacio
                )


                if (tablero[fil][col] == CHOQUE && ModoJuego == 2) {
                    canvas.drawRect(
                        (col) * (ancho/ Partes) + ancho / Partes * 0.05f,
                        (fil) * (ancho/ Partes) + ancho / Partes * 0.05f,
                        (col + 1) * (ancho/ Partes) - ancho / Partes * 0.05f,
                        (fil + 1) * (ancho/ Partes) - ancho / Partes * 0.05f,
                        pChoque
                    )
                }

                if (tablero[fil][col] == BARCO && ModoJuego == 1) {

                    canvas.drawRect(
                        (col) * (ancho/ Partes) + ancho / Partes * 0.05f,
                        (fil) * (ancho/ Partes) + ancho / Partes * 0.05f,
                        (col + 1) * (ancho/ Partes) - ancho / Partes * 0.05f,
                        (fil + 1) * (ancho/ Partes) - ancho / Partes * 0.05f,
                        pBarco
                    )
                }

                if (tablero[fil][col] == AGUA && ModoJuego == 2) {

                    canvas.drawRect(
                        (col) * (ancho/ Partes) + ancho / Partes * 0.05f,
                        (fil) * (ancho/ Partes) + ancho / Partes * 0.05f,
                        (col + 1) * (ancho/ Partes) - ancho / Partes * 0.05f,
                        (fil + 1) * (ancho/ Partes) - ancho / Partes * 0.05f,
                        pAgua
                    )
                }


            }
        }



    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val fil = (event!!.y / (measuredHeight / Partes)).toInt()
        val col = (event.x / (measuredWidth / Partes)).toInt()
        var existe = 0;

        //setFilyCol
        filR = fil
        colR = col

        if(ModoJuego == 1){
            //Actualizamos el tablero
            if(contadorBarcos < 16){
                if(tablero[fil][col] != BARCO){
                    contadorBarcos ++;
                    tablero[fil][col] = BARCO
                }else{
                    existe = 1;
                    //contadorBarcos = 16
                }

            }
        }

        if(ModoJuego == 2){
            if(tablero[fil][col] == BARCO){
                tablero[fil][col] = CHOQUE
            }else if(tablero[fil][col] == VACIO){
                tablero[fil][col] = AGUA
            }

        }

        //Lanzamos el evento de pulsación
        listener?.onCasillaSeleccionada(contadorBarcos, existe);

        //Refrescamos el control
        this.invalidate()

        return super.onTouchEvent(event)
    }

    fun setOnCasillaSeleccionadaListener(l: OnCasillaSeleccionadaListener) {
        listener = l
    }

    fun setOnCasillaSeleccionadaListener(seleccion: (Int, Int) -> Unit) {
        listener = object:OnCasillaSeleccionadaListener {
            override fun onCasillaSeleccionada(fila: Int, columna: Int) {
                seleccion(fila, columna)
            }
        }
    }
}