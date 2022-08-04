package edu.nextstep.camp.calculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 08. 02..
 */
@Database(entities = [CalculationRecord::class], version = 1)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun calculationRecordsDao(): CalculationRecordDao

    companion object {
        fun create(context: Context): CalculatorDatabase = Room.databaseBuilder(
            context.applicationContext,
            CalculatorDatabase::class.java,
            "calculationRecord.db"
        ).build()
    }
}