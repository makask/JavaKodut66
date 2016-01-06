import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Tennis implements ActionListener, KeyListener {
	
	public static Tennis tennis;
	
	public int laius = 700;
	public int k6rgus = 700;
	
	public Renderdaja renderdaja;
	
	public M2ngija m2ngija1;
	public M2ngija m2ngija2;
	
	public Pall pall;
	
	public boolean ai = false;
	
	public boolean w, s, up, down;
	
	public int m2nguStaatus = 0; // 0 = Seisatud, 1 = Paus, 2 = Mängib
	
	public Tennis(){
		
		Timer ajasti = new Timer(20, this);
		JFrame jf = new JFrame("Tennis");
		renderdaja = new Renderdaja();
		
		jf.setSize(laius + 15, k6rgus + 35);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(renderdaja);
		jf.addKeyListener(this);
		
		start();
		
		
		ajasti.start();
	}
	
	public void start(){
		
		
		m2ngija1 = new M2ngija(this, 1);
		m2ngija2 = new M2ngija(this, 2);
		pall = new Pall(this);
	}
	
	public void update(){
		
		if(w){
			m2ngija1.move(true);
		}
		if(s){
			m2ngija1.move(false);
		}
		if(up){
			m2ngija2.move(true);
		}
		if(down){
			m2ngija2.move(false);
		}
		pall.update(m2ngija1, m2ngija2);
	}
	
	public void render(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, laius, k6rgus);
		
		if(m2nguStaatus == 0){
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial",1, 50));
			
			g.drawString("TENNIS",laius / 2 - 100, 100);
			
			
			g.setFont(new Font("Arial",1, 20));
			g.drawString("Vajuta SPACE kui tahad mängida ",laius - 500, 200);
			
		}
		
		if(m2nguStaatus == 2 || m2nguStaatus == 1){
			
									
			g.setColor(Color.WHITE);	//Keskmine joon
			g.setStroke(new BasicStroke(10));
			g.drawLine(laius / 2, 0, laius / 2, k6rgus);
			
			g.drawOval(laius / 2 - 100, k6rgus / 2 - 100, 200, 200);
			
			g.drawRect(laius / 7, k6rgus / 7, 500, 500 );
			
			g.setFont(new Font("Arial",1, 50));
			g.drawString(String.valueOf(m2ngija1.punktiLugeja),laius / 2 - 65, 200);
			g.drawString(String.valueOf(m2ngija2.punktiLugeja),laius / 2 + 50, 200);
			
			m2ngija1.render(g);
			m2ngija2.render(g);
			pall.render(g);
			
		}
		
		pall.render(g);
		
		if(m2nguStaatus == 1){
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial",1, 50));
			g.drawString("PAUS",laius / 2 - 70, k6rgus / 2 - 25);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(m2nguStaatus == 2){
			
			update();
		}
		
		
		
		
		renderdaja.repaint();
		
	}
	
	public static void main(String[] args) {
		
		tennis = new Tennis();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		int id = e.getKeyCode();
		
		if (id == KeyEvent.VK_W){
			w = true;
		}
		if (id == KeyEvent.VK_S){
			s = true;
		}
		if (id == KeyEvent.VK_UP){
			up = true;
		}
		if (id == KeyEvent.VK_DOWN){
			down = true;
		}
		if(id == KeyEvent.VK_SHIFT && m2nguStaatus == 0){
			ai = true;
			m2nguStaatus = 2;
		}
		
		
		if (id == KeyEvent.VK_SPACE){
			if(m2nguStaatus == 0){
				m2nguStaatus = 2;
				ai = false;
			}
			else if(m2nguStaatus == 1){
				m2nguStaatus = 2;
			}
			else if(m2nguStaatus == 2){
				m2nguStaatus = 1;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int id = e.getKeyCode();
		
		if (id == KeyEvent.VK_W){
			w = false;
		}
		if (id == KeyEvent.VK_S){
			s = false;
		}
		if (id == KeyEvent.VK_UP){
			up = false;
		}
		if (id == KeyEvent.VK_DOWN){
			down = false;
		}
		
		
	}



	
	
	

}
