package io.endeios.massiccioPlayer

import java.awt.BorderLayout as BL
import groovy.swing.SwingBuilder
import java.awt.Color
import javax.swing.WindowConstants as WC
import javax.swing.BorderFactory as BF
import javax.swing.JOptionPane
import javax.swing.JFrame
import javax.swing.JFileChooser
import javax.swing.filechooser.FileFilter
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.filter.VideoFileFilter
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent


import io.endeios.massiccioPlayer.business.*
import io.endeios.massiccioPlayer.model.messages.*
 

swing = new SwingBuilder()

mediaPlayerComponent  = new EmbeddedMediaPlayerComponent();

observer = new FlightObserver()

messager = new FlightMessager()

messager.addObserver(observer)

movieFile = null;

about = swing.action(
	name:		"About",
	mnemonic: 	"A",
	accelerator:	"F1",
	closure:{ev->
		println "$ev"
	}
)

addPoint = swing.action(
    name: "Add point",
    mnemonic: "P",
    accelerator: "F5",
    closure:{
        println "Adding point"
        messager.addPoint()
    }
)

addPenalty = swing.action(
    name: "Add penalty",
    mnemonic: "D",
    accelerator: "F6",
    closure:{
        println "Adding penalty"
        messager.addPenalty()
    }
)

startTimer = swing.action(
    name: "Start Timer",
    mnemonic: "S",
    accelerator: "F2",
    closure:{
        println "Starting Timer"
        messager.startTime()
    }
)

openFile = swing.action(
    name: "Open Video",
    mnemonic: "O",
    accelerator: "ctrl o",
    closure:{
        println "Opening File"
        def fileChooser = swing.fileChooser(
            dialogTitle:"Choose an movie file",
            id:"fileChooser",
            fileSelectionMode:JFileChooser.FILES_AND_DIRECTORIES,
            fileFilter:new FileFilter(){
                VideoFileFilter filter = new VideoFileFilter()
                boolean accept(File f){filter.accept(f)||f.isDirectory()}
                String getDescription(){
                    return "Movie Files"
                }
            }
        )
        if(fileChooser.showOpenDialog()!=JFileChooser.APPROVE_OPTION) return
        movieFile = fileChooser.selectedFile
        
    }
)



frame = swing.frame(title:"MassiccioPlayer",size:[500,500],defaultCloseOperation:JFrame.DO_NOTHING_ON_CLOSE){
    lookAndFeel 'nimbus' 
    borderLayout()
    menuBar{
        menu(mnemonic:'F',"File"){
            menuItem(action:openFile)
            menuItem("Open Race")
            menuItem("Save Race")
        }
        menu(mnemonic:'C',"Actions"){
		    menuItem(action:startTimer)
		    menuItem(action:addPoint)
		    menuItem(action:addPenalty)
        }
        glue()
        menu(mnemonic:'H','Help'){
		    menuItem(action:about)
        }
    }        
    panel(border:BF.createEmptyBorder(5,5,5,5)){
        label(constraints:BL.NORTH,text:"NORTH")
    }
    vbox(constraints:BL.NORTH,id:"top"){
        hbox(){
            label(text:bind(source:observer,sourceProperty:"startDate",converter:
                {v-> v?"Start time: ${v}":"Timer not Started" })
            )
            glue()
            label(text:bind(source:observer,sourceProperty:"pointsCount",converter:
                {v->v?"Points: ${v}":"Points: 0"})
            )
            glue()
            label(text:bind(source:observer,sourceProperty:"penaltiesCount",converter:
                {v->v?"Penalties: ${v}":"Penalties: 0"})
            )
            glue()
            label(text:bind(source:observer,sourceProperty:"secRemaining",converter:
                {v->
                    def secs =(int) v / 1000
                    def mill = v - secs * 1000 
                    "Secs: ${secs}:${mill}"
                })
            )

        }
        /**
         
         */
        button("Play",actionPerformed:{
            
            /*
            def filename = "file:///home/bveronesi/Scaricati/Un.Milione.Di.Modi.Per.Morire.Nel.West.2014.1080p.BluRay.iTALiAN.AC3.5.1.640kbps.Dual.x264-TrtD_TeaM.mkv"
            def filename = "file:///home/bveronesi/projects/Groovate/massiccioPlayer/GOPR0750.MP4"
            mediaPlayerComponent.getMediaPlayer().playMedia(filename);
            */
            mediaPlayerComponent.getMediaPlayer().playMedia(movieFile.toString());

        })
    }
    vbox(constraints:BL.CENTER,id:"content"){
        hbox(){
            button(action:addPoint,id:"addPointButton")
            button(action:addPenalty,id:"addPenaltyButton")
            button(action:startTimer,id:"startTimerButton")
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
