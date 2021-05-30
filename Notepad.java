package notepad.step03;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Notepad extends JFrame implements WindowListener {
	// �������
	public JTextArea ta = new JTextArea();
	JFileChooser chooser = new JFileChooser();
	JMenuBar mb = new JMenuBar();
	String fileName = "";
	// ������
	public Notepad() {
		
		this.setTitle("Notepad");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		

		// 2. �޴�����
		String[] smenu = { "����" };
		JMenu[] mfile = new JMenu[10];
		for (int i = 0; i < smenu.length; i++) {
			mfile[i] = new JMenu(smenu[i]);
			mb.add(mfile[i]);
		}


		String[] ScrItem = { "������", "����", "����", "�ٸ��̸���������", "����" };
		JMenuItem[] item = new JMenuItem[5];
		for (int i = 0; i < ScrItem.length; i++) {
			item[i] = new JMenuItem(ScrItem[i]);
			// 1. �̺�Ʈ �ҽ�: JMenuItem
			// 2. �̺�Ʈ ����: ActionEvent
			// 3. ������ ����: ActionListener - > ����������
			// 4. ������ ����
			NoteActionListener nal = new NoteActionListener(this);
			item[i].addActionListener(nal);
			// 4. �޴��� ����
			this.setJMenuBar(mb);
			mfile[0].add(item[i]);

		}
		
		// ������Ʈ �߰�
		this.add(ta);

		this.setVisible(true);
	}
	/* ������ */
	public void newFile() {
		if(!ta.getText().equals("")) { //ta�ȿ� ������ ������ �����ϵ��� ����
			int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "���", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION ) {
				saveFile(ta.getText());
			} else {
				ta.setText("");
			}
		
		}
		
	}

	/* ���� */
	public void openFile() {

		int ret = chooser.showOpenDialog(null);

		if (ret != JFileChooser.APPROVE_OPTION) {

			JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
			return;

		} else {
			File inFile = chooser.getSelectedFile();
			BufferedReader in;
			try {
				in = new BufferedReader(new FileReader(inFile));
				String c;
				ta.setText("");
				while ((c = in.readLine()) != null) {
					ta.append(c + "\r\n"); //�ٹٲ�
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		fileName = chooser.getSelectedFile().toString();
		setTitle(chooser.getSelectedFile().getName());
	}

	/* ���� ���� */
	public void saveFile(String fn) {   //����
	      BufferedWriter out = null;
	      File file = new File(fileName);
	      try {
	         out = new BufferedWriter(new FileWriter(file));
	         out.write(fn);
	         this.setTitle(file.getName());
	         out.close();
	      }
	      catch(IOException e) {
	         e.printStackTrace();
	      }
	   }

	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/* ����� �����ϰ� �����ϵ��� ���� */
	@Override
	public void windowClosing(WindowEvent e) {
		if(!ta.getText().equals("")) { //ta�ȿ� ������ ������ �����ϵ��� ����
			int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "���", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION ) {
				int ret = chooser.showSaveDialog(null);
				if (ret == JFileChooser.APPROVE_OPTION) {
					fileName = chooser.getSelectedFile().getPath();
					saveFile(ta.getText());
				}
			} else {
				System.exit(0);
			}
		
		}else {
			System.exit(0);
		}
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new Notepad();
	}

}
