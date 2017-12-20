public class Card {
	
	
	enum Suit {Club, Diamond, Heart, Spade }//列舉將花色存入Suit裡
	
	private Suit suit; //Clubs, Diamonds, Hearts, Spades
	private int rank; //1~13
	/**
	 * @param s suit
	 * @param r rank
	 */	
	public Card(Suit s,int r){
		suit=s;
		rank=r;
	}	

	public void printCard(){
		
		String s = "";//將字串s顯示花色
		
		String r = "";//字串r顯示點數
		
		
		switch(suit)//選取Suit的內容是甚麼時，則s要顯示出甚麼
		{
			case Club:
				s ="Club";
				break;
			case Diamond:
				s = "Diamond";
				break;
			case Heart:
				s = "Heart";
				break;
			case Spade:
				s = "Spade";
				break;
		}
		
		switch(rank)//選取rank的內容是甚麼時，則r要顯示出甚麼
		{
			case 1:
				r ="Ace";
				break;
			case 11:
				r = "Jack";
				break;
			case 12:
				r = "Queen";
				break;
			case 13:
				r = "King";
				break;
			
			default:
				r = Integer.toString(rank);
				break;
		}

		
		System.out.println(s+" "+r);//把s跟r顯示出來
	}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}
}
