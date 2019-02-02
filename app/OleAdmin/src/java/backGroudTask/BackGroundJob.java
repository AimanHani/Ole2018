/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backGroudTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author user
 */
@WebListener
public class BackGroundJob implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleAtFixedRate(new SomeDailyJob(), 0, 1, TimeUnit.DAYS);
//        scheduler.scheduleAtFixedRate(new SomeHourlyJob(), 0, 1, TimeUnit.HOURS);
        try {
            scheduler.scheduleAtFixedRate(new UpdateMatchTableByTimer(), 1, 24, TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace(); 

        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduler.shutdownNow();
    }
}
