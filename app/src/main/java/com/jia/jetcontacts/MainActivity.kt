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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jia.jetcontacts.ui.theme.JetContactsTheme

class MainActivity : ComponentActivity() {

    private val contacts by lazy {
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetContactsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    var name by remember { mutableStateOf("") }
                    ContactList(contacts = contacts, name = name, onNameChange = {
                        name = it
                    })
                }
            }
        }
    }
}

@Composable
fun ContactList(contacts: List<Contact>, name: String, onNameChange: (String) -> Unit) {
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = name,
            onValueChange = onNameChange,
        )

        LazyColumn {
            items(contacts.filter { contact ->
                contact.name.contains(
                    name,
                    ignoreCase = true
                )
            }, key = {
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