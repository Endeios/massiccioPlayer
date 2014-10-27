    package io.endeios.massiccioPlayer;

    import javax.swing.JFrame;
    import javax.swing.SwingUtilities;

    import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;



    public class MassiccioTest {

        private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

        public static void main(final String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new MassiccioTest(args);
                }
            });
        }

        private MassiccioTest(String[] args) {
            JFrame frame = new JFrame("vlcj Tutorial");

            mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

            frame.setContentPane(mediaPlayerComponent);

            frame.setLocation(100, 100);
            frame.setSize(1050, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            def filename = "file:///home/bveronesi/Scaricati/Un.Milione.Di.Modi.Per.Morire.Nel.West.2014.1080p.BluRay.iTALiAN.AC3.5.1.640kbps.Dual.x264-TrtD_TeaM.mkv"
            mediaPlayerComponent.getMediaPlayer().playMedia(filename);
        }
    }
