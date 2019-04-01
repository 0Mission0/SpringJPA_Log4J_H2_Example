package idv.mission.example.SpringJPA_Example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("src/applicationContext.xml");
        CustomerService service = context.getBean(CustomerService.class);
        Customer customer = service.findOne(1);
        System.out.println("Context 關閉前都可以到 H2 Console 檢視資料");
        Logger logger = LogManager.getContext(false).getLogger("JDBC_Logger");
        logger.info(customer);
        try {
            Thread.sleep(30 * 1000);
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
        context.close();
    }
}
