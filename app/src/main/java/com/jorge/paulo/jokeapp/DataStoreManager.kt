package com.jorge.paulo.jokeapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore


import kotlinx.coroutines.flow.map



data class PhoneBook(
    val name: String,
    val phone: String,
    val address: String,
)

class DataStoreManager constructor(val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

    companion object {
        val NAME = stringPreferencesKey("NAME")
        val PHONE_NUMBER = stringPreferencesKey("PHONE_NUMBER")
        val ADDRESS = stringPreferencesKey("ADDRESS")
    }

    suspend fun saveDataStore(phonebook: PhoneBook) {
        context.dataStore.edit {
            it[NAME] = phonebook.name
            it[PHONE_NUMBER] = phonebook.phone
            it[ADDRESS] = phonebook.address
        }
    }

    suspend fun getFromDataStore() = context.dataStore.data.map {
        PhoneBook(
            name = it[NAME]?:"",
            phone = it[PHONE_NUMBER]?:"",
            address = it[ADDRESS]?:""
        )
    }
}
/*
TO USE
 */
/*
GlobalScope.launch(Dispatchers.IO) {
     dataStoreManager.savetoDataStore(User("Sharon", "3526281", "Bangalore"))
}
GlobalScope.launch(Dispatchers.IO) {
    dataStoreManager.getFromDataStore().collect
userDetailsText.setText(it.name + " " + it.phone +" "+ it.address)
    }
}
 */