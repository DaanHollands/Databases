package be.kuleuven.tennistoernooi;

import javax.persistence.Persistence;
import Database.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Bootstrapping JPA/Hibernate...");
        var sessionFactory = Persistence.createEntityManagerFactory("be.kuleuven.studenthibernate.domain");
        var entityManager = sessionFactory.createEntityManager();

        System.out.println("Bootstrapping Repository...");
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var quiery = criteriaBuilder.createQuery(dbAdressen.class);;
        var root = quiery.from(dbAdressen.class);

        quiery.where(criteriaBuilder.equal(root.get("straatnaam"), "Eikstraat"));
        System.out.println(entityManager.createQuery(quiery).getResultList());
    }

    private static void checkForValue(dbAdressen adress) {
        System.out.println("checken voor variable");
        System.out.println(adress);
    }

}
