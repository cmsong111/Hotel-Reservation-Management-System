package ds25.hotel.reservation.management.system.global.config

import ds25.hotel.reservation.management.system.global.common.BusinessException
import java.awt.AWTEvent
import java.awt.EventQueue
import javax.swing.JOptionPane

class CustomEventQueue : EventQueue() {
    override fun dispatchEvent(event: AWTEvent) {
        try {
            super.dispatchEvent(event)
        } catch (e: BusinessException) {
            e.printStackTrace()
            JOptionPane.showMessageDialog(null, e.message, e.javaClass.simpleName, JOptionPane.ERROR_MESSAGE)
        }
    }
}
