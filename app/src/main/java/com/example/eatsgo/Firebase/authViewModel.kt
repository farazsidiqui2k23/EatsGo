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

    fun UserLogIn(email:String, password:String){
        if (email.isBlank() && password.isBlank()){
            _authState.value = AuthState.onFailure("Missing fields")
        }

        _authState.value = AuthState.onLoading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.onFailure(task.exception?.message ?: "something went wrong")
                }
            }
    }

    fun UserSignUp(email: String, password: String){
        if (email.isBlank() && password.isBlank()){
            _authState.value = AuthState.onFailure("Missing fields")
        }

        _authState.value = AuthState.onLoading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.onFailure(task.exception?.message ?: "something went wrong")
                }
            }
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object onLoading : AuthState()
    data class onFailure(val message: String) : AuthState()
}