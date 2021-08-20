package com.proway.mvvm_auth.model

import com.google.common.truth.Truth.assertThat
import com.google.firebase.firestore.DocumentSnapshot
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class BillTest {

    @Spy
    @InjectMocks
    private var mockBill = Bill(null, null, null)

    @Mock
    lateinit var documentSnapshot: DocumentSnapshot

    @Before
    fun setup() {
        `when`(mockBill.uid).thenReturn(UUID.randomUUID().toString())
        `when`(mockBill.price).thenReturn(10.0)
//        `when`(mockBill.fromDocument(documentSnapshot)).thenReturn(
//            Bill(
//                UUID.randomUUID().toString(),
//                "Name",
//                10.0
//            )
//        )

    }

    @Test
    fun `valor é maior que zero retornar false`() {
        val result = mockBill.uid
        assertThat(result).isNotEmpty()
    }

    @Test
    fun `valor é maior que zero retornar true`() {

//        val result = mockBill.fromDocument(documentSnapshot)
//        println(result)
//        assertThat(result).isTrue()
    }

    @Test
    fun `valor é nullo retornar false`() {
        val result = mockBill.validarConta()
        assertThat(result).isFalse()
    }
}