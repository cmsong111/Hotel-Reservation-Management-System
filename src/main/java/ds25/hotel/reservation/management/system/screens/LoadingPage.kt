package ds25.hotel.reservation.management.system.screens

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Image
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel

/**
 * 로딩 화면
 */
class LoadingPage : JFrame() {

    private val label = JLabel("SpringBoot is Loading...")
    private val image = ImageIcon(this.javaClass.getResource("/image/loading_image.png")).image

    init {
        title = "Loading"
        setSize(300, 300)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE

        this.layout = BorderLayout()
        label.horizontalAlignment = JLabel.CENTER
        label.preferredSize = Dimension(300, 50)

        add(JLabel(ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH))), BorderLayout.CENTER)
        add(label, BorderLayout.SOUTH)

        isVisible = true
    }
}
