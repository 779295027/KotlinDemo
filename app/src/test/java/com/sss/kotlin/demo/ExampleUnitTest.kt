package com.sss.kotlin.demo

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() {
        var list = listOf<String>("1", "2", "3")

        val sss = listOf<String>("a_b_c_d-d", "e_f_g-2")
        sss.flatMap {
            it.split("_")
        }.flatMap {
            it.split("-")
        }.map {
            println(it)
        }

        val s = "{\"id\":0,\"name\":\"名字\"}"
        println(Gson().fromJson<User>(s, object : TypeToken<User>() {}.type))
    }

    data class User(var id: Int, var name: String)
}
