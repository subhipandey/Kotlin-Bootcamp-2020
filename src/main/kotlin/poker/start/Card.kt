package poker.start

class Card(val rank: String, val suit: Suit) {
    companion object {
        fun build(input: String): Card {
            val tenCard = input.startsWith("10")
            val rank = if (tenCard) "10" else input.substring(0, 1)
            val suitAsString = input.substring(if (tenCard) 2 else 1)

            return Card(rank, Suit.valueOf(suitAsString))
        }
    }

    override fun toString(): String {
        return rank + suit
    }
