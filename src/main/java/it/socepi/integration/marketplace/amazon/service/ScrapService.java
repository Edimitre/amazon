package it.socepi.integration.marketplace.amazon.service;



import it.socepi.integration.marketplace.amazon.model.*;
import it.socepi.integration.marketplace.amazon.repository.MarketPlaceRepository;
import it.socepi.integration.marketplace.amazon.repository.NationalityRepository;
import it.socepi.integration.marketplace.amazon.repository.OrderRepository;
import it.socepi.integration.marketplace.amazon.seleniumconfig.ConfigurationSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ScrapService {

    @Autowired
    public ConfigurationSelenium configurationSelenium;

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public MarketPlaceRepository marketPlaceRepository;

    @Autowired
    public NationalityRepository nationalityRepository;

//    @Scheduled(fixedRate = 1800 * 1000)
    @PostConstruct
    public void scrapeManoManoToolbox() throws InterruptedException {
        System.out.println("started scraping manomano toolbox ");
        List<String> allLinks = getItemLinksFromManoMano();

        Thread.sleep(1000);

        allLinks.forEach(link ->{
            try {
                scrapeItemLink(link);
            } catch (InterruptedException | ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<String> getItemLinksFromManoMano() throws InterruptedException {




        // set link
        configurationSelenium.driver().get("https://toolbox.manomano.com");


        Thread.sleep(getRandomNumber() * 1000);

        // insert email
        configurationSelenium.driver().findElement(By.xpath("//*[@id=\"root\"]/article/section[2]/div/form/div/span/input")).sendKeys("frizz1977@gmail.com");

        Thread.sleep(getRandomNumber() * 1000);


        WebElement button = configurationSelenium.driver().findElement(By.xpath("//*[@id=\"root\"]/article/section[2]/div/form/footer/button/span[1]"));
        Thread.sleep(getRandomNumber() * 1000);

        button.click();

        Thread.sleep(getRandomNumber() * 1000);


        // insert password
        configurationSelenium.driver().findElement(By.id("password")).sendKeys("coZOeeend!44l");




        // click login
        WebElement button1 = configurationSelenium.driver().findElement(By.xpath("//*[@id=\"kc-login\"]"));
        button1.click();


        Thread.sleep(1000);


        // click all pending
        WebElement buttonPending = configurationSelenium.driver().findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[1]/div[1]/div[1]/div[2]"));
        buttonPending.click();
        Thread.sleep(5000);


        List<String> itemLinks = new ArrayList<>();

        Thread.sleep(1000);

        // button next for pagination
        WebElement nxtBtn = configurationSelenium.driver().findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div/div[2]/div/div[2]/div/div[3]/button"));

        // if this btn is enabled it means it has more than one page
        if (nxtBtn.isEnabled()){

            System.out.println("pagination === true");

            while (nxtBtn.isEnabled()){

                Thread.sleep(2000);

                List<WebElement> allLinksInOtherPage = configurationSelenium.driver().findElements(By.tagName("a"));

                allLinksInOtherPage.forEach(link ->{

                    if (link.getText().startsWith("M")){

                        String itemLink = link.getAttribute("href");

                        if (!itemLinks.contains(itemLink)){
                            itemLinks.add(itemLink);

                        }
                    }
                });

                System.out.println("size of items " + itemLinks.size());

                System.out.println(" list filled ...going next page");

                nxtBtn.click();

            }

        }else{

            System.out.println("pagination is false");

            List<WebElement> allLinks = configurationSelenium.driver().findElements(By.tagName("a"));

            // fill list with order links from 1 page
            allLinks.forEach( link -> {

                if (link.getText().startsWith("M")){

                    String itemLink = link.getAttribute("href");

                    if (!itemLinks.contains(itemLink)){
                        itemLinks.add(itemLink);

                    }
                }
            });

            System.out.println("size of items " + itemLinks.size());

        }

        return itemLinks;


    }

    public void scrapeItemLink(String link) throws InterruptedException, ParseException {

        configurationSelenium.driver().navigate().to(link);

        Thread.sleep(5000);

        // ORDER
        Order order = new Order();

        // name
        WebElement orderName = configurationSelenium.driver().findElement(By.cssSelector(".inline-block.mr-2"));
        System.out.println("order tracking code : " + orderName.getText());

        order.setDenominazione(orderName.getText());

        //date
        WebElement orderDate = configurationSelenium.driver().findElement(By.cssSelector(".indicators-bar_summaryValue__UhVaz"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm aaa", Locale.getDefault());
        Date date = formatter.parse(orderDate.getText().replace(",", "").replace("/", "-"));
        System.out.println("order date : " + date.toString());

        order.setData(date);

        // status
        WebElement orderStatus = configurationSelenium.driver().findElement(By.cssSelector(".header_status__10jGJ"));
        System.out.println("order status : " + orderStatus.getText());

        order.setState(State.PENDING);


        List<WebElement> orderDetails  = configurationSelenium.driver().findElements(By.className("indicators-bar_summaryValue__UhVaz"));
        orderDetails.forEach(data ->{

            if (data.getText().equals("Shipping time") ) {
                WebElement shippingTime = data.findElement(By.xpath("following-sibling::div"));
                System.out.println("shippingTime : " + shippingTime.getText());
            }else if (data.getText().equals("Total Price") ) {
                WebElement totalPrice = data.findElement(By.xpath("following-sibling::div"));
                System.out.println("totalPrice: " + totalPrice.getText());
            }

        });






        // THE USER DATA OF THE ORDER
        List<WebElement> allAboveDivs = configurationSelenium.driver().findElements(By.cssSelector(".ClientDetails_client-details__field--label__1T_-g"));

        // because data in html is distributed in only one css class in the div we need to extract the element above this div
        allAboveDivs.forEach( div ->{

            if (div.getText().equals("Name / Company") ){
                WebElement name = div.findElement(By.xpath("following-sibling::div"));
                // get a user name
                System.out.println("name : " + name.getText());

                order.setUserName(name.getText());
            } else if (div.getText().equals("Phone") ) {
                WebElement phone = div.findElement(By.xpath("following-sibling::div"));
                //get a user phone
                System.out.println("phone : " + phone.getText());
                order.setTelefono(phone.getText());
            }else if (div.getText().equals("Recipient Code") ) {
                WebElement recipient_code = div.findElement(By.xpath("following-sibling::div"));
                // get a user recipient
                System.out.println("recipient code : " + recipient_code.getText());
                order.setCodice(recipient_code.getText());
            }else if (div.getText().equals("Fiscal number") ) {
                WebElement fiscal_number = div.findElement(By.xpath("following-sibling::div"));
                // get a user fiscal number
                System.out.println("fiscal_number : " + fiscal_number.getText());
            }else if (div.getText().equals("Delivery Address") ) {
                WebElement delivery_address = div.findElement(By.xpath("following-sibling::div"));

                String[] res = delivery_address.getText().split(",", 0);

                System.out.println("address : " + res[1]);
                order.setCitta(res[1]);
                System.out.println("zip_code : " + res[3]);
                order.setCodice(res[3]);
                System.out.println("country : " + res[4]);


                MarketPlace marketPlace = marketPlaceRepository.findByName("Italy").get();
                order.setMarketPlace(marketPlace);

            }


        });



        // ARTICLE

        Article article = new Article();
        article.setOrder(order);
        // name
        WebElement articleDiv = configurationSelenium.driver().findElement(By.className("Items_product__2NQ_G"));
        WebElement articleTitle = articleDiv.findElement(By.tagName("a"));
        System.out.println("articleTitle : " + articleTitle.getText());
        article.setNomeArticolo(articleTitle.getText());




        WebElement articleDetails = configurationSelenium.driver().findElement(By.cssSelector(".Items_productDetails__2u3lv"));

        List<WebElement> details = articleDetails.findElements(By.cssSelector(".Items_term__2Tuen"));
        details.forEach( data -> {


            if (data.getText().contains("SKU")){
                System.out.println("sku : " + data.getText());
                article.setSku(data.getText());
            } else if (data.getText().contains("Unit price VAT excl.")) {
                System.out.println("price without taxes : " + data.getText());
                article.setPrezzoUnitario(new BigDecimal(data.getText().replaceAll("[^\\d.]", "").replace(",", "").substring(1)));
            }else if (data.getText().contains("Quantity")) {
//                System.out.println("Quantity : " + data.getText().replaceAll("[^\\d.]", ""));
//                String quantity = data.getText().replaceAll("[^\\d.]", "");
                article.setQuantita(Integer.valueOf(data.getText().replaceAll("[^\\d.]", "")));
            }else if (data.getText().contains("Unit price VAT incl.")) {
                System.out.println("price with taxes : " + data.getText());
                article.setPrezzoTotale(new BigDecimal(data.getText().replaceAll("[^\\d.]", "").replace(",", "").substring(1)));
                order.setTotale(article.getPrezzoTotale());
                List<Article > articleList = new ArrayList<>();
                articleList.add(article);
                order.setArticleList(articleList);

                orderRepository.save(order);

            }
        });




        configurationSelenium.driver().navigate().back();


    }

    public long getRandomNumber(){

        long number = (long)  Math.floor(Math.random() * 10);
        if (number > 3L){
            return 1;
        }else{
            return number;
        }

    }

}
