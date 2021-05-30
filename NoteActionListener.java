package notepad.step03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class NoteActionListener implements ActionListener {
	// �������
	private Notepad frm;
	String cmd;
	// ������
	public NoteActionListener(Notepad n) {
		this.frm = n;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cmd = e.getActionCommand();
		switch (cmd) {

		case "������":
			frm.newFile();
			break;
		case "����":
			frm.openFile();
			break;
		case "����":
			if(frm.fileName.equals("")) { //�ٸ��̸����� ����� �Ϲ� ������ �����ϱ� ����, ó�� ����� �Ϲ������ư�� �������� chooser�� �������� ��
	               int ret = frm.chooser.showSaveDialog(null);
	               
	               if(ret != frm.chooser.APPROVE_OPTION) {
	                  JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�!", "���", JOptionPane.WARNING_MESSAGE);
	                  return;
	               }
	               
	               frm.fileName = frm.chooser.getSelectedFile().getPath();
	            }
	            frm.saveFile(frm.ta.getText()); //����
	            
	            break;
		case "�ٸ��̸���������": //������ chooser�� ���̾�α� ��� ����
				int ret = frm.chooser.showSaveDialog(null);
				if (ret == JFileChooser.APPROVE_OPTION) {
					frm.fileName = frm.chooser.getSelectedFile().getPath();
					frm.saveFile(frm.ta.getText());
				}
				
			break;
		case "����":
			frm.windowClosing(null);
			break;
		}

	}

}
