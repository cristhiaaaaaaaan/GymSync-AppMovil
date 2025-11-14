package cr.ac.utn.census

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import Controller.*
import Entity.*
import Util.Util
import java.util.Date

class MainActivity : AppCompatActivity() {

    // Controladores
    private lateinit var usuarioController: PersonController
    private lateinit var rutinaController: RutinaController
    private lateinit var ejercicioController: EjercicioController
    private lateinit var registroAvanceController: RegistroAvanceController
    private lateinit var fotoProgresoController: FotoProgresoController
    private lateinit var membresiaController: MembresiaController
    private lateinit var logroController: LogroController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar controladores
        initControllers()

        // Cargar datos de prueba
        loadTestData()

        // Setup button to launch Exercise CRUD Activity
        setupExerciseManagementButton()
    }

    private fun setupExerciseManagementButton() {
        val buttonManageExercises = findViewById<Button>(R.id.buttonManageExercises)
        buttonManageExercises.setOnClickListener {
            val intent = Intent(this, ExerciseCrudActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initControllers() {
        usuarioController = PersonController(this)
        rutinaController = RutinaController(this)
        ejercicioController = EjercicioController(this)
        registroAvanceController = RegistroAvanceController(this)
        fotoProgresoController = FotoProgresoController(this)
        membresiaController = MembresiaController(this)
        logroController = LogroController(this)
    }

    private fun loadTestData() {
        // Crear usuario de prueba
        val usuario = Person(
            id = Util.generateId(),
            name = "Juan Pérez",
            email = "juan.perez@gymsync.com",
            fotoPerfil = "https://example.com/foto.jpg",
            fechaRegistro = Util.getCurrentDate()
        )
        usuarioController.addUsuario(usuario)

        // Crear ejercicios de prueba
        val ejercicio1 = Ejercicio(
            id = Util.generateId(),
            nombre = "Press de Banca",
            series = 4,
            repeticiones = 12,
            pesoRecomendado = 60.0,
            notas = "Ejercicio para pecho"
        )
        ejercicioController.addEjercicio(ejercicio1)

        val ejercicio2 = Ejercicio(
            id = Util.generateId(),
            nombre = "Sentadillas",
            series = 4,
            repeticiones = 15,
            pesoRecomendado = 80.0,
            notas = "Ejercicio para piernas"
        )
        ejercicioController.addEjercicio(ejercicio2)

        val ejercicio3 = Ejercicio(
            id = Util.generateId(),
            nombre = "Peso Muerto",
            series = 3,
            repeticiones = 10,
            pesoRecomendado = 100.0,
            notas = "Ejercicio para espalda baja"
        )
        ejercicioController.addEjercicio(ejercicio3)

        // Crear rutina de prueba
        val rutina = Province(
            id = Util.generateId(),
            usuarioId = usuario.Id,
            fecha = Util.getCurrentDate(),
            nombre = "Rutina de Fuerza",
            ejercicios = mutableListOf(ejercicio1.Id, ejercicio2.Id, ejercicio3.Id),
            completada = false
        )
        rutinaController.addRutina(rutina)

        // Crear membresía de prueba
        val fechaInicio = Util.getCurrentDate()
        val calendar = java.util.Calendar.getInstance()
        calendar.time = fechaInicio
        calendar.add(java.util.Calendar.MONTH, 1)
        val fechaVencimiento = calendar.time

        val membresia = Membresia(
            id = Util.generateId(),
            usuarioId = usuario.Id,
            tipo = "Premium",
            fechaInicio = fechaInicio,
            fechaVencimiento = fechaVencimiento,
            activa = true,
            monto = 50000.0
        )
        membresiaController.addMembresia(membresia)

        // Crear registro de avance de prueba
        val registroAvance = RegistroAvance(
            id = Util.generateId(),
            ejercicioId = ejercicio1.Id,
            usuarioId = usuario.Id,
            fecha = Util.getCurrentDate(),
            pesoUtilizado = 55.0,
            repeticionesRealizadas = 12,
            seriesCompletadas = 4
        )
        registroAvanceController.addRegistroAvance(registroAvance)

        // Crear foto de progreso de prueba
        val fotoProgreso = FotoProgreso(
            id = Util.generateId(),
            usuarioId = usuario.Id,
            fecha = Util.getCurrentDate(),
            urlFoto = "https://example.com/progreso1.jpg",
            nota = "Primera foto de progreso"
        )
        fotoProgresoController.addFotoProgreso(fotoProgreso)

        // Crear logro de prueba
        val logro = Logro(
            id = Util.generateId(),
            usuarioId = usuario.Id,
            titulo = "Primera Rutina",
            descripcion = "Completaste tu primera rutina en GymSync",
            fecha = Util.getCurrentDate(),
            icono = "trophy"
        )
        logroController.addLogro(logro)
    }
}