package dev.backend.tinchi_hibernate.dao;

import dev.backend.tinchi_hibernate.entities.Student;
import dev.backend.tinchi_hibernate.util.CreditHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class StudentDAO {
    @SuppressWarnings("unchecked")
    public static List<Student> getAllStudent() {
        Transaction transaction = null;
        List<Student> listOfStudent = null;

        try (Session session = CreditHibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            listOfStudent = session.createQuery("from Student", Student.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfStudent;
    }

    public static Student getStudentById(String id) {
        Transaction transaction = null;
        Student student = null;
        try (Session session = CreditHibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            student = session.get(Student.class, id);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return student;
    }

    public static void main(String[] args) {
        List<Student> listOfStudent = getAllStudent();
        listOfStudent.forEach(student -> System.out.println(student));
    }
}
