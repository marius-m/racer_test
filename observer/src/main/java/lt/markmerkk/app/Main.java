package lt.markmerkk.app;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_76;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author mariusmerkevicius
 * @since 2016-10-18
 */
public class Main {

    private static WebSocketClient cc;

    public static void main(String[] args) {
        try {
            // cc = new ChatClient(new URI(uriField.getText()), area, ( Draft ) draft.getSelectedItem() );
            cc = new WebSocketClient( new URI("http://localhost:8887"), new Draft_76()) {

                @Override
                public void onMessage( String message ) {
                    System.out.println( "got: " + message + "\n" );
                }

                @Override
                public void onOpen( ServerHandshake handshake ) {
                    System.out.println( "You are connected to ChatServer: " + getURI() + "\n" );
                }

                @Override
                public void onClose( int code, String reason, boolean remote ) {
                    System.out.println( "You have been disconnected from: " + getURI() + "; Code: " + code + " " + reason + "\n" );
                }

                @Override
                public void onError( Exception ex ) {
                    System.out.println( "Exception occured ...\n" + ex + "\n" );
                    ex.printStackTrace();
                }
            };
            cc.connect();
        } catch ( URISyntaxException ex ) {
            ex.printStackTrace();
        }
    }

}
