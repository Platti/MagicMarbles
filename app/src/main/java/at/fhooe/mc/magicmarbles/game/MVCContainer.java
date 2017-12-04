package at.fhooe.mc.magicmarbles.game;

public class MVCContainer {
    private GameBoard model;
    private Controller controller;

    public MVCContainer(Settings settings){
        model = new GameBoard(settings);
        controller = new Controller();
        controller.setModel(model);
    }

    void setView(GameActivity view){
        view.setModel(model);
        view.setController(controller);
        model.setView(view);
    }
}
