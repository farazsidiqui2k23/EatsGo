package com.example.eatsgo.Firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AuthViewModel : ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().getReference("users")

    val _authState = MutableLiveData<AuthState<Nothing>>()
    val authState: LiveData<AuthState<Nothing>> = _authState


    val _realtimeDB = MutableLiveData<RealtimeDB>()
    val realtimeDB : LiveData<RealtimeDB> = _realtimeDB

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

        if (email.isBlank() && password.isBlank()) {
            _authState.value = AuthState.onFailure(Event("Missing Field"))
        }
        else{
            _authState.value = AuthState.onLoading

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

        }
        else if (!email.contains("@") || !email.contains(".")) {

            _authState.value = AuthState.onFailure(Event("Invalid email"))

        }
        else if (contact.length != 11) {
            _authState.value = AuthState.onFailure(Event("Invalid contact number"))

        }
        else if (password.length != 6) {
            _authState.value = AuthState.onFailure(Event("Password must be 6 characters long"))

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
                            email = email,
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

    fun getProfileData(userId: String){

        val postListner =object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.value as Map<*, *>
                val userdata = userData(
                    userId = data["userId"].toString(),
                    name = data["name"].toString(),
                    email = data["email"].toString(),
                    contact = data["contact"].toString(),
                    dob = data["dob"].toString()
                )
                _realtimeDB.value = RealtimeDB.onSuccess(userdata)
            }
            override fun onCancelled(error: DatabaseError) {
                _realtimeDB.value = RealtimeDB.onFailure(error.toString())
            }

        }

        _realtimeDB.value = RealtimeDB.onLoading
        database.child(userId).addListenerForSingleValueEvent(postListner)
    }

}

sealed class AuthState<T> {
    object Authenticated : AuthState<Nothing>()
    object Unauthenticated : AuthState<Nothing>()
    object onLoading : AuthState<Nothing>()
    data class onFailure(val message: Event<String>) : AuthState<Nothing>()
}

sealed class RealtimeDB{
    data class onSuccess(val userData: userData) : RealtimeDB()
    data class onFailure(val message : String) : RealtimeDB()
    object onLoading : RealtimeDB()
}

data class userData(
    val userId : String,
    val name : String,
    val email : String,
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