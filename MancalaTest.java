package Mancala;

public class MancalaTest {

	public static void main(String[] args) {
		MancalaModel model = new MancalaModel(0);
		MancalaView view = new MancalaView(model);
		view.display();
		view.startMenu();
	}
}
