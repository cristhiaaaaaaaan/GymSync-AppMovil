package cr.ac.utn.census

import Api.RetrofitClient
import Entity.RegisterRequest
import Util.SessionManager
import Util.Util
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextName: TextInputEditText
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var editTextConfirmPassword: TextInputEditText
    private lateinit var buttonSignUp: Button
    private lateinit var textViewSignIn: TextView
    private lateinit var buttonBack: ImageButton

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sessionManager = SessionManager(this)

        // Initialize views
        initViews()

        // Setup listeners
        setupListeners()
    }

    private fun initViews() {
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword)
        buttonSignUp = findViewById(R.id.buttonSignUp)
        textViewSignIn = findViewById(R.id.textViewSignIn)
        buttonBack = findViewById(R.id.buttonBack)
    }

    private fun setupListeners() {
        buttonSignUp.setOnClickListener {
            performRegistration()
        }

        textViewSignIn.setOnClickListener {
            finish()
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun performRegistration() {
        val name = editTextName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val confirmPassword = editTextConfirmPassword.text.toString().trim()

        if (!validateInput(name, email, password, confirmPassword)) {
            return
        }

        lifecycleScope.launch {
            try {
                val registerRequest = RegisterRequest(
                    name = name,
                    email = email,
                    password = password
                )

                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.authService.register(registerRequest)
                }

                if (response.responseCode == 201 && response.data != null) {
                    val authResponse = response.data

                    sessionManager.createLoginSession(
                        authResponse.user.Id,
                        authResponse.user.Name,
                        authResponse.user.Email,
                        authResponse.token
                    )

                    Toast.makeText(this@RegisterActivity, "Account created successfully!", Toast.LENGTH_SHORT).show()
                    navigateToMainActivity()
                } else {
                    Toast.makeText(this@RegisterActivity, response.message ?: "Registration failed", Toast.LENGTH_LONG).show()
                }

            } catch (e: Exception) {
                Toast.makeText(this@RegisterActivity, "Registration failed: ${e.message}", Toast.LENGTH_LONG).show()
                android.util.Log.e("RegisterActivity", "Registration error", e)
            }
        }
    }

    private fun validateInput(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (name.isEmpty()) {
            editTextName.error = "Name is required"
            editTextName.requestFocus()
            return false
        }

        if (name.length < 3) {
            editTextName.error = "Name must be at least 3 characters"
            editTextName.requestFocus()
            return false
        }

        if (email.isEmpty()) {
            editTextEmail.error = "Email is required"
            editTextEmail.requestFocus()
            return false
        }

        if (!Util.isEmailValid(email)) {
            editTextEmail.error = "Please enter a valid email"
            editTextEmail.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            editTextPassword.error = "Password is required"
            editTextPassword.requestFocus()
            return false
        }

        if (password.length < 6) {
            editTextPassword.error = "Password must be at least 6 characters"
            editTextPassword.requestFocus()
            return false
        }

        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.error = "Please confirm your password"
            editTextConfirmPassword.requestFocus()
            return false
        }

        if (password != confirmPassword) {
            editTextConfirmPassword.error = "Passwords do not match"
            editTextConfirmPassword.requestFocus()
            return false
        }

        return true
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
