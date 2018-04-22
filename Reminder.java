import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Reminder implements ActionListener {
	JFrame frame;
	JButton start;
	JButton stop;
	
	JTextField minute;
	JLabel minute_text;
	JTextField second;
	JLabel second_text;
	
	Timer timer;
	TimerTask task;
	Boolean isStart;		//控制点击开始后开始按钮不再可用
	int time;
	
	
	Reminder() {
		//页面布局
		minute = new JTextField();
		minute.setBounds(50, 30, 30, 30);
		minute_text = new JLabel("分");
		minute_text.setBounds(80, 30, 30, 30);
		
		second = new JTextField();
		second.setBounds(100, 30, 30, 30);
		second_text = new JLabel("秒");
		second_text.setBounds(130, 30, 30, 30);
		
		start = new JButton("开始");
		start.setBounds(25, 85, 60, 40);
		stop = new JButton("停止");
		stop.setBounds(105, 85, 60, 40);
		
		//按钮加上监听器
		start.addActionListener(this);	
		stop.addActionListener(this);	
		
		frame = new JFrame("alarm");
		frame.add(minute);
		frame.add(minute_text);
		frame.add(second);
		frame.add(second_text);
		frame.add(start);
		frame.add(stop);
		frame.setSize(210, 190);
		frame.setLocation(600, 300);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	//点击关闭后使程序退出，默认为HIDE_ON_CLOSE
		frame.getRootPane().setDefaultButton(start);	//监听回车，触发开始
		isStart = false;
		frame.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//点击开始时操作
		if(e.getSource() == start) {
			if(!isStart) {
				isStart = true;
				start();
			}
		}	
			
		//点击停止时操作
		if(e.getSource() == stop) {
			stop();
		}
	}
	
	
	private void start() {
		//不输入则默认为0
		String min_str = minute.getText().equals("") ? "0" : minute.getText();
		String sec_str = second.getText().equals("") ? "0" : second.getText();
		
		int min_num = Integer.parseInt(min_str);
		int sec_num = Integer.parseInt(sec_str);
		time = min_num * 60 + sec_num;
		
		if(time == 0) {
			return;
		}
		//使输入框不再能编辑
		minute.setEditable(false);
		second.setEditable(false);
		
		//利用Timer类实现计时
		timer = new Timer();
		task = new TimerTask() {
			public void run() {
				time--;
				minute.setText(String.valueOf(time / 60));
				second.setText(String.valueOf(time % 60));
				//计时结束 停止计时 弹出窗口 
				if(time == 0) {
					frame.setAlwaysOnTop(true);							//将窗口置顶，消息窗口随之置顶
					JOptionPane.showMessageDialog(frame, "时间到了！");
					stop();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}
	
	private void stop() {
		//取消计时任务 恢复开始按钮
		if(timer != null) {
			timer.cancel();
			timer.purge();
			clearTime();		//清空输入栏，方便输入
			minute.setEditable(true);
			second.setEditable(true);
			frame.setAlwaysOnTop(false);
			isStart = false;
		}
	}
	
	private void clearTime() {
		minute.setText("");
		second.setText("");	
	}
	
	public static void main(String[] args) {
		new Reminder();
	}
}
