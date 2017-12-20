import java.util.ArrayList;

public class Player extends Person{

	
		private String name;
		private int chips;//玩家的籌碼
		private int bet;//玩家此局下注的錢
		//private ArrayList <Card> oneRoundCard;//此牌局的卡//因為父類別已有此說明不可以和abstract calss重覆到
		
		public Player(String name1,int chips1)
		{
			name=name1;
			chips=chips1;
		}
		public String getName()
		{
			return name;
		}
		public int makeBet()//下注，且一次只能下注一元，所以設bet為1
		{
			if(chips>0)//玩家所剩的錢要一直扣掉賭注的錢，如果扣光了就不能在繼續往下扣，直接break
			{
				bet=1;
				
				//當籌碼還有時就可以下注，且設下注的錢為1
			}
			else
				bet=0;//在錢沒有的時候，下注的錢是0，就是不可以再下注的意思
			return bet;
		}
		

		/*public  void setOneRoundCard(ArrayList cards)
		{
			super()=cards;//將cards放入oneRoundCard裡
			
		}*/
		
		public boolean hitMe(Table table)//是否要牌//abstract method 要與父類別的參數型態相同
		{
			if(getTotalValue()<=16)
			{
				return true;
			}
			else
				return false;
}
		/*public int getTotalValue()//算點數
	
		{int total=0;
		for(int j=0;j<oneRoundCard.size();j++)
		{
			
		if(oneRoundCard.get(j).getRank()==11)//因為牌是j,q,k時在21點裡算數是10
			total+=10;
		else if(oneRoundCard.get(j).getRank()==12)
			total+=10;
		else if(oneRoundCard.get(j).getRank()==13)//問題:要如何在這邊寫入1可以是1也可以是11???
			total+=10;
		else
			total+=oneRoundCard.get(j).getRank();
		
		}
		return total;
		}*/
		public void increaseChips(int p1Bet) {//玩家籌碼變動
			// TODO Auto-generated method stub
			{
				chips=chips-p1Bet;
				
			}
		}
		public int getCurrentChips()
		{
			return chips;
		}
		
		
		public void sayHello()
		{
			System.out.println("Hello,I am"+name+".");
			System.out.println("I have"+chips+"chips.");
		}
		@Override
		public boolean hit_me(Table table) {
			// TODO Auto-generated method stub
			
				int total_value = getTotalValue();
				if (total_value < 17)
					return true;
				else if (total_value == 17 && hasAce()) {
					return true;
				} else {
					if (total_value >= 21)
						return false;
					else {
						Player[] players = table.get_player();
						int lose_count = 0;
						int v_count = 0;
						int[] betArray = table.get_players_bet();
						for (int i = 0; i < players.length; i++) {
							if (players[i] == null) {
								continue;
							}
							if (players[i].getTotalValue() != 0) {
								if (total_value < players[i].getTotalValue()) {
									lose_count += betArray[i];
								} else if (total_value > players[i].getTotalValue()) {
									v_count += betArray[i];
								}
							}
						}
						if (v_count < lose_count)
							return true;
						else
							return false;
					}
				}

			}

		}
		
		
		
