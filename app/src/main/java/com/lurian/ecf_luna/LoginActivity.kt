package com.lurian.ecf_luna

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lurian.ec3_luna.R
import com.lurian.ec3_luna.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth
        binding.tILEmail.editText?.addTextChangedListener{text ->
            binding.btnlogin.isEnabled = validateEmailPass(text.toString(),binding.tILContrasena.editText?.text.toString())
        }
        binding.tILContrasena.editText?.addTextChangedListener{text ->
            binding.btnlogin.isEnabled = validateEmailPass(binding.tILEmail.editText?.text.toString(), text.toString())
        }
        binding.btnlogin.setOnClickListener{
            val password = binding.tILContrasena.editText?.text.toString()
            val email = binding.tILEmail.editText?.text.toString()
            loginwithEmailAndPass(email, password)
        }
        binding.btnGoogle.setOnClickListener {
            val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
            val signInIntent = googleSignInClient.signInIntent
            googleSignInLauncher.launch(signInIntent)
        }
        binding.btnSingup.setOnClickListener{
            //Registro con email y contraseña
            val password = binding.tILContrasena.editText?.text.toString()
            val email = binding.tILEmail.editText?.text.toString()
            signupEmailyPass(email, password)
        }

        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {

        }

    }

    private fun loginwithEmailAndPass(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task->
                if (task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "El usuario no se encuentra registrado.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signupEmailyPass(email: String, password: String) {
        if (validateEmailPass(email, password)){
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        val user = firebaseAuth.currentUser
                        Toast.makeText(this, "El usuario fue registrado correctamente.", Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText(this, "No son credenciales validas.", Toast.LENGTH_SHORT).show()
        }

    }

    private val googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            authenticateWithFirebase(account.idToken)
        } catch (e: Exception) {
            Log.e("LoginActivity", "Error en autenticación de Google: ${e.message}")
        }
    }
    private fun authenticateWithFirebase(idToken: String?) {
        val authCredentials = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(authCredentials)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.e("LoginActivity", "Error en autenticación de Firebase: ${task.exception?.message}")
                }
            }
    }

    private fun loginWithGoogle(){
        val googleSingInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(R.string.web_client_id.toString())
            .requestEmail()
            .build()
        val googleCliente = GoogleSignIn.getClient(this, googleSingInOption)
        val intent = googleCliente.signInIntent
        googleLauncher.launch(intent)
    }

    private fun validateEmailPass(email:String, pass:String):Boolean{
        val isEmailvalid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val ispassvalid = pass.length >= 6
        return isEmailvalid && ispassvalid
    }



}