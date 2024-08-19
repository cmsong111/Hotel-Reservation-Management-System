package ds25.hotel.reservation.management.system.global.common.fragment

import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

class MyJMenuBar : JMenuBar() {
    val jMenuFile = JMenu("File")
    val jItemNew = JMenuItem("New")
    val jItemOpen = JMenuItem("Open")
    val jItemSave = JMenuItem("Save")
    val jItemSaveAs = JMenuItem("Save As")
    val jMenuEdit = JMenu("Edit")
    val jMenuView = JMenu("View")
    val jMenuHelp = JMenu("Help")

    init {
        jMenuFile.add(jItemNew)
        jMenuFile.add(jItemOpen)
        jMenuFile.add(jItemSave)
        jMenuFile.add(jItemSaveAs)
        this.add(jMenuFile)


        this.add(jMenuEdit)
        this.add(jMenuView)
        this.add(jMenuHelp)
    }
}
