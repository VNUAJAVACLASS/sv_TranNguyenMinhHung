package dev.backend.tinchi_hibernate.dao;

import dev.backend.tinchi_hibernate.entities.Lecturer;
import dev.backend.tinchi_hibernate.entities.Student;
import dev.backend.tinchi_hibernate.util.CreditHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LecturerDAO {
    @SuppressWarnings("unchecked")
    public static List<Lecturer> getAllLecturers() {
        Transaction transaction = null;
        List<Lecturer> listOfLecturer = null;

        try (Session session = CreditHibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            listOfLecturer = session.createQuery("from Lecturer ", Lecturer.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfLecturer;
    }

    public static Lecturer getLecturerById(String id) {
        Transaction transaction = null;
        Lecturer lecturer = null;
        try (Session session = CreditHibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            lecturer = session.get(Lecturer.class, id);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return lecturer;
    }
}
