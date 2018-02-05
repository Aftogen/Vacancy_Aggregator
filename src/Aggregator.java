
import controller.Controller;
import model.HHStrategy;
import model.Model;
import model.MoikrugStrategy;
import model.Provider;
import view.HtmlView;

public class Aggregator {

    public static void main(String[] args) {

        HHStrategy hhStrategy = new HHStrategy();
        MoikrugStrategy moikrugStrategy = new MoikrugStrategy();

        Provider provider = new Provider(hhStrategy);
        Provider provider1 = new Provider(moikrugStrategy);

        HtmlView view = new HtmlView();
        Model model = new Model(view, provider, provider1);
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();

    }

}
