import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reminder implements ActionListener {
	JFrame frame;
	JButton start;
	JButton stop;
	
	JTextField minute;
	JLabel minute_text;
	JTextField second;
	JLabel second_text;
	
	MyTimer timer;
	boolean isStart;
	
	Reminder() {
		
		minute = new JTextField();
		minute.setBounds(50, 30, 30, 30);
		minute_text = new JLabel("·Ö");
		minute_text.setBounds(80, 30, 30, 30);
		
		second = new JTextField();
		second.setBounds(100, 30, 30, 30);
		second_text = new JLabel("Ãë");
		second_text.setBounds(130, 30, 30, 30);
		
		start = new JButton("¿ªÊ¼");
		start.setBounds(25, 85, 60, 40);
		start.addActionListener(this);
		stop = new JButton("Í£Ö¹");
		stop.setBounds(105, 85, 60, 40);
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
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getRootPane().setDefaultButton(start);
		frame.setVisible(true);

		timer = new MyTimer();
		isStart = false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start) {
			if(!isStart) {
				timer.start(minute, second, frame);
				isStart = true;
			}
		}	
			
		if(e.getSource() == stop) {
			timer.stop(frame);
			isStart = false;
		}
		
		
	}
	
	public static void main(String[] args) {
		new Reminder();
	}
}
