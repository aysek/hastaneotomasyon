package Helper;

import javax.swing.JOptionPane;

public class Helper {
	public static void showMsg(String str) {
		String msg;
		switch(str) {
		case "fill":
			msg= "l�tfen t�m alanlar� doldurun. ";
			break;
		case "success":
			msg="i�lem Ba�ar�l� !";
		break;
			default:
				msg = str;
				
		}
		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
		
	}
}
