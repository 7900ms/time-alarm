import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MyTimer {
	Timer timer;
	TimerTask task;
	int time;
	JTextField minute;
	JTextField second;
	String min_str;
	String sec_str;
	
	public MyTimer() {
		
	}

	public void start(JTextField minutefield, JTextField secondfield, JFrame frame) {
		minute = minutefield;
		second = secondfield;
		
		min_str = minute.getText().equals("") ? "0" : minute.getText();
		sec_str = second.getText().equals("") ? "0" : second.getText();
		
		int min_num = Integer.parseInt(min_str);
		int sec_num = Integer.parseInt(sec_str);
		time = min_num * 60 + sec_num;
		if(time == 0) {
			return;
		}
		minute.setEditable(false);
		second.setEditable(false);
		timer = new Timer();
		task = new TimerTask() {
			public void run() {
				time--;
				minute.setText(String.valueOf(time / 60));
				second.setText(String.valueOf(time % 60));
				if(time == 0) {
					frame.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(frame, "时间到了！");
					stop(frame);
				}
			}
		};
		timer.scheduleAtFixedRate(task, 1000, 1000);
		
	}

	public void stop(JFrame frame) {
		if(timer != null) {
			timer.cancel();
			timer.purge();
			clearTime();
			minute.setEditable(true);
			second.setEditable(true);
			frame.setAlwaysOnTop(false);
		}
		
		
	}
	
	private void clearTime() {
		minute.setText("");
		second.setText("");
		
	}

}
