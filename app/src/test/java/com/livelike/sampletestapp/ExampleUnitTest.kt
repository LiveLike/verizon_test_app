package com.livelike.sampletestapp

import com.google.gson.Gson
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val jsonString = """{"name": "shivam", "id": 20}"""
        val gson = Gson()
        val st = gson.fromJson(jsonString, Student::class.java)
        println("ST>>${st.name}")
        assert(st.name != null)
    }


}

data class Student(val id: Int) {
    var name: String? = null
}