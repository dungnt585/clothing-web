/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.EntityManagerFactory;

import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;

import javax.persistence.EntityManagerFactory;

public class JPAUtils {
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null || !factory.isOpen()) {
            // Tên "UserManamentPU" phải khớp với file xml ở trên
            factory = Persistence.createEntityManagerFactory("WebClothingShopPU");
        }
        return factory;
    }

    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}