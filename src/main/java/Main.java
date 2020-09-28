import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    /**
     * На входе имеется файл в формате json, содержащий информацию о каком-то количестве организаций, в т.ч.
     * названия, адреса, номера телефонов, ИНН, ОГРН, а также о ценных бумагах, которыми владеет каждая компания.
     * Необходимо сформировать на основе исходного файла коллекцию объектов без потери информации, где каждый
     * объект представляет одну организацию.
     * Для сформированной коллекции:
     * -Вывести все имеющиеся компании в формате «Краткое название» – «Дата основания 17/01/98»;
     * -Вывести все ценные бумаги (их код, дату истечения и полное название организации-владельца), которые
     * просрочены на текущий день, а также посчитать суммарное число всех таких бумаг;
     * -На запрос пользователя в виде даты «ДД.ММ.ГГГГ», «ДД.ММ,ГГ», «ДД/ММ/ГГГГ» и «ДД/ММ/ГГ» вывести название
     * и дату создания всех организаций, основанных после введенной даты;
     * -На запрос пользователя в виде кода валюты, например EU, USD, RUB и пр. выводить id и коды ценных бумаг,
     * использующих заданную валюту.
     */


    public static void main(String[] args) throws JsonProcessingException {

//        ObjectMapper objectMapper = new ObjectMapper();
//
//        Docs doc1 = new Docs(1, new GregorianCalendar(2021,5,24));
//        Docs doc2 = new Docs(2, new GregorianCalendar(2020,4,12));
//
//        Companies company1 = new Companies("ПАО \"Совкомбанк\"", "123100," +
//                " г. Москва, Краснопресненская набережная, 14, стр. 1", "74959889370",
//                440111, 114440000, new GregorianCalendar(1990, 10, 27), doc1,doc2);
//        Companies company2 = new Companies("АО \"АЛЬФА-БАНК\"", "107078," +
//                " город Москва, Каланчевская улица, 27", "74956209191",
//                772816, 102770006, new GregorianCalendar(1990, 11, 20), doc1, doc2);
//        Companies company3 = new Companies("ООО \"Инбанк\"", "117420," +
//                " город Москва, улица Наметкина, 14-1", "74959158443",
//                772803, 103773905, new GregorianCalendar(1992, 1, 2), doc1, doc2);
//        Companies company4 = new Companies("АО \"Нефтепромбанк\"", "127018," +
//                " г. Москва, ул. Образцова,д. 31, стр. 3", "74952342200",
//                770102, 102773934, new GregorianCalendar(1992, 1, 2), doc1, doc2);
//
//        String result = objectMapper.writeValueAsString(company4);
//        System.out.println(result);

        List<Companies> list = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            list = Arrays.asList(mapper.readValue(Paths.get("companies.json").toFile(), Companies[].class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //Вывод названия компании и даты основания
        for (Companies c: list) {
            System.out.println("Название компании: " + c.getName() + "\nДата создания: " + c.getFoundationDate().getTime());
        }
        System.out.println();

        //Вывод документов компании (№, дата истечения)
        for (Companies c: list) {
            System.out.println("Название компании: " + c.getName());
            for (Docs d: c.getDocs()) {
                System.out.println("№ документа: " + d.getId() + "\nДата истечения договора" + d.getDate().getTime());
            }
            System.out.println();
        }
        System.out.println();


        //Ввод даты с клавиатуры для поиска организации созданных после этой даты
        Scanner sc = new Scanner(System.in); // дата введенная с клавиатуры
        Calendar dataIn = new GregorianCalendar(); // инициализация переменной для даты введенной с клавиатуры
        try {
            System.out.println("Введите дату (dd.mm.yyyy): ");
            String dateString = sc.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            dataIn.setTime(sdf.parse(dateString));
        }catch (ParseException e){
            System.out.println("Неправильно введена дата!!!");
        }
        List<Companies> filteredList = list.stream()
                .filter(x -> x.getFoundationDate().getTime().compareTo(dataIn.getTime()) > 0 ).collect(Collectors.toList());
        for (Companies c: filteredList) {
            System.out.println(c.getName());
        }
        System.out.println();

        //Поиск доков по валюте
        System.out.println("Введите валюту(EU, USD, RUB): ");
        String currency = sc.nextLine();
        for (Companies c: list) {
            List <Docs> docs;
            docs = Arrays.stream(c.getDocs()).filter(x -> x.getCurrency().equals(currency)).collect(Collectors.toList());
            for (Docs d: docs) {
                System.out.println("В документе с id: " + d.getId() + " валюта в: " + currency);
            }
        }
        sc.close();
    }
}
