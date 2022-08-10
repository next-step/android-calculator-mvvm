package edu.nextstep.camp.domain.counter

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

@Suppress("NonAsciiCharacters")
class CounterTest {

    private lateinit var counter: Counter

    @Before
    fun setup() {
        counter = Counter()
    }

    @Test
    fun `최초에 카운트 값이 0으로 초기화 되어야 한다`() {
        assertThat(counter.currentCount).isEqualTo(0)
    }

    @Test
    fun `현재 카운트가 0인 상태에서 증가 연산을 수행하면 카운트 값이 1 증가해야 한다`() {
        // when
        counter.countUp()

        // then
        assertThat(counter.currentCount).isEqualTo(1)
    }

    @Test
    fun `현재 카운트가 1인 상태에서 증가 연산을 수행하면 카운트 값이 1 증가해야 한다`() {
        // given
        counter.countUp()

        // when
        counter.countUp()

        // then
        assertThat(counter.currentCount).isEqualTo(2)
    }

    @Test(expected = NegativeCountNotSupported::class)
    fun `현재 카운트가 0인 상태에서 감소 연산을 수행하면 NegativeCountNotSupported 예외가 발생해야 한다`() {
        // when
        counter.countDown()
    }

    @Test
    fun `현재 카운트가 1인 상태에서 감소 연산을 수행하면 카운트 값이 1 감소해야 한다`() {
        // given
        counter.countUp()

        // when
        counter.countDown()

        // then
        assertThat(counter.currentCount).isEqualTo(0)
    }
}