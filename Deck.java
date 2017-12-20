import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private ArrayList<Card> cards;//宣告這四個項目
	private ArrayList<Card> usedCards;
	public int nUsed;
	private ArrayList<Card>openCard;
	

	public Deck(int nDeck){
		cards=new ArrayList<Card>();//將這幾個項目實體化
		usedCards=new ArrayList<Card>();
		openCard=new ArrayList<Card>();
		
		for(int i = 0; i < nDeck ;i++) //用迴圈將幾副牌，花色，數字顯示出來
		{
			
			for(Card.Suit s : Card.Suit.values())//花色設定
			{
				
				for(int r = 1; r <= 13; r++)//點數
				{
					Card card = new Card(s,r);
					
					cards.add(card);//將迴圈出來的牌加進cards裡
				}
			}
		}	
		shuffle();//每次設定完牌之後都要洗牌
	}	
	
	public  void shuffle() {
		
		
		if(usedCards.size() != 0)//把發出去的排放回cards裡
		{
			
			for(Card c : usedCards)//若發出去的排數不等於0時，就將這些發出去的排一個一個加進Cards裡
			{
				cards.add(c);
			}
			
			
			usedCards.clear();//清空發出去的牌
			nUsed = 0;
			openCard.clear();//清空明牌
		}

		Random rnd = new Random();
		
		
		for(int i = 0; i < cards.size(); i++)//洗牌的方法
		{
			int j = rnd.nextInt(cards.size());//隨機抽取一張牌將他放進cards裡，然後要袃把原本那個位子的牌抽出來
			Card tempCard = cards.get(j);
			cards.set(j, cards.get(i));
			cards.set(i, tempCard);
		}
		
	}
	
	public Card getOneCard(boolean isOpened){
		//判斷我拿到的那張牌是否為明牌
	
		Card c = cards.get(0);
		if(isOpened==true)//判斷正確就把牌加進明牌裡
		{
			openCard.add(c);
		}
		
		usedCards.add(c);//把所有發出去的排都放進usedCard裡
		nUsed++;
		
	
		cards.remove(c);//把牌發出去所以cards裡就會少掉這張牌
		
		
		if (cards.size() == 0)//如果cards裡的牌數都沒了就要把牌回來洗一洗
		{
			shuffle();
		}

		return c;
	}
	public ArrayList<Card> getOpenedCard()
	{
		return openCard;
	}
	
	public void printDeck(){

		for(Card c:cards)
		{
			c.printCard();//把cards都顯示出來
		}

	}
	
	public ArrayList<Card> getAllCards(){
		return cards;
	}	
}

