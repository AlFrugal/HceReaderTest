
import java.util.List;
import javax.smartcardio.*;

public class HceReaderClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Frugal HCE Reader");
		try {
			// Display the list of terminals
			TerminalFactory factory = TerminalFactory.getDefault();
			List<CardTerminal> terminals = factory.terminals().list();
			System.out.println("Lecteur : " + terminals);


			// Use the first terminal
			CardTerminal terminal = terminals.get(0);
			
			System.out.println("Posez votre mobile NFC-HCE sur le lecteur");	
			terminal.waitForCardPresent(0);

			// Connect with the card
			Card card = terminal.connect("*");
			System.out.println("card: " + card);
			CardChannel channel = card.getBasicChannel();	
			byte[] smartaccess= {0x00, (byte) 0xA4, 0x04,0x00,0x07,(byte) 0xF0, 0x46, 0x52, 0x55, 0x47, 0x41, 0x4C};
			System.out.println(" Envoi de la commande SELECT AID : 00 A4 04 00 07 F0 46 52 55 47 41 4C ");
			ResponseAPDU selectAIDa = channel.transmit(new CommandAPDU(smartaccess));
			System.out.println("Réponse au Select AID " + Response (selectAIDa));	
	
		
					// Disconnect the card
					card.disconnect(false);




			} catch(Exception e) {
				
				
				
				System.out.println("Probleme lors de la connexion: " + e.toString());

			}
		}

		public static String Response (ResponseAPDU r)

		{
			String response ="";
			for (int i=0; i< r.getBytes().length ; i++)

			{ response += " " + String.format("%02X",r.getBytes()[i]) ;}


			return response;
		}






	}


