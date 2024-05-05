package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.database.Adressen;
import javafx.fxml.FXML;

import javax.persistence.Persistence;

public class AanmakenSpelerController
{
    @FXML
    void initialize() {
        test();
    }

    public void test() {
            System.out.println("Bootstrapping JPA/Hibernate...");
            var sessionFactory = Persistence.createEntityManagerFactory("be.kuleuven.tennistoernooijava.databasePU");
            var entityManager = sessionFactory.createEntityManager();

            System.out.println("Bootstrapping Repository...");
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var quiery = criteriaBuilder.createQuery(Adressen.class);;
            var root = quiery.from(Adressen.class);

            quiery.where(criteriaBuilder.equal(root.get("straatnaam"), "Eikstraat"));
            System.out.println(entityManager.createQuery(quiery).getResultList());
    }
}
