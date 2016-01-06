import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Pall {
	
	public int palliXkoordinaat;
	public int palliYkoordinaat;
	
	public int palliLaius = 25;
	public int palliK6rgus = 25;
	
	public int mX;
	public int mY;
	
	public Random randomLiikumine;
	
	private Tennis tennis;
	
	public int l66kideArv;
	
	
	public Pall(Tennis tennis){
		
		this.tennis = tennis;
		
		this.randomLiikumine = new Random();
		
		palliAlgusPunkt();
	}
	
	public void update(M2ngija m2ngija1, M2ngija m2ngija2){
		
		int palliKiirus = 10 ;
		
		this.palliXkoordinaat += mX * palliKiirus;
		this.palliYkoordinaat += mY * palliKiirus;
		
		
		
		if(this.palliYkoordinaat + palliK6rgus - mY > tennis.k6rgus || this.palliYkoordinaat + mY < 0)
		{
			if(this.mY < 0){
				this.palliYkoordinaat = 0;
				this.mY = randomLiikumine.nextInt(4);
				if (mY == 0){
					mY = 1;
				}
			}
			else{
				this.mY = -randomLiikumine.nextInt(4);
				this.palliYkoordinaat = tennis.k6rgus - palliK6rgus;
				if (mY == 0){
					mY = 1;
				}
			}

		}
		
		
		if(checkCollision(m2ngija1) == 1){
			
			this.mX = 1 + (l66kideArv / 5);
			this.mY = -2 + randomLiikumine.nextInt(4);
			
			if (mY == 0){
				mY = 1;
			}
			l66kideArv++;
		}
		else if(checkCollision(m2ngija2) == 1){
			this.mX = -1-(l66kideArv / 5);
			this.mY = -2 + randomLiikumine.nextInt(4);
			
			if (mY == 0){
				mY = 1;
			}
			l66kideArv++;
		}
		if(checkCollision(m2ngija1) == 2){
			m2ngija2.punktiLugeja++;
			palliAlgusPunkt();
		}
		else if(checkCollision(m2ngija2) == 2){
			m2ngija1.punktiLugeja++;
			palliAlgusPunkt();
		}
		
		
	}
	public void palliAlgusPunkt(){
		
		this.l66kideArv = 0;
		this.palliXkoordinaat = tennis.laius / 2 - this.palliLaius / 2;
		this.palliYkoordinaat = tennis.k6rgus / 2 - this.palliK6rgus / 2;
		
		this.mY = -2 + randomLiikumine.nextInt(4);
		this.mX = -1 + randomLiikumine.nextInt(1);
		
		if (mY == 0){
			mY = 1;
		}
		
		
		if(randomLiikumine.nextBoolean()){
			mX = 1;
		}else{
			mX = -1;
		}
		
	}
	
	public int checkCollision(M2ngija m2ngija){
		
		if(this.palliXkoordinaat < m2ngija.xKoordinaat + m2ngija.m2ngijaLaius && this.palliXkoordinaat + palliLaius > m2ngija.xKoordinaat && this.palliYkoordinaat < m2ngija.yKoordinaat + m2ngija.m2ngijaK6rgus && this.palliYkoordinaat + palliK6rgus > m2ngija.yKoordinaat)
		{
		return 1;	// bounce
		}
		else if((m2ngija.xKoordinaat > palliXkoordinaat && m2ngija.m2ngijaNumber == 1) || (m2ngija.xKoordinaat < palliXkoordinaat - palliLaius && m2ngija.m2ngijaNumber == 2))
		{
		return 2;	// score
		}
		
		return 0;		// nothing
		
	}
	
	public void render(Graphics g){
		
		g.setColor(Color.WHITE);
		g.fillOval(palliXkoordinaat, palliYkoordinaat, palliLaius, palliK6rgus);
		
	}
}
