package com.example.demo.dao.initData;

import com.example.demo.dao.entity.BookBossPicture;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


public class BookBossPictureDaoImpl {
    @Autowired
    private static EntityManager manager;



    public static int insertBatch(List<BookBossPicture> list) {
        try {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            Session session = (Session) manager.getDelegate();
            session.setFlushMode(FlushMode.MANUAL);

            for (Object bookBossPicture : list) {
                session.save(bookBossPicture);
            }
            session.flush();
            session.clear();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
