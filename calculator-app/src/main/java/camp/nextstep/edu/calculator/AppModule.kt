package camp.nextstep.edu.calculator

import android.content.Context
import camp.nextstep.edu.calculator.data.DataModule
import camp.nextstep.edu.calculator.domain.RecordRepository

object AppModule {

	fun provideCalculatorViewModel(
		context: Context,
		recordRepository: RecordRepository = DataModule.provideRecordRepository(context),
	): CalculatorViewModel {
		return CalculatorViewModel(recordRepository)
	}
}