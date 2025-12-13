package cr.ac.utn.census

import Api.RetrofitClient
import Entity.LoginRequest
import Util.SessionManager
import Util.Util
import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var buttonLogin: Button
    private lateinit var textViewSignUp: TextView
    private lateinit var textViewForgotPassword: TextView

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sessionManager = SessionManager(this)

        // Check if user is already logged in
        if (sessionManager.isLoggedIn()) {
            navigateToMainActivity()
            return
        }

        // Initialize views
        initViews()

        // Setup listeners
        setupListeners()
    }

    private fun initViews() {
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        textViewSignUp = findViewById(R.id.textViewSignUp)
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword)
    }

    private fun setupListeners() {
        buttonLogin.setOnClickListener {
            performLogin()
        }

        textViewSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        textViewForgotPassword.setOnClickListener {
            Toast.makeText(this, "Forgot password feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun performLogin() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (!validateInput(email, password)) {
            return
        }

        lifecycleScope.launch {
            try {
                val loginRequest = LoginRequest(
                    email = email,
                    password = password
                )

                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.authService.login(loginRequest)
                }

                if (response.responseCode == 200 && response.data != null) {
                    val authResponse = response.data

                    sessionManager.createLoginSession(
                        authResponse.user.Id,
                        authResponse.user.Name,
                        authResponse.user.Email,
                        authResponse.token
                    )

                    Toast.makeText(this@LoginActivity, "Welcome back, ${authResponse.user.Name}!", Toast.LENGTH_SHORT).show()
                    navigateToMainActivity()
                } else {
                    Toast.makeText(this@LoginActivity, response.message ?: "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@LoginActivity, "Login failed: ${e.message}", Toast.LENGTH_LONG).show()
                android.util.Log.e("LoginActivity", "Login error", e)
            }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
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

        return true
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
