package org.jboss.as.quickstarts.ear.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;

/**
 * @author inv3r53
 * 
 */
@Singleton
@Startup
public class Poller {

    @Resource
    private TimerService timerService;

    @Inject
    private ProcessQueue processQueue;

    @PostConstruct
    public void startMonitor() {
        for (int i = 1; i <= 5; i++) {
            final TimerConfig timerConfig = new TimerConfig("ex-test-timer-" + i, false);
            final ScheduleExpression scheduleExpression = new ScheduleExpression();
            scheduleExpression.hour("*").minute("*/1").second("0");
            timerService.createCalendarTimer(scheduleExpression, timerConfig);
        }
    }

    @Timeout
    public void poll(final Timer timer) {
        System.out.println("*******************Timer fired!***********************");
        processQueue.process(timer.getInfo());
    }

}
