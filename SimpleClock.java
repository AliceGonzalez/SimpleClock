//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame implements ActionListener {

    Calendar calendar;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;

    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;
    String time;
    String day;
    String date;

    JButton button1;
    JButton button2;

    SimpleClock() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Alice's Digital Clock");
        this.setLayout(new FlowLayout());
        this.setSize(400, 300);
        this.setResizable(false);


        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 59));
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.PINK);
        timeLabel.setOpaque(true);
        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Verdana", Font.BOLD, 34));


        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Verdana", Font.BOLD, 30));

        button1 = new JButton();
        button1.setBounds(300, 200, 200, 150);
        button1.addActionListener(this);
        button1.setText("12/24 FORMAT");

        button2 = new JButton();
        button2.setBounds(300, 200, 200, 150);
        button2.addActionListener(this);
        button2.setText("GMT TIME");

        button2 = new JButton( new AbstractAction("GMT TIME") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                // add Action
                if(e.getSource() == button2){
                    timeFormat = new SimpleDateFormat("HH:mm:ss");
                    dayFormat = new SimpleDateFormat("EEEE");
                    dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
                    timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    dayFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                }
            }
        });


        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);
        this.setVisible(true);
        this.add(button1);
        this.add(button2);

        setTimer();
    }

    public void setTimer() {
        while (!Thread.currentThread().isInterrupted()) {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                Thread.currentThread().isInterrupted();
                e.getStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new SimpleClock();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1){
            timeFormat = new SimpleDateFormat("HH:mm:ss");
            dayFormat = new SimpleDateFormat("EEEE");
            dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
        }
    }
}

