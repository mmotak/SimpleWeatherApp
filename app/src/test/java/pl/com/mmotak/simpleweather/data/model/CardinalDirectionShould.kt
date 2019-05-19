package pl.com.mmotak.simpleweather.data.model

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class CardinalDirectionShould(private val degrees: Double, private val direction: CardinalDirection) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(349, CardinalDirection.N),
                arrayOf(10, CardinalDirection.N),
                arrayOf(0, CardinalDirection.N),

                arrayOf(12, CardinalDirection.NNE),
                arrayOf(33, CardinalDirection.NNE),
                arrayOf(16, CardinalDirection.NNE),

                arrayOf(34, CardinalDirection.NE),
                arrayOf(56, CardinalDirection.NE),
                arrayOf(45, CardinalDirection.NE),

                arrayOf(57, CardinalDirection.ENE),
                arrayOf(78, CardinalDirection.ENE),
                arrayOf(70, CardinalDirection.ENE),

                arrayOf(79, CardinalDirection.E),
                arrayOf(101, CardinalDirection.E),
                arrayOf(88, CardinalDirection.E),

                arrayOf(102, CardinalDirection.ESE),
                arrayOf(123, CardinalDirection.ESE),
                arrayOf(113, CardinalDirection.ESE),

                arrayOf(124, CardinalDirection.SE),
                arrayOf(146, CardinalDirection.SE),
                arrayOf(133, CardinalDirection.SE),

                arrayOf(147, CardinalDirection.SSE),
                arrayOf(168, CardinalDirection.SSE),
                arrayOf(158, CardinalDirection.SSE),

                arrayOf(169, CardinalDirection.S),
                arrayOf(191, CardinalDirection.S),
                arrayOf(187, CardinalDirection.S),

                arrayOf(192, CardinalDirection.SSW),
                arrayOf(213, CardinalDirection.SSW),
                arrayOf(201, CardinalDirection.SSW),

                arrayOf(214, CardinalDirection.SW),
                arrayOf(236, CardinalDirection.SW),
                arrayOf(222, CardinalDirection.SW),

                arrayOf(237, CardinalDirection.WSW),
                arrayOf(258, CardinalDirection.WSW),
                arrayOf(245, CardinalDirection.WSW),

                arrayOf(259, CardinalDirection.W),
                arrayOf(281, CardinalDirection.W),
                arrayOf(275, CardinalDirection.W),

                arrayOf(282, CardinalDirection.WNW),
                arrayOf(303, CardinalDirection.WNW),
                arrayOf(298, CardinalDirection.WNW),

                arrayOf(304, CardinalDirection.NW),
                arrayOf(326, CardinalDirection.NW),
                arrayOf(312, CardinalDirection.NW),

                arrayOf(327, CardinalDirection.NNW),
                arrayOf(348, CardinalDirection.NNW),
                arrayOf(333, CardinalDirection.NNW)
            )
        }
    }

    @Test
    fun shouldReturnExpectedRomanForArabic() {
        Assert.assertEquals(direction, CardinalDirection.fromDegrees(degrees))
    }

}