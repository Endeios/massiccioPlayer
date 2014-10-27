package io.endeios.massiccioPlayer

import java.awt.BorderLayout as BL
import groovy.swing.SwingBuilder
import java.awt.Color
import javax.swing.WindowConstants as WC
import javax.swing.BorderFactory as BF
import javax.swing.JOptionPane
import javax.swing.JFrame
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
swing = new SwingBuilder()

mediaPlayerComponent  = new EmbeddedMediaPlayerComponent();

frame = swing.frame(title:"MassiccioPlayer",size:[500,500],defaultCloseOperation:JFrame.DO_NOTHING_ON_CLOSE){
    lookAndFeel 'nimbus' 
    borderLayout()
    menuBar{
        menu(mnemonic:'f',"File"){
        }
    }        
    panel(border:BF.createEmptyBorder(5,5,5,5)){
        label(constraints:BL.NORTH,text:"NORTH")
    }
    vbox(constraints:BL.NORTH,id:"top"){
        label(text:"Player")
        button("Play",actionPerformed:{
            def filename = "file:///home/bveronesi/Scaricati/Un.Milione.Di.Modi.Per.Morire.Nel.West.2014.1080p.BluRay.iTALiAN.AC3.5.1.640kbps.Dual.x264-TrtD_TeaM.mkv"

            mediaPlayerComponent.getMediaPlayer().playMedia(filename);

        })
    }
    vbox(constraints:BL.CENTER,id:"content"){
        hbox(){
            button(text:"+1",actionPerformed:{ev->println "$ev -> +1"})
            button(text:"-1",actionPerformed:{ev->println "$ev -> +1"})
            button(text:"Start",actionPerformed:{ev->println "$ev -> +1"})
        }
    }


    swing.content.add(mediaPlayerComponent)

}

frame.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(frame, "Are you sure ?") == JOptionPane.OK_OPTION){
                    mediaPlayerComponent.release()
                    frame.setVisible(false);
                    frame.dispose();
                }
            }

})
frame.show()
