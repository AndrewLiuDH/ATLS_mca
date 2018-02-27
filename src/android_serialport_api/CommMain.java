package android_serialport_api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import android.content.Context;
import android.widget.Toast;

public class CommMain {

	private SerialPort mSerialPort = null;
	private InputStream mInputStream;
	private OutputStream mOutputStream;
	private ReadThread mReadThread;

	public void Comm_Init(Context _context) {
		try {
			mSerialPort = getSerialPort(_context);
			mInputStream = mSerialPort.getInputStream();
			mOutputStream = mSerialPort.getOutputStream();

			mReadThread = new ReadThread();//�������ݵ��߳�
			mReadThread.start();
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			Toast.makeText(_context, "�򿪴���ʧ�ܣ���Ч����",Toast.LENGTH_SHORT).show();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			Toast.makeText(_context, "�򿪴���ʧ�ܣ�Ȩ������",Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(_context, "�򿪴���ʧ�ܣ�IO�쳣",Toast.LENGTH_SHORT).show();
		}

	}

	private SerialPort getSerialPort(Context _context) throws SecurityException, IOException,
			InvalidParameterException {
		if (mSerialPort == null) {
			
				mSerialPort = new SerialPort("/dev/ttyS1", 9600, 0);
			
		}
		return mSerialPort;
	}

	public void SendCmd(byte[] _cmd) {
		try {
			mOutputStream.write(_cmd);//��������
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class ReadThread extends Thread {
		public void run() {
			super.run();
			if (mInputStream == null)
				return;
			while (!isInterrupted()) {
				try {
					System.out.println(mInputStream.read());//�����������
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}

	public void Comm_Exit() {
		if (mReadThread != null)
			mReadThread.interrupt();
		closeSerialPort();
	}


	public void closeSerialPort() {
		if (mSerialPort != null) {
			mSerialPort.close();
			mSerialPort = null;
		}
	}
}
