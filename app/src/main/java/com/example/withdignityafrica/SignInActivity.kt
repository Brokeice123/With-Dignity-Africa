package com.example.withdignityafrica

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    lateinit var log_edt_email: EditText
    lateinit var log_edt_password: EditText
    lateinit var log_btn_log: Button

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        // hide status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        log_edt_email = findViewById(R.id.edtLogemail)
        log_edt_password = findViewById(R.id.edtLogpassword)
        log_btn_log = findViewById(R.id.btnLog)

        auth = FirebaseAuth.getInstance()

        log_btn_log.setOnClickListener {
            var email = log_edt_email.text.toString().trim()
            var password = log_edt_password.text.toString().trim()

            //Validate Input
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "One of the inputs is empty", Toast.LENGTH_SHORT).show()
            } else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        var gotomain = Intent(this, OptionsPageActivity::class.java)
                        startActivity(gotomain)
                        finish()
                    } else{
                        Toast.makeText(this, "Login Failed. Kindly check your email or password", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

    }
}