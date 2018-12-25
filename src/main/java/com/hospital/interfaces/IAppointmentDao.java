package com.hospital.interfaces;

import com.hospital.model.Appointment;

import java.util.List;

public interface IAppointmentDao {

    /** Объект для управления персистентным состоянием объекта Employee */


    /** Создает новую запись и соответствующий ей объект */
    public void create(Appointment appointment) throws Exception;

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public Appointment read(int key) throws Exception;

    /** Сохраняет состояние объекта group в базе данных */
    public void update(int key, Appointment appointment) throws Exception;

    /** Удаляет запись об объекте из базы данных */
    public void delete(int key) throws Exception;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Appointment> getAll() throws Exception;

    public List<Appointment> getAllById(int id) throws Exception;

    public void quit();
}
