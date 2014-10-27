package io.endeios.massiccioPlayer

import uk.co.caprica.vlcj.binding.LibVlc
import uk.co.caprica.vlcj.runtime.RuntimeUtil
import com.sun.jna.Native
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent
import javafx.embed.swing.SwingNode

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.FlowLayout
import java.awt.Dimension


Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

filenme = "file:///home/bveronesi/Scaricati/Un.Milione.Di.Modi.Per.Morire.Nel.West.2014.1080p.BluRay.iTALiAN.AC3.5.1.640kbps.Dual.x264-TrtD_TeaM.mkv"
import static groovyx.javafx.GroovyFX.start
def swing_node = null;
start {
    stage(title: 'MassiccioPlayer', visible: true) {
        scene(fill: BLACK, width: 600, height: 600) {
            a= hbox(padding: 60) {
                lab = label(text:"Cialtronz Productions presents!")
                swing_node = new SwingNode()
                swing_node.prefWidth(500)
                swing_node.prefHeight(500)
                swing_node.minWidth(500)
                swing_node.minHeight(500)


                def a_pane = new JPanel()
//                a_pane.setLayout(new FlowLayout())
                a_pane.setSize(500,500)
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        swing_node.setContent(a_pane)
                        def innerCanvas = mediaPlayerComponent.getVideoSurface()
                        innerCanvas.setSize(400,400)
                        a_pane.add(innerCanvas)
                    }
                })
            }
        }
    }
                def b = a.getChildren().add(swing_node)
    mediaPlayerComponent.getMediaPlayer().playMedia(filenme)
}
