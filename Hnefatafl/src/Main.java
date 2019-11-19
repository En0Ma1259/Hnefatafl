
public class Main {

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.generateBoard(FigureLayout.fieldNormal);
		controller.printGameBoardGUI();

		//controller.start();
	}

}
