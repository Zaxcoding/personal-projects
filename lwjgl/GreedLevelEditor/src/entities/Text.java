package entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.newdawn.slick.opengl.Texture;

public class Text extends Shape
{
	public int type = 0, i = 0;
	public boolean spaceDraw = false;	// need to use this
	
	Texture words, words2, words3, words4, words5, words6, words7, 
			words8, words9, words10, words11, words12, words13,	words14,
			words15, words16, words17, words18, words19, words20, 
			words21, words22, p, pr, pre, pres, press;

	public Text(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		words = loadTexture("words");
		words2 = loadTexture("wordsLevel");
		words3 = loadTexture("wordsAbout");
		words4 = loadTexture("wordsExit");
		words5 = loadTexture("wordsControls");
		words6 = loadTexture("wordsMove");
		words7 = loadTexture("wordsJump");
		words8 = loadTexture("wordsMainMenu");
		words9 = loadTexture("level1");
		words10 = loadTexture("level2");
		words11 = loadTexture("wordsGameOver");
		words12 = loadTexture("wordsRestart");
		words13 = loadTexture("wordsYouWon");
		words14 = loadTexture("wordsWelcome");
		words15 = loadTexture("wordsIntroLevel");
		words16 = loadTexture("wordsto");
		words17 = loadTexture("wordsAvoidFire");
		words18 = loadTexture("wordsBossGreed");
		words19 = loadTexture("wordsSlidesonIce");
		words20 = loadTexture("wordsGravityFlipper");
		words21 = loadTexture("wordsCoinsAddWeightto");
		words22 = loadTexture("wordsAvoidspike");
		p = loadTexture("wordsP");
		pr = loadTexture("wordsPr");
		pre = loadTexture("wordsPre");
		pres = loadTexture("wordsPres");
		press = loadTexture("wordsPress");
	}

	@Override
	public void draw()
	{
		textureStart();
		
		if(this.type==0){
			words.bind();
		}
		else if(this.type==1){
			words2.bind();
		}
		else if(this.type==2){
			words3.bind();
		}
		else if(this.type==3){
			words4.bind();
		}
		else if(this.type==4){
			words5.bind();
		}
		else if(this.type==5){
			words6.bind();
		}
		else if(this.type==6){
			words7.bind();
		}
		else if(this.type==7){
			words8.bind();
		}
		else if(this.type==8){
			words9.bind();
		}
		else if(this.type==9){
			words10.bind();
		}
		else if(this.type==10){
			words11.bind();
		}
		else if(this.type==11){
			words12.bind();
		}
		else if(this.type==12){
			words13.bind();
		}
		else if(this.type==13){
			words14.bind();
		}
		else if(this.type==14){
			words15.bind();
		}
		else if(this.type==15){
			words16.bind();
		}
		else if(this.type==16){
			words17.bind();
		}
		else if(this.type==17){
			words18.bind();
		}
		else if(this.type==18){
			words19.bind();
		}
		else if(this.type==19){
			words20.bind();
		}
		else if(this.type==20){
			words21.bind();
		}
		else if(this.type==21){
			words22.bind();
		}
		else if(this.type==22){
			if(this.i<30){
		//		glColor4d(1.0,1,1,0);
			}
			else if(this.i>=30 && this.i<60){
				p.bind();
			}
			else if(this.i>=60 && this.i<90){
				pr.bind();
			}
			else if(this.i>=90 && this.i<120){
				pre.bind();
			}
			else if(this.i>=120 && this.i<150){
				pres.bind();
			}
			else if(this.i>=150 && this.i<350){
				press.bind();
				if(i>=180&&i<349){
					spaceDraw = true;
				}
				if(this.i==349){
					this.i=0;
					spaceDraw = false;
				}
			}
			i++;
		}
		textureVertices();
	}

	@Override
	public void save(ObjectOutputStream OS)
	{
	}

	@Override
	public Shape load(ObjectInputStream IS)
	{
		return null;
	}

	@Override
	public boolean intersects(Shape other)
	{
		return false;
	}

}
