package com.example.calculator.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import io.reactivex.Completable
import io.reactivex.Observable


class NetworkService  {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val firebaseDatabaseReference by lazy{
        FirebaseDatabase.getInstance().reference
    }


    fun login(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun currentUser() = firebaseAuth.currentUser

    fun uploadHistoryData(historyList: ArrayList<String>) = Completable.create{
        emitter ->
       val id =  firebaseAuth.currentUser?.uid
        Log.d("Network", "Adding history  $id ")

        firebaseDatabaseReference.child(id!!).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                emitter.onError(p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {
                emitter.onComplete()
            }
        })
        firebaseDatabaseReference.child(id).setValue(historyList)

    }

    fun getHistoryData() = Observable.create<HistoryList>{
            emitter ->
        val id =  firebaseAuth.currentUser?.uid
        Log.d("Network", "Emitting  history  $id ")

        firebaseDatabaseReference.child(id!!).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                emitter.onError(p0.toException())
            }

            override fun onDataChange(p0: DataSnapshot) {
                var historyList = HistoryList()
                for(snapshot in p0.children) {

                    val data = snapshot.value as String
                    historyList.add(data)

                }

                emitter.onNext(historyList)
                emitter.onComplete()
            }
        })
        firebaseDatabaseReference.child(id).orderByKey()

    }



}