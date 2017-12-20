import java.util.ArrayList;
public class Table {
	 final int MAXPLAYER=4;//PLAYER最多的人數
	private Deck allcards;//宣告變數
	private Player[] allplayer;//玩家變數
	private Dealer onedealer;//莊家變數
	private int[]pos_betArray;//存放每個玩家在一局下的注
	
	
	public Table (int nDeck)//將全部的牌和全部的玩家實體化
	{
		 //allcards=new ArrayList<Deck>();
		  allcards=new Deck(nDeck);
		 allplayer=new Player[MAXPLAYER];
		 pos_betArray=new int [MAXPLAYER];
	}
	public void set_player(int pos,Player p)//將牌桌上的玩家放進全部的玩家這個陣列裡
	{
		allplayer[pos]=p;
		
	}
	public Player[]get_player()//將所有的玩家回傳
	{
		Player[] c=allplayer;
		return c;
	}
	public void set_dealer(Dealer d)//將d放進onedealler
	{
		onedealer=d;
	}
	public Card get_face_up_card_of_dealer()//回傳dealer莊家打開的那張牌
	{
		return onedealer.getOneRoundCard().get(1);//取莊家牌的方法是在person裡，且dealer繼承person，在此Class裡就是onedealer
	}
	 
	private void ask_each_player_about_bets()
	{
		allplayer[0].sayHello();//sayhello這個方法是在player這個class裡,所以讓每個玩家sayhello，就要.sayhello
		allplayer[1].sayHello();
		allplayer[2].sayHello();
		allplayer[3].sayHello();
		pos_betArray[0]=allplayer[0].makeBet();//每個玩家所下的注存進pos_betArray
		pos_betArray[1]=allplayer[1].makeBet();
		pos_betArray[2]=allplayer[2].makeBet();
		pos_betArray[3]=allplayer[3].makeBet();
		
		
	}
	private void distribute_cards_to_dealer_and_players()
	{
		//給玩家兩張打開的牌
		ArrayList<Card> player0card=new ArrayList<Card>();//直接將兩張開著的牌存進一個新的arraylist裡
		player0card.add( allcards.getOneCard(true));
		player0card.add(allcards.getOneCard(true));
		allplayer[0].setOneRoundCard(player0card);//將這兩張排放進allplayer[0]裡
		ArrayList player1card=new ArrayList<Card>();
		player1card.add( allcards.getOneCard(true));
		player1card.add(allcards.getOneCard(true));
		allplayer[1].setOneRoundCard(player1card);
		ArrayList player2card=new ArrayList<Card>();
		player2card.add( allcards.getOneCard(true));
		player2card.add(allcards.getOneCard(true));
		allplayer[2].setOneRoundCard(player2card);
		ArrayList player3card=new ArrayList<Card>();
		player3card.add( allcards.getOneCard(true));
		player3card.add(allcards.getOneCard(true));
		allplayer[0].setOneRoundCard(player3card);
		//給莊家一張蓋著的牌和開著的牌
		ArrayList dealercard=new ArrayList<Card>();
		dealercard.add(allcards.getOneCard(false));//閉著的牌
		dealercard.add( allcards.getOneCard(true));
		onedealer.setOneRoundCard(dealercard);
		//印出莊家開著的那張牌
		 System.out.println("Dealer's face up card is");
		 get_face_up_card_of_dealer().printCard();
	}
	private void ask_each_player_about_hits()
	{
		//玩家是否要牌
		for(int i=0;i<=3;i++)
		{
			System.out.println(allplayer[i].getName()+"'s Cards now:");
			for(Card c : allplayer[i].getOneRoundCard()){
				c.printCard();}
			boolean hit;
		do{
			hit=allplayer[i].hit_me(this); //this
			if(hit){
				allplayer[i].getOneRoundCard().add(allcards.getOneCard(true));
				
				System.out.print("Hit! ");
				System.out.println(allplayer[i].getName()+"'s Cards now:");
				for(Card c : allplayer[i].getOneRoundCard()){
					c.printCard();
				}
			}
			else{
				System.out.println(allplayer[i].getName()+", Pass hit!");
				System.out.println(allplayer[i].getName()+", Final Card:");
				for(Card c : allplayer[i].getOneRoundCard()){
					c.printCard();
					
				}
			}
		}while(hit);}
		}
	private void ask_dealer_about_hits()
	{
		//問莊家是否要牌印出"Dealer's hit is over!"
		boolean hit=false;
		do{
			hit=onedealer.hit_me(this); //this
			if(hit){
				onedealer.getOneRoundCard().add(allcards.getOneCard(true));
				
				
			}
			else{
				System.out.println("Dealer's hit is over!");
				
					
				}
			
		}while(hit);
		
	}
	private void calculate_chips()
	//玩家贏印出",Get "+下注籌碼數+" Chips, the Chips now is: "+玩家最新籌碼數(提示: get_current_chips())
	
	{for(int i=0;i<=3;i++)
		{System.out.println(allplayer[i].getName()+"Cards:");
		allplayer[i].printAllCard();
	{
		if(allplayer[i].getTotalValue()>21 && onedealer.getTotalValue()>21)
		{
			System.out.println(allplayer[i].getName()+"card value is"+allplayer[i].getTotalValue()+"chips have no change! The Chips now is:"+allplayer[i].getCurrentChips());
			
			
		}else if(allplayer[i].getTotalValue()<=21&&onedealer.getTotalValue()>21){//玩家贏了
			allplayer[i].increaseChips(pos_betArray[i]);
			
		
			System.out.print((pos_betArray[i])+" Chips, the Chips now is:"+allplayer[i].getCurrentChips());
			
	
		}else if(allplayer[i].getTotalValue()>21&&onedealer.getTotalValue()<=21){//莊家贏了
			allplayer[i].increaseChips(-pos_betArray[i]);
			          
			System.out.print((pos_betArray[i])+" Chips, the Chips now is:"+allplayer[i].getCurrentChips());
			
		}else if(allplayer[i].getTotalValue()>onedealer.getTotalValue()&&allplayer[i].getTotalValue()<=21){//玩家贏了
			allplayer[i].increaseChips(pos_betArray[i]);
			
			System.out.print((pos_betArray[i])+" Chips, the Chips now is:"+allplayer[i].getCurrentChips());
			
		}else if(allplayer[i].getTotalValue()<onedealer.getTotalValue()&&onedealer.getTotalValue()<=21){//莊家贏了
			allplayer[i].increaseChips(pos_betArray[i]);
			
			System.out.print((pos_betArray[i])+" Chips, the Chips now is:"+allplayer[i].getCurrentChips());
		}else if(allplayer[i].getTotalValue()==21&&onedealer.getTotalValue()<=21)
		{
			if(allplayer[i].getOneRoundCard().size()==2&&allplayer[i].hasAce())
				allplayer[i].increaseChips(pos_betArray[i]*2);
			
			System.out.print((pos_betArray[i])+" Chips, the Chips now is:"+allplayer[i].getCurrentChips());
		}
		else 
		
			
			System.out.println(allplayer[i].getName()+"card value is"+allplayer[i].getTotalValue()+"chips have no change! The Chips now is:"+allplayer[i].getCurrentChips());//平手
		
	}
		}
	}
	


	
	public int[] get_players_bet()
	{
		return pos_betArray;
	}


	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
}
