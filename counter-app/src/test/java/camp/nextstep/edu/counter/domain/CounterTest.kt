package camp.nextstep.edu.counter.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

class CounterTest {

    @Test
    fun `0보다 작은 음수는 올 수 없다`() {
        assertThrows(IllegalArgumentException::class.java) {
            Counter(-1)
        }
    }

    @Test
    fun `0에서 1 증가`() {
        val actual = Counter(0)
        assertThat(actual+1).isEqualTo(Counter(1))
    }

    @Test
    fun `1에서 0 감소`() {
        val actual = Counter(1)
        assertThat(actual-1).isEqualTo(Counter(0))
    }
}