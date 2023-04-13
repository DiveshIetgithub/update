package com.example.ttt.model

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ttt.classs.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupViewModel : ViewModel() {

    private val _firstName=MutableLiveData<String>()
    val firstName:LiveData<String>
        get()=_firstName

    private val _lastName=MutableLiveData<String>()
    val lastName:LiveData<String>
        get()=_lastName

    private val _mobileNumber=MutableLiveData<String>()
    val mobileNumber:LiveData<String>
        get()=_mobileNumber

    fun signUp(firstName:String, lastName:String, mobileNumber:String):Boolean {
        // Check if all fields are non-empty
        if (firstName.isBlank() || lastName.isBlank() || mobileNumber.isBlank()) {
            return false
        }

        // Check if mobile number is valid (e.g. starts with a valid country code, etc.)
        if (!isValidMobileNumber(mobileNumber)) {
            return false
        }
        val user = User(firstName, lastName, mobileNumber)
        saveUserToDatabase(user)

        // Return true to indicate successful sign up
        return true
    }

    private fun isValidMobileNumber(mobileNumber: String): Boolean {
        // TODO: Implement mobile number validation logic
        // For example, check if mobile number starts with a valid country code, etc.
        // Return true if mobile number is valid, false otherwise.
        val regex = Regex("^[6-9]\\d{9}\$")
        return regex.matches(mobileNumber)
        return true
    }

    private fun saveUserToDatabase(user: User) {
        // TODO: Implement database logic to save user to database
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userDao.insert(user)
            }
        }
    }

    }


