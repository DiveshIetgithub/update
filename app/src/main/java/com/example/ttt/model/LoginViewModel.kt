package com.example.ttt.model
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ttt.classs.User

class LoginViewModel : ViewModel() {

    private val _mobileNumber = MutableLiveData<String>()
    val mobileNumber: LiveData<String>
        get() = _mobileNumber

    private val _isAuthenticated = MutableLiveData<Boolean>()
    val isAuthenticated: LiveData<Boolean> = _isAuthenticated

    fun login(mobileNumber: String, users: List<User>) {
        _isAuthenticated.value = false
        for (user in users) {
            if (user.mobileNumber == mobileNumber) {
                _isAuthenticated.value = true
                break
            }
        }
    }
    private val _navigateToSignup = MutableLiveData<Boolean>()
    val navigateToSignup: LiveData<Boolean> = _navigateToSignup

    fun navigateToSignup() {
        _navigateToSignup.value = true
    }
    fun onSignupNavigated() {
        _navigateToSignup.value = false
    }

}
