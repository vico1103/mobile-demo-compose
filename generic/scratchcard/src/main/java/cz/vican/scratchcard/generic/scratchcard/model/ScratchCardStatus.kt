package cz.vican.scratchcard.generic.scratchcard.model


data class ScratchCardStatus(val status: Status, val code: String = "") {
    enum class Status {
        UNSCRATCHED, REVEALED, REDEEMED
    }
}
