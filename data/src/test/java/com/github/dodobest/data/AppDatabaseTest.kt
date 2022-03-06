package com.github.dodobest.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.github.dodobest.data.util.TestUtil
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class AppDatabaseTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var resultRecordDao: ResultRecordDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        resultRecordDao = db.resultRecordDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun `데이터를 넣으면 저장된다`() {
        // given : "3+3", "6"을 가진 ResultRecord가 있을때
        val expression = "3+3"
        val result = "6"
        val resultRecord: ResultRecordEntity = TestUtil.createEmptyResultRecord().copy(expression, result)

        // when : DB에 넣으면
        resultRecordDao.insertResultRecord(resultRecord)

        // then : "3+3", "6"을 가진 ResultRecord가 저장되어야 한다
        val byExpression = resultRecordDao.findResultRecordByExpression(expression)
        assertThat(byExpression).isEqualTo(resultRecord)
    }
}