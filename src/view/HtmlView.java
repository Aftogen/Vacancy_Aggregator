package view;

import controller.Controller;
import vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

public class HtmlView implements View {
    private final String filePath = "D:\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task28\\task2810\\view\\vacancies.html";
    //private final String filePath = "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";
    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        String result;
        try {
            Document document = getDocument();
            Element element = document.getElementsByClass("template").first();
            Element copy = element.clone();
            copy.removeClass("template").removeAttr("style");
            document.getElementsByAttributeValue("class", "vacancy").remove();
            for (Vacancy vacancy : vacancies) {
                Element c = copy.clone();
                c.getElementsByAttributeValue("class", "city").get(0).text(vacancy.getCity());
                c.getElementsByAttributeValue("class", "companyName").get(0).text(vacancy.getCompanyName());
                c.getElementsByAttributeValue("class", "salary").get(0).text(vacancy.getSalary());
                Element link = c.getElementsByTag("a").get(0);
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                element.before(c.outerHtml());
            }
            result = document.html();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return result;
    }

    private void updateFile(String string) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

}
