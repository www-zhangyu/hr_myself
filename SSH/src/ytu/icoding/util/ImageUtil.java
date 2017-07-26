package ytu.icoding.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageUtil {
	public static String code;
	public static BufferedImage getImage(){
		Random random = new Random();
		BufferedImage img = new BufferedImage(60, 20, BufferedImage.TYPE_INT_BGR);
		Graphics g = img.getGraphics();
		g.setColor(new Color(244,234,224));
		g.fillRect(0, 0, 60, 20);
		code=random.nextInt(9000)+1000+"";
		g.setColor(Color.black);
		g.drawString(code, 10, 10);
		
		for(int i=0;i<40;i++){
			int x1=random.nextInt(60);
			int y1=random.nextInt(20);
			g.setColor(new Color(random.nextInt(40)+140,random.nextInt(40)+140,random.nextInt(40)+140));
			g.drawLine(x1, y1, x1+15, y1+15);
		}
		//System.out.println(code);
		return img;
		
		
	}
}
