package com.example.ankitjadavpracticaltest.ui.login

import android.util.Patterns
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ankitjadavpracticaltest.Utils.Consts
import com.example.ankitjadavpracticaltest.data.entity.User

class LoginViewModel : ViewModel() {

    var strUsername:String=""
    var strPassword:String=""

    private var loginUser =  MutableLiveData<User>()

    fun getUser() : LiveData<User>{
        return loginUser
    }

    fun onLoginClick(view: View){
        loginUser.value=User(strUsername,strPassword)
    }

    fun isUsernameValid() : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(strUsername).matches();
    }

    fun isValidCredentials() : Boolean {
        return strUsername == Consts.USERNAME && strPassword == Consts.PASSWORD
    }

}