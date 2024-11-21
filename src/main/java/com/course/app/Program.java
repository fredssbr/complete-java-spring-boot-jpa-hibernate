package com.course.app;

import com.course.domain.Person;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Program {
    public static void main(String[] args) {



        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();

        // insert(em);
        // System.out.println(findById(em, 2));

        // Can only remove a monitored person - in other words, one that comes from the DB that
        // and is inside of the context of the entity manager, before closing it.
        remove(em, findById(em, 2));

        em.close();
        emf.close();

    }

    private static void remove(EntityManager em, Person p) {
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
    }

    private static Person findById(EntityManager em, Integer id) {
        return em.find(Person.class, id);
    }

    private static void insert(EntityManager em) {

        Person person = new Person(null, "John Carpenter", "john.carp@hammer.com");
        Person person2 = new Person(null, "Martha Filasdóttir", "martha.f.dóttir@is.com");
        Person person3 = new Person(null, "Evandro Nunes", "evandro.nunes@terra.com.br");

        em.getTransaction().begin();

        em.persist(person);
        em.persist(person2);
        em.persist(person3);

        em.getTransaction().commit();
    }
}
