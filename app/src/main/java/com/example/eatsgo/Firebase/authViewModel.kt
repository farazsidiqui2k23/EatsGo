package com.example.eatsgo.Firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel : ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().reference.child("users")

    val _authState = MutableLiveData<AuthState<Nothing>>()
    val authState: LiveData<AuthState<Nothing>> = _authState

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
            _authState.value = AuthState.onFailure(Event("Missing Field"))
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
                            AuthState.onFailure((Event(task.exception?.message ?: "something went wrong")))
                    }
                }
        }
    }

    fun UserSignUp(email: String, password: String, name: String, contact: String, dob: String) {

        if (email.isBlank() || password.isBlank() || name.isBlank() || contact.isBlank() || dob.isBlank()) {

            _authState.value = AuthState.onFailure(Event("Missing fields"))
            println("Missing fields")
        }
        else if (!email.contains("@") || !email.contains(".")) {

            _authState.value = AuthState.onFailure(Event("Invalid email"))
            println("Invalid mails")
        }
        else if (contact.length != 11) {
            _authState.value = AuthState.onFailure(Event("Invalid contact number"))
            println("Missing mob")
        }
        else if (password.length != 6) {
            _authState.value = AuthState.onFailure(Event("Password must be 6 characters long"))
            println("Missing pass")
        }

        else{
            _authState.value = AuthState.onLoading

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid
                        val UserData = userData(
                            userId = userId.toString(),
                            name = name,
                            contact = contact,
                            dob = dob
                        )

                        database.child(userId.toString()).setValue(UserData).addOnSuccessListener {
                            _authState.value = AuthState.Authenticated
                        }.addOnFailureListener {
                            _authState.value =
                                AuthState.onFailure(
                                    Event(task.exception?.message ?: "something went wrong")
                                )
                        }

                    } else {
                        _authState.value =
                            AuthState.onFailure(Event(task.exception?.message ?: "something went wrong"))
                    }
                }
        }
    }

    fun UserSignOut(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

}

sealed class AuthState<T> {
    object Authenticated : AuthState<Nothing>()
    object Unauthenticated : AuthState<Nothing>()
    object onLoading : AuthState<Nothing>()
    data class onFailure(val message: Event<String>) : AuthState<Nothing>()
}

data class userData(
    val userId : String,
    val name : String,
    val contact : String,
    val dob : String
        )

class Event<out T>(val content : T){
    var isObserved = false
        private set

    fun getContentIfNotObserved() : T?{
        return if(!isObserved){
            isObserved = true
            content
        }
        else{
            null
        }
    }
}