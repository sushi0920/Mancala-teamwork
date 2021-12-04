package Mancala;

public class MancalaModel {
	private int player1mancala;
	private int player2mancala;
	private int turn;
	private Node a1, a2, a3, a4, a5, a6, mancala1, b1, b2, b3, b4, b5, b6, mancala2;
	public MancalaModel(int startStones) {
		player1mancala = 0;
		player2mancala = 0;
		
		a1 = new Node(startStones,0);
		a2 = new Node(startStones,0);
		a3 = new Node(startStones,0);
		a4 = new Node(startStones,0);
		a5 = new Node(startStones,0);
		a6 = new Node(startStones,0);
		mancala1 = new Node(player1mancala,2);
		b1 = new Node(startStones,1);
		b2 = new Node(startStones,1);
		b3 = new Node(startStones,1);
		b4 = new Node(startStones,1);
		b5 = new Node(startStones,1);
		b6 = new Node(startStones,1);
		mancala2 = new Node(player2mancala,3);
		
		
		a1.setNext(a2);
		a2.setNext(a3);
		a3.setNext(a4);
		a4.setNext(a5);
		a5.setNext(a6);
		a6.setNext(mancala1);
		mancala1.setNext(b6);
		b6.setNext(b5);
		b5.setNext(b4);
		b4.setNext(b3);
		b3.setNext(b2);
		b2.setNext(b1);
		b1.setNext(mancala2);
		mancala2.setNext(a1);
		
		a1.setSameColumn(b1);
		a2.setSameColumn(b2);
		a3.setSameColumn(b3);
		a4.setSameColumn(b4);
		a5.setSameColumn(b5);
		a6.setSameColumn(b6);
		b1.setSameColumn(a1);
		b2.setSameColumn(a2);
		b3.setSameColumn(a3);
		b4.setSameColumn(a4);
		b5.setSameColumn(a5);
		b6.setSameColumn(a6);
		
		turn = 0;
	}

	public void choosePit(Node n) {
		if(n.getSide() == 2) {
			System.out.println("you cant choose mancala!");
		}
		else if(n.getStones() == 0) {
			System.out.println("this pit is empty!");
		}
		else {
			if(n.getSide() == turn) {
				if(turn == 0) {
					turn = 1;
				}
				else {
					turn = 0;
				}
				int count = n.getStones();
				n.setStones(0);
				placeStonesRecursive(n,count);
			}
			else {
				System.out.println("it is not your turn!");
			}
		}
		checkstop();
	}
	public void placeStonesRecursive(Node n, int count) {
		int stones = n.getNext().getStones();
		n.getNext().setPreStones(n.getNext().getStones());
		n.getNext().setStones(stones + 1);
		if(n.getNext().getSide() == 2) {
			player1mancala ++;
		}
		else if(n.getNext().getSide() == 3) {
			player2mancala ++;
		}
		n = n.getNext();
		count--;
		placeStonesRecursive(n,count);
		if(n.getNext().getStones() == 0) {
			if(n.getSide() == 0) {
				player1mancala += n.getNext().getSameColumn().getStones();
			}
			else{
				player2mancala += n.getNext().getSameColumn().getStones();
			}
		}
	}
	public void undo() {
		a1.setStones(a1.getPreStones());
		a2.setStones(a2.getPreStones());
		a3.setStones(a3.getPreStones());
		a4.setStones(a4.getPreStones());
		a5.setStones(a5.getPreStones());
		a6.setStones(a6.getPreStones());
		mancala1.setStones(mancala1.getPreStones());
		b1.setStones(b1.getPreStones());
		b2.setStones(b2.getPreStones());
		b3.setStones(b3.getPreStones());
		b4.setStones(b4.getPreStones());
		b5.setStones(b5.getPreStones());
		b6.setStones(b6.getPreStones());
		mancala2.setStones(mancala2.getPreStones());
	}
	public boolean checkstop() {
		if(a1.getStones() == 0 && a2.getStones() == 0 && a3.getStones() == 0 && a4.getStones() == 0 && a5.getStones() == 0 && a6.getStones() == 0) {
			System.out.println(countWin());
			return true;
		}
		else if(b1.getStones() == 0 && b2.getStones() == 0 && b3.getStones() == 0 && b4.getStones() == 0 && b5.getStones() == 0 && b6.getStones() == 0) {
			System.out.println(countWin());
			return true;
		}
		return false;
	}
	public String countWin() {
		if(player1mancala > player2mancala) {
			return new String("Player 1 win.");
		}
		else {
			return new String("Player 2 win.");
		}
	}
	private class Node {
		private int stones, preStones, side;
		private Node next, sameColumn;
		
		public Node(int stones, int side) {
			this.setStones(stones);
			this.setSide(side);
			this.setPreStones(stones);
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getSameColumn() {
			return sameColumn;
		}

		public void setSameColumn(Node sameColumn) {
			this.sameColumn = sameColumn;
		}

		public int getSide() {
			return side;
		}

		public void setSide(int side) {
			this.side = side;
		}

		public int getStones() {
			return stones;
		}

		public void setStones(int stones) {
			this.stones = stones;
		}

		public int getPreStones() {
			return preStones;
		}

		public void setPreStones(int preStones) {
			this.preStones = preStones;
		}
	}
}
