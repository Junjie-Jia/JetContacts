package com.jia.jetcontacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactsViewModel : ViewModel() {

    private val _contacts by lazy {
        listOf(
            Contact("Liam", "13888888801"),
            Contact("Noah", "13888888802"),
            Contact("William", "13888888803"),
            Contact("James", "13888888804"),
            Contact("Logan", "13888888805"),
            Contact("Benjamin", "13888888806"),
            Contact("Mason", "13888888807"),
            Contact("Elijah", "13888888808"),
            Contact("Oliver", "13888888809"),
            Contact("Jacob", "13888888810"),
            Contact("Emma", "13888888811"),
            Contact("Olivia", "13888888812"),
            Contact("Ava", "13888888813"),
            Contact("Isabella", "13888888814"),
            Contact("Sophia", "13888888815"),
            Contact("Mia", "13888888816"),
            Contact("Charlotte", "13888888817"),
            Contact("Amelia", "13888888818"),
            Contact("Evelyn", "13888888819"),
            Contact("Abigail", "13888888820")
        )
    }


    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    val contacts = MutableLiveData(_contacts)

    fun onNameChange(newName: String) {
        _name.value = newName
        viewModelScope.launch {
            contacts.value = filterContacts(newName)
        }
    }

    private suspend fun filterContacts(newName: String): List<Contact> {
        return withContext(Dispatchers.Default) {
            _contacts.filter {
                it.name.contains(
                    newName,
                    ignoreCase = true
                )
            }
        }
    }
}