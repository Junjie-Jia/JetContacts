package com.jia.jetcontacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jia.jetcontacts.ui.theme.JetContactsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetContactsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ContactList()
                }
            }
        }
    }
}

@Composable
fun ContactList(contactsViewModel: ContactsViewModel = ContactsViewModel()) {
    val name: String by contactsViewModel.name.observeAsState("")
    val contacts: List<Contact> by contactsViewModel.contacts.observeAsState(initial = emptyList())
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = name,
            onValueChange = { contactsViewModel.onNameChange(it) }
        )
        LazyColumn {
            items(contacts, key = {
                it.phoneNumber
            }) { contact ->
                ContactRow(contact)
            }
        }
    }
}

@Composable
fun ContactRow(contact: Contact) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            style = typography.h5,
            text = contact.name,
            modifier = Modifier.padding(16.dp, 2.dp, 0.dp, 0.dp),
        )
        Text(
            color = typography.subtitle2.color.copy(alpha = 0.4f),
            text = contact.phoneNumber,
            modifier = Modifier.padding(16.dp, 2.dp, 0.dp, 4.dp)
        )
        Divider(
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetContactsTheme {
        ContactRow(Contact("Liam", "13888888801"))
    }
}