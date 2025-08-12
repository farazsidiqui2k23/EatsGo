package com.example.eatsgo.Firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkUserStatus()
    }

    fun checkUserStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    fun UserLogIn(email: String, password: String) {
        println("going to check")
        if (email.isBlank() && password.isBlank()) {
            _authState.value = AuthState.onFailure("Missing fields")
        }
        else{
            _authState.value = AuthState.onLoading
            println("going to login")
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authState.value = AuthState.Authenticated
                    } else {
                        _authState.value =
                            AuthState.onFailure(task.exception?.message ?: "something went wrong")
                    }
                }
        }
    }

    fun UserSignUp(email: String, password: String, name: String, contact: String, dob: String) {

        if (email.isBlank() && password.isBlank() && name.isBlank() && contact.isBlank() && dob.isBlank()) {
            _authState.value = AuthState.onFailure("Missing fields")
        }
        if (!email.contains("@") && !email.contains(".")) {
            _authState.value = AuthState.onFailure("Invalid email")
        }
        if (contact.length != 11) {
            _authState.value = AuthState.onFailure("Invalid contact number")
        }
        if (password.length != 6) {
            _authState.value = AuthState.onFailure("Password should be 6 characters long")
        }

        _authState.value = AuthState.onLoading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    println(userId)
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value =
                        AuthState.onFailure(task.exception?.message ?: "something went wrong")
                }
            }
    }

    fun UserSignOut(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    fun userData(){
        val userId : String
        val name : String
        val contact : String
        val dob : String
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object onLoading : AuthState()
    data class onFailure(val message: String) : AuthState()
}

data class (

        )