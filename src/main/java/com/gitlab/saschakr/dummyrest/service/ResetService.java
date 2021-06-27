package com.gitlab.saschakr.dummyrest.service;

import com.gitlab.saschakr.dummyrest.entity.Employee;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Startup
@Singleton
public class ResetService {

    @Inject
    @ConfigProperty(name = "AUTO_REFRESH", defaultValue = "true")
    private boolean autoRefresh;

    @PersistenceContext(unitName = "employee")
    private EntityManager em;

    @PostConstruct
    public void init() {
        this.resetState();
    }

    @Schedule(minute = "*/5", hour = "*")
    private void timeout() {
        if (this.autoRefresh) {
            System.out.println("Timer hitted");
            this.resetState();
        }
    }

    public void resetState() {

        this.em.createNativeQuery("delete from Employee where 1 = 1").executeUpdate();
        this.em.createNativeQuery("drop sequence if exists hibernate_sequence").executeUpdate();
        this.em.createNativeQuery("create sequence hibernate_sequence start with 1 increment by 1").executeUpdate();

        //@formatter:off
        this.em.persist(new Employee(null, "Köhler",   "Willibald",  56, 3000.00));
        this.em.persist(new Employee(null, "Janson",   "Regine",     67, 3100.00));
        this.em.persist(new Employee(null, "Messer",   "Jessika",    24, 3200.00));
        this.em.persist(new Employee(null, "Kraus",    "Swanhilde",  48, 3300.00));
        this.em.persist(new Employee(null, "Cline",    "Ada",        13, 3400.00));
        this.em.persist(new Employee(null, "Linden",   "Brigitte",   76, 3500.00));
        this.em.persist(new Employee(null, "Wechsler", "Rosa",       85, 3600.00));
        this.em.persist(new Employee(null, "Huber",    "Ruth",       51, 3700.00));
        this.em.persist(new Employee(null, "Giehl",    "Zoe",        25, 3800.00));
        this.em.persist(new Employee(null, "Pfenning", "Hieronymus", 73, 3900.00));
        //@formatter:on
    }

}
