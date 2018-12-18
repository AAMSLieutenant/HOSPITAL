/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.interfaces;


import java.sql.SQLException;
import java.util.List;
import com.hospital.model.Patient;
/**
 *
 * @author Rostislav Stakhov
 */
public interface IPatientDao {

    /** Объект для управления персистентным состоянием объекта Employee */


    /** Создает новую запись и соответствующий ей объект */
    public void create(Patient patient) throws Exception;

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public Patient read(int key) throws Exception;

    /** Сохраняет состояние объекта group в базе данных */
    public void update(long key, Patient employee) throws Exception;

    /** Удаляет запись об объекте из базы данных */
    public void delete(int key) throws Exception;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Patient> getAll() throws Exception;

    public void quit();


}
