package edu.nextstep.camp.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun `입력된 피연사자가 없을때, 피연산자 2 버튼을 누르면 화면에 2를 보여준다`() {
        // when : 피연산자 2 버튼을 누르면
        viewModel.num(2)

        // then : 화면에 2를 보여준다
        val actual = viewModel.result.value
        assertThat(actual.toString()).isEqualTo("2")
    }

    @Test
    fun `이전에 8 버튼을 눌렀을 때, 5 버튼을 누르면 화면에 85를 보여준다`() {
        // given : 8 버튼을 눌렀을 때
        viewModel.num(8)

        // when : 5 버튼을 누르면
        viewModel.num(5)

        // then : 화면에 85를 보여준다
        val actual = viewModel.result.value
        assertThat(actual.toString()).isEqualTo("85")
    }

    @Test
    fun `입력된 피연산자가 없을때, 연산자 버튼을 누르면 화면에 아무런 변화가 없다`() {
        // given : 입력된 피연산자가 없을때
        
        // when : 연산자 버튼을 누르면
        
        // then : 화면에 아무런 변화가 없다
    }
    
    @Test
    fun `입력된 피연산자가 있을때, 연산자 버튼을 누르면 해당 기호를 화면에 보여준다`() {
        // given : 입력된 피연산자가 있을때
        
        // when : 연산자 버튼을 누르면
        
        // then : 해당 기호를 화면에 보여준다
    }

    @Test
    fun `입력된 수식이 없을 때, 지우기 버튼을 누르면 화면에 아무런 변화가 없다`() {
        // given : 입력된 수식이 없을때
        
        // when : 지우기 버튼을 누르면
        
        // then : 화면에 아무런 변화가 없다
    }
    
    @Test
    fun `입력된 수식이 있을 때, 지우기 버튼을 누르면 수식에 마지막으로 입력된 것이 지워져야 한다`() {
        // given : 입력된 수식이 있을 때
        
        // when : 지우기 버튼을 누르면
        
        // then : 수식에 마지막으로 입력된 것이 지워져야 한다
    }

    @Test
    fun `입력된 수식이 완전할 때, = 버튼을 누르면 입력된 수식의 결과를 화면에 보여준다`() {
        // given : 입력된 수식이 완전할 때
        
        // when : = 버튼을 누르면
        
        // then : 입력된 수식의 결과를 화면에 보여준다
    }
    
    @Test
    fun `입력된 수식이 완전하지 않을 때, = 버튼을 누르면 완성되지 않은 수식입니다 토스트 메세지를 화면에 보여준다`() {
        // given : 입력된 수식이 완전하지 않을 때
        
        // when : = 버튼을 누르면
        
        // then : 완성되지 않은 수식입니다 토스트 메세지를 화면에 보여준다
    }
}