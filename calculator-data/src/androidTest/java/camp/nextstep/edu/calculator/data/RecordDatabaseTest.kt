package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import camp.nextstep.edu.calculator.domain.RecordResource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecordDatabaseTest {

	private lateinit var recordDatabase: RecordDatabase

	@Before
	fun setUp() {
		val context = ApplicationProvider.getApplicationContext<Context>()

		recordDatabase = Room.inMemoryDatabaseBuilder(context, RecordDatabase::class.java).build()
	}

	@After
	fun tearDown() {
		recordDatabase.close()
	}

	@Test
	fun `계산기록을_추가하면__계산기록이_추가된다`() = runBlocking {
		// given
		val recordEntity = RecordEntity(1, "1 + 2", 3)

		// when
		recordDatabase.recordDao().insert(recordEntity)

		// then
		recordDatabase.recordDao().getAll().test {
			val actual = awaitItem()
			assertEquals(listOf(recordEntity), actual)
		}
	}


	@Test
	fun 계산기록이_이미_존재할때__계산기록을_전부_가져오면__id_오름차순으로_전부_가져온다() = runBlocking {
		// given
		val recordEntity1 = RecordEntity(1, "1 + 2", 3)
		val recordEntity2 = RecordEntity(2, "3 + 4", 7)
		val recordEntity3 = RecordEntity(3, "5 + 6", 11)
		recordDatabase.recordDao().insert(recordEntity2)
		recordDatabase.recordDao().insert(recordEntity3)
		recordDatabase.recordDao().insert(recordEntity1)

		// when
		recordDatabase.recordDao().getAll().test {
			// then
			val actual = awaitItem()
			assertEquals(listOf(recordEntity1, recordEntity2, recordEntity3), actual)
		}
	}
}