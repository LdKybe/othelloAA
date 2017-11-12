import java.awt.*;
import java.awt.event.*;

public class MyFrame extends Frame implements WindowListener {

	/**�R���X�g���N�^*/
	public MyFrame(String frameName) {

		//�t���[���̃^�C�g����ݒ�
		setTitle(frameName);

		//�X���[���Ŕ��������C�x���g�ʒm�p�Ƃ��ēo�^
		addWindowListener(this);
	}

	/**�E�B���h�E���N���[�Y�������̏���*/
	public void windowClosing(WindowEvent evt) {

		dispose();	//�E�B���h�E�����
	}

	/*�E�B���h�E�N���[�Y��̏���*/
	public void windowClosed(WindowEvent evt) {

		System.exit(0);	//�v���O�����I��
	}

	/**���̑��̃E�B���h�E����*/
	public void windowDeiconified(WindowEvent evt) { }
	public void windowIconified(WindowEvent evt) { }
	public void windowOpened(WindowEvent evt) { }
	public void windowActivated(WindowEvent evt) { }
	public void windowDeactivated(WindowEvent evt) { }

	/**main()���\�b�h*/
	public static void main(String[] args) {

		MyFrame frame = new MyFrame("MyFrameTest");
		frame.setSize(200,100);
		frame.setVisible(true);
	}
}
