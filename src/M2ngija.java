import java.awt.Color;
import java.awt.Graphics;

public class M2ngija{
	
	public int m2ngijaNumber;
	
	public int xKoordinaat;
	public int yKoordinaat;
	public int m2ngijaLaius = 30;
	public int m2ngijaK6rgus = 250;
	
	public int punktiLugeja;
	
	public M2ngija(Tennis tennis, int m2ngijaNumber){
		
		this.m2ngijaNumber = m2ngijaNumber;
		
		if(m2ngijaNumber == 1){
			
			this.xKoordinaat = 0;
		}
		else if (m2ngijaNumber == 2){
			
			this.xKoordinaat = tennis.laius - m2ngijaLaius;
		}
		
		this.yKoordinaat = tennis.k6rgus / 2 - this.m2ngijaK6rgus / 2;
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(xKoordinaat, yKoordinaat, m2ngijaLaius, m2ngijaK6rgus);
		
	}

	public void move(boolean up) {
		
		int kiirus = 25;
		
		if(up){
			if(yKoordinaat - kiirus > 0){
				
				yKoordinaat-=kiirus;
				
			}else{
				
				yKoordinaat = 0;
			}
		}else{
			if(yKoordinaat +m2ngijaK6rgus + kiirus < Tennis.tennis.k6rgus){
				
				yKoordinaat+=kiirus;
			}else{
				
				yKoordinaat = Tennis.tennis.k6rgus - m2ngijaK6rgus;
				
			}
		}
		
	}

	
}
